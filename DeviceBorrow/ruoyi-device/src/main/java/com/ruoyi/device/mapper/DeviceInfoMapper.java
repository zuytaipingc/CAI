package com.ruoyi.device.mapper;


import com.ruoyi.device.domain.DeviceInfo;

import java.util.List;

/**
 * 设备信息数据访问层接口
 */
public interface DeviceInfoMapper {
    /**
     * 设备信息的新增
     * @param deviceinfo 设备信息
     * @return 行数
     */
    public int insertDeviceInfo(DeviceInfo deviceinfo);

    /**
     * 设备信息的修改
     * @param deviceInfo
     * @return
     */
    public int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 多条件查询  查询条件：分类、名称、型号、状态
     * @param deviceInfo 查询条件
     * @return
     */
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo);

    /**
     * 根据id查询设备信息
     * @param deviceId 设备id
     * @return 设备信息
     */
    public DeviceInfo selectDeviceInfoById(Long deviceId);

    /**
     * 根据id删除数据：物理删除
     * @param deviceId 设备id
     * @return 设备信息
     */
    public int deleteDeviceInfo(Long deviceId);

}
