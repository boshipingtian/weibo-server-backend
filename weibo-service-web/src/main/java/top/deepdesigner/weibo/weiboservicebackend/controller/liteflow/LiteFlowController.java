package top.deepdesigner.weibo.weiboservicebackend.controller.liteflow;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.deepdesigner.weibo.weiboservicebackend.service.impl.YourClass;

@Api(value = "测试", tags = "测试")
@RestController
public class LiteFlowController {

   @Resource
    private YourClass yourClass;

   @ApiOperation(value = "测试")
   @GetMapping("/test/padding")
   public String padding() {
       return yourClass.testConfig();
   }
}