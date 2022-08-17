<template>

  <div class="order-list">

    <table>
      <thead>
        <tr>
            <td>订单编码</td>
            <td>邮编</td>
            <td>订单时间</td>
            <td>订单金额</td>
            <td>操作</td>
        </tr>
      </thead>

      <tbody>
        <tr v-for="order in orderList" :key="order.orderCode">
          <td>{{order.orderCode}}</td>
          <td>{{order.zip}}</td>
          <td>{{orderTime(order.orderTime)}}</td>
          <td>￥{{order.orderAmount.toFixed(2)}}</td>
          <td>
            <el-button type="primary" icon="el-icon-document" @click="viewOrderDetail(order.orderCode)" circle>明细详情</el-button>
          </td>
        </tr>
      </tbody>
    </table>
    
  </div>
</template>

<script>
// 将时间戳转换为日期的库
import moment from 'moment'
//引用vuex中的map映射
import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'

    export default {

    name:'OrderList',

    data() {
      return {
      };
    },

    components:{},

    computed:{
      ...mapState('order',['orderList'])
    },

    methods:{
       orderTime(orderTime){
         return moment(orderTime).format("YYYY-MM-DD HH:mm:ss")
       },

       viewOrderDetail(orderCode){
          this.$router.push({
            name:'orderDetail',
            params:{
              orderCode:orderCode,
            }
          })
       }
    },

    mounted(){


    },

    beforeDestory(){

    }

  }
</script>

<style scoped>
  .order-list{
    width: 100%;
    height: 500px;
    overflow-y: auto;
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
    background-color: #ea65dd;
    background-image: linear-gradient(45deg, #ea65dd 0%, #a86bea 100%);
  }
  tr {
      border-bottom: #57626f solid 1px;
      text-align: center;
  }
 tbody tr:nth-of-type(even){
    background-color: rgb(141, 161, 228);
  }
 tbody tr:nth-of-type(odd){
    background-color: rgb(237, 206, 135);
  }
  tbody tr:hover{
    background-color: rgb(233, 55, 147);
  }
</style>