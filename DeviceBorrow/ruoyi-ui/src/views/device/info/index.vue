<template>
    <div class="container">
        <h1>设备信息</h1>
        <!-- 搜索：表单：条件：设备名称 设备类型 设备状态 分类id -->
        <el-form :inline="true" :model="deviceInfo" class="demo-form-inline">
            <el-form-item label="名称">
                <el-input v-model="deviceInfo.deviceName" placeholder="名称" clearable></el-input>
            </el-form-item>
            <el-form-item label="型号">
                <el-input v-model="deviceInfo.deviceModel" placeholder="型号" clearable></el-input>
            </el-form-item>
            <el-form-item label="状态">
                <el-select v-model="deviceInfo.deviceStatus" placeholder="状态" clearable>
                    <el-option label="正常" :value="0"></el-option>
                    <el-option label="借出" :value="1"></el-option>
                    <el-option label="维修" :value="2"></el-option>
                    <el-option label="废弃" :value="-1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="分类">
                <el-select v-model="deviceInfo.typeId" placeholder="分类" clearable>
                    <el-option v-for="type in deviceTypeList" :label="type.typeName" :value="type.typeId"
                        :key="type.typeId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="dialogVisible = true, title = '新增'">新增</el-button>
                <el-button type="primary" @click="exportDeviceInfo">导出</el-button>


            </el-form-item>
        </el-form>

        <!-- 展示数据 -->
        <el-table :data="deviceInfoList" border style="width: 100%">
            <el-table-column fixed prop="deviceId" label="设备id" width="50">
            </el-table-column>
            <el-table-column prop="deviceName" label="名称" width="150">
            </el-table-column>
            <el-table-column prop="deviceModel" label="型号" width="150">
            </el-table-column>
            <el-table-column prop="deviceImg" label="图片" width="150">
                <template slot-scope="scope">
                    <image-preview :src="scope.row.deviceImg" :width="50" :height="50"></image-preview>
                </template>
            </el-table-column>
            <el-table-column prop="deviceRemark" label="说明">
            </el-table-column>
            <el-table-column prop="typeStatus" label="状态" width="150">
                <template slot-scope="scope">
                    <span v-if="scope.row.deviceStatus == 0" style="color: green;">正常</span>
                    <span v-if="scope.row.deviceStatus == 1" style="color: green;">借出</span>

                    <span v-if="scope.row.deviceStatus == 2" style="color: orange;">维修</span>
                    <span v-if="scope.row.deviceStatus == -1" style="color: red;">废弃</span>
                </template>
            </el-table-column>
            <el-table-column prop="typeName" label="分类名称">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="200">
                <template slot-scope="scope">
                    <el-button @click="showDeviceInfo(scope.row.deviceId)" type="primary" size="small">修改</el-button>
                    <el-button @click="deleteDeviceInfo(scope.row.deviceId)" type="danger" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <div class="block">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="deviceInfo.pageNum" :page-sizes="[1, 2, 5, 10, 50, 100]" :page-size="deviceInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
        <!-- 添加新增、修改的对话框 -->
        <el-dialog :title="title" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="设备名称" prop="typeId">
                    <el-select v-model="ruleForm.typeId" placeholder="分类" clearable>
                        <el-option v-for="type in deviceTypeList" :label="type.typeName" :value="type.typeId"
                            :key="type.typeId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="设备名称" prop="deviceName">
                    <el-input v-model="ruleForm.deviceName">
                    </el-input>
                </el-form-item>
                <el-form-item label="设备型号" prop="deviceModel">
                    <el-input v-model="ruleForm.deviceModel">
                    </el-input>
                </el-form-item>
                <el-form-item label="设备图片" prop="deviceImg">
                    <image-upload v-model="ruleForm.deviceImg" :limit="1">
                    </image-upload>
                </el-form-item>
                <el-form-item label="设备状态">
                    <el-select v-model="ruleForm.deviceStatus" placeholder="状态" clearable>
                        <el-option label="正常" :value="0"></el-option>
                        <el-option label="借出" :value="1"></el-option>
                        <el-option label="维修" :value="2"></el-option>
                        <el-option label="废弃" :value="-1"></el-option>
                    </el-select>

                </el-form-item>
                <el-form-item label="设备说明" prop="deviceRemark">
                    <el-input type="textarea" v-model="ruleForm.deviceRemark"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">{{ title }}</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

    </div>
</template>

<script>
import axios from 'axios';
import { getInstanceByDom } from 'echarts';

