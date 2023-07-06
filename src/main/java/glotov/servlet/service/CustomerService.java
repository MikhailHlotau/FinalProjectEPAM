package glotov.servlet.service;

public interface UserService {
    boolean authenticate(String userName, String password);
}