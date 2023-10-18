package com.mvcmasters.ems.base;

import org.springframework.stereotype.Component;

/**
 * Represents the result of an operation with
 * status code, message, and result object.
 */
@Component("resultInfo")
public class ResultInfo {

    /**
     * Default status code.
     */
    private static final int DEFAULT_CODE = 200;

    /**
     * Default message.
     */
    private static final String DEFAULT_MSG = "success";

    /**
     * The status code of the result.
     */
    private Integer code = DEFAULT_CODE;

    /**
     * The message of the result.
     */
    private String msg = DEFAULT_MSG;

    /**
     * The actual result object.
     */
    private Object result;

    /**
     * Default constructor.
     */
    public ResultInfo() {
    }

    /**
     * Constructs a ResultInfo with given code, message and result.
     * @param pCode status code.
     * @param pMsg message.
     * @param pResult result object.
     */
    public ResultInfo(final Integer pCode,
                      final String pMsg,
                      final Object pResult) {
        this.code = pCode;
        this.msg = pMsg;
        this.result = pResult;
    }

    /**
     * Sets all fields of the ResultInfo.
     * @param pCode status code.
     * @param pMsg message.
     * @param pResult result object.
     */
    public void setAll(final Integer pCode,
                       final String pMsg,
                       final Object pResult) {
        this.code = pCode;
        this.msg = pMsg;
        this.result = pResult;
    }

    /**
     * Returns the result object.
     * @return the result.
     */
    public Object getResult() {
        return result;
    }

    /**
     * Sets the result object.
     * @param newResult the result to set.
     */
    public void setResult(final Object newResult) {
        this.result = newResult;
    }

    /**
     * Returns the status code.
     * @return the code.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets the status code.
     * @param newCode the code to set.
     */
    public void setCode(final Integer newCode) {
        this.code = newCode;
    }

    /**
     * Returns the message.
     * @return the message.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the message.
     * @param newMsg the message to set.
     */
    public void setMsg(final String newMsg) {
        this.msg = newMsg;
    }

    /**
     * Returns the string representation of this ResultInfo object.
     *
     * @return string representation of the ResultInfo
     */
    @Override
    public String toString() {
        return "ResultInfo{"
                + "code=" + code
                + ", msg='" + msg + '\''
                + ", result=" + result
                + '}';
    }
}
