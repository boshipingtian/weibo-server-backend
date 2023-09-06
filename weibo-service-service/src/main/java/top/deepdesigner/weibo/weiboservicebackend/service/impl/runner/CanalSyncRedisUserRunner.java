/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.impl.runner;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.deepdesigner.weibo.weiboservicebackend.exception.CanalParserException;
import top.deepdesigner.weibo.weiboservicebackend.service.impl.dto.canal.SimpleCanalAdapterConfigure;
import top.deepdesigner.weibo.weiboservicebackend.service.utils.RedisUtil;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 13:22
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class CanalSyncRedisUserRunner extends AbstractCanalRunner {

    @Autowired
    public CanalSyncRedisUserRunner(SimpleCanalAdapterConfigure canalAdapterConfigure) {
        super(canalAdapterConfigure);
    }

    @Override
    public void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN
                || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                log.info("事务 {}", entry.getEntryType());
                continue;
            }

            RowChange rowChage;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new CanalParserException("ERROR ## parser of eromanga-event has an error , data:" + entry, e);
            }

            EventType eventType = rowChage.getEventType();
            log.info("binlog[{}:{}] , name[{},{}] , eventType : {}",
                entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                eventType);

            log.info("event type {}", eventType);
            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    JSONObject jsonObject = printColumn(rowData.getBeforeColumnsList());
                    RedisUtil.delete(jsonObject.getString("id"));
                } else if (eventType == EventType.INSERT) {
                    JSONObject jsonObject = printColumn(rowData.getAfterColumnsList());
                    RedisUtil.put(jsonObject.getString("id"), jsonObject.toJSONString(), RedisUtil.randomExpire(1, 2),
                        TimeUnit.HOURS);
                } else if (eventType == EventType.UPDATE) {
                    log.info("------- update before");
                    printColumn(rowData.getBeforeColumnsList());
                    log.info("------- update after");
                    JSONObject jsonObject = printColumn(rowData.getAfterColumnsList());
                    RedisUtil.put(jsonObject.getString("id"), jsonObject.toJSONString(), RedisUtil.randomExpire(1, 2),
                        TimeUnit.HOURS);
                } else {
                    log.info("------- other before || do nothing");
                    printColumn(rowData.getBeforeColumnsList());
                    log.info("------- other after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private JSONObject printColumn(List<Column> columns) {
        JSONObject jsonObject = new JSONObject();
        for (Column column : columns) {
            jsonObject.fluentPut(column.getName(), column.getValue());
            log.info(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
        log.info(jsonObject.toJSONString());
        return jsonObject;
    }
}