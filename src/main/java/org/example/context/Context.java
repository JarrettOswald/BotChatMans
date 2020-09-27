package org.example.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Context {
    private static ApplicationContext instanceContext;
    public static ApplicationContext getInstance(){
        if (instanceContext == null){
            instanceContext = new ClassPathXmlApplicationContext("app-context.xml");
        }
        return instanceContext;
    }
}
