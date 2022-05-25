<template>
  <!-- 代码输入的组件 -->
  <Codemirror v-model:value="code" :options="cmOptions" border placeholder="测试 placeholder" :height="200" @change="onChange" />
</template>

<script setup>
import Codemirror from 'codemirror-editor-vue3'
// 检查语法的language
import 'codemirror/mode/javascript/javascript.js'
import { ref, defineExpose } from 'vue'
// 从localStorage中获取用户名
const username = window.localStorage.getItem('username')
const code = ref(`
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
	}
}`)
const runcode_result = ref('暂无运行结果')

const cmOptions = {
  mode: 'text/javascript', // 语言模式
  theme: 'default', // 主题
  lineNumbers: true, // 显示行号
  smartIndent: true, // 智能缩进
  indentUnit: 2, // 智能缩进单位为4个空格长度
  foldGutter: true, // 启用行槽中的代码折叠
  styleActiveLine: true // 显示选中行的样式
}
</script>
<style scoped>
* {
  font-size: 20px;
}
</style>
