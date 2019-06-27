package cn.wxb.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    //配置算法
    @Bean
    public IRule myIrule() {
        return new ConsumerRule();
    }
}
