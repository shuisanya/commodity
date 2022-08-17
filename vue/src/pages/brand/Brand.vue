<template>
    <div>
        <transition-group name="brand" appear>
            <BrandHead key="1"></BrandHead>
            <BrandList key="2"></BrandList>
            <BrandFooter key="3"></BrandFooter>
        </transition-group>
        <router-view></router-view>
    </div>
</template>

<script>

import BrandHead from '../../components/brand/BrandHead'
import BrandList from '../../components/brand/BrandList'
import BrandFooter from '../../components/brand/BrandFooter'

export default {
    name:'Brand',
    components:{BrandHead,BrandList,BrandFooter},
    data(){
        return{

        }
    },

    //路由到该页面需要查询数据
    mounted(){
        this.$store.dispatch('brand/queryBrands',{currentPage:1,pageSize:4})
    }

}
</script>

<style scoped>
    .brand-enter-active{
        animation: handoff 0.5s;
    }

    .brand-leave-active{
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