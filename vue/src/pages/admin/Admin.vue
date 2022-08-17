<template>
  
    <div>
        <transition-group name="admin" appear>
            <AdminHead key="1"></AdminHead>
            <AdminList key="2"></AdminList>
            <AdminFooter key="3"></AdminFooter>
        </transition-group>
        <!-- <AdminAdd></AdminAdd> -->
        <router-view></router-view>
    </div>

</template>

<script>
import AdminHead from '../../components/admin/AdminHead'
import AdminList from '../../components/admin/AdminList'
import AdminFooter from '../../components/admin/AdminFooter'
    export default {
        name:'Admin',
        components:{AdminHead,AdminList,AdminFooter},
        computed:{

        },
        //路由到该页面需要查询数据
        mounted(){
            this.$store.dispatch('admin/queryAdmins',{currentPage:1,pageSize:5})
        }
    }
</script>

<style scoped>
    .admin-enter-active{
        animation: handoff 0.5s;
    }

    .admin-leave-active{
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