package com.ruoyi.device.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.device.domain.DeviceInfo;
import com.ruoyi.device.mapper.DeviceInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.DeviceBorrowMapper;
import com.ruoyi.device.domain.DeviceBorrow;
import com.ruoyi.device.service.IDeviceBorrowService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 借用信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class DeviceBorrowServiceImpl implements IDeviceBorrowService {
    @Autowired
    private DeviceBorrowMapper deviceBorrowMapper;

    /**
     * 查询借用信息
     *
     * @param borrowId 借用信息主键
     * @return 借用信息
     */
    @Override
    public DeviceBorrow selectDeviceBorrowByBorrowId(Long borrowId) {
        return deviceBorrowMapper.selectDeviceBorrowByBorrowId(borrowId);
    }

    /**
     * 查询借用信息列表
     *
     * @param deviceBorrow 借用信息
     * @return 借用信息
     */
    @Override
    public List<DeviceBorrow> selectDeviceBorrowList(DeviceBorrow deviceBorrow) {
        return deviceBorrowMapper.selectDeviceBorrowList(deviceBorrow);
    }

    //    注入设备的数据访问层对象
    @Resource
    private DeviceInfoMapper deviceInfoMapper;
    /**
     * 新增借用信息(快速借用)
     *
     * @param deviceBorrow 借用信息
     * @return 结果
     */
    @Override
    @Transactional//开启事务：保证 多条sql语句要么都执行成功，要么都失败
    public AjaxResult insertDeviceBorrow(DeviceBorrow deviceBorrow) {
//        根据设备id重新设置信息
        DeviceInfo info = deviceInfoMapper.selectDeviceInfoById(deviceBorrow.getDeviceId());
//        设备信息是否存在。设备状态是否是0
        if (info != null && info.getDeviceStatus() == 0) {

//        1、修改设备状态为1 （借出）
            info.setDeviceStatus(1); //修改设备状态为1
            //调用数据访问层方法，修改数据
            deviceInfoMapper.updateDeviceInfo(info);
//        2.新增借用信息
            deviceBorrow.setCreateTime(DateUtils.getNowDate());
            deviceBorrowMapper.insertDeviceBorrow(deviceBorrow);
            return AjaxResult.success("借用成功");
        }
        return AjaxResult.warn("借用失败，请联系管理员！");
    }

    /**
     * 修改借用信息
     *
     * @param deviceBorrow 借用信息
     * @return 结果
     */
    @Override
    @Transactional //开启事务，保证 多条sql语句要么都执行成功，要么都失败
    public int updateDeviceBorrow(DeviceBorrow deviceBorrow) {
//        修改设备状态
            if (deviceBorrow.getDeviceStatus()!=null){
                //创建设备对象
                DeviceInfo info = new DeviceInfo();
                //给主键赋值
                info.setDeviceId(deviceBorrow.getDeviceId());
                //给状态赋值
                info.setDeviceStatus(deviceBorrow.getDeviceStatus());
                //调用数据访问层方法，更新数据
                deviceInfoMapper.updateDeviceInfo(info);
            }
//        修改归还状态
        deviceBorrow.setUpdateTime(DateUtils.getNowDate());
        return deviceBorrowMapper.updateDeviceBorrow(deviceBorrow);
    }

    /**
     * 批量删除借用信息
     *
     * @param borrowIds 需要删除的借用信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceBorrowByBorrowIds(Long[] borrowIds) {
        return deviceBorrowMapper.deleteDeviceBorrowByBorrowIds(borrowIds);
    }

    /**
     * 删除借用信息信息
     *
     * @param borrowId 借用信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceBorrowByBorrowId(Long borrowId) {
        return deviceBorrowMapper.deleteDeviceBorrowByBorrowId(borrowId);
    }
}