export default {
    name: 'index.vue',
    data() {
        return {
            title: "新增",//新增对话框的标题
            dialogVisible: false,//控制新增对话框的显示(true)和隐藏(false)
            deviceInfoList: [],//设备信息列表
            deviceInfo: {
                //搜索表单对象
                deviceName: "",
                deviceModel: "",
                deviceStatus: null,
                typeId: null,
                pageNum: 1,//当前页，默认第一页
                pageSize: 5,//每页显示的记录数，默认为5
            },
            total: 0,//总记录数
            deviceTypeList: [],//分类列表
            ruleForm: {},//新增表单对象
            rules:
            {
                deviceName: [{ required: true, message: '请输入设备名称', trigger: 'blur' },],
                deviceStatus: [{ required: true, message: '请输入设备状态', trigger: 'blur' },],
                typeId: [{ required: true, message: '请输入设备分类', trigger: 'blur' },],


            }

        }
    },
    methods: { //定义方法、函数
        exportDeviceInfo() {
            //导出excel，调用后端接口
            axios.post("http://localhost:8080/device/info/export", this.deviceInfo, { responseType: "blob" })//设置响应类型为blob二进制类型，用于文件下载
                .then(response => {
                    //创建url对象
                    const url = window.URL.createObjectURL(new Blob([response.data]))
                    //创建a标签
                    const link = document.createElement("a")
                    //设置链接
                    link.href = url
                    //设置下载属性
                    link.setAttribute("download", "设备信息.xlsx")
                    //将a标签添加到页面中
                    document.body.append(link)
                    //模拟点击
                    link.click()
                    //移除a标签
                    document.body.removeChild(link)
                    //移除url
                    window.URL.revokeObjectURL(url)
                }).catch(err => {
                    console.log(err);

                })

        },
        deleteDeviceInfo(deviceId) {
            //调用删除接口 实现删除操作
            if (confirm("确定删除该条数据吗？")) {
                axios.delete("http://localhost:8080/device/info/" + deviceId)
                    .then(response => {
                        alert(response.data.msg)
                        this.getData()//刷新数据
                    }).catch(err => {
                        console.log(err);

                    })
            }
        },
        showDeviceInfo(deviceId) {
            //调用后端接口，回显数据
            axios.get("http://localhost:8080/device/info/" + deviceId)
                .then(response => {
                    this.ruleForm = response.data.data//给表单对象赋值
                    this.dialogVisible = true // 显示弹框
                    this.title = "修改"  //修改title
                }).catch(err => {
                    console.log(err);

                })

        },
        submitForm(formName) {//新增、修改表单提交函数
            this.$refs[formName].validate((valid) => {//表单验证
                if (valid) {
                    // 验证通过：新增、修改操作
                    // 判断是新增或者是修改操作：deviceId是否有值
                    if (this.ruleForm.deviceId == null) {
                        //新增操作：调用后端新增接口，实现数据的新增
                        axios.post("http://localhost:8080/device/info", this.ruleForm)
                            .then(response => {
                                alert(response.data.msg) //弹出操作信息
                                this.dialogVisible = false// 关闭对话框
                                this.ruleForm = {}// 清理表单
                                this.getData()// 调用getData方法,刷新数据

                            }).catch(err => {
                                console.log(err);

                            })
                    } else {
                        //修改操作
                        axios.put("http://localhost:8080/device/info", this.ruleForm)
                            .then(response => {
                                alert(response.data.msg) //弹出操作信息
                                this.dialogVisible = false// 关闭对话框
                                this.ruleForm = {}// 清理表单
                                this.getData()// 调用getData方法,刷新数据

                            }).catch(err => {
                                console.log(err);
                            })
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },

        handleClose(done) {//新增对话框的关闭确认函数
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => { });
        },

        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.deviceInfo.pageSize = val//赋值
            this.getData() //调用getData方法，刷新数据
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.deviceInfo.pageNum = val //赋值
            this.getData() //调用getData方法，刷新数据

        },

        onSubmit() {
            //搜索表单提交
            // 调用getData方法，刷新数据
            this.getData()

        },

        //定义查询所有（多条件分页查询）
        getData() {
            axios.get("http://localhost:8080/device/info/list", {
                params: this.deviceInfo
            })
                .then(response => {
                    //
                    this.deviceInfoList = response.data.rows
                    // 给total赋值
                    this.total = response.data.total
                }).catch(err => {
                    console.log(err);

                })
        }
    },
    created() {
        //vue的生命周期函数
        this.getData()// 调用查询所有
        //调用分类列表的接口，查询分类列表
        axios.get("http://localhost:8080/device/type/list")
            .then(response => {
                //
                this.deviceTypeList = response.data.data
            }).catch(err => {
                console.log(err);

            })
    }
}
</script>

<style scoped></style>