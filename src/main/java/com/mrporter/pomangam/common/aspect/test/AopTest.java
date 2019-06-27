package com.mrporter.pomangam.common.aspect.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopTest {

    @Pointcut("execution(* com.mrporter.pomangam.common.aspect.test.RealClass.doSomething_1(..))")
    public void fun_1() {
    }

    @Pointcut("execution(* com.mrporter.pomangam.common.aspect.test.RealClass.doSomething_2(..))")
    public void fun_2() {
    }

    @Around("fun_1() || fun_2()")
    public Object readHandler(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("readHandler_start");
        Object obj = pjp.proceed();
        System.out.println("result : " + obj.toString());
        System.out.println("readHandler_end");
        return obj;
    }
}
