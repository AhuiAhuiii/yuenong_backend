/**
 *苦心人，天不负
 */
package ltd.newbee.mall.config.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToAdminUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default "adminUser";

}
