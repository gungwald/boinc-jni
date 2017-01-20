package edu.berkeley.boinc.jni;

/**
 * @author bill
 */
public class BoincException extends Exception {

    private static final long serialVersionUID = 1L;

    private int errorCode;
    
    public BoincException(int errorCode) {
        this.errorCode = errorCode;
    }
    
    /**
     * @param message
     */
    public BoincException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public BoincException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
