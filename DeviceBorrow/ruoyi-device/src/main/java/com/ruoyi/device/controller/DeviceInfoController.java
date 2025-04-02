package com.ruoyi.device.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备信息控制器
 * BaseController:若依提供的基础类，封装了功能（例如分页）
 */
@RestController //标记为控制器，数据以json的格式响应到客户端
@RequestMapping("/device/info")//请求路径
@Anonymous //支持匿名访问
public class DeviceInfoController extends BaseController {


    //注入业务层对象
    @Autowired
    private IDeviceInfoService deviceInfoService;

    /**
     * 设备信息的新增接口
     * 请求方式POST，路径：http://localhost:8080/device/info
     * @param deviceInfo 设备信息
     * @return 响应
     */
    @PostMapping
    public AjaxResult add(@RequestBody DeviceInfo deviceInfo){
//        调用业务层处理数据
        int result = deviceInfoService.insertDeviceInfo(deviceInfo);
        //响应
        return  success("新增成功").put("data",result);
    }

    /**
     * 设备信息的修改接口
     * 请求方式POST，路径：http://localhost:8080/device/info
     * @param deviceInfo 设备信息
     * @return 响应
     */
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceInfo deviceInfo){
//        调用业务层处理数据
        int result = deviceInfoService.updateDeviceInfo(deviceInfo);
        //响应
        return success("修改成功").put("data",result);
    }

    /**
     * 设备信息的多条件查询接口
     * 请求方式GeT，路径：http://localhost:8080/device/info/list
     * @param deviceInfo  查询条件
     * @return 响应数据
     */
    @GetMapping("/list")
    public TableDataInfo list(DeviceInfo deviceInfo){
//        开启分页查询：由ruoyi进行处理
            startPage();
            //调用业务层，进行多条件查询
        List<DeviceInfo> deviceInfos = deviceInfoService.selectDeviceInfo(deviceInfo);
        //响应
        return getDataTable(deviceInfos);
    }

    /**
     * 根据id查询的接口
     * 请求方法GET 路径：http://localhost:8080/device/info/1={deviceId}
     * 传统的路径：http://localhost:8080/device/info?deviceId=1
     * @PathVariable("deviceId") :获取请求路径上的数据，赋给deviceId
     * @param deviceId
     * @return
     */
    @GetMapping("{deviceId}")
    public AjaxResult get(@PathVariable("deviceId") Long deviceId){
        //调用业务层数据方法，获取数据
        DeviceInfo deviceInfo = deviceInfoService.selectDeviceInfoById(deviceId);
        //响应
        return success("查询成功").put("data",deviceInfo);

    }

    /**
     * 根据id删除的接口
     * @param deviceId 设备id
     * @return 响应的数据
     */
    @DeleteMapping("{deviceId}")
    public AjaxResult remove(@PathVariable("deviceId") Long deviceId){
//        调用业务层处理数据
        return deviceInfoService.deleteDeviceInfo(deviceId);
    }

    /**
     * 导出excel接口
     * 请求方法POST 路径：http://localhost:8080/device/info/export
     * @param response 响应对象
     * @param deviceInfo 查询条件
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response,DeviceInfo deviceInfo){
        //多条件查询需要导出的数据
        List<DeviceInfo> deviceInfos = deviceInfoService.selectDeviceInfo(deviceInfo);
        //创建导出的工具类对象
        ExcelUtil<DeviceInfo> excelUtil = new ExcelUtil<>(DeviceInfo.class);
        //调用导出方法
        excelUtil.exportExcel(response,deviceInfos,"设备信息-1","设备信息-2");
    }

}
