package top.deepdesigner.weibo.weiboservicebackend.liteflow;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.deepdesigner.weibo.weiboservicebackend.WeiboServiceBackendApplication;

@SpringBootTest(classes = WeiboServiceBackendApplication.class)
class LiteFlowControllerTest {

    @Resource
    private LiteFlowController liteFlowController;

    @Test
    void queryWithVersion() {
        liteFlowController.padding();
    }
}