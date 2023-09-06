/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 14:12
 * @Version: 1.0.0
 */
public class CheckUtil {

    private CheckUtil() {
    }

    public static CheckResult isAllNotNull(String... value) {
        List<String> result = Stream.of(value)
            .filter(String::isBlank).toList();
        if (!result.isEmpty()) {
            return new CheckResult(Boolean.FALSE, result);
        }
        return new CheckResult(Boolean.TRUE, new ArrayList<>());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class CheckResult {

        private Boolean status;

        private List<String> strings;
    }
}