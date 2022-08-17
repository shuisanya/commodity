<template>
    <div>
        <transition-group name="vendor" appear>
            <VendorHead key="1"></VendorHead>
            <VendorList key="2"></VendorList>
            <VendorFooter key="3"></VendorFooter>
        </transition-group>
        <router-view></router-view>
    </div>
</template>

<script>

import VendorHead from '../../components/vendor/VendorHead'
import VendorList from '../../components/vendor/VendorList'
import VendorFooter from '../../components/vendor/VendorFooter'

export default {
    name:'Vendor',
    components:{VendorHead,VendorList,VendorFooter},
    data(){
        return{

        }
    },

    //路由到该页面需要查询数据
    mounted(){
        this.$store.dispatch('vendor/queryVendors',{currentPage:1,pageSize:8})
    }

}
</script>

<style scoped>
    .vendor-enter-active{
        animation: handoff 0.5s;
    }

    .vendor-leave-active{
        animation: handoff 0.5s reverse;
     }  

    @keyframes handoff {
        from{
            transform: translateX(100%);
        }
        to{
            transform: translateX(0);
        }
    }
</style>