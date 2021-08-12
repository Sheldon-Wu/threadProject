package com.wxd.threads;

import com.wxd.bean.Contract;
import com.wxd.bean.Demand;
import com.wxd.bean.Prd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 线程：客户
 */
@Service
public class Client implements Runnable{

    private boolean hasNeed = true;
    private boolean addToNeed = false;
    private boolean flag = true;

    @Autowired
    Prd prd;

    @Autowired
    Demand demand;

    @Autowired
    Contract contract;

    FileOutputStream fileOutputStream;

    DataOutputStream dataOutputStream;

    public Client() throws FileNotFoundException {
        this.fileOutputStream = new FileOutputStream("files/demand.txt");
        this.dataOutputStream = new DataOutputStream(fileOutputStream);
    }

    @Override
    public void run() {

        StringBuilder stringBuilder = new StringBuilder();

        while(flag){
            if (hasNeed){   //提出第一次需求
                System.out.println("客户提出需求");
                stringBuilder.append("初始的需求");
                try {
                    dataOutputStream.write("初始的需求".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                synchronized (demand){
                    demand.setContent(stringBuilder);
                    System.out.println(demand);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                hasNeed = false;
                addToNeed = true;
            }

            if (addToNeed){     //提出追加的需求
                stringBuilder.append("+追加的需求");
                try {
                    dataOutputStream.write("+追加的需求".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("客户追加需求");

                synchronized (demand){
                    demand.setContent(stringBuilder);
                    System.out.println(demand);
                }

                flag = false;
            }
        }

        synchronized (contract){
            try {       //资源浪费
                contract.write("甲方：同意条款1 ");
                Thread.sleep(50);
                contract.write("同意条款2  ");
                Thread.sleep(50);
                contract.write("同意条款3 ");
                Thread.sleep(50);
                contract.write("同意条款4 ");
                Thread.sleep(50);
                contract.write("甲方签名：");
                Thread.sleep(50);
                contract.write("Jack  ");

                fileOutputStream = new FileOutputStream("files/contract.txt");
                dataOutputStream = new DataOutputStream(fileOutputStream);
                dataOutputStream.write("甲方：同意条款1 同意条款2  同意条款3 同意条款4 甲方签名：Jack  ".getBytes());
            } catch (InterruptedException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
