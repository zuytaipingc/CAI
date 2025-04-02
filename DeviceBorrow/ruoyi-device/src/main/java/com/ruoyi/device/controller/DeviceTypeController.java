package com.ruoyi.device.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.device.domain.DeviceType;
import com.ruoyi.device.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//标记为控制器
@RequestMapping("/device/type")//访问路径
@Anonymous //匿名访问不鉴权注解
public class DeviceTypeController {
    //注入业务层对象
    @Autowired
    private IDeviceTypeService deviceTypeService;
    /**
     * 分类新增接口
     * @PostMapping("/add"):定义访问路径add，定义请求为post
     * 等价：//    @RequestMapping(value = "/add",method = RequestMethod.POST)
     * @RequestBody：用户提供请求体传递json形式数据时候，使用该注解接收，赋值给参数
     * url:http://localhost:8080/device/type/add
     * @param deviceType
     * @return
     */
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody DeviceType deviceType){
        //调用业务层处理数据
        int result = deviceTypeService.insertDeviceType(deviceType);
        //响应数据
        return AjaxResult.success("新增成功").put("data",result);
    }

    /**
     * 分类信息的修改接口
     * http://localhost:8080/device/type/edit
     * @param deviceType 分类信息
     * @return 响应
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody DeviceType deviceType){
        //调用业务层处理数据
        int result = deviceTypeService.updateDeviceType(deviceType);
        //响应
        return AjaxResult.success("修改成功").put("data",result);
    }


/**
 * 查询所有
 * @GetMapping("/list):定义访问路径/list,定义请求方法Get
 * @return 响应数据
 */
@GetMapping("/list")
    public AjaxResult list(){
    //调用业务层处理数据
    List<DeviceType> deviceTypeList = deviceTypeService.selectDeviceTypeList();
    //响应
    return AjaxResult.success("查询成功").put("data",deviceTypeList);
}

@GetMapping("/get")
    public AjaxResult get(Long typeId){
    //调用业务层获取数据
    DeviceType deviceType = deviceTypeService.selectDeviceTypeId(typeId);
    //响应
    return AjaxResult.success("查询成功").put("data",deviceType);
}

    /**
     * 根据id删除
     * @param typeId 分类id
     * @return 响应的数据
     */
    @GetMapping("/remove")
    public AjaxResult remove(Long typeId){
        //调用业务层的删除方法，处理数据
        int result = deviceTypeService.deleteDeviceTypeById(typeId);
        //响应
        return AjaxResult.success("删除成功").put("data",result);

    }
}
