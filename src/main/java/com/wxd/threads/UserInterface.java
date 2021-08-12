package com.wxd.threads;

import com.wxd.WebCompany;
import com.wxd.bean.Problem;
import com.wxd.bean.UIProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void run() {

        try {
            webCompany.getCountDownLatch().await();
            this.work();
            this.modify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void work() throws InterruptedException {
        while (!userExperience.isStartFlag()){
            Thread.sleep(1000);
        }
        System.out.println("前端工程师："+Thread.currentThread().getName()+"开始工作。。。");
        synchronized (uiProject){
            uiProject.uiCode.append("前端工程师："+Thread.currentThread().getName()+"的代码；");
        }
    }

    public void modify() throws InterruptedException {

        while (problem.getUiProblem() == 0){
            Thread.sleep(1000);
        }
        System.out.println("前端工程师："+Thread.currentThread().getName()+"接收到测试人员反映的"+problem.getUiProblem()+"个前端问题");
    }
}
