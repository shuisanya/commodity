<template>
    <transition name="userDetail" appear>
        <div class="user-detail">
            <form class="form-horizontal" method="post">
                <div>
                    <div class="user-title">
                        <span>用户信息</span>
                    </div>
                    <hr>
                    
                    <div class="form-group">
                        <div>
                            <label for="userCode" class="col-sm-2 control-label">用户编码：</label>
                            <div class="col-sm-10">
                                {{userDetail.userCode}}
                            </div>
                        </div>
                        
                        <div>
                            <label for="userName" class="col-sm-2 control-label">用户名称：</label>
                            <div class="col-sm-10">
                                {{userDetail.userName}}
                            </div>
                        </div>
                    </div>
    
                    <div class="form-group">
                        <div>
                            <label for="phone" class="col-sm-2 control-label">用户电话：</label>
                            <div class="col-sm-10">
                                {{userDetail.phone}}
                            </div>
                        </div>

                        <div>
                            <label for="address" class="col-sm-2 control-label">用户地址：</label>
                            <div class="col-sm-10" style="width:35%">
                                {{userDetail.address}}
                            </div>                            
                        </div>
                    </div>

                    <hr style="border-color:rgb(0, 0, 245)">
                </div>

                <div class="order-list">
                    <div class="user-title">
                        <span>订单列表</span>
                        <span>总的订单数:{{orders.length}}</span>
                    </div>
                    <hr>



                    <table>
                        <thead>
                            <tr>
                                <td>订单编号</td>
                                <td>订单邮编</td>
                                <td>购买时间</td>
                                <td>订单金额</td>
                                <td>操作</td>
                            </tr>
                        </thead>

                        <tbody>
                            <tr v-for="order in orders" :key="order.orderCode">
                                <td>{{order.orderCode}}</td>
                                <td>{{order.zip}}</td>
                                <td>{{orderTime(order.orderTime)}}</td>
                                <td>￥{{order.orderAmount.toFixed(2)}}</td>
                                <td>
                                    <el-button type="primary" icon="el-icon-edit" @click="viewOrderDetail(order.orderCode)" circle>订单详情</el-button>
                                </td>
                            </tr>
                        </tbody>
                    </table>


                    <router-view></router-view>
                </div>

            </form>

            <div class="not-buy-msg" v-if="!orders.length">
                <span >该用户没有购买东西哟！！！</span>
            </div>

            <div class="form-group user-btn">
                <el-button type="success" style="width:300px" @click="back">返回</el-button>
            </div>
        </div>
    </transition>


</template>

<script>
import { mapState } from 'vuex';
import moment from 'moment'
    export default {
        name:'UserDetail',
        data() {
            return {
            };
        },
        //路由传过来的参数
        props:['userCode'],

        computed:{
            ...mapState('user',['userList']),


            userDetail(){
                return (this.userList.filter((user) => {
                    return user.userCode === this.userCode
                }))[0]
            },

            orders(){
                return this.userDetail.orders
            }
        },
        methods:{

            orderTime(orderTime){
                return moment(orderTime).format("YYYY-MM-DD HH:mm:ss")
            },

            //路由到订单明细视图
            viewOrderDetail(orderCode){
                // console.log(orderCode)
                this.$router.push({
                    name:'userDetailOrderDetail',
                    params:{
                        orderCode:orderCode,
                    }
                })
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
    .user-title{
        font:20px bolder;
        color: rgb(235, 8, 186);
        position: relative;
        left: 30%;
    }
    .user-title span{
        padding-right: 25%;
    }
    .not-buy-msg{
        position: relative;
        left: 35%;
        top: 20%;
        color: red;
        font: 25px bold;
    }
    .icon-font{
        font-size: 25px;
    }
    .icon-span{
        font-size: 20px;
    }
    .user-detail{
        background-color: rgb(206, 233, 34, 0.9);
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
    .user-btn{
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


    .userDetail-enter-active{
        animation: handoff 0.5s;
    }

    .userDetail-leave-active{
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

    .order-list{
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
        height: 50px;
        background-color: #36e877;
        background-image: linear-gradient(45deg, #36e877 0%, #FFFB7D 100%);
    }
    tr {
        border-bottom: #660add solid 1px;
        text-align: center;
        height: 25px;
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