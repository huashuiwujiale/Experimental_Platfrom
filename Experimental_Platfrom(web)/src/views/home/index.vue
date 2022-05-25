<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <div style="display: flex; justify-content: space-between">
          <p>你好，{{ user_name }}</p>
          <el-button type="primary" @click="gotologin">退出实验平台</el-button>
        </div>
      </el-header>
      <!-- 左侧导航栏 -->
      <el-container>
        <el-aside width="200px">
          <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
            <el-radio-button :label="false">展开</el-radio-button>
            <el-radio-button :label="true">折叠</el-radio-button>
          </el-radio-group>
          <el-menu default-active="2" class="el-menu-vertical-demo" :collapse="isCollapse" @open="handleOpen" @close="handleClose">
            <el-sub-menu index="1">
              <template #title>
                <!-- <el-icon><location /></el-icon> -->
                <img src="@/assets/experiment.png" alt="" style="width: auto; height: 60%" />
                <span>实验环境管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1" @click="tolist(user_name)">环境概览</el-menu-item>
                <el-menu-item index="1-2" @click="toconfig(user_name)">新建环境</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
            <el-menu-item index="2" @click="tocode(user_name)">
              <img src="@/assets/code.png" alt="" style="width: auto; height: 60%" />
              <template #title>开始实验</template>
            </el-menu-item>
            <el-sub-menu index="3">
              <template #title>
                <!-- <el-icon><location /></el-icon> -->
                <img src="@/assets/advice.png" alt="" style="width: auto; height: 60%" />
                <span>用户反馈</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1" @click="topostadvice">提交反馈</el-menu-item>
                <el-menu-item index="1-2" @click="toseeadvice">查看反馈</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
            <el-menu-item index="4" :disabled="isupdate" @click="toupdate">
              <img src="@/assets/update.png" alt="" style="width: auto; height: 60%" />
              <template #title>更新实验环境</template>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
// 从登录页面获取用户名
let route = useRoute()
let router = useRouter()
// 从localStorage中获取用户名
const user_name = window.localStorage.getItem('username')
// alert('localStorage获取到的' + user_name)
const isupdate = ref(true)
const isCollapse = ref(false)
const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}
if (user_name == 'wujiale') {
  isupdate.value = false
}
const tolist = username => {
  router.push({
    name: 'list'
  })
}
const toconfig = username => {
  router.push({
    name: 'config'
  })
}
const toupdate = () => {
  router.push({
    name: 'update'
  })
}
const tocode = username => {
  // console.log('走！去编写java代码')
  router.push({
    name: 'code'
  })
}
const toseeadvice = () => {
  // console.log('走！去编写java代码')
  router.push({
    name: 'seeadvice'
  })
}
const topostadvice = () => {
  // console.log('走！去编写java代码')
  router.push({
    name: 'postadvice'
  })
}
const gotologin = () => {
  router.push({
    name: 'login'
  })
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-mian {
  padding-left: 200px;
}
</style>
