<template>
  
    <div>
        <transition-group name="order" appear>
            <OrderHead key="1"></OrderHead>
            <OrderList key="2"></OrderList>
            <OrderFooter key="3"></OrderFooter>
        </transition-group>
        <router-view></router-view>
    </div>

</template>

<script>
import OrderHead from '../../components/order/OrderHead'
import OrderList from '../../components/order/OrderList'
import OrderFooter from '../../components/order/OrderFooter'
    export default {
        name:'Order',
        components:{OrderHead,OrderList,OrderFooter},
        computed:{

        },
        //路由到该页面需要查询数据
        mounted(){
            this.$store.dispatch('order/queryOrders',{currentPage:1,pageSize:5})
        }
    }
</script>

<style scoped>

    .order-enter-active{
        animation: handoff 0.5s;
    }

    .order-leave-active{
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