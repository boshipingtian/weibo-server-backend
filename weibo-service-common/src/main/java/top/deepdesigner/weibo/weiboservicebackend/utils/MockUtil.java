/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/7 10:32
 * @Version: 1.0.0
 */
public class MockUtil {

    private static final Random RANDOM = new Random();

    private MockUtil() {
    }

    public static List<BigDecimal> randomList(Integer size, Integer max, Integer min) {
        List<BigDecimal> bigDecimals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bigDecimals.add(BigDecimal.valueOf(min + RANDOM.nextDouble() * (max - min)).setScale(2, RoundingMode.HALF_UP));
        }
        return bigDecimals;
    }

    public static List<String> getTimes(String startDate, String endDate, Integer filed, Integer gap, String format) {
        List<String> lists = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat resultFormat = new SimpleDateFormat(format);
        try {
            Date start = simpleDateFormat.parse(startDate);
            Date end = simpleDateFormat.parse(endDate);
            calendar.setTime(start);
            while (calendar.getTime().compareTo(end) < 0) {
                lists.add(resultFormat.format(calendar.getTime()));
                calendar.add(filed, gap);
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式应为yyyy-MM-dd");
        }
        return lists;
    }
}