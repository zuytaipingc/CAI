package com.ruoyi.device.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.device.mapper.DeviceBorrowMapper;
import com.ruoyi.device.domain.DeviceBorrow;
import com.ruoyi.device.service.IDeviceBorrowService;

/**
 * 借用信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class DeviceBorrowServiceImpl implements IDeviceBorrowService
{
    @Autowired
    private DeviceBorrowMapper deviceBorrowMapper;

    /**
     * 查询借用信息
     *
     * @param borrowId 借用信息主键
     * @return 借用信息
     */
    @Override
    public DeviceBorrow selectDeviceBorrowByBorrowId(Long borrowId)
    {
        return deviceBorrowMapper.selectDeviceBorrowByBorrowId(borrowId);
    }

    /**
     * 查询借用信息列表
     *
     * @param deviceBorrow 借用信息
     * @return 借用信息
     */
    @Override
    public List<DeviceBorrow> selectDeviceBorrowList(DeviceBorrow deviceBorrow)
    {
        return deviceBorrowMapper.selectDeviceBorrowList(deviceBorrow);
    }

    /**
     * 新增借用信息
     *
     * @param deviceBorrow 借用信息
     * @return 结果
     */
    @Override
    public int insertDeviceBorrow(DeviceBorrow deviceBorrow)
    {
        deviceBorrow.setCreateTime(DateUtils.getNowDate());
        return deviceBorrowMapper.insertDeviceBorrow(deviceBorrow);
    }

    /**
     * 修改借用信息
     *
     * @param deviceBorrow 借用信息
     * @return 结果
     */
    @Override
    public int updateDeviceBorrow(DeviceBorrow deviceBorrow)
    {
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
    public int deleteDeviceBorrowByBorrowIds(Long[] borrowIds)
    {
        return deviceBorrowMapper.deleteDeviceBorrowByBorrowIds(borrowIds);
    }

    /**
     * 删除借用信息信息
     *
     * @param borrowId 借用信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceBorrowByBorrowId(Long borrowId)
    {
        return deviceBorrowMapper.deleteDeviceBorrowByBorrowId(borrowId);
    }
}
