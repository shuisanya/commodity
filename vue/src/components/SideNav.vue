<template>
  <el-col  class="el-col">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#FBAB7E"
      text-color="#2902c6"
      active-text-color="#FF2525">

      <el-menu-item index="7" @click="toHome">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-s-check"></i>
          <span>管理员</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1" @click="toAdmin">管理员列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>


      <el-submenu index="2">
        <template slot="title">
            <i class="el-icon-user-solid"></i>
            <span slot="title">用户信息</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="2-1" @click="toUser" >用户列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>


     <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-tickets"></i>
                <span slot="title">订单信息</span>
            </template>

            <el-menu-item-group>
                <el-menu-item index="3-1" @click="toOrder">订单列表</el-menu-item>
            </el-menu-item-group>
      </el-submenu>

      <el-submenu index="4">
        <template slot="title">
            <i class="el-icon-setting"></i>
            <span slot="title">商品信息</span>
        </template>

        <el-menu-item-group>
            <el-menu-item index="4-1" @click="toCommodity">商品列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>


      <el-submenu index="5">
        <template slot="title">
            <i class="el-icon-office-building"></i>
            <span slot="title">供货商信息</span>
        </template>
        <el-menu-item-group>
            <el-menu-item index="5-1" @click="toVendor">供货商列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>


      <el-submenu index="6">
        <template slot="title">
            <i class="el-icon-goods"></i>
            <span slot="title">品牌信息</span>
        </template>

        <el-menu-item-group>
            <el-menu-item index="6-1" @click="toBrand">品牌列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

    </el-menu>
  </el-col>

</template>

<script>
import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'

  export default {
    name:'SideNav',

    //计算属性
    computed:{
        //获取store中的数据（数组写法）  pageTotal  表示admin总的数据量
        ...mapState('admin',['adminList'])
    },

    //函数
    methods: {

      // 导航栏进入时并路由跳转
      toHome(){
        this.$router.push({
          name:'home'
        })
      },
      toAdmin(){
// 查询数据放到了路由的admin页面的mounted里面了
        // this.$store.dispatch('admin/queryAdmins',{currentPage:1,pageSize:5})
        // //路由到该页面
        this.$router.push({
          name:'admin'
        })
      },
      toUser(){
          this.$router.push({
          name:'user'
        })
      },
      toOrder(){
          this.$router.push({
          name:'order'
        })
      },
      toVendor(){
        this.$router.push({
          name:'vendor'
        })
      },
      toBrand(){
        this.$router.push({
          name:'brand'
        })
      },
      toCommodity(){
        this.$router.push({
          name:'commodity'
        })        
      }

    }
  }
</script>

<style scoped>


</style>