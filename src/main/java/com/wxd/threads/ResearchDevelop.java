package com.wxd.threads;

import com.wxd.WebCompany;
import com.wxd.bean.Prd;
import com.wxd.bean.Problem;
import com.wxd.bean.RDProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 线程：后端开发人员
 */
@Service
public class ResearchDevelop implements Runnable{

    @Autowired
    WebCompany webCompany;

    @Autowired
    Prd prd;

    @Autowired
    RDProject rdProject;

    @Autowired
    Problem problem;

    FileOutputStream fileOutputStream;

    OutputStreamWriter outputStreamWriter;

    public ResearchDevelop() throws FileNotFoundException {
        this.fileOutputStream = new FileOutputStream("files/rd_project.txt",true);
        this.outputStreamWriter = new OutputStreamWriter(fileOutputStream);
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
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void work() throws InterruptedException, IOException {

        while (!prd.getContent().toString().endsWith("end")){
            Thread.sleep(1000);
        }
        System.out.println("后端工程师："+Thread.currentThread().getName()+"开始工作。。。");
        synchronized (rdProject){
            rdProject.rdCode.append("后端工程师："+Thread.currentThread().getName()+"的代码；");
        }

        outputStreamWriter.write("后端工程师："+Thread.currentThread().getName()+"的代码；\r\n");
        outputStreamWriter.flush();
    }

    public void modify() throws InterruptedException {

        while (problem.getRdProblem() == 0){
            Thread.sleep(1000);
        }
        System.out.println("后端工程师："+Thread.currentThread().getName()+"接收到测试人员反映的"+problem.getRdProblem()+"个后端问题");
    }
}
