### 客户与产品经理交流阶段：
    设置线程休眠的方式模拟需求的提出、接收与追加，没有加入并发协作模型，较为不合理，效率也不高。

### 开发人员依据prd文档进行开发阶段：
    为了模拟多个线程同时启动，引入了发令枪CountDownLatch；使用线程并发协作模型：信号灯法实现线程之间的同步和通信。
    
### 测试，修改，提交运维人员阶段：
    反馈过程使用线程并发协作模型：管程法；问题交由开发人员后，使用AOP对方法进行增强，修改了项目代码。