package com.prateek.reap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static com.prateek.reap.util.HtmlConstants.HTML_404_PAGE;
import static com.prateek.reap.util.HtmlConstants.HTML_500_PAGE;
import static com.prateek.reap.util.HtmlConstants.HTML_ERROR_PAGE;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return HTML_404_PAGE;
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return HTML_500_PAGE;
            }
        }
        return HTML_ERROR_PAGE;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
