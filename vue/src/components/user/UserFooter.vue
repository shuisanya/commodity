<template>
    <div class="paging">
        <el-pagination
            @current-change="currentChange"
            @size-change="sizeChange"
            :page-size="5"
            :page-sizes="[5,10,15]"
            background
            :current-page="currentPage"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageTotal"
            >
        </el-pagination>
    </div>
</template>

<script>
import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'

    export default {
        //组件名
        name:'UserFooter',

        //计算属性
        computed:{
            pageSize(){return this.$children[0].internalPageSize},

            //获取store中的数据（数组写法）  pageTotal  表示user总的数据量
            //pageTotal：总的数量，currentPage：当前页数，searchUserName：搜索框的内容
            ...mapState('user',['pageTotal','currentPage','searchUserName'])
        },

        //函数
        methods:{
            // 当页数发生改变时调用的函数   currentPage 页数  
            currentChange(currentPage){
                // console.log(currentPage,this.pageSize)
                //currentPage：当前页数，pageSize：当前每页的数量，searchUserName：搜索框的内容
                this.$store.dispatch('user/queryUsers',{currentPage:currentPage,pageSize:this.pageSize,searchUserName:this.searchUserName})

            },

        // 当每页数量改变时调用的函数
            sizeChange(pageSize){
                // console.log(pageSize,this.searchUserName)
                // pageSize：改变的每页的数量    searchUserName：搜索框的内容
                this.$store.dispatch('user/queryUsers',{currentPage:1,pageSize:pageSize,searchUserName:this.searchUserName})     
            },
        },

        
        mounted(){
        },

    }
</script>

<style>
    .paging{
    position: relative;
    left: 30%;
    top: 2%;
    }
</style>