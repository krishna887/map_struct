package com.example.elasticsearchcrud.api;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//singleton huda updated aaucha
@RequestScope //proxy esmai embedded bhayeko huncha
public class BeanScope {
    private  String websiteName="Krishna personal website";

    public BeanScope() {
        System.out.println("BeanScope class created...");
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }
}
