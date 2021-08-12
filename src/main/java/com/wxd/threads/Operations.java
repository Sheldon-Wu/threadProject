package com.wxd.threads;

import com.wxd.WebCompany;
import com.wxd.bean.Github;
import com.wxd.bean.OLProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 线程：运维人员
 */
@Service
public class Operations implements Runnable{

    @Autowired
    WebCompany webCompany;

    @Autowired
    OLProject olProject;

    @Autowired
    Github github;

    @Override
    public void run() {

        try {
            webCompany.getCountDownLatch().await();
            this.deploy();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deploy() throws InterruptedException {

        while (github.getTempProject()==null||github.getTempProject().equals("")){
            Thread.sleep(1000);
        }
        System.out.println("运维工程师："+Thread.currentThread().getName()+"开始部署项目。。。");
        StringBuffer tempStringBuffer = new StringBuffer();
        tempStringBuffer.append(github.getTempProject());
        olProject.setStringBuffer(tempStringBuffer);
        System.out.println(olProject);
    }
}
