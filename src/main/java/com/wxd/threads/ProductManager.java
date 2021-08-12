package com.wxd.threads;

import com.wxd.WebCompany;
import com.wxd.bean.Contract;
import com.wxd.bean.Demand;
import com.wxd.bean.Prd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 线程：产品经理
 */
@Service
public class ProductManager implements Runnable{

    @Autowired
    WebCompany webCompany;

    @Autowired
    Prd prd;

    @Autowired
    Demand demand;

    @Autowired
    Contract contract;

    @Autowired
    UserExperience userExperience;

    FileOutputStream fileOutputStream;

    BufferedOutputStream bufferedOutputStream;

    public ProductManager() throws FileNotFoundException {
        this.fileOutputStream = new FileOutputStream("files/rd_project.txt");
        this.fileOutputStream = new FileOutputStream("files/prd.txt");
        this.bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
    }

    @Override
    public void run() {

        try {
            webCompany.getCountDownLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();

        synchronized (demand){
            StringBuilder demandContent1 = demand.getContent();
            System.out.println("产品经理接收到客户需求："+demandContent1);
        }

        synchronized (prd){
            stringBuilder.append("需求1.0");
            prd.setContent(stringBuilder);
            try {
                bufferedOutputStream.write("需求1.0".getBytes());
                bufferedOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("产品经理编写了PRD文档："+prd);
            System.out.println(prd);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder demandContent2 = demand.getContent();
        System.out.println("产品经理接收到客户需求："+demandContent2);

        synchronized (prd){
            stringBuilder.append("+需求2.0 end");
            prd.setContent(stringBuilder);
            try {
                bufferedOutputStream.write("+需求2.0 end".getBytes());
                bufferedOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("产品经理更新了PRD文档："+prd);
            System.out.println(prd);
            userExperience.wakeUp();
        }

        synchronized (contract){
            try {
                contract.write("乙方：条款1 ");
                Thread.sleep(50);
                contract.write("条款2 ");
                Thread.sleep(50);
                contract.write("条款3 ");
                Thread.sleep(50);
                contract.write("条款4 ");
                Thread.sleep(50);
                contract.write("乙方签名：");
                Thread.sleep(50);
                contract.write("Sheldon  ");

                fileOutputStream = new FileOutputStream("files/contract.txt",true);
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                bufferedOutputStream.write("乙方：条款1 条款2 条款3 条款4 乙方签名：Sheldon  ".getBytes());
                bufferedOutputStream.flush();
            } catch (InterruptedException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(contract);
    }
}
