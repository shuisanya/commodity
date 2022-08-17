<template>
    <div class="paging">
        <el-pagination
            @current-change="currentChange"
            @size-change="sizeChange"
            :page-size="4"
            :page-sizes="[4,8,16]"
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
        name:'BrandFooter',

        //计算属性
        computed:{
            pageSize(){return this.$children[0].internalPageSize},

            // 获取store中的数据（数组写法）  pageTotal  表示user总的数据量
            // pageTotal：总的数量，currentPage：当前页数，searchVendorName：搜索框的内容
            ...mapState('brand',['pageTotal','currentPage','searchBrandName'])
        },

        //函数
        methods:{
            // 当页数发生改变时调用的函数   currentPage 页数  
            currentChange(currentPage){
                const data = {
                    currentPage:currentPage,
                    pageSize:this.pageSize,
                }
                if(this.searchBrandName !== ''){
                    data.searchBrandName = this.searchBrandName
                }

                this.$store.dispatch('brand/queryBrands',data)
            },

        // 当每页数量改变时调用的函数
            sizeChange(pageSize){
                const data = {
                    currentPage:1,
                    pageSize:pageSize,
                }
                if(this.searchBrandName !== ''){
                    data.searchBrandName = this.searchBrandName
                }

                this.$store.dispatch('brand/queryBrands',data)
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