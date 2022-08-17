<template>
    <transition name="orderDetail" appear>
        <div class="order-detail-list">

            <div class="order-detail-title">
                <span>订单明细</span>
                <span>订单号:{{orderCode}}</span>
                <span>订单明细数:{{orderDetails.length}}</span>
            </div>
            <hr>

            <table>
                <thead>
                    <tr>
                        <td>订单明细编号</td>  
                        <td>商品名称</td>  
                        <td>商品单价</td>
                        <td>商品数量</td>
                        <td>总金额</td>
                    </tr>
                </thead>

                <tbody>
                    <tr v-for="orderDetail in orderDetails" :key="orderDetail.detailsCode" >
                        <td>{{orderDetail.detailsCode}}</td>
                        <td>{{orderDetail.commodity.commodityName}}</td>
                        <td>￥{{orderDetail.unitPrice.toFixed(2)}}</td>
                        <td>{{orderDetail.count}}</td>
                        <td>￥{{orderDetail.amount.toFixed(2)}}</td>
                    </tr>
                </tbody>
            </table>


        </div>
    </transition>
</template>

<script>
import { mapState } from 'vuex'
    export default {
        name:'UserDetailOrderDerail',
        data(){
            return{

            }
        },
        props:['orderCode','userCode'],
        computed:{
            ...mapState('user',['userList']),

            orderDetails(){
                const userDetail = (this.userList.filter((user) => {
                    return user.userCode === this.userCode
                }))[0]

                const order = (userDetail.orders.filter((order)=>{
                    return order.orderCode === this.orderCode
                }))[0]

                return order.orderDetails
            },

        }

    }
</script>

<style scoped>
    .order-detail-title{
        font:20px bolder;
        color: rgb(28, 0, 165);
        position: relative;
        left: 13%;
    }
    .order-detail-title span{
        padding-right: 12%;
        background-color: aqua;
    }


    .order-detail-list{
        position: absolute;
        top: 47%;
        width: 100%;
        height: 50%;
        background-color: rgb(246, 220, 245, 0.7);
        /* overflow-y: auto;
        overflow-x: hidden; */
    }
    table{
        color: #000000;
        width: 100%;
        height:100%;
        border-collapse: collapse;
        border-spacing: 0;
    }
    thead{
        color: #000000;
        font-size: 20px;
        font-weight: bolder;
        height: 40px;
        background-color: #73c1f1;
        background-image: linear-gradient(45deg, #73c1f1 0%, #FFFB7D 100%);
    }
    tr {
        border-bottom: #660add solid 1px;
        text-align: center;
        height: 25px;
    }
    tbody tr:nth-of-type(even){
        background-color: rgb(223, 240, 241, 0.7);
    }
    tbody tr:nth-of-type(odd){
        background-color: rgb(246, 220, 245, 0.7);
    }
    tbody tr:hover{
        background-color: rgb(228, 72, 20, 0.7);
    }



    .orderDetail-enter-active{
        animation: handoff 0.5s;
    }

    .orderDetail-leave-active{
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