<template>
    <div class="container">
        <h1>分类管理</h1>
        <button @click="show = !show; title = '新增'">新增</button>
        <!-- 展示数据 -->
        <table border="1px" width="500px">
            <tr>
                <td>主键</td>
                <td>名称</td>
                <td>说明</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            <tr v-for="deviceType in deviceTypeList" :key="deviceType.typeId">
                <td>{{ deviceType.typeId }}</td>
                <td>{{ deviceType.typeName }}</td>
                <td>{{ deviceType.typeRemark }}</td>
                <td>
                    <span v-if="deviceType.typeStatus == 0" style="color:green">正常</span>
                    <span v-if="deviceType.typeStatus == 1" style="color:orange">禁用</span>
                    <span v-if="deviceType.typeStatus == -1" style="color:red">删除</span>


                </td>

                <!-- <td>{{ deviceType.typeStatus }}</td> -->
                <td>
                    <button @click="showDeviceType(deviceType.typeId)">修改</button>
                    <button @click="deleteDeviceType(deviceType.typeId)">删除</button>
                </td>
            </tr>
        </table>

        <!-- 显示弹框 -->
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
            { "typeId": 3, "typeName": "ceshi03", "typeRemark": "说明3", "typeStatus": -1 }]
        }
    },
    created(){//vue生命周期函数：在vue对象创建后执行
        this.getData()//调用获取数据的方法

    },
    methods: {
        //获取数据库中的分类列表
        getData(){
            //请求后端API
            axios.get("http://localhost:8080/device/type/list")
            .then(response=>{//请求成功
                console.log(response);
                // 将分类列表的值赋值给数组
                this.deviceTypeList = response.data.data
                
            })
            .catch(error=>{//请求失败
                console.log(error);
                

            })
        },
        //编写方法
        // 新增分类的方法
        addOrUpdate() {
            //typeId:有值就是修改，没有值就是新增
            if (this.deviceType.typeId == null) {
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
                //修改数据：遍历数组。查找需要修改的数据，赋值到数组中
                for (let i = 0; i < this.deviceTypeList.length; i++) {//遍历数组
                    if (this.deviceTypeList[i].typeId == this.deviceType.typeId) {//查找需要修改的数据，
                        this.deviceTypeList[i] = this.deviceType   //赋值到数组中

                    }

                }

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
            for (let i = 0; i < this.deviceTypeList.length; i++) {//循环数组deviceTypeList
                if (this.deviceTypeList[i].typeId == typeId) {//查找需要修改的数据
                    this.deviceType.typeId = this.deviceTypeList[i].typeId
                    this.deviceType.typeName = this.deviceTypeList[i].typeName//赋值给表单对象deviceType
                    this.deviceType.typeRemark = this.deviceTypeList[i].typeRemark//赋值给表单对象deviceType
                    this.deviceType.typeStatus = this.deviceTypeList[i].typeStatus//赋值给表单对象deviceType

                }

            }

        },
        //删除
        deleteDeviceType(typeId){
            //弹出删除确认的对话框
            if (confirm("确定删除该数据吗？")) {
                // 删除操作：遍历数组，查找需要删除的数据，删除操作
                for (let i = 0; i < this.deviceTypeList.length; i++) {
                    if(this.deviceTypeList[i].typeId == typeId){
                        //splice(i,1):从下标i开始删除1个元素
                        this.deviceTypeList.splice(i,1)//删除操作
                        alert("删除成功")
                    }
                    
                }
            }

        }

    }
}


</script>

<style scoped></style>
