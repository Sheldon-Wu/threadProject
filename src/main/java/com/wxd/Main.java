package com.wxd;

import com.wxd.threads.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;

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

        TimeUnit.SECONDS.sleep(10);

        fileInputStream = new FileInputStream("files/demand.txt");
        inputStreamReader = new InputStreamReader(fileInputStream);
        char[] buffer = new char[48];
        int d;
        while ((d = inputStreamReader.read(buffer, 0, buffer.length))!= -1){
            String s = new String(buffer,0,d);
            System.out.println(s);
        }

        fileInputStream = new FileInputStream("files/ui_project.txt");
        inputStreamReader = new InputStreamReader(fileInputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while((line = bufferedReader.readLine())!=null){
            System.out.println(line);   //一次读一行，并不能识别换行
        }

        fileInputStream = new FileInputStream("files/rd_project.txt");
        inputStreamReader = new InputStreamReader(fileInputStream);
        char[] buffer2 = new char[2048];
        int d2;
        while ((d2 = inputStreamReader.read(buffer2, 0, buffer2.length))!= -1){
            String s2 = new String(buffer2,0,d2);
            System.out.println(s2);
        }

        fileInputStream.close();
        inputStreamReader.close();
        bufferedReader.close();
    }
}
