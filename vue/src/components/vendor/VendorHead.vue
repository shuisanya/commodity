<template>

    <div class="vendor-head">
        <el-button class="add-btn" icon="el-icon-plus" type="primary" @click="viewVendorAdd">增加</el-button>

        <el-input 
        class="vendor-mhy"
        placeholder="请输入供货商名"
        v-model="searchVendorName"
        clearable>
        </el-input>

        <el-button type="info" icon="el-icon-search" class="vendor-mhy vendor-btn" @click="searchVendors">搜索</el-button>

    </div>

</template>


<script>
import { mapState } from 'vuex'
    export default {
        name:'VendorHead',
        data() {
        return {
            //搜索匡内容
            searchVendorName:''
        }
        },
        computed:{
            ...mapState('vendor',['pageSize'])
        },
        methods:{
            searchVendors(){
                this.$store.dispatch('vendor/queryVendors',{currentPage:1,pageSize:this.pageSize,searchVendorName:this.searchVendorName})
            },
            viewVendorAdd(){
                this.$router.push({
                    name:'vendorAdd',
                })
            }

        },

        watch:{
            searchVendorName(){
                this.$store.commit('vendor/CHANGE_SEARCH_VENDOR_NAME',this.searchVendorName)
            }
        }
    }
</script>

<style scoped>
    .vendor-head{
        width: 100%;
        height:50px;
        font: 25px bolder;
        background-color: rgb(215, 210, 202 , 0.6);
    }
    .vendor-mhy{
        width: 200px;
        margin-right: 25px;
        position: relative;
        left: 30%;
    }
    .vendor-btn{
        width: 100px;
    }
    .vendor-btn{
        position: relative;
        left: 35%;
    }
</style>