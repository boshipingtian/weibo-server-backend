/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.dto.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import java.net.InetSocketAddress;
import org.springframework.stereotype.Component;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 14:31
 * @Version: 1.0.0
 */
@Component
public class SimpleCanalAdapterConfigure extends CanalAdapterConfigure {

    @Override
    public String getSubscribe() {
        return ".*\\..*";
    }

    @Override
    public String getServers() {
        return "192.168.192.215";
    }

    @Override
    public String getDestination() {
        return "syncdemo_redis";
    }

    @Override
    public String getUsername() {
        return "canal";
    }

    @Override
    public String getPassword() {
        return "canal";
    }

    @Override
    public CanalConnector getConnector() {
        return CanalConnectors.newSingleConnector(new InetSocketAddress(getServers(), 11111), getDestination(), getUsername(),
            getPassword());
    }
}