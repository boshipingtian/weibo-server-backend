package top.deepdesigner.weibo.weiboservicebackend.service.impl;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("c")
public class CCmp extends NodeComponent {



	@Override
	public void process() {
		//do your business
        System.out.println("c");
        //int insert = userMapper.insert(new User(1L, "1", "1", "1", 1, "1"));
        // System.out.println("insert = " + insert);
    }
}