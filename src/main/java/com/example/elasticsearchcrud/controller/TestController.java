package com.example.elasticsearchcrud.controller;

import com.example.elasticsearchcrud.api.BeanScope;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class TestController {
    @Autowired
    BeanScope beanScope;

    @RequestMapping("/testing")
    public void testing(HttpServletResponse response) throws IOException {
        response.getWriter().write("the website name is:  "+ beanScope.getWebsiteName());
        beanScope.setWebsiteName("Krishna personal website updated !!");
        response.getWriter().write("   Updated website is:  "+ beanScope.getWebsiteName());
    }
    @RequestMapping("/testing2")
    public void testing2(HttpServletResponse response) throws IOException{
        response.getWriter().write(beanScope.getWebsiteName());

    }
}
