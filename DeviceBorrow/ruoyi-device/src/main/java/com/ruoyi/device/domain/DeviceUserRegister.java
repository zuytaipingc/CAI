package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * app用户对象 device_user_register
 *
 * @author XiaoCai
 * @date 2025-04-23
 */
public class DeviceUserRegister extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long regId;

    /**
     * 归属部门id
     */
    private Long regDeptId;

    /**
     * 部门/班级
     */
    @Excel(name = "部门/班级")
    private String regDept;

    /**
     * 工号/学号
     */
    @Excel(name = "工号/学号")
    private String regCode;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String regName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String regMobile;

    /**
     * 登录密码
     */
    private String regPassword;

    /**
     * 小程序openid
     */
    private String regOpenid;

    /**
     * 注册状态，0待审核，1审核通过，2审核不通过
     */
    @Excel(name = "注册状态", readConverterExp = "0待审核，1=审核通过，2=审核不通过")
    private Integer regStatus;

    /**
     * 审核说明
     */
    @Excel(name = "审核说明")
    private String regRemark;
    /**
     * 审核人:用户列表页面的数据展示
     */
    private String approveName;
    //生成getter和setter方法


    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public Long getRegId() {
        return regId;
    }

    public void setRegDeptId(Long regDeptId) {
        this.regDeptId = regDeptId;
    }

    public Long getRegDeptId() {
        return regDeptId;
    }

    public void setRegDept(String regDept) {
        this.regDept = regDept;
    }

    public String getRegDept() {
        return regDept;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegMobile(String regMobile) {
        this.regMobile = regMobile;
    }

    public String getRegMobile() {
        return regMobile;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegOpenid(String regOpenid) {
        this.regOpenid = regOpenid;
    }

    public String getRegOpenid() {
        return regOpenid;
    }

    public void setRegStatus(Integer regStatus) {
        this.regStatus = regStatus;
    }

    public Integer getRegStatus() {
        return regStatus;
    }

    public void setRegRemark(String regRemark) {
        this.regRemark = regRemark;
    }

    public String getRegRemark() {
        return regRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("regId", getRegId())
                .append("regDeptId", getRegDeptId())
                .append("regDept", getRegDept())
                .append("regCode", getRegCode())
                .append("regName", getRegName())
                .append("regMobile", getRegMobile())
                .append("regPassword", getRegPassword())
                .append("regOpenid", getRegOpenid())
                .append("regStatus", getRegStatus())
                .append("regRemark", getRegRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
