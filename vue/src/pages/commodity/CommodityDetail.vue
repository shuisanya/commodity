<template>
    <transition name="commodityDetail" appear>
        <div class="commodity-detail">
            <el-form method="post" ref="commodity" label-position="right" label-width="100px" size="mini" :model="commodity">

                <el-descriptions class="margin-top" :column="4" size="medium" border>
                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-postcard"></i>
                        商品编号
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.commodityCode}}</span>

                    <el-form-item
                    v-show="isinputed"
                    class="commodity-input">
                        <el-input v-model="commodity.commodityCode" readonly></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-tickets"></i>
                        商品名称
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.commodityName}}</span>

                    <el-form-item
                    v-show="isinputed"
                    prop="commodityName"
                    :rules="[
                    {required: true, message: '商品名称不能为空',trigger:'blur'}]"  
                    class="commodity-input">
                        <el-input v-model="commodity.commodityName"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-data-board"></i>
                        商品规格
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.specification}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.specification"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-collection-tagk"></i>
                        商品型号
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.model}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.model"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-connection"></i>
                        商品计量单位
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.unit}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.unit"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-data"></i>
                        商品库存
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.inventoryCount}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.inventoryCount"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-coffee"></i>
                        商品市场价
                    </template>
                    <span v-show="!isinputed">￥{{commodityDetail.marketPrice.toFixed(2)}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.marketPrice"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-ice-tea"></i>
                        商品销售价
                    </template>
                    <span v-show="!isinputed">￥{{commodityDetail.salesPrice.toFixed(2)}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.salesPrice"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-ice-drink"></i>
                        商品成本价
                    </template>
                    <span v-show="!isinputed">￥{{commodityDetail.costPrice.toFixed(2)}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.costPrice"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-cpu"></i>
                        商品地址
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.commodityUrl}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-input">
                        <el-input v-model="commodity.commodityUrl"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-full-screen"></i>
                        商品品牌
                    </template>
                    <span v-show="!isinputed" v-if="commodityDetail.brand">
                        {{commodityDetail.brand.brandName}}
                    </span>

                    <el-form-item
                    v-show="isinputed"
                    class="commodity-input"
                    prop="brandCode"
                    >
                        <el-select v-model="commodity.brand.brandCode" clearable>
                            <el-option
                            v-for="item in allBrands"
                            :key="item.brandCode"
                            :label="item.brandName"
                            :value="item.brandCode">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-present"></i>
                        商品种类
                    </template>
                    <span v-for="classify in commodityDetail.classifies" :key="classify.classifyId">
                        {{classify.name}}
                    </span>
                    
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-grid"></i>
                        操作
                    </template>
                        <el-button v-show="!isinputed" icon="el-icon-s-ticket" style="padding: 5px 4px" type="primary" @click="revise">
                            修改
                        </el-button>
                        <el-button v-show="isinputed" icon="el-icon-s-open" style="padding: 5px 4px" type="primary" @click="updateCommodity('commodity')">
                            保存
                        </el-button>
                        <el-button v-show="isinputed" icon="el-icon-brush" style="padding: 5px 4px" type="primary" @click="cancel">
                            取消
                        </el-button>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-chat-dot-round"></i>
                        商品简介
                    </template>
                    <span v-show="!isinputed">{{commodityDetail.commodityIntroduce}}</span>

                    <el-form-item
                    v-show="isinputed" 
                    class="commodity-testarea">
                        <el-input type="textarea" v-model="commodity.commodityIntroduce"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                </el-descriptions>


                <div>
                    <hr style="border-color:rgb(0, 0, 245)">
                </div>

            </el-form>

            <el-tabs type="border-card">
                <el-tab-pane label="供货商">
                    <CommodityDetaileVendor></CommodityDetaileVendor>
                </el-tab-pane>
                <el-tab-pane label="类别">
                    <CommodityDetailClassify></CommodityDetailClassify>
                </el-tab-pane>
            </el-tabs>

            <div class="form-group commodity-detail-btn">
                <el-button type="success" style="width:300px" @click="back">返回</el-button>
            </div>
        </div>
    </transition>


</template>

<script>
import { mapState } from 'vuex'

import CommodityDetaileVendor from '../../components/commodity/CommodityDetailVendor'
import CommodityDetailClassify from '../../components/commodity/CommodityDetailClassify'

    export default {
        name:'CommodityDetail',
        components:{CommodityDetaileVendor,CommodityDetailClassify},
        data() {
            return {
                isinputed:false,
                commodity:{
                    commodityCode: '',
                    commodityName: '',
                    specification: '',
                    model:'',
                    unit:'',
                    marketPrice:'',
                    salesPrice:'',
                    costPrice:'',
                    commodityUrl:'',
                    commodityIntroduce:'',
                    inventoryCount:'',
                    brand:{
                        brandCode:''
                    },
                }
            };
        },
        //路由传过来的参数
        props:['commodityCode'],

        computed:{
            ...mapState('commodity',['commodityDetail','allBrands']),
        },
        methods:{
            // 回退            
            back(){
                // 返回上一次路由位置
                this.$router.back()
            },
            //更新操作
            revise(){
                this.isinputed = true
                this.commodity.commodityCode = this.commodityDetail.commodityCode
                this.commodity.commodityName = this.commodityDetail.commodityName
                this.commodity.specification = this.commodityDetail.specification
                this.commodity.model = this.commodityDetail.model
                this.commodity.unit = this.commodityDetail.unit
                this.commodity.marketPrice = this.commodityDetail.marketPrice
                this.commodity.salesPrice = this.commodityDetail.salesPrice
                this.commodity.costPrice = this.commodityDetail.costPrice
                this.commodity.commodityUrl = this.commodityDetail.commodityUrl
                this.commodity.commodityIntroduce = this.commodityDetail.commodityIntroduce
                this.commodity.inventoryCount = this.commodityDetail.inventoryCount
                this.commodity.brand.brandCode = this.commodityDetail.brand.brandCode
            },
            //取消更新操作
            cancel(){
                this.isinputed = false
            },

            updateCommodity(commodity){
                this.$refs[commodity].validate((valid) => {
                    if (valid) {
                        if(confirm('确定需要保存吗?')){
                            this.$store.dispatch('commodity/updateCommodity',this.commodity)
                            this.cancel()
                        }               
                    } else {
                        console.log('失败')
                        return false;
                    }
                });
            }

        },

        watch:{

        },

        created(){
        },

        mounted(){
            this.$store.dispatch('commodity/queryOneCommodity',this.commodityCode)
            this.$store.dispatch('commodity/queryAllVendorsAndBrands')
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
    .commodity-detail-title{
        font:20px bolder;
        color: rgb(235, 8, 186);
        position: relative;
        left: 10%;
    }
    .commodity-input{
        margin-bottom: 0px;
        position: relative;
        left: -45%;
    }
    .commodity-testarea{
        margin-bottom: 0px;
        position: relative;
        left: -12%;
    }
    .icon-font{
        font-size: 25px;
    }
    .icon-span{
        font-size: 20px;
    }
    .commodity-detail{
        background-color: rgb(236, 134, 10);
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
    .commodity-detail-btn{
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


    .commodityDetail-enter-active{
        animation: handoff 0.5s;
    }

    .commodityDetail-leave-active{
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


</style>