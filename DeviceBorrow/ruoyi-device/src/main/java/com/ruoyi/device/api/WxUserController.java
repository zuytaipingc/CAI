package com.ruoyi.device.api;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.device.domain.DeviceUserRegister;
import com.ruoyi.device.service.IDeviceUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 小程序用户控制器：注册接口、登录接口
 */
@RestController//标记未控制器
@RequestMapping("/wechart")
@Anonymous//匿名访问
public class WxUserController extends BaseController {
    //    注入业务层
    @Autowired
    private IDeviceUserRegisterService service;

    /**
     * 小程序用户注册接口
     * 请求方法POST 路径http://localhost:8080/wechat/register
     *
     * @param userRegister
     * @return
     */

    @PostMapping("/register")
    public AjaxResult register(@RequestBody DeviceUserRegister userRegister) {
        //验证学号/工号是否一唯一被注册
//        根据学号/工号进行查询
        List<DeviceUserRegister> list = service.selectDeviceUserRegisterByCode(userRegister.getRegCode());
        //判断学号/工号是否被使用
        if (list != null && list.size()>0){
            return AjaxResult.warn("注册失败,学号/工号被注册！");
        }else {//没有被注册
            //密码：加密
            String encryptPassword = SecurityUtils.encryptPassword(userRegister.getRegPassword());//加密后的密码
            //替换密码
            userRegister.setRegPassword(encryptPassword);
            //调用业务层，实现新增
            int result = service.insertDeviceUserRegister(userRegister);
            return result ==1?AjaxResult.success("注册成功!"):AjaxResult.warn("注册失败!");

        }
    }

    /**
     * 小程序用户登录
     * 请求方法POST 路径http://localhost:8080/wechat/login
     * @param code 微信登录密码
     * @param userName 工号/学号
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(String code,String userName,String password){
        //根据工号/学号查询->没有数据、多条数据，一条数据->验证注册状态->验证密码->登录成功
        List<DeviceUserRegister> list = service.selectDeviceUserRegisterByCode(userName);
        //判断list集合
        if (list==null || list.size()==0){
            return AjaxResult.warn("登录失败，工号/学号输入错误！");
        }else if (list.size()>1){
            return AjaxResult.warn("登录失败，工号/学号存在多个，联系管理员！");

        }else {
            //取出登录的用户信息
            DeviceUserRegister loginUser = list.get(0);
            //验证注册状态
            if (loginUser.getRegStatus()==0){
                return AjaxResult.warn("登录失败,还未审核，联系管理员");
            } else if (loginUser.getRegStatus()==2) {
                return AjaxResult.warn("登录失败,审核未通过，联系管理员");

            } else if (SecurityUtils.matchesPassword(password,loginUser.getRegPassword())) {
                //验证密码：密码输入正确、登陆成功
                loginUser.setRegPassword("");//清空密码：数据脱敏
                return AjaxResult.success("登录成功！",loginUser);
            }else {
                return AjaxResult.warn("登录失败，密码输入错误！");
            }
        }

    }
}
