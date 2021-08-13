package com.wxd.threads;

import com.wxd.bean.UIProject;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Aspect
public class UserInterfaceProxy {

    @Autowired
    UIProject uiProject;

    FileOutputStream fileOutputStream;

    OutputStreamWriter outputStreamWriter;

    BufferedWriter bufferedWriter;

    public UserInterfaceProxy() throws FileNotFoundException {
        this.fileOutputStream = new FileOutputStream("files/ui_project.txt",true);
        this.outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        this.bufferedWriter = new BufferedWriter(outputStreamWriter);
    }

    private final ReentrantLock lock = new ReentrantLock();

    @After(value = "execution(* com.wxd.threads.UserInterface.run(..))")
    public void fixing(){
        try{
            this.lock.lock();

            uiProject.uiCode.append("前端工程师："+Thread.currentThread().getName()+"更新的代码；");

            bufferedWriter.write("前端工程师："+Thread.currentThread().getName()+"更新的代码；\r\n");
            bufferedWriter.flush();

            System.out.println("前端工程师："+Thread.currentThread().getName()+"修改了代码");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.lock.unlock();
        }
    }
}
