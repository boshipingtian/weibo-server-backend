/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.impl.dto.canal;

import com.alibaba.otter.canal.client.CanalConnector;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 14:25
 * @Version: 1.0.0
 */
public abstract class CanalAdapterConfigure {

    /**
     * zkServers 192.168.192.215:2181,...
     * host 192.168.192.215
     * @return
     */
    public abstract String getServers();

    public abstract String getDestination();

    public abstract String getUsername();
    public abstract String getPassword();
    public abstract String getSubscribe();

    public abstract CanalConnector getConnector();
}