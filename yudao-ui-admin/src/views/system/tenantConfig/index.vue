<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="参数分组" prop="category">
        <el-input v-model="queryParams.category" placeholder="请输入参数分组" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="参数类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择参数类型" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.INFRA_CONFIG_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="参数名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入参数名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="参数键名" prop="configKey">
        <el-input v-model="queryParams.configKey" placeholder="请输入参数键名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="参数键值" prop="value">
        <el-input v-model="queryParams.value" placeholder="请输入参数键值" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="是否可见" prop="visible">
        <el-select v-model="queryParams.visible" placeholder="请选择是否可见" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="queryParams.remark" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['system:tenant-config:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['system:tenant-config:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="参数主键" align="center" prop="id" />
      <el-table-column label="参数分组" align="center" prop="category" />
      <el-table-column label="参数类型" align="center" prop="type">
        <template v-slot="scope">
          <dict-tag :type="DICT_TYPE.INFRA_CONFIG_TYPE" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="参数名称" align="center" prop="name" />
      <el-table-column label="参数键名" align="center" prop="configKey" />
      <el-table-column label="参数键值" align="center" prop="value" />
      <el-table-column label="是否可见" align="center" prop="visible" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:tenant-config:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['system:tenant-config:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="参数分组" prop="category">
          <el-input v-model="form.category" placeholder="请输入参数分组" />
        </el-form-item>
        <el-form-item label="参数类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择参数类型">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.INFRA_CONFIG_TYPE)"
                       :key="dict.value" :label="dict.label" :value="parseInt(dict.value)" />
          </el-select>
        </el-form-item>
        <el-form-item label="参数名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入参数名称" />
        </el-form-item>
        <el-form-item label="参数键名" prop="configKey">
          <el-input v-model="form.configKey" placeholder="请输入参数键名" />
        </el-form-item>
        <el-form-item label="参数键值" prop="value">
          <el-input v-model="form.value" placeholder="请输入参数键值" />
        </el-form-item>
        <el-form-item label="是否可见" prop="visible">
          <el-radio-group v-model="form.visible">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { createTenantConfig, updateTenantConfig, deleteTenantConfig, getTenantConfig, getTenantConfigPage, exportTenantConfigExcel } from "@/api/system/tenantConfig";

export default {
  name: "TenantConfig",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 租户参数配置列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        category: null,
        type: null,
        name: null,
        configKey: null,
        value: null,
        visible: null,
        remark: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        category: [{ required: true, message: "参数分组不能为空", trigger: "blur" }],
        type: [{ required: true, message: "参数类型不能为空", trigger: "change" }],
        name: [{ required: true, message: "参数名称不能为空", trigger: "blur" }],
        configKey: [{ required: true, message: "参数键名不能为空", trigger: "blur" }],
        value: [{ required: true, message: "参数键值不能为空", trigger: "blur" }],
        visible: [{ required: true, message: "是否可见不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getTenantConfigPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        category: undefined,
        type: undefined,
        name: undefined,
        configKey: undefined,
        value: undefined,
        visible: undefined,
        remark: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加租户参数配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getTenantConfig(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改租户参数配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateTenantConfig(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createTenantConfig(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除租户参数配置编号为"' + id + '"的数据项?').then(function() {
          return deleteTenantConfig(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有租户参数配置数据项?').then(() => {
          this.exportLoading = true;
          return exportTenantConfigExcel(params);
        }).then(response => {
          this.$download.excel(response, '租户参数配置.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
