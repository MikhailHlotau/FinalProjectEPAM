package glotov.servlet.repository;

import java.util.List;

public interface Repository<T> {

    boolean create(T t);

    T read(T t);

    T read(int input);

    boolean update(T t);

    boolean delete(int input);

    List<T> readAll();
}