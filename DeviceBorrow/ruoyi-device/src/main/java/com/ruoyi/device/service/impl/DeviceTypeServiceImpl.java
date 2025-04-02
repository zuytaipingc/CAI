package com.ruoyi.device.service.impl;

import com.ruoyi.device.domain.DeviceType;
import com.ruoyi.device.mapper.DeviceTypeMapper;
import com.ruoyi.device.service.IDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 分类业务层实现类
 */
@Service //标记为业务层
public class DeviceTypeServiceImpl implements IDeviceTypeService {
    //注入数据访问层对象
    //@Autowired //给属性赋值，Springboot框架提供的注解
    @Resource //给属性赋值，由Java提供的注解
    private DeviceTypeMapper deviceTypeMapper;

    /**
     * 新增
     * @param deviceType 分类信息
     * @return
     */
    @Override
    public int insertDeviceType(DeviceType deviceType) {
        //给新增时间赋值
        deviceType.setCreateTime(new Date());
        //调用数据访问层的新增方法，将数据新增到数据库
        return deviceTypeMapper.insertDeviceType(deviceType);
    }

    /**
     *
     * @param deviceType 设备类型
     * @return
     */
    @Override

    public  int updateDeviceType(DeviceType deviceType){
        //修改时间
        deviceType.setUpdateTime(new Date());
        //调用数据访问层
        return deviceTypeMapper.updateDeviceType(deviceType);

    }

    @Override
    public List<DeviceType> selectDeviceTypeList(){
        //调用数据访问层，获取数据
        return deviceTypeMapper.selectDeviceTypeList();
    }

    @Override
    public DeviceType selectDeviceTypeId(Long typeId) {
//        调用数据访问层，获取数据
        return deviceTypeMapper.selectDeviceTypeId(typeId);
    }

    @Override
    public int deleteDeviceTypeById(Long typeId) {
        return deviceTypeMapper.deleteDeviceTypeById(typeId);
    }
}
