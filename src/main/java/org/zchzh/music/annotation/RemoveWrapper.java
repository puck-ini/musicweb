package org.zchzh.music.annotation;

import java.lang.annotation.*;

/**
 * @author zengchzh
 * @date 2021/5/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RemoveWrapper {
}
