/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.deepdesigner.weibo.weiboservicebackend.service.pojo.User;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/4 17:36
 * @Version: 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    void insertByMapper(@Param("user") User user);
}