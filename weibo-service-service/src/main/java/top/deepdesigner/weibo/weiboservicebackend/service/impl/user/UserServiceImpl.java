/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.impl.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.deepdesigner.weibo.weiboservicebackend.service.api.user.UserService;
import top.deepdesigner.weibo.weiboservicebackend.service.mapper.UserMapper;
import top.deepdesigner.weibo.weiboservicebackend.service.pojo.User;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 15:39
 * @Version: 1.0.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public void insert(User user) {
        this.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void insertByMapper(User user) {
        userMapper.insertByMapper(user);
    }

    @Override
    public List<User> select() {
        return this.list(Wrappers.<User>lambdaQuery()
            .ge(User::getAge, 17));
    }
}

//public class DtsAccountTraceServiceImpl extends ServiceImpl<BaseMapper<DtsAccountTrace>, DtsAccountTrace> implements DtsAccountTraceService