<template>
  
  <div>
      <router-view></router-view>
  </div>

</template>

<script>
    import Manage from './pages/Manage'
    import Login from './pages/Login'
    export default {
        name:'App',
        components:{Manage,Login}, 
        
    }
</script>

<style>

</style>

