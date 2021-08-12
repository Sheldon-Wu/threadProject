package com.wxd.bean;

import org.springframework.stereotype.Component;

/**
 * 资源：后端应用程序
 */
@Component
public class RDProject {

    public StringBuilder rdCode;

    public RDProject() {
        this.rdCode = new StringBuilder();
    }

    @Override
    public String toString() {
        return "RDProject{" +
                "rdCode=" + rdCode +
                '}';
    }
}
