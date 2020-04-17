package com.springbootDemo.controller;/**
 * Created by asbg on 2020/3/10.
 */


import com.alibaba.fastjson.JSONObject;
import com.springbootDemo.entity.Emp;
import com.springbootDemo.test.dao.EmpMapper;
import com.springbootDemo.test1.dao.EmpMapper1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Description：
 * Author: 程序猿学社
 * Date:  2020/3/7 12:15
 * Modified By:
 */
@RestController
@Api("测试多数据源接口")
public class EmpController {
    @Autowired
    private EmpMapper1 empMapper1;
    @Autowired
    private EmpMapper empMapper;

    @ApiOperation("测试mybatis@select注解，通过test1数据库实现")
    @GetMapping("/getKing1")
    public List getKing1(){
        List<Emp> emps = empMapper1.selectList();
        return emps;
    };


    @ApiOperation("测试mybatis@select注解，通过test数据库实现")
    @GetMapping("/getKing2")
    public List getKing2(){
        List<Emp> emps = empMapper.selectList();
        return emps;
    };

    @ApiOperation("测试mybatis的mapper.xml文件调用，通过test1数据库实现")
    @GetMapping("/getKing3")
    public List getKing3(){
        List<Emp> emps = empMapper1.getAll();
        return emps;
    };

    @ApiOperation("测试mybatis的mapper.xml文件调用，通过test数据库实现")
    @GetMapping("/getKing4")
    public List getKing4(){
        List<Emp> emps = empMapper.getAll();
        return emps;
    };

    @ApiOperation("通过mp调用test1数据库实现查询")
    @GetMapping("/getKing5")
    public List getKing5(){
        List<Emp> emps = empMapper1.selectList(null);
        return emps;
    };

    @ApiOperation("通过mp调用test数据库实现查询")
    @GetMapping("/getKing6")
    public List getKing6(){
        List<Emp> emps = empMapper.selectList(null);
        return emps;
    };

    @ApiOperation("测试插入数据")
    @PostMapping("/saveEmp1")
    @Transactional
    public String saveEmp(Emp emp) {
        int insert = empMapper1.insert(emp);
        if(insert > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    };

    @ApiOperation("测试给test1插入数据,增加指定某个事务的代码")
    @PostMapping("/saveEmp2")
    @Transactional(value = "test2TransactionManager")
    public String saveEmp2(Emp emp) {
        int insert = empMapper1.insert(emp);
        if(insert > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    };

    @ApiOperation("测试给test1插入数据,增加指定某个事务的代码,并故意在代码中报错")
    @PostMapping("/saveEmp3")
    @Transactional(value = "test2TransactionManager")
    public String saveEmp3(Emp emp) {
        int insert = empMapper1.insert(emp);
        //故意报错
        String str= null;
        System.out.println(str.toString());   //这里会报错
        if(insert > 0){
            return "插入成功";
        }else{
            return "插入失败";
        }
    };

    @GetMapping("/test")
    public JSONObject test(int id ) {
       return toJsons(id);
    };

    private static JSONObject toJsons(int  id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("title","test");
        jsonObject.put("expand",true);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id",111);
        jsonObject1.put("title","asdhaj");
        jsonObject1.put("pid",11);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id",22);
        jsonObject2.put("title","asdhaj");
        jsonObject2.put("pid",22);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("id",33);
        jsonObject3.put("title","asdhaj");
        jsonObject3.put("pid",33);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("id",11);
        jsonObject4.put("title","第一级菜单");
        jsonObject4.put("expand",true);
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("id",11);
        jsonObject5.put("title","第二级菜单");
        jsonObject5.put("expand",true);
        List list = new ArrayList<Object>();
        List list1 = new ArrayList<Object>();
        list.add(jsonObject1);
        list.add(jsonObject2);
        list.add(jsonObject3);
        list1.add(jsonObject4);
        list1.add(jsonObject5);
        jsonObject4.put("children",list);
        jsonObject.put("children",list1);
        return jsonObject;
    }
}
