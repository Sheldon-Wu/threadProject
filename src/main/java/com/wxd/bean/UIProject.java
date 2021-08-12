package com.wxd.bean;

import org.springframework.stereotype.Component;

/**
 * 资源：前端应用程序
 */
@Component
public class UIProject {

    public StringBuilder uiCode;

    public UIProject() {
        this.uiCode = new StringBuilder();
    }

    @Override
    public String toString() {
        return "UIProject{" +
                "uiCode=" + uiCode +
                '}';
    }
}
