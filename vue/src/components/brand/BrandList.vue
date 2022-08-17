<template>
  <div class="brand-list">
    <el-card class="box-card brand-card" shadow="hover" v-for="brand in brandList" :key="brand.brandCode">
        <div slot="header" class="clearfix">
            <span>{{brand.brandName}}</span>
            <el-button style="float: right; padding: 5px 2px" 
            icon="el-icon-office-building" 
            @click="viewBrandDetail(brand.brandCode,brand.brandName)" 
            type="primary">
            管理
            </el-button>
        </div>
        <div class="brand-text item">
            <table>
                <tbody>
                    <td>
                        <tr>
                            <img src="../../assets/cola.jpg" alt="">
                        </tr>
                        <tr>
                            <span>编号：</span>{{brand.brandCode}}
                        </tr>
                        <tr>
                            <a v-show="brand.brandUrl"  :href="brand.brandUrl" target="_blank">官网</a>
                            <span style="font-size:14px;color:rgb(6, 79, 198) " v-show="!brand.brandUrl">没有官网哟</span>
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
    name:'BrandList',
    data(){
        return{

        }
    },
    computed:{
        ...mapState('brand',['brandList'])
      },
    methods:{
      viewBrandDetail(brandCode,brandName){
        this.$router.push({
          name:'brandDetail',
          params:{
            brandCode:brandCode,
            brandName,brandName,
          }
        })
      }
    }

}
</script>

<style scoped>
  .brand-list{
    width: 100%;
    height: 500px;
    overflow-y: auto;
    overflow-x: hidden;
  }
  .brand-card{
    float: left;
    margin-left: 8px;
    margin-bottom: 8px;
    overflow-y: auto;
    overflow-x: hidden;
  }
  .brand-text span {
    font-size: 16px;
    font-weight: bolder;
  }
  .brand-text tr {
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
    height: 470px;
  }
</style>