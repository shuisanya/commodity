<template>
  
    <div>
        <transition-group name="user" appear>
            <UserHead key="1"></UserHead>
            <UserList key="2"></UserList>
            <UserFooter key="3"></UserFooter>
        </transition-group>
        <keep-alive include="UserDetail">
            <router-view></router-view>
        </keep-alive>
    </div>

</template>

<script>
import UserHead from '../../components/user/UserHead'
import UserList from '../../components/user/UserList'
import UserFooter from '../../components/user/UserFooter'
    export default {
        name:'User',
        components:{UserHead,UserList,UserFooter},
        computed:{

        },
        //路由到该页面需要查询数据
        mounted(){
            this.$store.dispatch('user/queryUsers',{currentPage:1,pageSize:5})
        }
    }
</script>

<style scoped>

    .user-enter-active{
        animation: handoff 0.5s;
    }

    .user-leave-active{
        animation: handoff 0.5s reverse;
     }  

    @keyframes handoff {
        from{
            transform: translateY(-100%);
        }
        to{
            transform: translateY(0);
        }
    }

</style>