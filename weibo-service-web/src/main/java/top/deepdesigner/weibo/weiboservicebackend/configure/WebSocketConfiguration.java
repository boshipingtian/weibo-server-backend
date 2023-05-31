package top.deepdesigner.weibo.weiboservicebackend.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com.cn
 * @Date: 2023/5/31 15:06
 * @Version: 1.0.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}