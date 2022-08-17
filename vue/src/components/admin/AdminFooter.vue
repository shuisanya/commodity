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
        name:'AdminFooter',

        //计算属性
        computed:{
            //this.$children[0].internalPageSize 这是每页数量
            pageSize(){return this.$children[0].internalPageSize},

            //获取store中的数据（数组写法）  pageTotal  表示admin总的数据量
            ...mapState('admin',['pageTotal','searchAdminName','searchAdminRole','currentPage'])
        },

        //函数
        methods:{
            // 当页数发生改变时调用的函数   currentPage 页数  
            currentChange(currentPage){
                // this.$children[0].internalCurrentPage = 3
                // 如果搜索框没有数据就执行这个
                if(this.searchAdminName === '' && this.searchAdminRole === ''){
                    this.$store.dispatch('admin/queryAdmins',{currentPage:currentPage,pageSize:this.pageSize})
                } else{
                    this.$store.dispatch('admin/searchAdmins',
                    {currentPage:currentPage,
                    pageSize:this.pageSize,
                    searchAdminName:this.searchAdminName,
                    searchAdminRole:this.searchAdminRole
                    })
                }
            },

        // 当每页数量改变时调用的函数
            sizeChange(pageSize){
                // 如果搜索框没有数据就执行这个
                if(this.searchAdminName === '' && this.searchAdminRole === ''){
                    this.$store.dispatch('admin/queryAdmins',{currentPage:1,pageSize:pageSize})
                } else{
                    this.$store.dispatch('admin/searchAdmins',
                    {currentPage:1,
                    pageSize:pageSize,
                    searchAdminName:this.searchAdminName,
                    searchAdminRole:this.searchAdminRole
                    })
                }
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