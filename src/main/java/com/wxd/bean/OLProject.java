package com.wxd.bean;

import org.springframework.stereotype.Component;

/**
 * 资源：上线项目
 */
@Component
public class OLProject {

    private StringBuffer stringBuffer;

    public OLProject() {
        this.stringBuffer = new StringBuffer();
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    @Override
    public String toString() {
        return "OLProject{" +
                "stringBuffer=" + stringBuffer +
                '}';
    }
}
