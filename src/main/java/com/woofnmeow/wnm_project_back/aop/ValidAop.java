package com.woofnmeow.wnm_project_back.aop;

import com.woofnmeow.wnm_project_back.exception.ValidCheckException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;


@Component
@Aspect
public class ValidAop {

    @Pointcut("@annotation(com.woofnmeow.wnm_project_back.aop.annotation.ValidAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }

        if(bindingResult == null) { // null check
            return proceedingJoinPoint.proceed();
        }

        if(bindingResult.hasErrors()) { // 에러 처리
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            throw new ValidCheckException(errorMap);
        }

        Object target = proceedingJoinPoint.proceed();
        return target;
    }
}
