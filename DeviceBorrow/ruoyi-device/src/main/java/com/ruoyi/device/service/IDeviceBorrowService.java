package com.ruoyi.device.service;

import java.util.List;
import com.ruoyi.device.domain.DeviceBorrow;

/**
 * 借用信息Service接口
 *
 * @author ruoyi
 * @date 2025-05-07
 */
public interface IDeviceBorrowService
{
    /**
     * 查询借用信息
     *
     * @param borrowId 借用信息主键
     * @return 借用信息
     */
    public DeviceBorrow selectDeviceBorrowByBorrowId(Long borrowId);

    /**
     * 查询借用信息列表
     *
     * @param deviceBorrow 借用信息
     * @return 借用信息集合
     */
    public List<DeviceBorrow> selectDeviceBorrowList(DeviceBorrow deviceBorrow);

    /**
     * 新增借用信息
     *
     * @param deviceBorrow 借用信息
     * @return 结果
     */
    public int insertDeviceBorrow(DeviceBorrow deviceBorrow);

    /**
     * 修改借用信息
     *
     * @param deviceBorrow 借用信息
     * @return 结果
     */
    public int updateDeviceBorrow(DeviceBorrow deviceBorrow);

    /**
     * 批量删除借用信息
     *
     * @param borrowIds 需要删除的借用信息主键集合
     * @return 结果
     */
    public int deleteDeviceBorrowByBorrowIds(Long[] borrowIds);

    /**
     * 删除借用信息信息
     *
     * @param borrowId 借用信息主键
     * @return 结果
     */
    public int deleteDeviceBorrowByBorrowId(Long borrowId);
}
