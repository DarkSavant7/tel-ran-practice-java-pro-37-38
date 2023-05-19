package de.telran.marketapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
/**
 * https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/aop.html
 */
public class LoggerAspect {
    // определяем срез по всем методам бинов из пакета com.example.aop.service
    @Pointcut("execution(* de.telran.marketapp.services..*.*(..))")
    private void getName() {
    } // определяем совет (Advice) "ПЕРЕД" выполнением кода бина (класса)

    @Before("getName()")
//    @After("getName()")
    public void logBefore(JoinPoint joinPoint) {
// выводим в консоль информацию о текущей точке соединения
        log.info("Service method called: {}", joinPoint.getSignature());
    }

    @Before("execution(public * de.telran.marketapp.web.AuthController.*(..))")
    public void beforeUserEditProduct(JoinPoint joinPoint) {
        log.info("Auth controller called: {}", joinPoint.getSignature());
    }

    @Around("execution(public * de.telran.marketapp.services.ProductService.methodForAspect(String))")
    public Object beforeUserEditProduct(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Aspect fake method called: {}", joinPoint.getSignature());
        var result = (String) joinPoint.proceed();
        log.info("Result was {}", result);
        return "Hello from aspect";
//        return result;
    }
}
