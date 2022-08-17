<template>

    <div class="admin-head">
        <el-button class="add-btn" icon="el-icon-plus" type="primary" @click="adminAddRouter">增加</el-button>

        <el-input 
        class="admin-mhy"
        placeholder="请输入姓名"
        v-model="searchAdminName"
        clearable>
        </el-input>

        <el-select class="admin-mhy" v-model="searchAdminRole" clearable placeholder="请选择">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>

        <el-button type="info" icon="el-icon-search" class="admin-mhy admin-btn" @click="searchAdmins">搜索</el-button>

    </div>


</template>

<script>
import { mapState } from 'vuex'
    export default {
        name:'AdminHead',
        data() {
        return {
            options: [{
            value: '1',
            label: '经理'
            }, {
            value: '2',
            label: '主管'
            }, {
            value: '3',
            label: '普通员工'
            }],
            searchAdminRole: '',
            searchAdminName: '',
        }
        },
        computed:{
            ...mapState('admin',['pageSize'])
        },
        methods:{
            adminAddRouter(){
                //路由到该页面
                this.$router.push({
                    name:'adminAdd',
                })
            },

            searchAdmins(){
                // 根据搜索的内容和页码还有每页数量查询
                this.$store.dispatch('admin/searchAdmins',{searchAdminRole:this.searchAdminRole,
                                                            searchAdminName:this.searchAdminName,
                                                            pageSize:this.pageSize,
                                                            currentPage:1})
            }
        },

        watch:{
            searchAdminRole(){
                this.$store.commit('admin/SEARCH_DATA',{searchAdminRole:this.searchAdminRole,searchAdminName:this.searchAdminName})
            },
            searchAdminName(){
                this.$store.commit('admin/SEARCH_DATA',{searchAdminRole:this.searchAdminRole,searchAdminName:this.searchAdminName})
            }
        }
    }
</script>

<style>
    .admin-head{
        width: 100%;
        height:50px;
        font: 25px bolder;
        background-color: darkcyan;
    }
    .admin-mhy{
        width: 200px;
        margin-right: 25px;
        position: relative;
        left: 30%;
    }
    .admin-btn{
        width: 100px;
    }
    .add-btn{
        position: relative;
        left: 5%;
    }
</style>