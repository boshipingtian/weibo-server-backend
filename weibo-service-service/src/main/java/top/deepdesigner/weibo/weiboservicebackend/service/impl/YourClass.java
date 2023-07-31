package top.deepdesigner.weibo.weiboservicebackend.service.impl;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class YourClass{
    
    @Resource
    private FlowExecutor flowExecutor;
    
    public String testConfig(){
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg", DemoContext.class);
        DemoContext contextBean = response.getContextBean(DemoContext.class);
        log.info("contextBean = " + contextBean);
        log.info(response.getExecuteStepStrWithTime());
        return contextBean.toString();
    }
}