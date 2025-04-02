package com.ruoyi.device.domain;

import java.util.Date;

/**
 * 设备类型的实体类
 */

public class DeviceType {
    /*
    * 主键 自增
    * */

    private long typeId;
    /*
    类型名称
     */
    private  String typeName;

    /*
    类型说明
     */
    private String typeRemark;
    /*
    类型状态：-1删除，0正常，1使用
     */
    private Integer typeStatus;

    /*
    创建者
     */
    private  long createBy;
    /*
    创建时间
     */
    private Date createTime;
    /*
    更新者
     */
    private long updateBy;

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeRemark() {
        return typeRemark;
    }

    public void setTypeRemark(String typeRemark) {
        this.typeRemark = typeRemark;
    }

    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /*
        更新时间
         */
    private Date updateTime;

    //生成无参的构造方法

    public DeviceType() {

    }

    //生成有参的构造方法

    public DeviceType(long typeId, String typeName, String typeRemark,
                      Integer typeStatus, long createBy, Date createTime,
                      long updateBy, Date updateTime) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeRemark = typeRemark;
        this.typeStatus = typeStatus;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }
}
