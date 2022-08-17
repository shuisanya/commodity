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
        name:'OrderFooter',

        //计算属性
        computed:{
            //this.$children[0].internalPageSize 这是每页数量
            pageSize(){return this.$children[0].internalPageSize},

            //获取store中的数据（数组写法）  pageTotal  表示order总的数据量
            ...mapState('order',['pageTotal','currentPage','startTime','endTime','maxPrice','minPrice'])
        },

        //函数
        methods:{
            // 当页数发生改变时调用的函数   currentPage 页数  
            currentChange(currentPage){
                // 页码和页面的条数
                const data = {
                    currentPage:currentPage,
                    pageSize:this.pageSize,
                }
                // 搜索框时间内容
                if(this.startTime !== '' && this.endTime !== ''){
                    data.startTime = this.startTime
                    data.endTime = this.endTime
                }
                //搜索框金额内容
                if(this.maxPrice !== '' && this.minPrice !== ''){
                    data.maxPrice = this.maxPrice
                    data.minPrice = this.minPrice
                }
                this.$store.dispatch('order/queryOrders',data)
            },

        // 当每页数量改变时调用的函数
            sizeChange(pageSize){
                const data = {
                    currentPage:1,
                    pageSize:pageSize,
                }
                if(this.startTime !== '' && this.endTime !== ''){
                    data.startTime = this.startTime
                    data.endTime = this.endTime
                }
                if(this.maxPrice !== '' && this.minPrice !== ''){
                    data.maxPrice = this.maxPrice
                    data.minPrice = this.minPrice
                }
                this.$store.dispatch('order/queryOrders',data)
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