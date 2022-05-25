<template>
  <el-row :gutter="20" style="margin-left: 100px">
    <el-col :span="6">
      <div class="grid-content bg-purple">
        <el-timeline>
          <el-timeline-item v-for="(activity, index) in activities" :key="index" :type="activity.type" :hollow="activity.hollow" :timestamp="activity.date">
            {{ activity.comment }}
          </el-timeline-item>
        </el-timeline>
      </div></el-col
    >
    <el-col :span="18"
      ><div class="grid-content bg-purple">
        <el-table :data="activities" stripe style="width: 90%">
          <el-table-column prop="username" label="用户名" width="200" />
          <el-table-column prop="comment" label="反馈" width="220" />
          <el-table-column prop="date" label="时间" width="220" />
        </el-table></div
    ></el-col>
  </el-row>
</template>

<script setup>
import { MoreFilled } from '@element-plus/icons-vue'
import { ref, reactive } from 'vue'
import axios from 'axios'
const activities = ref([])
const getAdviceList = () => {
  axios
    .get('http://localhost:8080/getAdviceList')
    .then(function (response) {
      console.log(response)
      activities.value = response.data
      console.log(activities.value)
    })
    .catch(function (error) {
      console.log(error)
    })
    .then(function () {
      // 总是会执行
    })
}
getAdviceList()
</script>
<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
