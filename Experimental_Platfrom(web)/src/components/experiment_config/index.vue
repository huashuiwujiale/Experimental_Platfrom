<template>
  <el-steps :active="step" align-center>
    <el-step title="Step 1" description="选择想创建的环境类型" />
    <el-step title="Step 2" description="创建环境中" />
    <el-step title="Step 3" description="创建成功" />
  </el-steps>
  <div style="width: 300px; margin: 200px auto">
    <el-select v-model="value" clearable placeholder="请选择" @change="create_environment" @clear="button_disabled">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>
    <el-button type="primary" :disabled="disabled">创建</el-button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
const step = ref('1')
// 用户选择的环境的值
const value = ref('')
// 从localStorage中获取用户名
const username = window.localStorage.getItem('username')
// 创建按钮是否可点击，默认为不可点击，选择完才可以
const disabled = ref(true)
// 用户的选择环境的列表
const options = ref([])
// 用户选择环境后触发
const create_environment = () => {
  disabled.value = false
  // alert(value.value)
  send(username, value)
  step.value = 2
}
// 发送获取实验环境列表的请求
const getImageList = () => {
  axios
    .get('http://localhost:8080/getImageList')
    .then(function (response) {
      console.log(response)
      options.value = response.data
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
getImageList()
// 发送创建环境的请求
const send = (username, value) => {
  // alert(username, value)
  axios
    .get('http://localhost:8080/create_environment', {
      params: {
        username: username,
        image_name: value.value
      }
    })
    .then(function (response) {
      console.log(response)
      step.value = 3
      success()
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
// 用户清除选择后，无法点击创建按钮
const button_disabled = () => {
  disabled.value = true
}
// 创建环境成功后，提示用户
const success = () => {
  ElNotification({
    title: 'Success',
    message: '创建实验环境成功',
    type: 'success'
  })
}
</script>

<style></style>
