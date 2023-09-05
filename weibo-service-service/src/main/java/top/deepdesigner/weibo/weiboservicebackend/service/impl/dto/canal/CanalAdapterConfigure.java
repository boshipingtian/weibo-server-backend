/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.impl.dto.canal;

import lombok.Data;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 14:25
 * @Version: 1.0.0
 */
@Data
public abstract class CanalAdapterConfigure {

    protected String zkServers;

    protected String destination;

    protected String username = "canal";

    protected String password = "canal";

    protected String subscribe = ".*\\..*";
}