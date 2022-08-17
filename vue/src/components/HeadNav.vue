<template>
    <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#FBAB7E"
    text-color="#2902c6"
    active-text-color="#FF2525">

        <el-menu-item index="1">我的工作台</el-menu-item>
        <el-menu-item index="2">个人中心</el-menu-item>
        <el-menu-item index="3">
          <el-button type="primary" @click="logout">注销</el-button>
        </el-menu-item>

    </el-menu>
</template>


<script>
  export default {
    name:'HeadNav',
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      logout(){
        localStorage.removeItem("admin")
        localStorage.removeItem("header-token")
        this.$router.push({
              name:'login'
        })
      }
    }
  }
</script>

<style scoped>

</style>