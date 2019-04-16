package com.industrialscansystem.Config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component //主要参考链接  https://blog.csdn.net/larger5/article/details/81047869
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {

        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setStatus("300");
        responseBody.setMsg("Need Authorities!");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}
