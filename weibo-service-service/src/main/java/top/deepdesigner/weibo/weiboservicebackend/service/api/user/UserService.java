/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.api.user;

import java.util.List;
import top.deepdesigner.weibo.weiboservicebackend.service.pojo.User;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 15:39
 * @Version: 1.0.0
 */
public interface UserService {

    void insert(User user);

    void insertByMapper(User user);

    List<User> select();
}