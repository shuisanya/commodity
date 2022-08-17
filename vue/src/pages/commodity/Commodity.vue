<template>
  
    <div>
        <transition-group name="commodity" appear>
            <CommodityHead key="1"></CommodityHead>
            <CommodityList key="2"></CommodityList>
            <CommodityFooter key="3"></CommodityFooter>
        </transition-group>
        <router-view></router-view>
    </div>

</template>

<script>
import CommodityHead from '../../components/commodity/CommodityHead'
import CommodityList from '../../components/commodity/CommodityList'
import CommodityFooter from '../../components/commodity/CommodityFooter'
    export default {
        name:'Commodity',
        components:{CommodityHead,CommodityList,CommodityFooter},
        computed:{

        },
        //路由到该页面需要查询数据
        mounted(){
            this.$store.dispatch('commodity/queryCommodities',{currentPage:1,pageSize:5})
        }
    }
</script>

<style scoped>

    .commodity-enter-active{
        animation: handoff 0.5s;
    }

    .commodity-leave-active{
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