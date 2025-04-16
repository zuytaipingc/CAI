<template>
    <div class="container">
        <h1>分类管理</h1>
        <el-button type="primary" plain size="small" @click="show = !show; title = '新增'">新增</el-button>
        <!-- 展示数据 -->
        <el-table :data="deviceTypeList" border style="width: 100%">
            <el-table-column fixed prop="typeId" label="主键" width="50">
            </el-table-column>
            <el-table-column prop="typeName" label="名称" width="150">
            </el-table-column>
            <el-table-column prop="typeRemark" label="说明">
            </el-table-column>
            <el-table-column prop="typeStatus" label="状态" width="150">
                <template slot-scope="scope">
                    <span v-if="scope.row.typeStatus == 0" style="color: green;">正常</span>
                    <span v-if="scope.row.typeStatus == 1" style="color: orange;">禁用</span>
                    <span v-if="scope.row.typeStatus == -1" style="color: red;">删除</span>
                </template>
            </el-table-column>

            <el-table-column fixed="right" label="操作" width="200">
                <template slot-scope="scope">
                    <el-button @click="showDeviceType(scope.row.typeId)" type="primary" size="small">修改</el-button>
                    <el-button @click="deleteDeviceType(scope.row.typeId, scope.row.typeStatus)" type="danger"
                        size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 新增或修改弹框 -->
        <el-dialog :title="title" :visible.sync="show" width="50%" :before-close="handleClose">
            <!-- 表单：model绑定表单对象，rules表单验证、ref：获取表单的dom对象 label-width：宽度 -->
            <el-form :model="deviceType" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="名称" prop="typeName">
                    <el-input v-model="deviceType.typeName"></el-input>
                </el-form-item>
                <el-form-item label="说明" prop="typeRemark">
                    <el-input v-model="deviceType.typeRemark"></el-input>
                </el-form-item>
                <el-form-item label="状态" prop="typeStatus">
                    <el-radio-group v-model="deviceType.typeStatus">
                        <el-radio :label="0">正常</el-radio>
                        <el-radio :label="1">禁用</el-radio>
                        <el-radio :label="-1">删除</el-radio>

                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">{{title}}</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
            <!-- 
            <span slot="footer" class="dialog-footer">
                <el-button @click="show = false">取 消</el-button>
                <el-button type="primary" @click="show = false">确 定</el-button>
            </span> -->
        </el-dialog>
        <div v-if="show">
            <h2>{{ title }}分类</h2>
            <form>
                名称：<input type="text" v-model="deviceType.typeName"><br>
                说明：<input type="text" v-model="deviceType.typeRemark"><br>
                状态：<input type="radio" value="0" name="typeStatus" v-model="deviceType.typeStatus">正常
                <input type="radio" value="1" name="typeStatus" v-model="deviceType.typeStatus">禁用
                <input type="radio" value="-1" name="typeStatus" v-model="deviceType.typeStatus">删除<br>
                <input type="reset" value="重置"><input type="button" :value="title" @click="addOrUpdate">


            </form>
        </div>
    </div>
</template>

<script>
//导入 axios
import axios from 'axios'
export default {
    name: "index.vue",
    data() {
        return {//定义数据
            title: "新增",
            deviceType: {},//定义数据，获取表单中数据，v-model
            show: false,//控制弹框的显示和隐藏（false
            deviceTypeList: [{ "typeId": 1, "typeName": "ceshi01", "typeRemark": "说明1", "typeStatus": 0 },
            { "typeId": 2, "typeName": "ceshi02", "typeRemark": "说明2", "typeStatus": 1 },
            { "typeId": 3, "typeName": "ceshi03", "typeRemark": "说明3", "typeStatus": -1 }],
            rules: {
                typeName: [{ required: true, message: '请输入分类名称', trigger: 'blur' },],
                typeStatus: [{ required: true, message: '请选择分类状态', trigger: 'blur' },],
            }
        }
    },
    created() {//vue生命周期函数：在vue对象创建后执行
        this.getData()//调用获取数据的方法

    },
    methods: {
        //获取数据库中的分类列表
        getData() {
            //请求后端API
            axios.get("http://localhost:8080/device/type/list")
                .then(response => {//请求成功
                    console.log(response);
                    // 将分类列表的值赋值给数组
                    this.deviceTypeList = response.data.data

                })
                .catch(error => {//请求失败
                    console.log(error);


                })
        },
        //编写方法
        // 新增分类的方法
        addOrUpdate() {
            //typeId:有值就是修改，没有值就是新增
            if (this.deviceType.typeId == null) {
                //请求后端接口，实现新增
                axios.post("http://localhost:8080/device/type/add", this.deviceType)
                    .then(response => {
                        alert(response.data.msg)
                        //查询所有
                        this.getData()
                    }).catch(err => {
                        console.log(err);

                    })
                //typeId 随机生成整数
                // parseInt:将字符串转成为整数
                this.deviceType.typeId = parseInt(Math.random() * 100)
                //将表单中的数据，添加到deviceTypeList数组中
                this.deviceTypeList.push(this.deviceType)
                //隐藏表单
                this.show = false
                //清空表单
                this.deviceType = {}

            } else {//有值是修改
                //修改数据：调用后端接口，实现修改
                axios.post("http://localhost:8080/device/type/edit", this.deviceType)
                    .then(response => {
                        alert(response.data.msg)
                        //查询所有
                        this.getData()
                    }).catch(err => {//请求报错的处理函数
                        console.log(err);

                    })
            }
            //隐藏弹框
            this.show = false
            //清空表单
            this.deviceType = {}

        },
        showDeviceType(typeId) {
            //修改标题的值为‘修改’
            this.title = "修改"
            //数据回显的方法，继续使用新增的弹框
            //显示弹框
            this.show = true
            //回显数据：循环数组 查找需要修改的数据 赋值给表单对象deviceType
            axios.get("http://localhost:8080/device/type/get?typeId=" + typeId)
                .then(response => {//请求成功的处理函数
                    this.deviceType = response.data.data
                }).catch(err => {//请求报错的处理函数
                    console.log(err);

                })

        },
        //删除
        deleteDeviceType(typeId, typeStatus) {
            //
            if (typeStatus == -1) {
                alert("此数据已经是删除状态！")
            } else {
                //弹出删除确认的对话框
                if (confirm("确定删除该数据吗？")) {
                    // 删除操作：请求后端接口，进行删除操作
                    axios.get("http://localhost:8080/device/type/remove?typeId=" + typeId)
                        .then(response => {
                            alert(response.data.msg)//弹出删除信息
                            //查询所有
                            this.getData()
                        }).catch(err => {
                            console.log(err);

                        })

                }

            }



        },
        //弹框关闭函数
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => { });
        },
        //表单提交
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {//表单验证
          if (valid) {//验证通过
            //执行修改、删除操作
            this.addOrUpdate()
          } else {//验证未通过
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {//表单重置
        this.$refs[formName].resetFields();
      }


    }
}


</script>

<style scoped></style>
