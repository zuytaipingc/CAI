<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="100px">
            <el-form-item label="设备" prop="deviceId">
                <el-select v-model="queryParams.deviceId" placeholder="请选择设备" filterable clearable style="width: 150px">
                    <el-option v-for="device in deviceInfoList" :key="device.deviceId" :label="device.deviceName"
                        :value="device.deviceId"></el-option>
                </el-select> </el-form-item>
            <el-form-item label="借用者" prop="userId">
                <el-select v-model="queryParams.userId" placeholder="请选择借用者" filterable clearable style="width: 150px">
                    <el-option v-for="user in deviceUserList" :key="user.regId" :label="user.regName"
                        :value="user.regId">
                    </el-option>
                </el-select>

            </el-form-item>
            <el-form-item label="借用时间" prop="borrowTime">
                <el-date-picker clearable v-model="queryParams.beginBorrowTime" type="date" value-format="yyyy-MM-dd"
                    style="width: 150px" placeholder="请选择借用时间">
                </el-date-picker>
                <el-date-picker clearable v-model="queryParams.endBorrowTime" type="date" value-format="yyyy-MM-dd"
                    style="width: 150px" placeholder="请选择借用时间">
                </el-date-picker>

            </el-form-item>
            <el-form-item label="归还状态" prop="returnStatus">
                <el-select v-model="queryParams.returnStatus" placeholder="请选择归还状态" clearable>
                    <el-option v-for="dict in dict.type.device_return_status" :key="dict.value" :label="dict.label"
                        :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                    v-hasPermi="['device:borrow:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                    v-hasPermi="['device:borrow:edit']">修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
                    @click="handleDelete" v-hasPermi="['device:borrow:remove']">删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['device:borrow:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="borrowList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <!-- <el-table-column label="主键" align="center" prop="borrowId" /> -->
            <!-- <el-table-column label="设备id" align="center" prop="deviceId" /> -->
            <!-- <el-table-column label="借用用户id" align="center" prop="userId" /> -->
            <el-table-column label="学号/工号" align="center" prop="user.regCode" />
            <el-table-column label="姓名" align="center" prop="user.regName" />
            <el-table-column label="班级/部门" align="center" prop="user.regDept" />
            <el-table-column label="设备名称" align="center" prop="device.deviceName" />
            <el-table-column label="设备型号" align="center" prop="device.deviceModel" />
            <el-table-column label="设备图片" align="center" prop="device.deviceImg" width="100">
                <template slot-scope="scope">
                    <image-preview :src="scope.row.device.deviceImg" :width="50" :height="50">
                    </image-preview>
                </template>
            </el-table-column>

            <el-table-column label="借用时间" align="center" prop="borrowTime" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.borrowTime, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="借用说明" align="center" prop="borrowRemark" />
            <el-table-column label="计划归还时间" align="center" prop="returnTime" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.returnTime, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="归还状态" align="center" prop="returnStatus">
                <template slot-scope="scope">
                    <dict-tag :options="dict.type.device_return_status" :value="scope.row.returnStatus" />
                </template>
            </el-table-column>
            <el-table-column label="归还说明" align="center" prop="returnRemark" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['device:borrow:edit']">修改</el-button>
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                        v-hasPermi="['device:borrow:remove']">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />

        <!-- 添加或修改借用信息对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="40%" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="设备id" prop="deviceId">
                    <el-input v-model="form.deviceId" placeholder="请输入设备id" />
                </el-form-item>
                <el-form-item label="借用用户id" prop="userId">
                    <el-input v-model="form.userId" placeholder="请输入借用用户id" />
                </el-form-item>
                <el-form-item label="借用时间" prop="borrowTime">
                    <el-date-picker clearable v-model="form.borrowTime" type="date" value-format="yyyy-MM-dd"
                        style="width: 100%" placeholder="请选择借用时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="借用说明" prop="borrowRemark">
                    <el-input v-model="form.borrowRemark" placeholder="请输入借用说明" />
                </el-form-item>
                <el-form-item label="计划归还时间" prop="returnTime">
                    <el-date-picker clearable v-model="form.returnTime" type="date" value-format="yyyy-MM-dd"
                        style="width: 100%" placeholder="请选择计划归还时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="归还状态" prop="returnStatus">
                    <el-radio-group v-model="form.returnStatus">
                        <el-radio v-for="dict in dict.type.device_return_status" :key="dict.value"
                            :label="parseInt(dict.value)">{{ dict.label }}</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="归还说明" prop="returnRemark">
                    <el-input v-model="form.returnRemark" placeholder="请输入归还说明" />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { listBorrow, getBorrow, delBorrow, addBorrow, updateBorrow } from "@/api/device/borrow";
import axios from "axios";

export default {
    name: "Borrow",
    dicts: ['device_return_status'],
    data() {
        return {
            // 遮罩层
            loading: true,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // 借用信息表格数据
            borrowList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            deviceUserList: [],//小程序用户列表
            deviceInfoList: [],//设备列表

            // 查询参数
            queryParams: {
                beginBorrowTime: null,//借用时间：开始值
                endBorrowTime: null,//借用时间:结束值
                pageNum: 1,
                pageSize: 10,
                deviceId: null,
                userId: null,
                borrowTime: null,
                returnStatus: null,
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                deviceId: [
                    { required: true, message: "设备id不能为空", trigger: "blur" }
                ],
                userId: [
                    { required: true, message: "借用用户id不能为空", trigger: "blur" }
                ],
                borrowTime: [
                    { required: true, message: "借用时间不能为空", trigger: "blur" }
                ],
                returnStatus: [
                    { required: true, message: "归还状态不能为空", trigger: "change" }
                ],
            }
        };
    },
    created() {
        this.getList();
        //调用后端接口，获取小程序用户列表
        axios.get("http://localhost:8080/device/register/all")
            .then(response => {
                this.deviceUserList = response.data.rows
            }).catch(err => {
                console.log(err);

            })
        //调用后端接口：获取设备列表
        axios.get("http://localhost:8080/device/info/all")
            .then(response => {
                this.deviceInfoList = response.data.rows
            }).catch(err => {
                console.log(err);

            })

    },
    methods: {
        /** 查询借用信息列表 */
        getList() {
            this.loading = true;
            listBorrow(this.queryParams).then(response => {
                this.borrowList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
        },
        // 表单重置
        reset() {
            this.form = {
                borrowId: null,
                deviceId: null,
                userId: null,
                borrowTime: null,
                borrowRemark: null,
                returnTime: null,
                returnStatus: null,
                returnRemark: null,
                createBy: null,
                createTime: null,
                updateBy: null,
                updateTime: null
            };
            this.resetForm("form");
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.resetForm("queryForm");
            this.handleQuery();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map(item => item.borrowId)
            this.single = selection.length !== 1
            this.multiple = !selection.length
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加借用信息";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const borrowId = row.borrowId || this.ids
            getBorrow(borrowId).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改借用信息";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.borrowId != null) {
                        updateBorrow(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addBorrow(this.form).then(response => {
                            this.$modal.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    }
                }
            });
        },
        /** 删除按钮操作 */
        handleDelete(row) {
            const borrowIds = row.borrowId || this.ids;
            this.$modal.confirm('是否确认删除借用信息编号为"' + borrowIds + '"的数据项？').then(function () {
                return delBorrow(borrowIds);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => { });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('device/borrow/export', {
                ...this.queryParams
            }, `borrow_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>