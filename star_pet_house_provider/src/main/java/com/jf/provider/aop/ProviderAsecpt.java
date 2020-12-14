package com.jf.provider.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 *@description:
 *@author jiafeng
 *@date 2020/9/27 0027 10:47
 */
@Aspect
@Component
public class ProviderAsecpt {

    @Pointcut("execution(public * com.jf.provider.impl..*(..))")
    public void BrokerAspect() {

    }

    @Before("BrokerAspect()")
    public void doBefore() {
        System.out.println("方法开始了呀，大家系好安全带！");
    }

    @After("BrokerAspect()")
    public void doAfter() {
        System.out.println("方法结束了，请大家安全下车！");
    }
}
