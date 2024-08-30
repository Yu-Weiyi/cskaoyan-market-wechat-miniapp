package happy.coding.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class TransactionAspect{
    @Pointcut("execution(* happy.coding.servlet.*.*(..))")
    public void allFn(){}

    @Pointcut("@annotation(happy.coding.aspect.BaseTransaction)")
    public void annoFn(){};



    @After("annoFn()")
    public void annoFnAfter(JoinPoint joinPoint){
        System.out.println("after");
    }

    @After("allFn()")
    public void after(JoinPoint joinPoint){
        System.out.println("before");
        //在执行所有方法之前都会执行这个方法
        //我们在这里执行日志记录的信息

        //先打印出来看看吧
        Signature signature = joinPoint.getSignature();
        Object proxy = joinPoint.getThis();
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();

        System.out.println("signature:" + signature.getName());
        System.out.println(proxy.getClass().getName());
        System.out.println(target.getClass().getName());
        System.out.println(Arrays.asList(args));

    }
}
