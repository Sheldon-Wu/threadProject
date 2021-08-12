package com.wxd.bean;

import org.springframework.stereotype.Component;

/**
 * 资源：产品需求文档，Product Requirements Document
 */
@Component
public class Prd {

    private StringBuilder content;      //StringBuilder没有实现线程安全功能

    public Prd() {
        this.content = new StringBuilder();
    }

    public StringBuilder getContent() {
        return content;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Prd{" +
                "content='" + content + '\'' +
                '}';
    }
}
