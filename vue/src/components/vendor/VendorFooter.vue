<template>
    <div class="paging">
        <el-pagination
            @current-change="currentChange"
            @size-change="sizeChange"
            :page-size="8"
            :page-sizes="[8,16,24]"
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
        name:'VendorFooter',

        //计算属性
        computed:{
            pageSize(){return this.$children[0].internalPageSize},

            // 获取store中的数据（数组写法）  pageTotal  表示user总的数据量
            // pageTotal：总的数量，currentPage：当前页数，searchVendorName：搜索框的内容
            ...mapState('vendor',['pageTotal','currentPage','searchVendorName'])
        },

        //函数
        methods:{
            // 当页数发生改变时调用的函数   currentPage 页数  
            currentChange(currentPage){
                const data = {
                    currentPage:currentPage,
                    pageSize:this.pageSize,
                }
                if(this.searchVendorName !== ''){
                    data.searchVendorName = this.searchVendorName
                }

                this.$store.dispatch('vendor/queryVendors',data)
            },

        // 当每页数量改变时调用的函数
            sizeChange(pageSize){
                const data = {
                    currentPage:1,
                    pageSize:pageSize,
                }
                if(this.searchVendorName !== ''){
                    data.searchVendorName = this.searchVendorName
                }

                this.$store.dispatch('vendor/queryVendors',data)
            },
        },

        
        mounted(){
        },

    }
</script>

<style scoped>
    .paging{
    position: relative;
    left: 30%;
    top: 2%;
    }
</style>