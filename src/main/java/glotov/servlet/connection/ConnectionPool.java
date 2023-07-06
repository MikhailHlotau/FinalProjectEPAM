package glotov.servlet.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int POOL_SIZE = 10;
    private static final Lock lock = new ReentrantLock(true);  // Блокировка для обеспечения потокобезопасности
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);  // Флаг, показывающий, создан ли уже экземпляр пула соединений
    private static ConnectionPool instance;  // Единственный экземпляр пула соединений
    private final BlockingQueue<Connection> freeConnections;  // Очередь свободных соединений
    private final Queue<Connection> givenAwayConnections;  // Очередь активных соединений

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());  // Регистрация JDBC-драйвера
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private ConnectionPool() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(input);  // Загрузка настроек базы данных из файла database.properties
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database.properties", e);
        }
        freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);  // Создание очереди свободных соединений
        givenAwayConnections = new ArrayDeque<>();  // Создание очереди активных соединений
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(prop.getProperty("url"), prop);  // Создание соединения с базой данных
                ProxyConnection proxyConnection = new ProxyConnection(connection);  // Создание прокси-соединения
                freeConnections.add(proxyConnection);  // Добавление прокси-соединения в очередь свободных соединений
            } catch (SQLException e) {
                logger.log(Level.FATAL, "Couldn't create connection to database" + e);
                throw new ExceptionInInitializerError(e.getMessage());
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (!isCreated.get()) {
                    instance = new ConnectionPool();  // Создание единственного экземпляра пула соединений
                    isCreated.set(true);
                    logger.log(Level.INFO, "Выполнен метод getInstance() ConnectionPool");
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = (ProxyConnection) freeConnections.take();  // Извлечение соединения из очереди свободных соединений
            logger.log(Level.DEBUG, "Gave connection" + proxyConnection);
            givenAwayConnections.offer(proxyConnection);  // Добавление соединения в очередь активных соединений
            logger.log(Level.INFO, "Выполнен метод getConnection() ConnectionPool");
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException in method getConnection " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (!connection.getClass().equals(ProxyConnection.class)) {
            logger.log(Level.ERROR, "Attempt to release a non-proxy connection ");
            throw new IllegalArgumentException();
        }
        givenAwayConnections.remove(connection);  // Удаление соединения из очереди активных соединений
        try {
            freeConnections.put(connection);  // Добавление соединения обратно в очередь свободных соединений
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "InterruptedException in method releaseConnection " + e.getMessage());
        }
    }

    public static void deregister() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);  // Отмена регистрации всех JDBC-драйверов
            } catch (SQLException e) {
                logger.error("Error deregistering driver" + e.getMessage());
            }
        });
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection proxyConnection = (ProxyConnection) freeConnections.take();
                proxyConnection.isReallyClose();  // Закрытие реального соединения
                logger.log(Level.INFO, "Выполнен метод destroyPool() ConnectionPool");
            } catch (InterruptedException e) {
                logger.error("Failed destroy pool" + e.getMessage());
            }
        }
    }
}