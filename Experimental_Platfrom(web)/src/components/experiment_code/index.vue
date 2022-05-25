<template>
  <div style="margin-bottom: 20px">
    <el-select v-model="selectvalue" class="m-2" placeholder="请选择实验环境" size="large">
      <el-option v-for="item in options" :key="item.number" :label="item.image_name" :value="item.container_id" />
    </el-select>
    <el-button type="primary" @click="read_code">读取上次保存的文件</el-button>
    <el-button type="primary" @click="add_templete">添加模板</el-button>
    <p>结束实验之前别忘了保存实验进度</p>
    <Codemirror v-model:value="code" :options="cmOptions" border placeholder="测试 placeholder" :height="200" @change="onChange" />
  </div>
  <div>
    <p>运行结果</p>
    <el-input style="font-size: 25px" v-model="runcode_result" :rows="5" type="textarea" placeholder="Please input" />
  </div>
  <div style="margin-top: 40px; display: flex; justify-content: center">
    <el-button type="primary" @click="save_code">保存进度</el-button>
    <el-button type="primary" @click="run_code">运行</el-button>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import Codeinput from '@/views/codeinput/index.vue'
import axios from 'axios'
import Codemirror from 'codemirror-editor-vue3'
// 检查语法的language
import 'codemirror/mode/javascript/javascript.js'

// 从localStorage中获取用户名
const username = window.localStorage.getItem('username')
const runcode_result = ref('暂时没有运行结果')
const selectvalue = ref('')
// 提示用户去创建实验环境
const openCenter = () => {
  ElMessage({
    showClose: true,
    message: '你还没有实验环境，快去创建吧！',
    center: true
  })
}
const options = ref([])

// 获取用户的实验环境列表
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
      if (res.data.length == 0) {
        openCenter()
      }
      options.value = res.data
      // alert('成功')
    })
    .catch(err => {
      console.log(err)
      alert('失败')
    })
}
getContainerList(username)
const run_code = () => {
  console.log('run_code方法触发')
  if (selectvalue.value == '') {
    alert('请先选择运行环境！')
  } else {
    // alert(selectvalue.value)
    code_run()
  }
}
const add_templete = () => {
  code.value = `public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}`
}
const save_code = () => {
  console.log('run_code方法触发')
  if (selectvalue.value == '') {
    alert('请先选择运行环境！')
  } else {
    code_save()
  }
}
const read_code = () => {
  console.log('run_code方法触发')
  if (selectvalue.value == '') {
    alert('请先选择要读取的运行环境！')
  } else {
    code_read()
  }
}
// 定义默认代码模板
const code = ref()
const code_run = () => {
  axios
    .post('http://localhost:8080/code_run', {
      data: {
        username: username,
        container_id: selectvalue.value,
        code: code.value
      }
    })
    .then(function (response) {
      console.log(response.data)
      runcode_result.value = response.data
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
const code_save = () => {
  axios
    .post('http://localhost:8080/code_save', {
      data: {
        username: username,
        container_id: selectvalue.value,
        code: code.value
      }
    })
    .then(function (response) {
      console.log(response.data)
      runcode_result.value = response.data
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
const code_read = () => {
  axios
    .post('http://localhost:8080/code_read', {
      data: {
        username: username,
        container_id: selectvalue.value,
        code: code.value
      }
    })
    .then(function (response) {
      console.log(response.data)
      code.value = response.data
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
// 定义代码输入框的各个参数
const cmOptions = {
  mode: 'text/javascript', // 语言模式
  theme: 'default', // 主题
  lineNumbers: true, // 显示行号
  smartIndent: true, // 智能缩进
  indentUnit: 2, // 智能缩进单位为4个空格长度
  foldGutter: true, // 启用行槽中的代码折叠
  styleActiveLine: true // 显示选中行的样式
}
const onChange = (val, cm) => {}
</script>
<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 500;
}
.el-tabs--card {
  height: 400px !important;
}
.el-tabs__content {
  height: 400px !important;
}
.el-tab-pane {
  height: 400px !important;
}
.codemirror-container {
  height: 400px !important;
}
element.style {
  height: 400px !important;
}
.CodeMirror {
  height: 400px !important;
  font-size: 20px;
}
.cm-s-default {
  height: 400px !important;
  font-size: 20px;
}
</style>
