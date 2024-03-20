/**
 * Copyright(C),2015‐2024,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.sharding;

import java.util.Collection;
import java.util.Date;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2024/3/20 20:16
 * @Version: 1.0.0
 */
public class MonthAlgorithm implements PreciseShardingAlgorithm<Date>, RangeShardingAlgorithm<Date> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        return null;
    }
}