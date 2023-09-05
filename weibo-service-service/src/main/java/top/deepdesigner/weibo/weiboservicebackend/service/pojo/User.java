/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/8/11 16:02
 * @Version: 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String age;

    private Integer gender;
    private String address;
}