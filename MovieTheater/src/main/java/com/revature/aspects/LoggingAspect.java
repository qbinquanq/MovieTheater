package com.revature.aspects;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect
{
	private Logger log = Logger.getRootLogger();
	
	@Around("everything()")
	public Object logger(ProceedingJoinPoint pjp)
	{
		Object obj = null;
		log.warn("Method with signature "+pjp.getSignature()+" called.");
		log.warn("With arguments: "+Arrays.toString(pjp.getArgs()));
		
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			log.error(e.getMessage());
			for(StackTraceElement s : e.getStackTrace())
			{
				log.error(s);
			}
		}
		log.warn(pjp.getSignature()+" returned: "+obj);
		return obj;
	}
	
	//hook for everything
	@Pointcut("execution(* com.revature..*(..))")
	public void everything(){}
}
