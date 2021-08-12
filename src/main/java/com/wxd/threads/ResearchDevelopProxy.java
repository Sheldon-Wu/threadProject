package com.wxd.threads;

import com.wxd.bean.RDProject;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
@Aspect
public class ResearchDevelopProxy {

    @Autowired
    RDProject rdProject;

    private final ReentrantLock lock = new ReentrantLock();

    @After(value = "execution(* com.wxd.threads.ResearchDevelop.run(..))")
    public void fixing(){

        try{
            this.lock.lock();       //若rdCode的类型是StringBuffer，则没有必要加锁，但会降低性能

            rdProject.rdCode.append("后端工程师："+Thread.currentThread().getName()+"更新的代码；");
            //此处就算不上锁也能同步执行？？？

            System.out.println("后端工程师："+Thread.currentThread().getName()+"修改了代码");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.lock.unlock();
        }
    }
}
