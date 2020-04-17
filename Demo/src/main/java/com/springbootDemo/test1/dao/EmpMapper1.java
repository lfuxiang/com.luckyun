package com.springbootDemo.test1.dao;/**
 * Created by asbg on 2020/3/10.
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springbootDemo.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName EmpMapper
 * @Description DoTo
 * @Author lfx
 * @Date
 * @Version V1.0
 **/
@Mapper
@Repository
public interface EmpMapper1 extends BaseMapper<Emp> {
    @Select("select * from emp")
    public List<Emp> selectList();

    /**
     * 测试mapper
     * @return
     */
    public List<Emp> getAll();
}