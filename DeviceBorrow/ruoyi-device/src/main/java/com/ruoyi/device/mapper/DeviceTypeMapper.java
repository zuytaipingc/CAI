package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.DeviceType;

import java.util.List;

/*
*分类的数据访问接口
 */
public interface DeviceTypeMapper {
    /**
     * 设备分类的新增功能
     * @param deviceType 分类信息
     * @return
     */
    public int  insertDeviceType(DeviceType deviceType);

    /**
     * 修改设备类型
     * @param deviceType
     * @return 修改的行数
     */
    public int updateDeviceType(DeviceType deviceType);

    /**
     *
     * @param
     * @return
     */
    public List<DeviceType> selectDeviceTypeList();

    /**
     * 根据id查询设备类型
     * @param typeId 类型id
     * @return 设备类型
     */

    public DeviceType selectDeviceTypeId(Long typeId);

    /**
     * 根据id删除
     * 类型状态 type_status:-1 删除 0正常 1 禁用
     * 物理删除：从表中彻底删除数据：delete from device_type where type_id=1
     * 逻辑删除：修改表中类型状态的值为-1，updtae device_type_status=-1 where type_id=1
     * @param typeId 分类id
     * @return
     */
    public int deleteDeviceTypeById(Long typeId);
}
