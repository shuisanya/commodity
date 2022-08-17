<template>
    <transition name="brandDetail" appear>
        <div class="brand-detail">
            <form class="form-horizontal" method="post">

                <el-descriptions class="margin-top" :column="3" size="medium" border>
                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-postcard"></i>
                        品牌编号
                    </template>
                    {{brandDetail.brandCode}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-ticket"></i>
                        品牌名称
                    </template>
                    {{brandDetail.brandName}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-document-remove"></i>
                        品牌的官网地址
                    </template>
                    {{brandDetail.brandUrl}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-document-copy"></i>
                        品牌的logo图片地址
                    </template>
                    {{brandDetail.brandImgUrl}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-thumb"></i>
                        品牌的商品数量
                    </template>
                    {{brandDetail.commodities.length}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-chat-dot-round"></i>
                        操作
                    </template>

                    </el-descriptions-item>
                </el-descriptions>

                <div>
                    <hr style="border-color:rgb(0, 0, 245)">
                </div>

                <div class="brand-detail-list">

                    <table>
                        <thead>
                            <tr>
                                <td>商品编号</td>
                                <td>商品名称</td>
                                <td>商品单价</td>
                                <td>商品规格</td>
                                <td>商品库存量</td>
                                <td>商品型号</td>
                                <td>计数单位</td>
                            </tr>
                        </thead>

                        <tbody>
                            <tr v-for="commodity in brandDetail.commodities" :key="commodity.commodityCode">
                                <td>{{commodity.commodityCode}}</td>
                                <td>{{commodity.commodityName}}</td>
                                <td>￥{{commodity.marketPrice.toFixed(2)}}</td>
                                <td>{{commodity.specification}}</td>
                                <td>{{commodity.inventoryCount}}</td>
                                <td>{{commodity.model}}</td>
                                <td>{{commodity.unit}}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>

            </form>

            <div class="form-group brand-detail-btn">
                <el-button type="success" style="width:300px" @click="back">返回</el-button>
            </div>
        </div>
    </transition>


</template>

<script>
import { mapState } from 'vuex';
    export default {
        name:'BrandDetail',
        data() {
            return {
            };
        },
        //路由传过来的参数
        props:['brandCode'],

        computed:{
            ...mapState('brand',['brandDetail']),
        },
        methods:{
            // 回退            
            back(){
                // 返回上一次路由位置
                this.$router.back()
            },

        },

        watch:{

        },

        created(){
        },

        mounted(){
            this.$store.dispatch('brand/queryOneBrand',this.brandCode)
        },


// 使用下面两个钩子方法必须让 这个组件不销毁
        //组件被激活
        activated(){
        },

        //组件失活
        deactivated(){

        }


  }
</script>

<style scoped>
    .brand-detail-title{
        font:20px bolder;
        color: rgb(235, 8, 186);
        position: relative;
        left: 10%;
    }
    .icon-font{
        font-size: 25px;
    }
    .icon-span{
        font-size: 20px;
    }
    .brand-detail{
        background-color: rgb(109, 198, 6);
        width: 79%;
        height: 97%;
        position: absolute;
        top: 0px;
        overflow-x: hidden;
    }
    .form-horizontal{
        position: relative;
        left: 2%;
        top: 5%;
    }
    .col-sm-10{
        width: 15%;
        line-height: 35px;
        color: rgb(0, 0, 245);
        font: 20px bolder;
    }
    .brand-detail-btn{
        position: absolute;
        left: 35%;
        width: 300px;
        top: 93%;
    }
    .error{
        position: relative;
        color: red;
        font-size: 25px;
        left: 10%;
    }


    .brandDetail-enter-active{
        animation: handoff 0.5s;
    }

    .brandDetail-leave-active{
        animation: handoff 0.5s reverse;
     }  

    @keyframes handoff {
        from{
            transform: translateY(100%);
        }
        to{
            transform: translateY(0);
        }
    }

    .brand-detail-list{
        position: relative;
        width: 100%;
        height: 100%;
    }
    table{
        color: #000000;
        width: 100%;
        height:100%;
        border-collapse: collapse;
        border-spacing: 0;
    }
    thead{
        color: #000000;
        font-size: 20px;
        font-weight: bolder;
        height: 55px;
        background-color: #367ae8;
        background-image: linear-gradient(135deg, #367ae8 0%, #FFFB7D 100%);
    }
    tr {
        border-bottom: #660add solid 1px;
        text-align: center;
        height: 30px;
    }
    tbody tr:nth-of-type(even){
        background-color: rgb(223, 240, 241);
    }
    tbody tr:nth-of-type(odd){
        background-color: rgb(246, 220, 245);
    }
    tbody tr:hover{
        background-color: rgb(228, 72, 20);
    }

</style>