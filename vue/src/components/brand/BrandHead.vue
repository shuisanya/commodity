<template>

    <div class="brand-head">
        <el-button class="add-btn" icon="el-icon-plus" type="primary" @click="viewBrandAdd">增加</el-button>

        <el-input 
        class="brand-mhy"
        placeholder="请输入供货商名"
        v-model="searchBrandName"
        clearable>
        </el-input>

        <el-button type="info" icon="el-icon-search" class="brand-mhy brand-btn" @click="searchBrands">搜索</el-button>

    </div>

</template>


<script>
import { mapState } from 'vuex'
    export default {
        name:'BrandHead',
        data() {
        return {
            //搜索匡内容
            searchBrandName:''
        }
        },
        computed:{
            ...mapState('brand',['pageSize'])
        },
        methods:{
            searchBrands(){
                this.$store.dispatch('brand/queryBrands',{currentPage:1,pageSize:this.pageSize,searchBrandName:this.searchBrandName})
            },
            viewBrandAdd(){
                this.$router.push({
                    name:'brandAdd',
                })
            }

        },

        watch:{
            searchBrandName(){
                this.$store.commit('brand/CHANGE_SEARCH_BRAND_NAME',this.searchBrandName)
            }
        }
    }
</script>

<style scoped>
    .brand-head{
        width: 100%;
        height:50px;
        font: 25px bolder;
        background-color: rgb(215, 210, 202 , 0.6);
    }
    .brand-mhy{
        width: 200px;
        margin-right: 25px;
        position: relative;
        left: 30%;
    }
    .brand-btn{
        width: 100px;
    }
    .brand-btn{
        position: relative;
        left: 35%;
    }
</style>