package com.ruoyi.device.service;

import com.ruoyi.device.domain.DeviceType;

import java.util.List;

/**
 * 分类的业务层接口
 */
public interface IDeviceTypeService {

    /**
     * 新增设备类型
     * @param deviceType 分类信息
     * @return 行数
     */
    public int insertDeviceType(DeviceType deviceType);

    /**
     * 修改设备分类
     * @param deviceType 分类信息
     * @return 修改的行数
     */

    public int updateDeviceType(DeviceType deviceType);

    /**
     *
     * @return
     */
    public List<DeviceType> selectDeviceTypeList();

    /**
     * 根据id查询
     * @param typeId
     * @return
     */

    public DeviceType selectDeviceTypeId(Long typeId);

    /**
     * 根据id删除 逻辑删除
     * @param typeId 分类id
     * @return 行数
     */

    public int deleteDeviceTypeById(Long typeId);
}

