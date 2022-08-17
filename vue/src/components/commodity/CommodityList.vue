<template>

  <div class="commodity-list">

    <table>
      <thead>
        <tr>
            <td>编号</td>
            <td>名称</td>
            <td>规格</td>
            <td>型号</td>
            <td>计量单位</td>
            <td>市场价</td>
            <td>销售价</td>
            <td>成本价</td>
            <td>库存量</td>
            <td>操作</td>
        </tr>
      </thead>

      <tbody>
        <tr v-for="commodity in commodityList" :key="commodity.commodityCode">
          <td>{{commodity.commodityCode}}</td>
          <td>{{commodity.commodityName}}</td>
          <td>{{commodity.specification}}</td>
          <td>{{commodity.model}}</td>
          <td>{{commodity.unit}}</td>
          <td>￥{{commodity.marketPrice.toFixed(2)}}</td>
          <td>￥{{commodity.salesPrice.toFixed(2)}}</td>
          <td>￥{{commodity.costPrice.toFixed(2)}}</td>
          <td>{{commodity.inventoryCount}}</td>
          <td>
            <el-button type="primary" icon="el-icon-edit" @click="lookCommodityDetail(commodity.commodityCode,commodity.commodityName)" circle>详情</el-button>
            <el-button type="danger" icon="el-icon-close" @click="deleteCommodityById(commodity.commodityCode,commodity.commodityName)" circle>删除</el-button> 
          </td>
        </tr>
      </tbody>

    </table>
    
  </div>
</template>

<script>
//引用vuex中的map映射
import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'

    export default {

    name:'CmmodityList',

    data() {
      return {
      };
    },

    components:{},

    computed:{
      ...mapState('commodity',['commodityList'])
    },

    methods:{
      lookCommodityDetail(commodityCode,commodityName){
        this.$router.push({
          name:'commodityDetail',
          params:{
            commodityCode:commodityCode,
            commodityName:commodityName
          }
        })
      },
      deleteCommodityById(commodityCode,commodityName){
        if(confirm("确定要删除【" + commodityName + "】?")){
            this.$store.dispatch('commodity/deleteCommodityByCommodityCode',commodityCode)
        }
        
      }
      
    },

    mounted(){

    },

    beforeDestory(){
      }

  }
</script>

<style scoped>
  .commodity-list{
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
    background-color: #85FFBD;
    background-image: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);
  }
  tr {
      border-bottom: #57626f solid 1px;
      text-align: center;
  }
 tbody tr:nth-of-type(even){
    background-color: rgb(239, 191, 159);
  }
 tbody tr:nth-of-type(odd){
    background-color: rgb(229, 124, 126);
  }
  tbody tr:hover{
    background-color: rgb(69, 81, 239);
  }
</style>