<template>

    <div class="commodity-head">
        <el-button class="add-btn" icon="el-icon-plus" type="primary" @click="viewCommodityAdd">增加</el-button>

        <el-input 
        class="commodity-mhy"
        placeholder="请输入商品名"
        v-model="searchCommodityName"
        clearable>
        </el-input>

        <el-button type="info" icon="el-icon-search" class="commodity-mhy commodity-btn" @click="searchCommodities" >搜索</el-button>

    </div>


</template>

<script>
import { mapState } from 'vuex'
    export default {
        name:'CommodityHead',
        data() {
        return {
            searchCommodityName: '',
        }
        },
        computed:{
            ...mapState('commodity',['pageSize'])
        },
        methods:{
            searchCommodities(){
                this.$store.dispatch('commodity/queryCommodities',{currentPage:1,pageSize:this.pageSize,searchCommodityName:this.searchCommodityName})
            },
            viewCommodityAdd(){
                this.$router.push({
                    name:'commodityAdd'
                })
            }
        },

        watch:{
            searchCommodityName(){
                this.$store.commit('commodity/CHANGE_COMMODITY_NAME',this.searchAdminName)
            }
        }
    }
</script>

<style>
    .commodity-head{
        width: 100%;
        height:50px;
        font: 25px bolder;
        background-color: rgb(39, 201, 234);
    }
    .commodity-mhy{
        width: 200px;
        margin-right: 25px;
        position: relative;
        left: 30%;
    }
    .commodity-btn{
        width: 100px;
    }
    .add-btn{
        position: relative;
        left: 5%;
    }
</style>