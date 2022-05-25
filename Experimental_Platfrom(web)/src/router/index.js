import Login from "@/views/login/index.vue"
import Home from "@/views/home/index.vue"
import { createRouter,createWebHistory} from "vue-router";

// 1. 定义一些路由
// 每个路由都需要映射到一个组件。
const routes = [
  { path: '/', redirect:'/login' },
  { 
    path: '/home',
    name: 'home',
    component: Home,
    redirect:'/home/list',
    children: [
      {
        path: 'list',
        name: 'list',
        component: ()=> import('@/components/experiment_list/index.vue')
      },
      {
        path: 'config',
        name: 'config',
        component: ()=> import('@/components/experiment_config/index.vue')
      },
      {
        path: 'code',
        name: 'code',
        component: ()=> import('@/components/experiment_code/index.vue')
      },
      {
        path: 'update',
        name: 'update',
        component: ()=> import('@/views/update/index.vue')
      },
      {
        path: 'postadvice',
        name: 'postadvice',
        component: ()=> import('@/views/postadvice/index.vue')
      },
      {
        path: 'seeadvice',
        name: 'seeadvice',
        component: ()=> import('@/views/seeadvice/index.vue')
      },
    ]
  },
  { 
    path: '/login',
    name: 'login',
    component: Login
   },
]

// 2. 创建路由实例并传递 `routes` 配置
// 你可以在这里输入更多的配置，但我们在这里
// 暂时保持简单
const router = createRouter({
// 3. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  history: createWebHistory(),
  routes, // `routes: routes` 的缩写
})

router.beforeEach(()=>{
  console.log("我是导航守卫");
})

export default router