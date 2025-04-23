package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.DeviceUserRegisterMapper;
import com.ruoyi.device.domain.DeviceUserRegister;
import com.ruoyi.device.service.IDeviceUserRegisterService;

/**
 * app用户Service业务层处理
 *
 * @author XiaoCai
 * @date 2025-04-23
 */
@Service
public class DeviceUserRegisterServiceImpl implements IDeviceUserRegisterService
{
    @Autowired
    private DeviceUserRegisterMapper deviceUserRegisterMapper;

    /**
     * 查询app用户
     *
     * @param regId app用户主键
     * @return app用户
     */
    @Override
    public DeviceUserRegister selectDeviceUserRegisterByRegId(Long regId)
    {
        return deviceUserRegisterMapper.selectDeviceUserRegisterByRegId(regId);
    }

    /**
     * 查询app用户列表
     *
     * @param deviceUserRegister app用户
     * @return app用户
     */
    @Override
    public List<DeviceUserRegister> selectDeviceUserRegisterList(DeviceUserRegister deviceUserRegister)
    {
        return deviceUserRegisterMapper.selectDeviceUserRegisterList(deviceUserRegister);
    }

    /**
     * 新增app用户
     *
     * @param deviceUserRegister app用户
     * @return 结果
     */
    @Override
    public int insertDeviceUserRegister(DeviceUserRegister deviceUserRegister)
    {
        deviceUserRegister.setCreateTime(DateUtils.getNowDate());
        return deviceUserRegisterMapper.insertDeviceUserRegister(deviceUserRegister);
    }

    /**
     * 修改app用户
     *
     * @param deviceUserRegister app用户
     * @return 结果
     */
    @Override
    public int updateDeviceUserRegister(DeviceUserRegister deviceUserRegister)
    {
        deviceUserRegister.setUpdateTime(DateUtils.getNowDate());
        return deviceUserRegisterMapper.updateDeviceUserRegister(deviceUserRegister);
    }

    /**
     * 批量删除app用户
     *
     * @param regIds 需要删除的app用户主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUserRegisterByRegIds(Long[] regIds)
    {
        return deviceUserRegisterMapper.deleteDeviceUserRegisterByRegIds(regIds);
    }

    /**
     * 删除app用户信息
     *
     * @param regId app用户主键
     * @return 结果
     */
    @Override
    public int deleteDeviceUserRegisterByRegId(Long regId)
    {
        return deviceUserRegisterMapper.deleteDeviceUserRegisterByRegId(regId);
    }
}
