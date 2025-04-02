package com.ruoyi.device.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.mapper.DeviceInfoMapper;
import com.ruoyi.device.service.IDeviceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 设备信息业务层实现类
 */
@Service //标记为业务层
public class DeviceInfoServiceImpl implements IDeviceInfoService {

    //        注入数据访问层对象
    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo) {
        //给创建时间赋值
        deviceInfo.setCreateTime(new Date());
        //调用数据访问层方法，实现新增
        int result = deviceInfoMapper.insertDeviceInfo(deviceInfo);
        //测试获取新增的id值
        System.out.println("新增的id值:" + deviceInfo.getDeviceId());

        return result;
    }

    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) {
//        修改更新时间赋值
        deviceInfo.setUpdateTime(new Date());
        //调用数据访问层，修改数据
        return deviceInfoMapper.updateDeviceInfo(deviceInfo);
    }

    @Override
    public List<DeviceInfo> selectDeviceInfo(DeviceInfo deviceInfo) {
        //调用数据访问层获取数据
        return deviceInfoMapper.selectDeviceInfoList(deviceInfo);

    }

    @Override
    public DeviceInfo selectDeviceInfoById(Long deviceId) {
        //调用数据访问层的方法，获取数据
        return deviceInfoMapper.selectDeviceInfoById(deviceId);
    }

    @Override
    public AjaxResult deleteDeviceInfo(Long deviceId) {
//        状态为借出 不能删除数据、不是借出则可以删除
//        获取状态值：根据id查询设备信息
        DeviceInfo deviceInfo = deviceInfoMapper.selectDeviceInfoById(deviceId);
//        如果设备信息不存在
        if ( deviceInfo == null){
            return AjaxResult.warn("该设备信息不存在");
        }
        //判断状态值
        if ( deviceInfo.getDeviceStatus()==1){
//            状态为借出(1)，不能删除数据
            return AjaxResult.warn("删除失败，该设备被借出不能删除!");
        }else {
            //不是借出则可以删除：调用数据访问层的删除方法，进行删除操作
            int result = deviceInfoMapper.deleteDeviceInfo(deviceId);
            //result：删除的行数，1是正常删除，其它删除失败
            return result==1?AjaxResult.success("删除成功"):AjaxResult.error();
        }


    }
}
