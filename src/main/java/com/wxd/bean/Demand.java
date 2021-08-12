package com.wxd.bean;

import org.springframework.stereotype.Component;

/**
 * 资源：客户需求
 */
@Component
public class Demand {

    private StringBuilder content;

    public StringBuilder getContent() {
        return content;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "content='" + content + '\'' +
                '}';
    }
}
