package exception;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException(String message) {
        System.out.println(message);
    }
}
