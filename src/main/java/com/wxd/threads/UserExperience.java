package com.wxd.threads;

import com.wxd.WebCompany;
import com.wxd.bean.Prd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 线程：交互设计师
 */
@Service
public class UserExperience implements Runnable{

    private boolean startFlag = false;

    @Autowired
    WebCompany webCompany;

    @Autowired
    Prd prd;

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    @Override
    public void run() {

        try {
            webCompany.getCountDownLatch().await();
            this.work();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void work() throws InterruptedException {

        try {
            //System.out.println("交互设计师zzzzz.....");
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("交互设计师拿到PRD产品需求文档，开始工作");
        this.startFlag = true;
    }

    public synchronized void wakeUp(){
        System.out.println("产品经理已完成PRD文档");
        this.notifyAll();
    }
}
