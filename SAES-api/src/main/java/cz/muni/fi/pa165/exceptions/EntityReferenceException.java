package cz.muni.fi.pa165.exceptions;

/**
 * Created by Marian Camak on 8. 1. 2016.
 */
public class EntityReferenceException extends Throwable {
    public EntityReferenceException() {
    }

    public EntityReferenceException(String message) {
        super(message);
    }

    public EntityReferenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityReferenceException(Throwable cause) {
        super(cause);
    }

    public EntityReferenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
