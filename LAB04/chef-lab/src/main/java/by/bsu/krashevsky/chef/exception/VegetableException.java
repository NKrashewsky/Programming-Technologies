package by.bsu.krashevsky.chef.exception;

/**
 * Исключение, возникающее при ошибках чтения данных об овощах.
 */
public class VegetableException extends Exception {

    public VegetableException(String message) {
        super(message);
    }

    public VegetableException(String message, Throwable cause) {
        super(message, cause);
    }
}
