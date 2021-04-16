package springframework.randomApp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException() {
        super();
    }
    public CatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public CatNotFoundException(String message) {
        super(message);
    }
    public CatNotFoundException(Throwable cause) {
        super(cause);
    }
}
