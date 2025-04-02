package com.ruoyi.device.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
测试类
*/
@RestController  //控制器
@RequestMapping("/device/test")//配置访问路径
@Anonymous//匿名访问
public class TContorller {
    /*
    * 测试接口
    * @return
    * */
    @RequestMapping("/list")
    public AjaxResult list(){
        return AjaxResult.success("测试接口");
    }
}
