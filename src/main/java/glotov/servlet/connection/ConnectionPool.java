package glotov.servlet.connection;


import glotov.servlet.exception.ServiceException;
import glotov.servlet.util.PropertiesStreamReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int CAPACITY_POOL = 10;
    private static final String PROPERTIES_FILE_NAME = "database.properties";
    private static final String DB_URL_PROPERTY = "url";
    private static final Properties properties = new Properties();
    private static final Lock lock = new ReentrantLock(true);
    private static final PropertiesStreamReader propertiesStreamReader = new PropertiesStreamReader();
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private static String databaseUrl;

    private final BlockingQueue<ProxyConnection> connections = new LinkedBlockingQueue<>(CAPACITY_POOL);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.log(Level.FATAL,"Error by registering driver: ", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionPool() {
        try {
            properties.load(new FileReader(propertiesStreamReader.getFileFromResource(PROPERTIES_FILE_NAME).toFile()));
        } catch (IOException | ServiceException e) {
            logger.log(Level.FATAL,"Error loading properties file: ", e);
            throw new ExceptionInInitializerError(e);
        }
        databaseUrl = properties.getProperty(DB_URL_PROPERTY);
        for (int i = 0; i < CAPACITY_POOL; i++) {
            Connection connection = createConnection(databaseUrl);
            connections.add((ProxyConnection) connection);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (!isCreated.get()) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            logger.log(Level.WARN,"Error by getting connection: ", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxy) {
            try {
                connections.put(proxy);
            } catch (InterruptedException e) {
                logger.log(Level.WARN,"Error closing connection: ", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public void destroyPool() {
        for (ProxyConnection connection : connections) {
            try {
                connection.reallyClose();
            } catch (Exception e) {
                logger.log(Level.FATAL,"Error by closing connections: ", e);
                throw new ExceptionInInitializerError(e);
            }
        }

        try {
            DriverManager.deregisterDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.log(Level.FATAL,"Error unregistering driver: ", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private Connection createConnection(String url) {
        try {
            return new ProxyConnection(DriverManager.getConnection(url, properties));
        } catch (SQLException e) {
            logger.log(Level.FATAL,"Error creating connection: ", e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
