package com.wxd;

import com.wxd.threads.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        Client client = context.getBean("client",Client.class);
        WebCompany webCompany = context.getBean("webCompany",WebCompany.class);

        ProductManager productManager = context.getBean("productManager",ProductManager.class);
        UserExperience userExperience = context.getBean("userExperience",UserExperience.class);
        UserInterface userInterface = context.getBean("userInterface",UserInterface.class);
        ResearchDevelop researchDevelop = context.getBean("researchDevelop",ResearchDevelop.class);
        QualityAssurance qualityAssurance =  context.getBean("qualityAssurance",QualityAssurance.class);
        Operations operations = context.getBean("operations",Operations.class);

        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(20);

        //执行Runnable的实现类
        service.execute(productManager);    //产品经理开始工作

        service.execute(client);    //客户提出需求

        service.execute(userExperience);

        service.execute(userInterface);
        service.execute(userInterface);
        service.execute(userInterface);
        service.execute(userInterface);

        service.execute(researchDevelop);
        service.execute(researchDevelop);
        service.execute(researchDevelop);
        service.execute(researchDevelop);

        service.execute(qualityAssurance);

        service.execute(operations);

        webCompany.getCountDownLatch().countDown();

        //关闭连接，停止接收新任务，原来的任务继续执行
        service.shutdown();
    }
}
