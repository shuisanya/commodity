<template>
    <transition name="orderDetail" appear>
        <div class="order-detail">
            <form class="form-horizontal" method="post">

                <el-descriptions class="margin-top" :column="3" size="medium" border>
                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-postcard"></i>
                        订单号
                    </template>
                    {{order.orderCode}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-truck"></i>
                        订单邮编
                    </template>
                    {{order.zip}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-shopping-cart-1"></i>
                        订单金额
                    </template>
                    ￥{{order.orderAmount.toFixed(2)}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-time"></i>
                        订单时间
                    </template>
                    {{changeTime(order.orderTime)}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-fold"></i>
                        订单明细数量
                    </template>
                    {{order.orderDetails.length}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-unfold"></i>
                        已发货数量
                    </template>
                    {{shippingCount(order.orderDetails)}}
                    </el-descriptions-item>
                </el-descriptions>

                <div>
                    <hr style="border-color:rgb(0, 0, 245)">
                </div>

                <div class="order-detail-list">

                    <table>
                        <thead>
                            <tr>
                                <td>明细编号</td>
                                <td>商品名称</td>
                                <td>商品单价</td>
                                <td>数量</td>
                                <td>总金额</td>
                                <td>发货时间</td>
                                <td>商品状态</td>
                                <td>操作</td>
                            </tr>
                        </thead>

                        <tbody>
                            <tr v-for="orderDetail in order.orderDetails" :key="orderDetail.detailsCode">
                                <td>{{orderDetail.detailsCode}}</td>
                                <td>{{orderDetail.commodity.commodityName}}</td>
                                <td>{{orderDetail.unitPrice}}</td>
                                <td>{{orderDetail.count}}</td>
                                <td>￥{{orderDetail.amount.toFixed(2)}}</td>
                                <td>{{changeTime(orderDetail.shippingTime)}}</td>
                                <td>{{orderState(orderDetail.orderState)}}</td>
                                <td>
                                    <el-button 
                                    v-if="orderDetail.orderState === 0" 
                                    type="primary" icon="el-icon-s-promotion"
                                    @click="shippingOrderDetail(orderDetail.detailsCode,orderDetail.commodity.commodityName)"
                                    round>
                                    发货
                                    </el-button>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>

            </form>

            <div class="form-group order-detail-btn">
                <el-button type="success" style="width:300px" @click="back">返回</el-button>
            </div>
        </div>
    </transition>


</template>

<script>
import { mapState } from 'vuex';
import moment from 'moment'
    export default {
        name:'OrderDetail',
        data() {
            return {
            };
        },
        //路由传过来的参数
        props:['orderCode'],

        computed:{
            ...mapState('order',['order']),
        },
        methods:{
            changeTime(time){
                if(time){
                    return moment(time).format("YYYY-MM-DD HH:mm:ss")
                }
            },
           
           //状态判断  把数字转成文字
           orderState(orderState){
               if(orderState === 0){
                   return '未发货'
               }else if(orderState === 1){
                   return '运输中'
               }else if(orderState === 2){
                   return '已签收'
               }
           },
           //获取已发货数量
           shippingCount(orderDetails){
                return (orderDetails.filter((orderDetail)=>{
                   return orderDetail.orderState !== 0
               })).length
           },
           //发货
           shippingOrderDetail(detailCode,commodityName){
               if(confirm('确认发货【' +commodityName+ '】，订单号为：' + detailCode)){
                   this.$store.dispatch('order/shippingOrderDetail',{detailCode:detailCode,orderCode:this.orderCode})
               }
           },

            // 回退            
            back(){
                // 返回上一次路由位置
                this.$router.back()
            },

        },

        watch:{

        },

        created(){
        },

        mounted(){
            this.$store.dispatch('order/queryOrder',this.orderCode)
        },


// 使用下面两个钩子方法必须让 这个组件不销毁
        //组件被激活
        activated(){
        },

        //组件失活
        deactivated(){

        }


  }
</script>

<style scoped>
    .order-detail-title{
        font:20px bolder;
        color: rgb(235, 8, 186);
        position: relative;
        left: 10%;
    }
    .icon-font{
        font-size: 25px;
    }
    .icon-span{
        font-size: 20px;
    }
    .order-detail{
        background-color: rgba(135, 242, 103, 0.9);
        width: 79%;
        height: 97%;
        position: absolute;
        top: 0px;
        overflow-x: hidden;
    }
    .form-horizontal{
        position: relative;
        left: 2%;
        top: 5%;
    }
    .col-sm-10{
        width: 15%;
        line-height: 35px;
        color: rgb(0, 0, 245);
        font: 20px bolder;
    }
    .order-detail-btn{
        position: absolute;
        left: 35%;
        width: 300px;
        top: 93%;
    }
    .error{
        position: relative;
        color: red;
        font-size: 25px;
        left: 10%;
    }


    .orderDetail-enter-active{
        animation: handoff 0.5s;
    }

    .orderDetail-leave-active{
        animation: handoff 0.5s reverse;
     }  

    @keyframes handoff {
        from{
            transform: translateY(100%);
        }
        to{
            transform: translateY(0);
        }
    }

    .order-detail-list{
        position: relative;
        width: 100%;
        height: 100%;
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
        height: 55px;
        background-color: #36e877;
        background-image: linear-gradient(45deg, #36e877 0%, #FFFB7D 100%);
    }
    tr {
        border-bottom: #660add solid 1px;
        text-align: center;
        height: 30px;
    }
    tbody tr:nth-of-type(even){
        background-color: rgb(223, 240, 241);
    }
    tbody tr:nth-of-type(odd){
        background-color: rgb(246, 220, 245);
    }
    tbody tr:hover{
        background-color: rgb(228, 72, 20);
    }

</style>