package cn.bitterbee.zylprojects.module.java.annotation;

import java.lang.annotation.*;

/**
 * Created by zyl06 on 1/16/16.
 */
public @interface Uniqueness {
    Constraints constraints() default @Constraints(unique = true);
}
