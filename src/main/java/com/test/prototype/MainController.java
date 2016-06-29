package com.test.prototype;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class MainController implements Serializable {

    private static final long serialVersionUID = 1l;

    @Inject
    HttpServletRequest httpRequest;

    public String hello() {
        return "Hello world";
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath().concat("/index.xhtml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("Start session host:" + httpRequest.getRemoteHost());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Stop session host:" + httpRequest.getRemoteHost());
    }

}
