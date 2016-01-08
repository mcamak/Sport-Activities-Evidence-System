/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.exceptions;

/**
 * @author MajoCAM
 */
public class SaesServiceException extends RuntimeException {

    public SaesServiceException() {
    }

    public SaesServiceException(String message) {
        super(message);
    }

    public SaesServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaesServiceException(Throwable cause) {
        super(cause);
    }

}
