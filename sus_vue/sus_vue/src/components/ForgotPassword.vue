<template>
  <div class="forgot-password-container">
    <el-card class="forgot-password-card">
      <div class="forgot-password-header">
        <h2>Forgot Your Password?</h2>
      </div>

      <el-form :model="form" ref="form" label-width="80px" class="forgot-password-form">
        <!-- 邮箱输入框 -->
        <el-form-item label="Email" :rules="emailRules">
          <el-input v-model="form.email" placeholder="Enter your email"></el-input>
        </el-form-item>

        <!-- 提交按钮 -->
        <el-form-item>
          <el-button
              type="primary"
              class="submit-button"
              @click="submitForm"
              :loading="loading"
              block
          >
            Submit
          </el-button>
        </el-form-item>

        <!-- 登录链接 -->
        <el-form-item class="login-link">
          <span>Remember your password?</span>
          <el-link href="/login">Login</el-link>
        </el-form-item>
      </el-form>

      <!-- 提示信息 -->
      <el-dialog
          v-model:visible="dialogVisible"
          title="Success"
          width="400px"
      >
        <span>A password reset link has been sent to your email.</span>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">OK</el-button>
        </span>
      </el-dialog>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElButton, ElCard, ElForm, ElFormItem, ElInput, ElLink, ElDialog } from 'element-plus';

// 表单数据
const form = ref({
  email: ''
});

// 表单验证规则
const emailRules = ref([
  { required: true, message: 'Please enter your email', trigger: 'blur' },
  { type: 'email', message: 'Please enter a valid email address', trigger: 'blur' }
]);

const loading = ref(false); // 控制提交按钮的加载状态
const dialogVisible = ref(false); // 控制弹出框的显示状态

// 提交表单的方法
const submitForm = () => {
  // 启动加载状态
  loading.value = true;

  // 模拟提交请求
  setTimeout(() => {
    loading.value = false;  // 提交结束后关闭加载状态
    dialogVisible.value = true; // 显示成功弹窗
    // 这里可以加入实际的API请求，比如向后端发送邮箱地址，触发密码重置逻辑
    console.log('Password reset request sent to', form.value.email);
  }, 2000); // 模拟请求延迟
};
</script>

<style scoped>
/* 忘记密码页面的容器 */
.forgot-password-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #5a5a5a 0%, #3b3b3b 100%);
}

/* 忘记密码卡片 */
.forgot-password-card {
  width: 400px;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background: #fff;
}

/* 卡片头部 */
.forgot-password-header h2 {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

/* 输入框和按钮样式 */
.el-input {
  margin-bottom: 20px;
}

/* 提交按钮样式 */
.submit-button {
  margin-top: 20px;
}

/* 登录链接 */
.login-link {
  text-align: center;
  margin-top: 10px;
}

/* 弹窗 */
.dialog-footer {
  text-align: center;
}
</style>
