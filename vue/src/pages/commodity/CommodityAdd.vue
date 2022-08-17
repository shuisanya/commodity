<template>
    <transition name="commodityAdd" appear>
        <div class="commodity-add">

            <el-form method="post" class="commodity-form" ref="commodity" label-position="right" label-width="100px" size="medium" :model="commodity">
                <el-form-item 
                class="commodity-input"
                style="width:45%"
                prop="commodityCode"
                :rules="rules.commodityCode"
                label="商品编码">
                    <el-input v-model="commodity.commodityCode"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:45%"
                prop="commodityName"
                :rules="[
                {required: true, message: '商品名称不能为空',trigger:'blur'}]"
                label="商品名称">
                    <el-input v-model="commodity.commodityName"></el-input>
                </el-form-item>

                <el-form-item 
                class="commodity-input"
                style="width:28%"
                prop="specification"
                label="商品规格">
                    <el-input v-model="commodity.specification"></el-input>
                </el-form-item>

                <el-form-item 
                class="commodity-input"
                style="width:28%"
                prop="model"
                label="商品型号">
                    <el-input v-model="commodity.model"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:28%"
                prop="unit"
                label="商品计量单位">
                    <el-input v-model="commodity.unit"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:28%"
                prop="marketPrice"  
                label="商品的市场价">
                    <el-input v-model="commodity.marketPrice"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:28%"
                prop="salesPrice"  
                label="商品的销售价">
                    <el-input v-model="commodity.salesPrice"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:28%"
                prop="costPrice"  
                label="商品的成本价">
                    <el-input v-model="commodity.costPrice"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:45%"
                prop="commodityUrl"
                :rules="rules.commodityUrl"  
                label="商品的官网">
                    <el-input v-model="commodity.commodityUrl"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:45%"
                prop="inventoryCount"
                label="商品的库存">
                    <el-input v-model="commodity.inventoryCount"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:95%"
                prop="commodityIntroduce"
                label="商品的简介">
                    <el-input type="textarea" v-model="commodity.commodityIntroduce"></el-input>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                prop="brandCode"
                style="width:35%"
                label="商品的品牌">
                    <el-select v-model="commodity.brandCode" clearable placeholder="请选择">
                        <el-option
                        v-for="item in brands"
                        :key="item.brandCode"
                        :label="item.brandName"
                        :value="item.brandCode">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:55%"
                prop="classifies"
                label="商品的类别">
                    <el-select v-model="classifyIds"
                    style="width:90%"
                    multiple  placeholder="请选择 可多选">
                        <el-option
                        v-for="item in classifies"
                        :key="item.classifyId"
                        :label="item.name"
                        :value="item.classifyId">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item
                class="commodity-input"
                style="width:92%"
                prop="vendors"
                label="商品的供货商">
                    <el-select v-model="vendorCodes"
                    style="width:92%"
                    multiple  placeholder="请选择 可多选">
                        <el-option
                        v-for="item in vendors"
                        :key="item.vendorCode"
                        :label="item.vendorName"
                        :value="item.vendorCode">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item class="commodity-btn">
                    <el-button type="primary" @click="submitForm('commodity')">添加</el-button>
                    <el-button @click="resetForm('commodity')">重置</el-button>
                </el-form-item>

            </el-form>
            
            <div class="commodity-add-back">
                <el-button type="success" style="width:300px" @click="back">返回</el-button>
            </div>
        
        </div>
    </transition>

</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
    export default {
        name:'VendorAdd',
        data() {
        return {
            commodity: {
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
                brandCode: '',
            },

            brands: [],
            vendors: [],
            vendorCodes:[],
            classifies:[],
            classifyIds:[],

            rules: {
                //供货商编号的正则表达式
                commodityCode:[{required: true, message: '商品编码不能为空',trigger:'blur'},
                    {validator:function(rule,value,callback){
                        if(/^[a-zA-Z][a-zA-Z0-9_]{4,15}$/.test(value) === false){
                            callback(new Error('字母开头，允许5-16字节，允许字母数字下划线组合'))
                        }else{
                            axios.get(`${this.BASE_PATH}/commodity/isCommodityExistByCommodityCode/${value}`).then(
                                response=>{
                                    if(response.data){
                                        callback(new Error('商品编号已经存在！'))      
                                    }else{
                                        callback()
                                    }
                                },
                                error=>{
                                    console.log('请求失败了',error.message)
                                }
                            )
                            
                        }
                    },trigger:'blur'}
                ],
                //供货商url正则表达式
                commodityUrl:[{required: false, message: '',trigger:'blur'},
                    {validator(rule,value,callback){
                        if(/^(((ht|f)tps?):\/\/)?[\w-]+(\.[\w-]+)+([\w.,@?^=%&:/~+#-]*[\w@?^=%&/~+#-])?$/.test(value) === false && value !== ''){
                            callback(new Error('请输入正确的url,非必须填写'))
                        }else{
                            callback()
                        }
                    },trigger:'blur'}
                ]

            },

        };
        },

        computed:{
            ...mapState('vendor',['BASE_PATH'])
        },

        methods:{
            submitForm(commodity) {
                this.$refs[commodity].validate((valid) => {
                    if (valid) {
                        if(confirm('确定需要添加吗?')){
                            if(this.vendorCodes.length === 0){
                                this.vendorCodes.push(-1)
                            }
                            if(this.classifyIds.length === 0){
                                this.classifyIds.push(-1)
                            }
                            const commodity = JSON.stringify(this.commodity)
                            axios.post(`${this.BASE_PATH}/commodity/addCommodity/${commodity}/${this.vendorCodes}/${this.classifyIds}`).then(
                                response=>{
                                    this.vendorCodes=[]
                                    this.classifyIds=[]
                                    this.resetForm('commodity')
                                    alert("添加成功")
                                },
                                error=>{
                                    console.log('请求失败了',error.message)
                                    alert("添加失败")
                                }
                            )
                        }               
                    } else {
                        return false;
                    }
                });
            },
            resetForm(commodity) {
                this.$refs[commodity].resetFields()
                this.vendorCodes=[]
                this.classifyIds=[]
            },
            back(){
                this.$router.back()
            }
        },

        //路由到该页面需要查询数据
        mounted(){
            axios.get(`${this.BASE_PATH}/commodity/queryAllVendorsAndBrands`).then(
                response=>{
                    this.brands = response.data.brands
                    this.vendors = response.data.vendors
                    this.classifies = response.data.classifies
                },
                error=>{
                    console.log('请求失败了',error.message)
                }
            )
        }
    }
</script>

<style scoped>
    .commodity-add{
        background-color: lavender;
        width: 75%;
        height: 100%;
        position: absolute;
        top: 0px;
        overflow-x: hidden;
    }
    .commodity-form{
        position: relative;
        left: 1%;
        width:100%;
        height: 92%;
    }
    .commodity-input{
        float: left;
    }
    .commodity-btn{
        position: relative;
        float: left;
        left: 30%;
        width: 35%;
    }
    .commodity-add-back{
        position: relative;
        left: 35%;
    }


    .commodityAdd-enter-active{
        animation: handoff 0.5s;
    }

    .commodityAdd-leave-active{
        animation: handoff 0.5s reverse;
     }  

    @keyframes handoff {
        from{
            transform: translateX(100%);
        }
        to{
            transform: translateX(0);
        }
    }
</style>