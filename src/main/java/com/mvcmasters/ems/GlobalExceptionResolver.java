package com.mvcmasters.ems;

import com.mvcmasters.ems.base.ResultInfo;
import com.mvcmasters.ems.exceptions.ParamsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSON;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    /**
     * The default server error code representing an internal server error.
     */
    private static final int SERVER_ERROR_CODE = 500;
    /**
     * Resolves exceptions and decides the response to be returned,
     * whether it's a view or a JSON object.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @param handler  the object that handled the request
     * @param ex       the exception thrown during the processing of the request
     * @return a ModelAndView object that represents the response to be returned
     */
    @Override
    public ModelAndView resolveException(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @Nullable final Object handler,
            @NonNull final Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code", SERVER_ERROR_CODE);
        modelAndView.addObject("msg", "Server exception. Please try again...");

        if (handler instanceof HandlerMethod handlerMethod) {
            // Check if the handler method has a @ResponseBody annotation
            ResponseBody responseBody = handlerMethod.getMethod().
                    getDeclaredAnnotation(ResponseBody.class);
            if (responseBody == null) {
                // If not an @ResponseBody method and
                // the exception is ParamsException,
                // set the code and message in the ModelAndView
                if (ex instanceof ParamsException p) {
                    modelAndView.addObject("code", p.getCode());
                    modelAndView.addObject("msg", p.getMsg());
                }

                return modelAndView;

            } else {
                // If it's an @ResponseBody method
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(SERVER_ERROR_CODE);
                resultInfo.setMsg("Server exception. Please try again...");
                // Check if the exception is an instance of ParamsException
                if (ex instanceof ParamsException p) {
                    // If it is, set the code and message from the exception
                    resultInfo.setCode(p.getCode());
                    resultInfo.setMsg(p.getMessage());
                    // Set the HTTP response status code to
                    // indicate a bad request
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    // If it's not a ParamsException, set the HTTP response
                    // status code to indicate an internal server error
                    response.setStatus(HttpServletResponse.
                            SC_INTERNAL_SERVER_ERROR);
                }

                // Set the response content type to JSON
                response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    // Serialize the ResultInfo object to JSON
                    String json = JSON.toJSONString(resultInfo);
                    // Write the JSON response to the PrintWriter
                    out.write(json);
                } catch (IOException e) {
                    // Handle any IO exceptions that may occur
                    e.printStackTrace();
                }
                // Return null to indicate that the response has been handled
                return null;
            }
        }
        return modelAndView;
    }
}
