<template>

  <div>
    <!-- <Admin></Admin> -->
    <!-- keep-alive  路由走后不销毁组件 -->
    <keep-alive :include="['admin','user']">
      <router-view></router-view>
    </keep-alive>
  </div>
</template>

<script>
import Admin from '../pages/admin/Admin'

    export default {
      name:'Pincipal',
      components:{Admin
      },

  }
</script>

<style scoped>

</style>