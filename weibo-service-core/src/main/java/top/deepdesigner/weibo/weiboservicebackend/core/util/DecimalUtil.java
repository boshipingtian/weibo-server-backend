/**
 * Copyright(C),2015‐2024,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2024/3/22 13:49
 * @Version: 1.0.0
 */
@Slf4j
public class DecimalUtil {
    
    private static final Random RANDOM = new SecureRandom();

    private DecimalUtil() {
    }
    
    public static List<BigDecimal> slice(BigDecimal value, Integer size){
        Assert.notNull(value, "value must not be null");
        Assert.isTrue(size > 0, "size must be greater than 0");
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            float random = RANDOM.nextFloat();
            BigDecimal half = value.divide(new BigDecimal(2), RoundingMode.HALF_UP);
            float val = (0.01f + random * half.floatValue());
            int count = 0;
            while (val < 0.01){
                val = (0.01f + random * half.floatValue());
                count++;
                if(count > 100){
                    val = 0.01f; 
                    break;
                }
            }
            BigDecimal forReturn = BigDecimal.valueOf(val).setScale(2, RoundingMode.HALF_UP);
            list.add(forReturn);
            value = value.subtract(forReturn);
        }
        return list;
    }

    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("100");
        List<BigDecimal> list = slice(value, 1000);
        log.info("list:{}", list);
    }
}