package cn.wxb.spring.controller;

import cn.wxb.spring.entity.Dept;
import cn.wxb.spring.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "provider_get")
    public Dept get(@PathVariable("id") Long id) {

        Dept dept = deptService.get(id);
        if (dept == null) {
            throw new RuntimeException("该" + id + "没有响应信息");
        }
        return dept;
    }

    public Dept provider_get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id).setDname("该" + id + "没有对应的信息，null--@HyStrix").setDb_source("not this database sources");
    }
}
