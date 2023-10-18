package com.mvcmasters.ems.exceptions;

/**
 * Exception thrown when there is a parameter exception.
 */
public class ParamsException extends RuntimeException {

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
    private String msg = "Parameter exception!";

    /**
     * Default constructor.
     */
    public ParamsException() {
        super("Parameter exception!");
    }

    /**
     * Constructor with a message parameter.
     * @param message Error message.
     */
    public ParamsException(final String message) {
        super(message);
        this.msg = message;
    }

    /**
     * Constructor with a code parameter.
     * @param codeValue Error code.
     */
    public ParamsException(final Integer codeValue) {
        super("Parameter exception!");
        this.code = codeValue;
    }

    /**
     * Constructor with code and message parameters.
     * @param codeValue Error code.
     * @param message Error message.
     */
    public ParamsException(final Integer codeValue, final String message) {
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
