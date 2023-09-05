/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.deepdesigner.weibo.weiboservicebackend.service.api.user.UserService;
import top.deepdesigner.weibo.weiboservicebackend.service.pojo.User;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 15:36
 * @Version: 1.0.0
 */
@RestController
@Api(value = "用户", tags = "用户")
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/insert")
    public Object insertUser(@RequestBody User user) {
        userService.insert(user);
        return Boolean.TRUE;
    }

    @ApiOperation(value = "添加用户 mapper")
    @PostMapping("/insert/mapper")
    public Object insertUserByMapper(@RequestBody User user) {
        userService.insertByMapper(user);
        return Boolean.TRUE;
    }

    @ApiOperation(value = "查询用户")
    @GetMapping("/select")
    public List<User> selectUser() {
        return userService.select();
    }


}