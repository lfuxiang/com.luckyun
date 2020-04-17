package com.springbootDemo.entity;/**
 * Created by asbg on 2020/3/10.
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;

/**
 * @ClassName Emp
 * @Description DoTo
 * @Author lfx
 * @Date
 * @Version V1.0
 **/
@Data
public class Emp {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
}