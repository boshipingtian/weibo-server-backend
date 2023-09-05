/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.runnable.canal;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.deepdesigner.weibo.weiboservicebackend.exception.CanalSyncRunnerException;
import top.deepdesigner.weibo.weiboservicebackend.service.api.runner.CanalRunner;
import top.deepdesigner.weibo.weiboservicebackend.singleton.ThreadPoolSingleton;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 11:21
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class CanalSyncRedis implements ApplicationRunner {

    private List<CanalRunner> canalRunners;

    @Autowired
    public void setCanalRunners(List<CanalRunner> canalRunners) {
        this.canalRunners = canalRunners;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("args {}", args.getNonOptionArgs());
        try {
            canalRunners.forEach(canalRunner -> ThreadPoolSingleton.instance().submit(canalRunner));
        } catch (Exception e) {
            throw new CanalSyncRunnerException("提交到线程池失败", e);
        }
    }
}