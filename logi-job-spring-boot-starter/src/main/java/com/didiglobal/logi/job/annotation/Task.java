package com.didiglobal.logi.job.annotation;

import com.didiglobal.logi.job.core.consensual.ConsensualEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author zqr
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Task {
    /**
     * task taskName.
     */
    String name() default "";

    /**
     * task desc.
     */
    String description() default "";

    /**
     * task owner
     */
    String owner() default "system";

    /**
     * task cron.
     */
    String cron() default "";

    /**
     * retry times.
     */
    int retryTimes() default 0;

    /**
     * timeout seconds.
     */
    long timeout() default 0;

    /**
     * if not exists in db will auto register.
     */
    boolean autoRegister() default false;

    /**
     * consensual.
     */
    ConsensualEnum consensual() default ConsensualEnum.RANDOM;
}
