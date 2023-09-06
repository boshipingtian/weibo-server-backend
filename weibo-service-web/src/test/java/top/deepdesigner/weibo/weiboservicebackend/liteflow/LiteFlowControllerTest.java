package top.deepdesigner.weibo.weiboservicebackend.liteflow;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.deepdesigner.weibo.weiboservicebackend.WeiboServiceBackendApplication;
import top.deepdesigner.weibo.weiboservicebackend.controller.liteflow.LiteFlowController;

@SpringBootTest(classes = WeiboServiceBackendApplication.class)
class LiteFlowControllerTest {

    @Resource
    private LiteFlowController liteFlowController;

    @Test
    void queryWithVersion() {
        liteFlowController.padding();
    }
}