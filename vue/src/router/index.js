//这个文件专门用于创建整个应用的路由器
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决el-UI导航栏重复点击问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location){
    return originalPush.call(this,location).catch(err => err)
}

//引入组件
import Login from '../pages/Login'


import Manage from '../pages/Manage'

import Home from '../pages/home/Home'

import Admin from '../pages/admin/Admin'
import AdminAdd from '../pages/admin/AdminAdd'
import AdminDetail from '../pages/admin/AdminDetail'

import User from '../pages/user/User'
import UserDetail from '../pages/user/UserDetail'
import UserDetailOrderDetail from '../pages/user/UserDetailOrderDetail'

import Order from '../pages/order/Order'
import OrderDetail from '../pages/order/OrderDetail'

import Vendor from '../pages/vendor/Vendor'
import VendorAdd from '../pages/vendor/VendorAdd'
import VendorDetail from '../pages/vendor/VendorDetail'

import Brand from '../pages/brand/Brand'
import BrandAdd from '../pages/brand/BrandAdd'
import BrandDetail from '../pages/brand/BrandDetail'

import Commodity from '../pages/commodity/Commodity'
import CommodityAdd from '../pages/commodity/CommodityAdd'
import CommodityDetail from '../pages/commodity/CommodityDetail'

const router =  new VueRouter({
    //history可以去掉路径中的#
    mode:'history',
    base:'/commodity/',
    routes:[
        {
            path:'/',
            redirect:{
                name:'login'
            }
        },
        {
            name:'login',
            path:`/login`,
            component:Login,             
        },

        {
            name:'manage',
            path:`/manage`,
            component:Manage,
            redirect:{
                name:'home'
            },
            meta:{},
            children:[
                {
                    name:'home',
                    path:`home`,
                    component:Home,             
                    meta:{},
                },
                // ===================================================================
                {
                    name:'admin',
                    path:`admin`,
                    component:Admin,             
                    meta:{},
                    children:[
                        {
                            name:'adminAdd',
                            path:'adminAdd',
                            component:AdminAdd,             
                            meta:{},
                        },
                        {
                            name:'adminDetail',
                            path:'adminDetail/:adminName',
                            component:AdminDetail,             
                            meta:{},
                        },                    
                    ],
                },
                // ======================================================================
                {
                    name:'user',
                    path:`user`,
                    component:User,             
                    meta:{},
                    children:[
                        {
                            name:'userDetail',
                            path:'userDetail/:userName/:userCode',
                            component:UserDetail,             
                            meta:{},
                            children:[
                                {
                                    name:'userDetailOrderDetail',
                                    path:'userDetailOrderDetail/:orderCode',
                                    component:UserDetailOrderDetail,
                                    props($route){
                                        return{
                                            userCode:$route.params.userCode,
                                            orderCode:$route.params.orderCode
                                        }
                                    }
                                }
                            ],
                            // path中需要的(:userName)在下对象中，该对象可以以props的形式在组件中进行接收
                            props($route){
                                return {
                                    userName:$route.params.userName,
                                    userCode:$route.params.userCode,
                                }
                            }
                        },
                    ],
                },
                //==================================================================================================
                {
                    name:'order',
                    path:`order`,
                    component:Order,             
                    meta:{},
                    children:[
                        {
                            name:'orderDetail',
                            path:`orderDetail/:orderCode`,
                            component:OrderDetail,
                            meta:{},
                            props($route){
                                return{
                                    orderCode:$route.params.orderCode
                                }
                            }
                        }
                    ]            
                },
                //==================================================
                {
                    name:'vendor',
                    path:`vendor`,
                    component:Vendor,
                    meta:{},
                    children:[
                        {
                            name:'vendorAdd',
                            path:`vendorAdd`,
                            component:VendorAdd,
                            meta:{}
                        },
                        
                        {
                            name:'vendorDetail',
                            path:`vendorDetail/:vendorCode/:vendorName`,
                            component:VendorDetail,
                            meta:{},
                            props($route){
                                return{
                                    vendorCode:$route.params.vendorCode,
                                    vendorName:$route.params.vendorName,
                                }
                            }
                        },
        
                    ]
        
                },
                {
                    name:'brand',
                    path:`brand`,
                    component:Brand,
                    meta:{},
                    children:[
                        {
                            name:'brandAdd',
                            path:`brandAdd`,
                            component:BrandAdd,
                            meta:{},
                        },
                        {
                            name:'brandDetail',
                            path:`brandDetail/:brandCode/:brandName`,
                            component:BrandDetail,
                            meta:{},
                            props($route){
                                return{
                                    brandCode:$route.params.brandCode,
                                    brandName:$route.params.brandName,
                                }
                            }
                        },
                    ]
                },
                {
                    name:'commodity',
                    path:`commodity`,
                    component:Commodity,
                    meta:{},
                    children:[
                        {
                            name:'commodityAdd',
                            path:`commodityAdd`,
                            component:CommodityAdd,
                            meta:{}, 
                        },
                        {
                            name:'commodityDetail',
                            path:`commodityDetail/:commodityCode/:commodityName`,
                            component:CommodityDetail,
                            meta:{}, 
                            props($route){
                                return{
                                    commodityCode:$route.params.commodityCode,
                                    commodityName:$route.params.commodityName,
                                }
                            }
                        }
                    ]
                }
            ]   
        },

        
    ]
})


import axios from 'axios'

//全局前置路由守卫，权限检测，初始化时也会被调用
const PATH = 'http://localhost:8081/commodity'
//全局前置路由守卫，权限检测，初始化时也会被调用
router.beforeEach((to,from,next)=>{
    const path = to.path
    //判断请求路径中是否带有这个地址
    if(path.indexOf("/login") === -1 ){
        const token = localStorage.getItem("header-token");
        if(token !== null && token !== ""){//是否带有token
            axios.get(`${BASE_PATH}/token/${token}`).then(//去后端判断这个token是否正确
                response=>{
                    if(response.data){//正确就放行
                        next();
                    }else{//错误就跳到登录页
                        next("/login")
                    }
                },
                error=>{
                    console.log('请求失败了',error.message)
                })
        }else{
            next("/login")
        }
    }else{
        next()
    }
})

//后置路由守卫，没有next属性,
router.afterEach((to,from)=>{
    //换title的作用
    document.title = to.meta.title || '商品信息管理系统'
})

export default router