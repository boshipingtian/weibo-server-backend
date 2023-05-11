package top.deepdesigner.weibo.weiboservicebackend.demos.web;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicControllerTest {

    @Resource
    BasicController basicController;

    @Test
    void hello() {
        String hello = basicController.hello("hello");
        System.out.println("hello = " + hello);
    }
}