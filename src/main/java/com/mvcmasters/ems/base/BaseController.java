package com.mvcmasters.ems.base;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

    @ModelAttribute
    public void preHandler(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
    }

    public ResultInfo success() {
        return new ResultInfo();
    }

    public ResultInfo success(String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg, Object result) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }
}