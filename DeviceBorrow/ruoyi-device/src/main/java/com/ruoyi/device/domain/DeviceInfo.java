package com.ruoyi.device.domain;

import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * 设备信息的实体类
 */
public class DeviceInfo {
    /**
     * 主键
     */
    private Long deviceId;
    /**
     * 设备分类的id
     */
    private Long typeId;
    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    private String deviceName;
    /**
     * 设备型号
     */
    @Excel(name = "设备型号")

    private String deviceModel;
    /**
     * 设备照片
     */
    private String deviceImg;
    /**
     * 设备说明
     */
    @Excel(name = "设备说明")

    private String deviceRemark;
    /**
     * 设备状态，-1废弃，0正常，1借出，2维修
     */
    @Excel(name = "设备状态",readConverterExp = "-1=废弃,0=正常,1=借出,2=维修")

    private Integer deviceStatus;
    /**
     * 创建者id
     */
    private Long createBy;
    /**
     * 创建时间
     */
    @Excel(name = "设备时间",dateFormat = "yyyy-MM-dd")

    private Date createTime;
    /**
     * 更新者id
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    private Date updateTime;
    /**
     * 分类名称：在列表页面中展示数据
     */
    @Excel(name = "分类名称")

    private String typeName;

    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
//生成getter，setter方法

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceImg() {
        return deviceImg;
    }

    public void setDeviceImg(String deviceImg) {
        this.deviceImg = deviceImg;
    }

    public String getDeviceRemark() {
        return deviceRemark;
    }

    public void setDeviceRemark(String deviceRemark) {
        this.deviceRemark = deviceRemark;
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

//    生成无参构造方法

    public DeviceInfo() {
    }


//    生成有参构造方法


    public DeviceInfo(Long deviceId, Long typeId, String deviceName, String deviceModel,
                      String deviceImg, String deviceRemark, Integer deviceStatus, Long createBy,
                      Date createTime, Long updateBy, Date updateTime) {
        this.deviceId = deviceId;
        this.typeId = typeId;
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.deviceImg = deviceImg;
        this.deviceRemark = deviceRemark;
        this.deviceStatus = deviceStatus;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }
}

