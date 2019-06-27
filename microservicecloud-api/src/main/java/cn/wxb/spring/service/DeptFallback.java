package cn.wxb.spring.service;

import cn.wxb.spring.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptFallback implements FallbackFactory<DeptServiceClient> {
    @Override
    public DeptServiceClient create(Throwable cause) {
        return new DeptServiceClient() {
            @Override
            public Dept get(long id) {
                return new Dept().setDeptno(id).setDname("该" + id + "没有对应的信息，null--@HyStrix+========").setDb_source("not this database sources");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
