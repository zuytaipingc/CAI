package com.ruoyi.device.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 借用信息对象 device_borrow
 *
 * @author ruoyi
 * @date 2025-05-07
 */
public class DeviceBorrow extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long borrowId;

    /**
     * 设备id
     */
    @Excel(name = "设备id")
    private Long deviceId;

    /**
     * 借用用户id
     */
    @Excel(name = "借用用户id")
    private Long userId;

    /**
     * 借用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date borrowTime;

    /**
     * 借用说明
     */
    @Excel(name = "借用说明")
    private String borrowRemark;

    /**
     * 计划归还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /**
     * 归还状态，0申请借用，1未归还，2申请归还，3已归还
     */
    @Excel(name = "归还状态", readConverterExp = "0=申请借用,1=未归还,2=申请归还,3=已归还")
    private Integer returnStatus;

    /**
     * 归还说明
     */
    @Excel(name = "归还说明")
    private String returnRemark;
    /**
     * 用户信息
     */
    @Excels({
            @Excel(name = "学号/工号", targetAttr = "regCode", type = Excel.Type.EXPORT),
            @Excel(name = "姓名", targetAttr = "regName", type = Excel.Type.EXPORT),
            @Excel(name = "班级", targetAttr = "regDept", type = Excel.Type.EXPORT),
    })

    private DeviceUserRegister user;

    /**
     * 设备信息
     */

    @Excels({
            @Excel(name = "设备名称", targetAttr = "deviceName", type = Excel.Type.EXPORT),
            @Excel(name = "设备型号", targetAttr = "deviceModel", type = Excel.Type.EXPORT),
    })
    private DeviceInfo device;
    /**
     * 借用时间：开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginBorrowTime;
    /**
     * 借用时间：结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endBorrowTime;
    /**
     * 设备状态：用于接受页面的数据
     */
    private Integer deviceStatus;


//    生成getter和setter方法


    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public DeviceUserRegister getUser() {
        return user;
    }

    public void setUser(DeviceUserRegister user) {
        this.user = user;
    }

    public DeviceInfo getDevice() {
        return device;
    }

    public void setDevice(DeviceInfo device) {
        this.device = device;
    }

    public Date getBeginBorrowTime() {
        return beginBorrowTime;
    }

    public void setBeginBorrowTime(Date beginBorrowTime) {
        this.beginBorrowTime = beginBorrowTime;
    }

    public Date getEndBorrowTime() {
        return endBorrowTime;
    }

    public void setEndBorrowTime(Date endBorrowTime) {
        this.endBorrowTime = endBorrowTime;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowRemark(String borrowRemark) {
        this.borrowRemark = borrowRemark;
    }

    public String getBorrowRemark() {
        return borrowRemark;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("borrowId", getBorrowId()).append("deviceId", getDeviceId()).append("userId", getUserId()).append("borrowTime", getBorrowTime()).append("borrowRemark", getBorrowRemark()).append("returnTime", getReturnTime()).append("returnStatus", getReturnStatus()).append("returnRemark", getReturnRemark()).append("createBy", getCreateBy()).append("createTime", getCreateTime()).append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).toString();
    }
}
