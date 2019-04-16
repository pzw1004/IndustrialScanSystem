package com.industrialscansystem;

import com.industrialscansystem.Service.MonitorThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan//自定义拦截器  解决问题 （在请求的资源上没有“访问控制允许源”标头。因此，“http://LoalHoal:8080”是不允许访问的。） Access-Control-Allow-Origin

public class InterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterfaceApplication.class, args);

        // 启动一个线程 ，次线程负责计算数据库中没有进行损伤检测的数据。
        MonitorThread monitorThread = new MonitorThread();
        monitorThread.start();
        System.out.println("启动完毕");

    }
}