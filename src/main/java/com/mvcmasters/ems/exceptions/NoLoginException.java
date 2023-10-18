package com.mvcmasters.ems.exceptions;

/**
 * Exception thrown when a user is not logged in.
 */
public class NoLoginException extends RuntimeException {

    /**
     * Default error code.
     */
    private static final int DEFAULT_CODE = 300;

    /**
     * Error code.
     */
    private Integer code = DEFAULT_CODE;

    /**
     * Error message.
     */
    private String msg = "User is not logged in!";

    /**
     * Default constructor.
     */
    public NoLoginException() {
        super("User is not logged in!");
    }

    /**
     * Constructor with a message parameter.
     * @param message Error message.
     */
    public NoLoginException(final String message) {
        super(message);
        this.msg = message;
    }

    /**
     * Constructor with a code parameter.
     * @param codeValue Error code.
     */
    public NoLoginException(final Integer codeValue) {
        super("User is not logged in!");
        this.code = codeValue;
    }

    /**
     * Constructor with code and message parameters.
     * @param codeValue Error code.
     * @param message Error message.
     */
    public NoLoginException(final Integer codeValue, final String message) {
        super(message);
        this.code = codeValue;
        this.msg = message;
    }

    /**
     * Retrieves the error code.
     * @return Error code.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets the error code.
     * @param codeValue Error code to set.
     */
    public void setCode(final Integer codeValue) {
        this.code = codeValue;
    }

    /**
     * Retrieves the error message.
     * @return Error message.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the error message.
     * @param message Error message to set.
     */
    public void setMsg(final String message) {
        this.msg = message;
    }
}
