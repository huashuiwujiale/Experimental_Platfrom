<template>
  提交反馈
  <div style="margin-top: 100px; display: flex; justify-content: center; flex-direction: column; width: 50%; margin-left: 150px">
    <el-form label-width="200px">
      <el-input v-model="textarea" maxlength="30" placeholder="请输入对本实验平台的反馈" show-word-limit type="textarea" />
    </el-form>
  </div>
  <el-button style="margin-left: 930px; margin-top: 100px" type="primary" @click="addAdvice">提交</el-button>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRoute, useRouter } from 'vue-router'
// 从localStorage中获取用户名
const username = window.localStorage.getItem('username')
const textarea = ref('')
Date.prototype.Format = function (fmt) {
  var o = {
    'M+': this.getMonth() + 1, //月份
    'd+': this.getDate(), //日
    'h+': this.getHours(), //小时
    'm+': this.getMinutes(), //分
    's+': this.getSeconds(), //秒
    'q+': Math.floor((this.getMonth() + 3) / 3), //季度
    S: this.getMilliseconds() //毫秒
  }
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length))
  for (var k in o) if (new RegExp('(' + k + ')').test(fmt)) fmt = fmt.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length))
  return fmt
}
let route = useRoute()
let router = useRouter()
var current_time = new Date().Format('yyyy-MM-dd hh:mm:ss')
// 提示用户去反馈成功
const openCenter = () => {
  ElMessage({
    showClose: true,
    message: '反馈成功！',
    center: true
  })
}
const addAdvice = () => {
  console.log(' ')
  axios({
    method: 'GET',
    url: 'http://localhost:8080/addAdvice',
    params: {
      username: username,
      comment: textarea.value,
      date: current_time
    }
  })
    .then(function (response) {
      console.log(response)
      openCenter()
      router.push({
        name: 'seeadvice'
      })
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
  // 跳转到查看反馈页面
}
</script>

<style></style>
