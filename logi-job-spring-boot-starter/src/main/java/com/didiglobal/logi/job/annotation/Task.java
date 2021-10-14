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
     * @return String
     */
    String name() default "";

    /**
     * task desc.
     * @return String
     */
    String description() default "";

    /**
     * task owner
     * @return String
     */
    String owner() default "system";

    /**
     * task cron.
     * @return String
     */
    String cron() default "";

    /**
     * retry times.
     * @return int
     */
    int retryTimes() default 0;

    /**
     * timeout seconds.
     * @return long
     */
    long timeout() default 0;

    /**
     * if not exists in db will auto register.
     * @return boolean
     */
    boolean autoRegister() default false;

    /**
     * consensual.
     * @return ConsensualEnum
     */
    ConsensualEnum consensual() default ConsensualEnum.RANDOM;
}
