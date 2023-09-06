/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.impl.runner;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.Message;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.deepdesigner.weibo.weiboservicebackend.service.api.runner.CanalRunner;
import top.deepdesigner.weibo.weiboservicebackend.service.impl.dto.canal.CanalAdapterConfigure;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 14:04
 * @Version: 1.0.0
 */
@Slf4j
@Data
public abstract class AbstractCanalRunner implements CanalRunner {

    private CanalAdapterConfigure canalAdapterConfigure;

    protected AbstractCanalRunner(CanalAdapterConfigure canalAdapterConfigure) {
        this.canalAdapterConfigure = canalAdapterConfigure;
    }

    @Override
    public void run() {
        // 创建链接
        CanalConnector connector = canalAdapterConfigure.getConnector();
        int batchSize = 1000;
        while (true) {
            try {
                connector.connect();
                // 默认订阅所有表
                connector.subscribe(canalAdapterConfigure.getSubscribe());
                while (true) {
                    Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        log.info("message[batchId={},size={}]", batchId, size);
                        this.printEntry(message.getEntries());
                    }
                    connector.ack(batchId); // 提交确认
                    // connector.rollback(batchId); // 处理失败, 回滚数据
                }
            } finally {
                connector.disconnect();
            }
        }
    }

    public abstract void printEntry(List<Entry> entrys);
}