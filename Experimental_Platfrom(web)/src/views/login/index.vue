<template>
  <div class="login-page">
    <img src="@/assets/work.jpg" alt="" style="top: 221px; right: 340px; position: absolute; width: 746px" />
    <img src="@/assets/login-bkgd.jpg" style="width: 100%; height: 925px" alt="" />
    <div style="width: 30%" class="input">
      <el-tag size="large" hit="true"><p style="font-size: 30px">基于Docker的在线Java实验平台</p></el-tag>
      <el-tabs type="border-card" style="margin-top: 50px">
        <el-tab-pane label="登录">
          <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="120px" class="demo-ruleForm" :size="formSize">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="ruleForm.name" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="ruleForm.password" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
              <el-button @click="resetForm(ruleFormRef)">清空</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册">
          <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="120px" class="demo-ruleForm" :size="formSize">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="ruleForm.name" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="ruleForm.password" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="registerForm(ruleFormRef)">注册</el-button>
              <el-button @click="resetForm(ruleFormRef)">清空</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import axios from 'axios'
import { ElNotification } from 'element-plus'
import { useRouter } from 'vue-router'

let router = useRouter()
// 登录成功的消息提醒框
const opensuccess = message => {
  ElNotification({
    title: 'Success',
    message: message,
    type: 'success'
  })
}
// 登录失败的消息提醒框
const openerror = message => {
  ElNotification({
    title: 'Error',
    message: message,
    type: 'error'
  })
}
const gotohome = username => {
  // alert('即将要传递的值为' + username)
  router.push({
    name: 'home',
    params: {
      username: username
    },
    query: {
      username: username
    }
  })
}
const formSize = ref('default')
const ruleFormRef = ref('')
const ruleForm = reactive({
  name: '',
  password: ''
})
// 输入是否合法的判断标志
const isrule = ref('0')
const rules = reactive({
  name: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 6, max: 8, message: '长度应为6到8位', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 8, message: '长度应为6到8位', trigger: 'blur' }
  ]
})
// 登录表单验证
const submitForm = async formEl => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      axios
        .get('http://localhost:8080/user/login', {
          params: {
            username: ruleForm.name,
            password: ruleForm.password
          }
        })
        .then(function (response) {
          console.log(response)
          if (response.data == '登录成功') {
            opensuccess(response.data)
            gotohome(ruleForm.name)
            window.localStorage.setItem('username', ruleForm.name)
          } else if (response.data == '密码错误') {
            openerror(response.data)
          } else if (response.data == '你还没有注册') {
            openerror(response.data)
          }
        })
        .catch(function (error) {
          console.log(error)
          openerror(error.data)
        })
        .then(function () {
          // 总是会执行
        })
    } else {
      console.log('error submit!', fields)
    }
  })
}
// 注册表单验证
const registerForm = async formEl => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      // axios.get()
      axios
        .get('http://localhost:8080/user/register', {
          params: {
            username: ruleForm.name,
            password: ruleForm.password
          }
        })
        .then(function (response) {
          console.log(response)
          opensuccess(response.data)
        })
        .catch(function (error) {
          console.log(error)
          openerror(error.data)
        })
        .then(function () {
          // 总是会执行
        })
    } else {
      console.log('error submit!', fields)
    }
  })
}
const resetForm = formEl => {
  if (!formEl) return
  formEl.resetFields()
}
</script>
<style scoped>
.login-page {
  position: relative;
  height: 100%;
}
.input {
  position: absolute;
  top: 320px;
  left: 170px;
}
</style>
