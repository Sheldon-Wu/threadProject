package com.wxd.bean;

import org.springframework.stereotype.Component;

/**
 * 资源：发现的问题
 */
@Component
public class Problem {

    private Integer uiProblem = 0;
    private Integer rdProblem = 0;

    public Integer getUiProblem() {
        return uiProblem;
    }

    public void setUiProblem(Integer uiProblem) {
        this.uiProblem = uiProblem;
    }

    public Integer getRdProblem() {
        return rdProblem;
    }

    public void setRdProblem(Integer rdProblem) {
        this.rdProblem = rdProblem;
    }
}
