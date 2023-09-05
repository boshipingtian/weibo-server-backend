/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.controller.tools;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "定时任务", tags = "定时任务")
public class CronController {

    @ApiOperation(value = "解析定时任务表达式")
    @GetMapping("/corn/parse")
    public ArrayList<String> queryWithVersion(String cron) {
       final CronExpression parse = CronExpression.parse(cron);
        LocalDateTime of = LocalDateTimeUtil.of(new Date());
        System.out.println("of = " + of);
//        System.out.println("now = " + now);
//        LocalTime next = parse.next(now);
//        System.out.println("next = " + next);
        ArrayList<String> strings = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 10; i++) {
            LocalDateTime next = parse.next(of);
            System.out.println("next = " + next);
            String format = next.format(dateTimeFormatter);
            System.out.println("format = " + format);
            strings.add(format);
            of = next;
        }
        return strings;
    }
}