package com.wxd.bean;

import org.springframework.stereotype.Component;

/**
 * 资源：合同
 */
@Component
public class Contract {

    private StringBuilder content;

    //StringBuilder需要先创建对象，不然会报空指针异常
    public Contract() {
        this.content = new StringBuilder("");
    }

    public void write(String str){
        this.content.append(str);
    }

    public StringBuilder getContent() {
        return content;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "content='" + content + '\'' +
                '}';
    }
}
