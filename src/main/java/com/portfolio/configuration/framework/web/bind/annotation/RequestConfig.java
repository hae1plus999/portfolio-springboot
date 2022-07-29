package com.portfolio.configuration.framework.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestConfig {
	
	//등록/수정/삭제에만 사용(로그인이 필요하기 때문에)
	//default false 는 로그인이 체크를 하지않고 true일때만 체크를 함
	boolean loginCheck() default false;
}
