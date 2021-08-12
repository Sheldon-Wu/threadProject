package com.wxd.threads;

import com.wxd.WebCompany;
import com.wxd.bean.Github;
import com.wxd.bean.Problem;
import com.wxd.bean.RDProject;
import com.wxd.bean.UIProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 线程：测试人员
 */
@Service
public class QualityAssurance implements Runnable{

    @Autowired
    WebCompany webCompany;

    @Autowired
    UIProject uiProject;

    @Autowired
    RDProject rdProject;

    @Autowired
    Problem problem;

    @Autowired
    Github github;

    @Override
    public void run() {

        try {
            webCompany.getCountDownLatch().await();
            this.work();
            this.submit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void work() throws InterruptedException {

        this.wait(5000);
        System.out.println(uiProject);
        System.out.println(rdProject);
        System.out.println("测试工程师开始进行测试。。。");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("测试工程师将问题反映给开发人员");
        problem.setUiProblem((int)(Math.random()*10)+1);
        problem.setRdProblem((int)(Math.random()*10)+1);

        this.wait(2000);
        System.out.println(uiProject);
        System.out.println(rdProject);
        problem.setUiProblem(0);
        problem.setRdProblem(0);
    }

    public void submit(){
        System.out.println("测试工程师："+Thread.currentThread().getName()+"将代码提交给运维人员。");
        github.setTempProject("uiProject+rdProject");
    }
}
