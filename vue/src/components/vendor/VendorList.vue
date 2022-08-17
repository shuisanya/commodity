<template>
  <div class="vendor-list">
    <el-card class="box-card vendor-card" shadow="hover" v-for="vendor in vendorList" :key="vendor.vendorCode">
        <div slot="header" class="clearfix">
            <span>{{vendor.vendorName}}</span>
            <el-button style="float: right; padding: 5px 2px" 
            icon="el-icon-office-building" 
            @click="viewVendorDetail(vendor.vendorCode,vendor.vendorName)" 
            type="primary">
            管理
            </el-button>
        </div>
        <div class="vendor-text item">
            <table>
                <tbody>
                    <td>
                        <tr>
                            <span>编号：</span>{{vendor.vendorCode}}
                        </tr>
                        <tr>
                            <span>负责人：</span>{{vendor.contact}}
                        </tr>
                        <tr>
                            <span>电话：</span>{{vendor.phone}}
                        </tr>
                        <tr>
                            <a v-show="vendor.vendorUrl"  :href="vendor.vendorUrl" target="_blank">官网</a>
                            <span style="font-size:14px;color:rgb(6, 79, 198) " v-show="!vendor.vendorUrl">没有官网哟</span>
                        </tr>
                    </td>
                </tbody>
            </table>
        </div>
    </el-card>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
export default {
    name:'VendorList',
    data(){
        return{

        }
    },
    computed:{
        ...mapState('vendor',['vendorList'])
      },
    methods:{
      viewVendorDetail(vendorCode,vendorName){
        this.$router.push({
          name:'vendorDetail',
          params:{
            vendorCode:vendorCode,
            vendorName,vendorName
          }
        })
      }
    }

}
</script>

<style scoped>
  .vendor-list{
    width: 100%;
    height: 500px;
    overflow-y: auto;
    overflow-x: hidden;
  }
  .vendor-card{
    float: left;
    margin-left: 8px;
    margin-bottom: 8px;
    overflow-y: auto;
    overflow-x: hidden;
  }
  .vendor-text span {
    font-size: 16px;
    font-weight: bolder;
  }
  .vendor-text tr {
      height: 28px;
  }

  .item {
    margin-bottom: 12px;
  }
  .clearfix span{
      font-size: 16px;
      font-weight: bolder;
      color: rgb(244, 76, 25);
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 295px;
    height: 230px;
  }
</style>