<template>
  <el-table :data="tableData" stripe style="width: 90%" @cell-click="delete_image">
    <el-table-column prop="value" label="镜像的名字" width="300" />
    <el-table-column prop="lable" label="镜像的标签" width="300" />
    <el-table-column prop="describe" label="镜像的描述" width="300" />
    <el-table-column label="操作" width="120">
      <template #default>
        <el-button text size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div style="margin-top: 200px; display: flex; justify-content: center">
    <el-form :model="form" label-width="120px">
      <el-form-item label="镜像的名字">
        <el-input v-model="form.value" />
      </el-form-item>
      <el-form-item label="镜像的标签">
        <el-input v-model="form.lable" />
      </el-form-item>
      <el-form-item label="镜像的描述">
        <el-input v-model="form.describe" />
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="addImage">新增</el-button>
    <el-button type="primary" @click="openDocker">Docker管理</el-button>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import axios from 'axios'
const form = reactive({
  value: '',
  lable: '',
  describe: ''
})
const tableData = ref([])
// 发送获取实验环境列表的请求
const getImageList = () => {
  axios
    .get('http://localhost:8080/getImageList')
    .then(function (response) {
      console.log(response)
      tableData.value = response.data
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
getImageList()
const deleteImage = value => {
  axios({
    method: 'GET',
    url: 'http://localhost:8080/deleteImage',
    params: {
      value: value
    }
  })
    .then(res => {
      //请求成功
      console.log(res)
      tableData.value = res.data
      // alert('成功')
      location.reload()
    })
    .catch(err => {
      console.log(err)
      alert('失败')
    })
}
const delete_image = (row, column, cell, event) => {
  // alert(row.container_id)
  // alert(row.value)
  deleteImage(row.value)
}
const addImage = () => {
  axios({
    method: 'GET',
    url: 'http://localhost:8080/addImage',
    params: {
      value: form.value,
      lable: form.lable,
      describe: form.describe
    }
  })
    .then(function (response) {
      console.log(response)
      location.reload()
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
const openDocker = () => {
  window.open('http://119.3.86.125:8088/#/auth', '_blank')
}
</script>

<style></style>
