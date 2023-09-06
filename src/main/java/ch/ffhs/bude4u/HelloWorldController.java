package ch.ffhs.bude4u;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HelloWorldController {
    private String hello = "Hello World!";
    public String getHello() {
        return hello;
    }
}
