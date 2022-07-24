package com.industrialscansystem.Controller;

import com.industrialscansystem.util.GetHostIp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexRestController {

    @PostMapping(value = "/getLocalIpv4")
    public String getLocalIpv4(){

        GetHostIp getHostIp = new GetHostIp();
        String localIpv4 = getHostIp.getHostIp();
        return localIpv4;
    }
}
