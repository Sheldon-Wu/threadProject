package com.wxd.bean;

import org.springframework.stereotype.Component;

@Component
public class Github {

    private String tempProject;

    public String getTempProject() {
        return tempProject;
    }

    public void setTempProject(String tempProject) {
        this.tempProject = tempProject;
    }
}
