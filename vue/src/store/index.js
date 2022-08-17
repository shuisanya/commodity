//用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'
//必须在引入后使用vuex
Vue.use(Vuex)

import axios from 'axios'

const BASE_PATH = 'http://localhost:8081/commodity'


const admin = {
//命名空间
    namespaced:true,

    //准备一个actions 用于响应组件中的动作
    actions : {
        //写dispatch调用的函数，第一个参数:context上下文参数，第二个传入的参数

        // 根据页码查询 ================================================================================
        queryAdmins(context,page){
            //发起axios请求
            axios.get(`${BASE_PATH}/admin/queryAdmins/${page.currentPage}/${page.pageSize}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.commit('CHANGE_ADMIN_LIST',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },//=================================================================================================

        // 搜索框查询数据 *****************************************************************************************
        searchAdmins(context,data){
            data = JSON.stringify(data)
            //发起axios请求   ${data.currentPage}/${data.pageSize}/${data.searchAdminName}/${data.searchAdminRole}
            axios.get(`${BASE_PATH}/admin/searchAdmins/${data}`)
            .then(
                response=>{
                    context.commit('CHANGE_ADMIN_LIST',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )         
        },//************************************************************************************************************

        //添加用户时发送axios请求判断用户名是否存在 =================================================
        isAdminCodeExist(context,adminCode){
            axios.get(`${BASE_PATH}/admin/isAdminCodeExist/${adminCode}`)
            .then(
                response=>{
                    context.commit('IS_ADMIN_CODE',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )         
        },//============================================================================================

        //增加一个用户 **********************************************************************************
        addAdmin(context,admin){
            const adminString = JSON.stringify(admin);
            axios.post(`${BASE_PATH}/admin/addAdmin/${adminString}`)
            .then(
                response=>{
                    // console.log(response.data)
                    context.commit('IS_ADD_ADMIN_OK')
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )  
        },//***********************************************************************************

        //通过id更新一个admin    ========================================================
        updateAdminById(context,admin){
            // console.log(admin)
            const adminString = JSON.stringify(admin);
            axios.post(`${BASE_PATH}/admin/updateAdminById/${adminString}`)
            .then(
                response=>{
                    // console.log(response.data)
                    context.commit('CHANGE_ADMIN_DETAIL',admin)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )  
            // context.commit('CHANGE_ADMIN_DETAIL',admin)
        },//=================================================================================

        // 通过id删除一个用户  ******************************************************************
        deleteAdminById(context,id){
            axios.get(`${BASE_PATH}/admin/deleteAdminById/${id}`)
            .then(
                response=>{
                    // console.log(response.data)
                    //删除成功就从新查询改业数据
                    context.dispatch('searchAdmins',{currentPage:context.state.currentPage,
                    pageSize:context.state.pageSize,
                    searchAdminName:context.state.searchAdminName,
                    searchAdminRole:context.state.searchAdminRole})
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )  
        },//****************************************************************************************

    },

    //准备mutations 用于储存操作数据（state）
    mutations : {
        //commit调用的函数，第一个参数state，第二个是传入的参数
        //改变state中法数据
        CHANGE_ADMIN_LIST(state,page){
            //更新adminList 
            state.adminList = page.items
            //更新总数
            state.pageTotal = page.pageTotalCount
            // 更新当前所在页
            state.currentPage = page.currentPage
            // 更新每页的数量
            state.pageSize = page.pageSize
        },

        //头部搜索数据的传递
        SEARCH_DATA(state,data){
            state.searchAdminName = data.searchAdminName
            state.searchAdminRole = data.searchAdminRole
        },

        //修改isAdminCode
        IS_ADMIN_CODE(state,isAdminCode){
            state.isAdminCode = isAdminCode
        },

        //添加成功后的操作  （回到AdminLis页面并跳到最后一页，添加的那一页）
        IS_ADD_ADMIN_OK(state){
            //改变数据  同时AdminAdd中watch监听着这个属性  有后续操作
            state.isAddAdminOk++
        },

        //路由到admin详情页面时获取一个当前admin数据
        GET_ADMIN_DETAIL(state,id){
            // console.log(id)

            //通过传过来的id获取对应的admin   
            // 使用的是filter过滤的方法  （使用时注意  回调函数中需要 加 return 返回的是一个数组 所以需要取第一个）
            state.adminDetail = (state.adminList.filter((admin) => {
                return admin.id === id
            }))[0]
            
            // console.log(state.adminDetail)
        },

        //更新数据成功 就改变adminDetail的数据
        CHANGE_ADMIN_DETAIL(state,newAdminDetail){
            // console.log('xxxxx')
            state.adminDetail.adminCode = newAdminDetail.adminCode
            state.adminDetail.adminName = newAdminDetail.adminName
            state.adminDetail.sex = newAdminDetail.sex
            state.adminDetail.phone = newAdminDetail.phone
            state.adminDetail.address = newAdminDetail.address
            state.adminDetail.adminRole = newAdminDetail.adminRole
            state.adminDetail.birthday = newAdminDetail.birthday
        }
    },

    //准备state 用于储存数据
    state : {
        //adminList的数据
        adminList:[],
        //总记录数
        pageTotal:12,
        //当前的所在页
        currentPage:1,
        // 每页的数量
        pageSize:5,
        // 这是搜索框的内容
        searchAdminName:'',
        //这是选择框的value值
        searchAdminRole:'',
        //用于判断admin账号是否存在
        isAdminCode:false,
        //是否添加成功  （如果数据发送改变表示添加成功）
        isAddAdminOk:0,
        // 一个admin的详情
        adminDetail:'',

    },

    //准备getters 用于将state中的数据进行加工  类似于计算属性computed
    getters : {

    }

}

const user = {
    //命名空间
    namespaced:true,

    //准备一个actions 用于响应组件中的动作
    actions : {
        //查询用户信息列表
        queryUsers(context,data){
            // console.log('sasdasda',data)
            //发起axios请求
            data = JSON.stringify(data)
            axios.get(`${BASE_PATH}/user/queryUsers/${data}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.commit('CHANGE_USER_LIST',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        }
    },

    //准备mutations 用于储存操作数据（state）
    mutations : {
        CHANGE_USER_LIST(state,data){
            state.userList = data.items
            state.pageTotal = data.pageTotalCount
            state.currentPage = data.currentPage
            state.pageSize = data.pageSize
        },

        CHANGE_SEARCH_USER_NAME(state,searchUserName){
            state.searchUserName = searchUserName
        },

    },

    //准备state 用于储存数据
    state : {
        //用户列表
        userList:[],
        //总记录数
        pageTotal:12,
        //当前的所在页
        currentPage:1,
        // 每页的数量
        pageSize:5,
        //搜索框的内容
        searchUserName:"",

    },

    //准备getters 用于将state中的数据进行加工  类似于计算属性computed
    getters : {

    }    

}

const order = {
    //命名空间
    namespaced:true,

    //准备一个actions 用于响应组件中的动作
    actions : {
       //查询用户信息列表
       queryOrders(context,data){
            // console.log('sasdasda',data)
            //发起axios请求
            data = JSON.stringify(data)
            axios.get(`${BASE_PATH}/order/queryOrders/${data}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.commit('CHANGE_ORDER_LIST',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },
        //查询一个订单
        queryOrder(context,orderCode){
            axios.get(`${BASE_PATH}/order/queryOrder/${orderCode}`).then(
                response => {
                    context.commit('CHANGE_ORDER',response.data)
                    // console.log(response.data)
                },
                error => {
                    console.log('请求失败了',error.message)
                }
            )
        },
        //发货 一个订单明细
        shippingOrderDetail(context,data){
            axios.get(`${BASE_PATH}/order/shippingOrderDetail/${data.detailCode}`).then(
                response => {
                    context.dispatch('queryOrder',data.orderCode)
                    // console.log(response.data)
                },
                error => {
                    console.log('请求失败了',error.message)
                }
            )
        }

    },

    //准备mutations 用于储存操作数据（state）
    mutations : {
        CHANGE_ORDER_LIST(state,data){
            state.orderList = data.items
            state.pageTotal = data.pageTotalCount
            state.currentPage = data.currentPage
            state.pageSize = data.pageSize
        },
        CHANGE_ORDER(state,data){
            state.order = data
        },

        CHANGE_SEARCH_ORDER_CONTENT(state,data){
            
            for(let i in data){  
                state[i] = data[i]
            }
        }
    },

    //准备state 用于储存数据
    state : {
        //订单列表
        orderList:[],
        //订单明细列表
        order:'',
        //总记录数
        pageTotal:12,
        //当前的所在页
        currentPage:1,
        // 每页的数量
        pageSize:5,
        // 搜索框开始时间
        startTime:'',
        // 搜索框结束时间
        endTime:'',
        // 搜索框最高金额
        maxPrice:'',
        // 搜索框最低金额
        minPrice:'',
    },

    //准备getters 用于将state中的数据进行加工  类似于计算属性computed
    getters : {

    }    

}

const vendor = {
    namespaced:true,

    actions: {
        //根据查询条件查询供货商集合
        queryVendors(context,data){
            //发起axios请求
            data = JSON.stringify(data)
            axios.get(`${BASE_PATH}/vendor/queryVendors/${data}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.commit('CHANGE_VENDOR_LIST',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },
        //增加一个供应商
        addVendor(context,data){
            //发起axios请求
            data = JSON.stringify(data)
            // console.log(context)
            const lastPage = Math.ceil(context.state.pageTotal / context.state.pageSize) + 1
            axios.post(`${BASE_PATH}/vendor/addVendor/${data}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.dispatch('queryVendors',{
                        currentPage:lastPage,
                        pageSize:context.state.pageSize,
                    })
                    alert("添加成功！！！")
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )            
        },
        //查询一个vendor供应商
        queryVendorByVendorCode(context,vendorCode){
            axios.get(`${BASE_PATH}/vendor/queryVendorByVendorCode/${vendorCode}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.commit('CHANGE_VENDOR_DETAIL',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            ) 
        },
        //更新一个vendor供货商
        updateVendor(context,data){
            const commodities = data.commodities
            delete data.commodities
            const data1 = JSON.stringify(data)
            axios.post(`${BASE_PATH}/vendor/updateVendor/${data1}`).then(
                response=>{
                    data.commodities = commodities
                    context.commit('CHANGE_VENDOR_DETAIL',data)
                    alert(response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                    alert("更新失败")
                }
            )  
        },
        //搜索商品
        searchCommodities(context,searchCommodityName){
            console.log(searchCommodityName)
            axios.get(`${BASE_PATH}/vendor/searchCommodities/${searchCommodityName}`).then(
                response=>{
                    // console.log(response.data)
                    context.commit('CHANGE_SEARCH_COMMODITIES',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            ) 
        }
    },

    mutations: {
        CHANGE_VENDOR_LIST(state,data){
            state.vendorList = data.items
            state.pageTotal = data.pageTotalCount
            state.currentPage = data.currentPage
            state.pageSize = data.pageSize
        },
        CHANGE_SEARCH_VENDOR_NAME(state,searchVendorName){
            state.searchVendorName = searchVendorName
        },
        CHANGE_VENDOR_DETAIL(state,data){
            for(let i in data.commodities){
                data.commodities[i].ischecked=false
            }
            state.vendorDetail = data
        },
        CHANGE_SEARCH_COMMODITIES(state,data){
            state.serchCommodities = []

            for(let i in data ){
                let flag = true
                for(let j in state.vendorDetail.commodities){
                    if(data[i].commodityCode === state.vendorDetail.commodities[j].commodityCode){
                        flag = false
                    } 
                }
                if(flag){
                    data[i].ischecked=false
                    state.serchCommodities.push(data[i])
                }
            }
                
        },

    },

    state: {
        //供货商列表
        vendorList:[],
        //供货商详细列表
        vendorDetail:'',
        //总记录数
        pageTotal:12,
        //当前的所在页
        currentPage:1,
        // 每页的数量
        pageSize:5,
        //搜索内容
        searchVendorName:'',
        //搜索出来的商品集合
        serchCommodities:[],
    },

    getters: {

    }
}

const brand = {
    namespaced:true,

    actions: {
        //根据查询条件查询供货商集合
        queryBrands(context,data){
            //发起axios请求
            data = JSON.stringify(data)
            axios.get(`${BASE_PATH}/brand/queryBrands/${data}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.commit('CHANGE_BRAND_LIST',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },
        //查询一个品牌
        queryOneBrand(context,brandCode){
            axios.get(`${BASE_PATH}/brand/queryBrandByBrandCode/${brandCode}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.commit('CHANGE_BRAND_DETAIL',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },

        //增加一个品牌
        addBrand(context,data){
            //发起axios请求
            data = JSON.stringify(data)
            // console.log(context)
            const lastPage = Math.ceil(context.state.pageTotal / context.state.pageSize) + 1
            axios.post(`${BASE_PATH}/brand/addBrand/${data}`).then(
                response=>{
                    // console.log('请求成功了',response.data)
                    context.dispatch('queryBrands',{
                        currentPage:lastPage,
                        pageSize:context.state.pageSize,
                    })
                    alert("添加成功！！！")
                },
                error=>{
                    console.log('请求失败了',error.message)
                    alert("添加失败！！！")                    
                }
            )            
        },
    },

    mutations: {
        CHANGE_BRAND_LIST(state,data){
            state.brandList = data.items
            state.pageTotal = data.pageTotalCount
            state.currentPage = data.currentPage
            state.pageSize = data.pageSize
        },
        CHANGE_SEARCH_BRAND_NAME(state,data){
            state.searchBrandName = data
        },
        CHANGE_BRAND_DETAIL(state,data){
            state.brandDetail = data
        }

    },

    state: {
        //供货商列表
        brandList:[],
        //供货商详细列表
        brandDetail:'',
        //总记录数
        pageTotal:12,
        //当前的所在页
        currentPage:1,
        // 每页的数量
        pageSize:5,
        //搜索内容
        searchBrandName:'',
    },

    getters: {

    }
}

const commodity = {
    namespaced:true,

    actions: {
        //根据查询条件查询商品集合
        queryCommodities(context,data){
            //发起axios请求
            data = JSON.stringify(data)
            axios.get(`${BASE_PATH}/commodity/queryCommodities/${data}`).then(
                response=>{
                    context.commit('CHANGE_COMMODITY_LIST',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },
        //查询一个商品
        queryOneCommodity(context,commodityCode){
            axios.get(`${BASE_PATH}/commodity/queryCommodityByCommodityCode/${commodityCode}`).then(
                response=>{
                    context.commit('CHANGE_COMMODITY_DETAIL',response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },
        //更新一个商品
        updateCommodity(context,commodity){
            console.log(commodity)
            const strCommodity = JSON.stringify(commodity)
            console.log(strCommodity)
            axios.post(`${BASE_PATH}/commodity/updateCommodityByCommodityCode/${strCommodity}`).then(
                response=>{
                    context.commit('CHANGE_COMMODITY_DETAIL1',commodity)
                    alert("更新成功")
                },
                error=>{
                    alert("更新失败")
                    console.log('请求失败了',error.message)
                }
            )            
        },
        //查询所有的供货商 品牌  类别
        queryAllVendorsAndBrands(context){
            axios.get(`${BASE_PATH}/commodity/queryAllVendorsAndBrands`).then(
                response=>{
                    context.commit("CHANGE_ALL",response.data)
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        },
        //删除一个商品
        deleteCommodityByCommodityCode(context,commodityCode){
            axios.get(`${BASE_PATH}/commodity/deleteCommodityByCommodityCode/${commodityCode}`).then(
                response=>{
                    alert(response.data)
                    context.dispatch('queryCommodities',{
                        currentPage:context.state.currentPage,
                        pageSize:context.state.pageSize,
                        searchCommodityName:context.state.searchCommodityName
                    })
                },
                error=>{
                    alert('删除失败')
                    console.log('请求失败了',error.message)
                }
            )            
        }
       
    },

    mutations: {
        CHANGE_COMMODITY_LIST(state,data){
            state.commodityList = data.items
            state.pageTotal = data.pageTotalCount
            state.currentPage = data.currentPage
            state.pageSize = data.pageSize
        },
        CHANGE_COMMODITY_NAME(state,data){
            state.searchCommodityName = data
        },
        CHANGE_COMMODITY_DETAIL(state,data){
            state.commodityDetail = data
        },
        CHANGE_COMMODITY_DETAIL1(state,data){
            state.commodityDetail.commodityCode = data.commodityCode
            state.commodityDetail.commodityName = data.commodityName
            state.commodityDetail.specification = data.specification
            state.commodityDetail.model = data.model
            state.commodityDetail.unit = data.unit
            state.commodityDetail.marketPrice = data.marketPrice
            state.commodityDetail.salesPrice = data.salesPrice
            state.commodityDetail.costPrice = data.costPrice
            state.commodityDetail.commodityUrl = data.commodityUrl
            state.commodityDetail.commodityIntroduce = data.commodityIntroduce
            state.commodityDetail.inventoryCount = data.inventoryCount
            state.commodityDetail.brand.brandCode = data.brandCode
        },
        CHANGE_ALL(state,data){
            state.allVendors=[]
            state.allClassifies=[]
            state.allBrands=[]

            for(let i in data.vendors){
                let flag = true
                for(let j in state.commodityDetail.vendors){
                    if(data.vendors[i].vendorCode === state.commodityDetail.vendors[j].vendorCode){
                        flag = false
                    }
                }
                if(flag){
                    data.vendors[i].ischecked=false
                    state.allVendors.push(data.vendors[i])
                }
            }

            for(let i in data.classifies){
                let flag1 = true
                for(let j in state.commodityDetail.classifies){
                    if(data.classifies[i].classifyId === state.commodityDetail.classifies[j].classifyId){
                        flag1 = false
                    }
                }
                if(flag1){
                    data.classifies[i].ischecked=false
                    state.allClassifies.push(data.classifies[i])
                }
            }


  
            state.allBrands = data.brands
  

        }
    },

    state: {
        //供货商列表
        commodityList:[],
        //供货商详细列表
        commodityDetail:'',
        //总记录数
        pageTotal:12,
        //当前的所在页
        currentPage:1,
        // 每页的数量
        pageSize:5,
        //搜索内容
        searchCommodityName:'',
        //所有的供货商
        allVendors:[],
        //所有品牌
        allBrands:[],
        //所有类别
        allClassifies:[]
    },

    getters: {

    }
}


//创建store 并暴露store
export default new Vuex.Store({
    // 模块化
    modules:{
        admin,
        user,
        order,
        vendor,
        brand,
        commodity
    }
})