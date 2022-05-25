<template>
  <div>
    <el-table :data="tableData" stripe style="width: 90%" @cell-click="delete_container">
      <el-table-column prop="container_id" label="实验环境ID" width="600" />
      <el-table-column prop="image_name" label="实验环境类型" width="220" />
      <el-table-column prop="container_type" label="构建实验环境的镜像" width="220" />
      <el-table-column label="操作" width="120">
        <template #default>
          <el-button text size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { ref } from 'vue'
import axios from 'axios'
// 从localStorage中获取用户名
const username = window.localStorage.getItem('username')
// 从登录页面获取用户名
let route = useRoute()
let router = useRouter()
const tableData = ref([])

// 提示用户去创建实验环境
const openCenter = () => {
  ElMessage({
    showClose: true,
    message: '你还没有实验环境，快去创建吧！',
    center: true
  })
}
const getContainerList = username => {
  axios({
    method: 'GET',
    url: 'http://localhost:8080/user/container_list',
    params: {
      username: username
    }
  })
    .then(res => {
      //请求成功
      console.log(res)
      tableData.value = res.data
      // console.log(res.data.length)
      if (res.data.length == 0) {
        openCenter()
      }
      // alert('成功')
    })
    .catch(err => {
      console.log(err)
      alert('失败')
    })
}
getContainerList(username)
//
const deleteContainer = container_id => {
  axios({
    method: 'GET',
    url: 'http://localhost:8080/deletecontainer',
    params: {
      container_id: container_id
    }
  })
    .then(res => {
      //请求成功
      console.log(res)
      tableData.value = res.data
      location.reload()
      // alert('成功')
    })
    .catch(err => {
      console.log(err)
      alert('失败')
    })
}
const delete_container = (row, column, cell, event) => {
  // alert(row.container_id)
  deleteContainer(row.container_id)
}
</script>

<style></style>
