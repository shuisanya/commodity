<template>

    <div class="user-head">

        <el-input 
        class="user-mhy"
        placeholder="请输入姓名"
        v-model="searchUserName"
        clearable>
        </el-input>

        <el-button type="info" icon="el-icon-search" class="user-mhy user-btn" @click="serchUsers">搜索</el-button>


    </div>


</template>

<script>
import { mapState } from 'vuex'
    export default {
        name:'UserHead',
        data() {
        return {
            searchUserName:"",
        }
        },
        computed:{
            ...mapState("user",["pageSize"])
        },
        methods:{
            serchUsers(){
                // console.log('xxx',this.searchUserName,this.pageSize)
                this.$store.dispatch('user/queryUsers',{currentPage:1,pageSize:this.pageSize,searchUserName:this.searchUserName})
            }
        },

        watch:{
            // 监听搜索框的变化取改变store中searchUserName的内容
            searchUserName(){
                this.$store.commit('user/CHANGE_SEARCH_USER_NAME',this.searchUserName)
            }
        }
    }
</script>

<style>
    .user-head{
        width: 100%;
        height:50px;
        font: 25px bolder;
        background-color: rgb(29, 231, 238);
    }
    .user-mhy{
        width: 200px;
        margin-right: 25px;
        position: relative;
        left: 30%;
    }
    .user-btn{
        width: 100px;
    }
</style>