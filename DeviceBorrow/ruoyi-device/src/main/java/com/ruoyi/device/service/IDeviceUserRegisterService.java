package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.DeviceUserRegister;

/**
 * app用户Service接口
 *
 * @author XiaoCai
 * @date 2025-04-23
 */
public interface IDeviceUserRegisterService
{
    /**
     * 查询app用户
     *
     * @param regId app用户主键
     * @return app用户
     */
    public DeviceUserRegister selectDeviceUserRegisterByRegId(Long regId);

    /**
     * 查询app用户列表
     *
     * @param deviceUserRegister app用户
     * @return app用户集合
     */
    public List<DeviceUserRegister> selectDeviceUserRegisterList(DeviceUserRegister deviceUserRegister);

    /**
     * 新增app用户
     *
     * @param deviceUserRegister app用户
     * @return 结果
     */
    public int insertDeviceUserRegister(DeviceUserRegister deviceUserRegister);

    /**
     * 修改app用户
     *
     * @param deviceUserRegister app用户
     * @return 结果
     */
    public int updateDeviceUserRegister(DeviceUserRegister deviceUserRegister);

    /**
     * 批量删除app用户
     *
     * @param regIds 需要删除的app用户主键集合
     * @return 结果
     */
    public int deleteDeviceUserRegisterByRegIds(Long[] regIds);

    /**
     * 删除app用户信息
     *
     * @param regId app用户主键
     * @return 结果
     */
    public int deleteDeviceUserRegisterByRegId(Long regId);
}
