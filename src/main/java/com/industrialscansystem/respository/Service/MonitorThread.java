package com.industrialscansystem.respository.Service;

/**
 * @Author 宋宗垚
 * @Date 2019/2/27 10:56
 * @Description 线程监视类，每隔一段时间就检查一下后台的线程是否正常，
 * 如果不正常，在新建一个线程并记录这个线程的tid用于下一次检查
 */
public class MonitorThread extends Thread{

    private DetectionThread detectionThread;
    private long tid;
    @Override
    public void run() {
        this.detectionThread = new DetectionThread();
        this.tid = this.detectionThread.getId();
        this.detectionThread.start();
        while (true){
            Thread tt = findThread(tid);
            if(null==tt){
                this.detectionThread = new DetectionThread();
                this.tid = this.detectionThread.getId();
                this.detectionThread.start();
                System.out.println("旧线程失效，新建了一个线程");
            }
            try {
                // 设置每次监控时间为十分钟
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(tt.getName());
//            System.out.println(tt.getName());
        }

    }
    /**
     * 通过线程组获得线程
     * @param threadId 要查找的线程tID
     * @return 返回一个线程对象
     */
    private  Thread findThread(long threadId) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
                if(threadId == threads[i].getId()) {
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }
}
