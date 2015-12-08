package cz.muni.fi.pa165.service.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Subclass of DataAccessException.
 *
 * @author MajoCAM
 */
public class SaesDataAccessException extends DataAccessException {

    public SaesDataAccessException(String msg) {
        super(msg);
    }

    public SaesDataAccessException(Throwable cause) {
        super("", cause);
    }

    public SaesDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
