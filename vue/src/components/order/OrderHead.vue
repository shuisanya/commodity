<template>

    <div class="order-head">

        <el-select class="order-select" v-model="searchMannerValue" placeholder="请选择">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>

        <div class=" order-date-price">
            <el-date-picker
            v-show="searchMannerValue === '1'"
            v-model="searchTimes"
            type="datetimerange"
            align="right"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :default-time="['00:00:00', '23:59:59']">
            </el-date-picker>

            <div v-show="searchMannerValue === '2'" class="order-price">
                <el-input-number v-model="minPrice" :precision="2" :step="1" :min="0" :max="9999"></el-input-number>
                <i class="el-icon-minus"></i>
                <el-input-number v-model="maxPrice" :precision="2" :step="1" :min="0" :max="9999999"></el-input-number>
            </div>

        </div>
        <el-button type="info" icon="el-icon-search" class="order-mhy order-btn" @click="searchOrders" >搜索</el-button>



    </div>


</template>

<script>
// 将时间戳转换为日期的库
import moment from 'moment'
import { mapState } from 'vuex'
    export default {
        name:'OrderHead',
        data() {
        return {
            options: [{
                value: '1',
                label: '按时间查询'
                }, {
                value: '2',
                label: '按金额查询'
                },
            ],
            searchMannerValue: '1',
            searchTimes: [],  
            minPrice:0,
            maxPrice:9999999,
        }
        },
        computed:{
            ...mapState('order',['pageSize'])
        },
        methods:{
            searchOrders(){
                const data = {
                    'currentPage':1,
                    'pageSize':this.pageSize,
                }
                if(this.searchTimes !== null){
                    if(this.searchTimes.length !== 0){
                        data.startTime = moment(this.searchTimes[0]).format("YYYY-MM-DD HH:mm:ss")
                        data.endTime = moment(this.searchTimes[1]).format("YYYY-MM-DD HH:mm:ss")
                    }
                }else{
                    data.startTime = ''
                    data.endTime = '' 
                }

                if(this.minPrice !== 0){
                    data.minPrice = this.minPrice
                    data.maxPrice = this.maxPrice
                }
                if(this.maxPrice !== 9999999){
                    data.maxPrice = this.maxPrice
                    data.minPrice = this.minPrice
                }

                this.$store.dispatch('order/queryOrders',data)
                this.$store.commit('order/CHANGE_SEARCH_ORDER_CONTENT',data)

            }

        },

        watch:{
            searchMannerValue(){
                this.searchTimes=[]
                this.minPrice=0
                this.maxPrice=9999999
                this.$store.commit('order/CHANGE_SEARCH_ORDER_CONTENT',{startTime:'',endTime:'',maxPrice:'',minPrice:''})
            },
            
        }
    }
</script>

<style>
    .order-head{
        width: 100%;
        height:50px;
        font: 25px bolder;
        background-color: rgb(233, 82, 110);
    }
    .order-select{
        position: relative;
        left: 15%;
        display:inline-block;
        width: 10%;
    }
    .order-date-price{
        position: relative;
        width: 51%;
        left: 25%;
        display:inline-block;
    }
    .order-price{
        position: relative;
        display:inline-block;
    }
    .order-mhy{
        width: 200px;
        margin-right: 25px;
        position: relative;
        left: 15%;
    }
    .order-btn{
        width: 100px;
    }
</style>