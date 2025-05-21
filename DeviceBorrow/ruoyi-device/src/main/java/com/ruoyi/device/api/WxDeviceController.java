package com.ruoyi.device.api;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.device.domain.DeviceBorrow;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.domain.DeviceType;
import com.ruoyi.device.service.IDeviceBorrowService;
import com.ruoyi.device.service.IDeviceInfoService;
import com.ruoyi.device.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 小程序接口控制器
 */
@RestController //标记为控制器
@RequestMapping("/wechart/device") //路径
@Anonymous  //支持匿名访问
public class WxDeviceController extends BaseController {
    //注入设备分类业务层对象
    @Autowired
    private IDeviceTypeService deviceTypeService;

    /**
     * 获取设备分类列表的接口
     * 请求方法：Get 路径：http://localhost:8080/wechart/device/typeList
     * @return
     */
    @GetMapping("typeList")
    public AjaxResult typeList(){
//        调用业务层：获取数据
        List<DeviceType> list = deviceTypeService.selectDeviceTypeList();
        //响应
        return AjaxResult.success("查询成功",list);

    }
    //注入设备业务层对象
    @Autowired
    private IDeviceInfoService deviceInfoService;

    /**
     * 获取设备信息列表的接口
     * http://localhost:8080/wechart/device/deviceList
     * @param typeId
     * @param deviceName
     * @param deviceStatus
     * @return
     */
    @GetMapping("/deviceList")
    public AjaxResult deviceList(Long typeId,String deviceName,Integer deviceStatus){
//        创建设备对象
        DeviceInfo info = new DeviceInfo();
        //封装数据
        if (typeId != null) {
            info.setTypeId(typeId);
        }
        if (StringUtils.isNotBlank(deviceName)) {
            info.setDeviceName(deviceName);

        }
        if (deviceStatus != null) {
            info.setDeviceStatus(deviceStatus);
        }
        //调用业务层方法，获取数据
        List<DeviceInfo> list = deviceInfoService.selectDeviceInfo(info);
        //响应
        return AjaxResult.success("查询成功",list);
    }


    /**
     * 获取设备详情的接口
     * @param deviceId 设备id
     * @return
     */
    @GetMapping("/deviceInfo")
    public AjaxResult deviceInfo(Long deviceId){
//        调用业务层方法，获取数据
        DeviceInfo deviceInfo = deviceInfoService.selectDeviceInfoById(deviceId);
        //响应
        return AjaxResult.success("查询成功",deviceInfo);
    }

    //注入借用业务层对象
    @Autowired
    private IDeviceBorrowService deviceBorrowService;

    /**
     * 获取借用列表的接口
     * http://localhost:8080/wechart/device/borrowList
     * @param returnStatus 归还状态
     * @param useId 小程序用户的id
     * @return
     */
    @GetMapping("/borrowList")
    public AjaxResult borrowList(Integer returnStatus,Long useId){
        //创建对象
        DeviceBorrow deviceBorrow = new DeviceBorrow();
        //封装数据
        if (returnStatus != null) {
            deviceBorrow.setReturnStatus(returnStatus);
        }
        if (useId != null) {
            deviceBorrow.setUserId(useId);
        }
        //调用业务层获取数据
        List<DeviceBorrow> list = deviceBorrowService.selectDeviceBorrowList(deviceBorrow);
        //响应
        return AjaxResult.success("查询成功",list);
    }

    /**
     * 设备借用(小程序用户申请借用)接口
     * Post:http://localhost:8080/wechart/device/borrowDevice
     * @param deviceBorrow
     * @return
     */
    @PostMapping("/borrowDevice")
    public AjaxResult borrowDevice(@RequestBody DeviceBorrow deviceBorrow){
        //设置归还状态0:申请借用
        deviceBorrow.setReturnStatus(0);
        //设置借用时间:使用当前系统时间
        deviceBorrow.setBorrowTime(new Date());
        //调用业务层，实现借用
        return deviceBorrowService.insertDeviceBorrow(deviceBorrow);
    }

    /**
     * 设备归还（小程序用户申请归还）接口
     * 访问路径：Post:http://localhost:8080/wechart/device/returnBorrow
     * @param deviceBorrow
     * @return
     */
    @PostMapping("returnBorrow")
    public AjaxResult returnBorrow(@RequestBody DeviceBorrow deviceBorrow){
//        设置归还状态2：申请归还
        deviceBorrow.setReturnStatus(2);
        //调用业务层，实现申请归还
        int result = deviceBorrowService.updateDeviceBorrow(deviceBorrow);
        //响应
        return AjaxResult.success("申请归还成功",result);
    }
}
