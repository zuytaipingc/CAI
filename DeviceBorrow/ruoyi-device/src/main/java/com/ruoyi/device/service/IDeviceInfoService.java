package com.ruoyi.device.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.device.domain.DeviceInfo;

import java.util.List;

public interface IDeviceInfoService {
    /**
     * 设备信息的新增
     * @param deviceinfo
     * @return 行数
     */
    public int insertDeviceInfo(DeviceInfo deviceinfo);

    /**
     *设备信息的修改
     * @param deviceInfo 设备信息
     * @return 行数
     */
    public int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 多条件查询 查询条件：分类、名称、型号、状态
     * @param deviceInfo
     * @return 设备信息列表
     */
    public List<DeviceInfo> selectDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 根据id查询设备信息
     * @param deviceId 设备id
     * @return 设备信息
     */
    public DeviceInfo selectDeviceInfoById(Long deviceId);

    /**
     * 根据id删除数据：物理删除
     *
     * 问题：设备处于借出状态时，不能删除
     * @param deviceId 设备id
     * @return 响应的数据
     */
    public AjaxResult deleteDeviceInfo(Long deviceId);
}
