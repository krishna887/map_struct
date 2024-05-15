package com.example.elasticsearchcrud.exception.custom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler5 implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv= new ModelAndView();
        mv.addObject("message", "An error occurred: " + ex.getMessage());
        mv.setViewName("abc");
        return mv;
    }
}
