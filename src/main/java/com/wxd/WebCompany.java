package com.wxd;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * 公司对象，持有发令枪
 */
@Service
public class WebCompany {

    private CountDownLatch countDownLatch;

    public WebCompany() {
        this.countDownLatch = new CountDownLatch(1);
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
