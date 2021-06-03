package com.bying.service;

import com.bying.pojo.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/5/28 6:53 下午
 * @description
 */
@Service
public class UserService {

    @Resource
    private LoadBalancerClient loadBalancerClient; //ribbon负载均衡器

    public List<User> getUser() {
        //serviceInstance 封装了服务的基本信息，如 IP，端口
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("springcloud-eureka-provider");

        //拼接访问服务的URL
        StringBuffer sb = new StringBuffer();
        //http://localhost:9090/user
        sb.append("http://").append(serviceInstance.getHost()).append(":").append(serviceInstance.getPort()).append("/user");

        //springMVC RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<User>> type = new ParameterizedTypeReference<List<User>>() {
        };

        //ResponseEntity 封装了返回值信息
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, type);
        List<User> list = responseEntity.getBody();
        return list;
    }
}
