package com.wxd.threads;

import com.wxd.WebCompany;
import com.wxd.bean.Problem;
import com.wxd.bean.UIProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 线程：视觉设计师
 */
@Service
public class UserInterface implements Runnable{

    @Autowired
    WebCompany webCompany;

    @Autowired
    UIProject uiProject;

    @Autowired
    UserExperience userExperience;

    @Autowired
    Problem problem;

    FileOutputStream fileOutputStream;

    OutputStreamWriter outputStreamWriter;

    BufferedWriter bufferedWriter;

    public UserInterface() throws FileNotFoundException {
        this.fileOutputStream = new FileOutputStream("files/ui_project.txt",true);
        this.outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        this.bufferedWriter = new BufferedWriter(outputStreamWriter);
    }

    @Override
    public void run() {

        try {
            webCompany.getCountDownLatch().await();
            this.work();
            this.modify();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void work() throws InterruptedException, IOException {
        while (!userExperience.isStartFlag()){
            Thread.sleep(1000);
        }
        System.out.println("前端工程师："+Thread.currentThread().getName()+"开始工作。。。");
        synchronized (uiProject){
            uiProject.uiCode.append("前端工程师："+Thread.currentThread().getName()+"的代码；");
        }

        bufferedWriter.write("前端工程师："+Thread.currentThread().getName()+"的代码；\r\n");
        bufferedWriter.flush();
    }

    public void modify() throws InterruptedException {

        while (problem.getUiProblem() == 0){
            Thread.sleep(1000);
        }
        System.out.println("前端工程师："+Thread.currentThread().getName()+"接收到测试人员反映的"+problem.getUiProblem()+"个前端问题");
    }
}
