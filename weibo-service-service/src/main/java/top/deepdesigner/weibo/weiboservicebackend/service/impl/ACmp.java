package top.deepdesigner.weibo.weiboservicebackend.service.impl;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("a")
public class ACmp extends NodeComponent {

	@Override
	public void process() {
		//do your business
        DemoContext contextBean = this.getContextBean(DemoContext.class);
        log.info("contextBean = " + contextBean);
        try {
            contextBean.setAge(10);
            contextBean.setName("张三");
        } catch (Exception e) {
            log.info("Exception");
        }
    }

    @Override
    public void beforeProcess() {
        log.info("beforeProcess");
    }

    @Override
    public void afterProcess() {
        log.info("afterProcess");
        log.info("contextBean = " + this.getContextBean(DemoContext.class));
    }
}