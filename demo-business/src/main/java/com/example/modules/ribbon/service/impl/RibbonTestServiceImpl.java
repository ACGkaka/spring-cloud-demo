package com.example.modules.ribbon.service.impl;

import com.example.modules.ribbon.service.RibbonTestService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

/**
 * <p> @Title RibbonTestServiceImpl
 * <p> @Description 测试 Ribbon 的轮询策略
 *
 * @author ACGkaka
 * @date 2020/12/14 14:47
 */
@Service
@AllArgsConstructor
public class RibbonTestServiceImpl implements RibbonTestService {

    /**
     * Ribbon 负载均衡器
     */
    private LoadBalancerClient loadBalancerClient;

    @Override
    public String test1() {
        // 例如：
        // http://192.168.10.79:1003/product/list
        // http://192.168.10.79:2003/product/list
        // 默认轮询策略，两个地址会交替出现
        ServiceInstance instance = loadBalancerClient.choose("demo-gateway");
        String url = String.format("http://%s:%s/product/list", instance.getHost(), instance.getPort());
        System.out.println(url);
        return url;
    }
}
