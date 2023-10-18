package com.mvcmasters.ems.base;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Base controller class that provides common
 * functionalities for child controllers.
 */
public class BaseController {

    /**
     * Prepares common attributes before handling a request.
     *
     * @param request The HttpServletRequest object.
     */
    @ModelAttribute
    public void preHandler(final HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
    }

    /**
     * Creates a default success result.
     *
     * @return A ResultInfo object indicating success.
     */
    public ResultInfo success() {
        return new ResultInfo();
    }

    /**
     * Creates a success result with a specific message.
     *
     * @param msg The success message.
     * @return A ResultInfo object indicating success.
     */
    public ResultInfo success(final String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    /**
     * Creates a success result with a specific message and result data.
     *
     * @param msg The success message.
     * @param result The result data.
     * @return A ResultInfo object indicating success.
     */
    public ResultInfo success(final String msg, final Object result) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }
}
