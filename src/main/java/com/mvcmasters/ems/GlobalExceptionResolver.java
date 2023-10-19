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
            ResponseBody responseBody = handlerMethod.getMethod().
                    getDeclaredAnnotation(ResponseBody.class);
            if (responseBody == null) {
                if (ex instanceof ParamsException p) {
                    modelAndView.addObject("code", p.getCode());
                    modelAndView.addObject("msg", p.getMsg());
                }

                return modelAndView;

            } else {
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(SERVER_ERROR_CODE);
                resultInfo.setMsg("Server exception. Please try again...");

                if (ex instanceof ParamsException p) {
                    resultInfo.setCode(p.getCode());
                    resultInfo.setMsg(p.getMessage());
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    response.setStatus(HttpServletResponse.
                            SC_INTERNAL_SERVER_ERROR);
                }

                response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    String json = JSON.toJSONString(resultInfo);
                    out.write(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;

            }
        }

        return modelAndView;
    }
}
