<template>
    <transition name="vendorDetail" appear>
        <div class="vendor-detail">
            <el-form method="post" class="vendor-detail-form" ref="vendor" label-position="right" size="mini" :model="vendor">

                <el-descriptions class="margin-top" :column="3" size="mini" border>
                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-star-off"></i>
                        供货商编码
                    </template>
                    <span v-show="!isinputed">{{vendorDetail.vendorCode}}</span>
                    <el-form-item 
                    v-show="isinputed"
                    prop="vendorCode"
                    style="margin-bottom: 0px;"
                    label="">
                        <el-input v-model="vendor.vendorCode" readonly></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>

                    <template slot="label">
                        <i class="el-icon-s-ticket"></i>
                        供货商名
                    </template>
                    <span v-show="!isinputed">{{vendorDetail.vendorName}}</span>
                    <el-form-item
                    prop="vendorName"
                    :rules="[
                    {required: true, message: '供货商名称不能为空',trigger:'blur'}]"
                    v-show="isinputed"
                    style="margin-bottom: 0px;"
                    label="">
                        <el-input v-model="vendor.vendorName"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-custom"></i>
                        联系人
                    </template>
                    <span v-show="!isinputed">{{vendorDetail.contact}}</span>
                    <el-form-item
                    prop="contact"
                    :rules="[
                    {required: true, message: '联系人姓名不能为空',trigger:'blur'}]"
                    v-show="isinputed" 
                    style="margin-bottom: 0px;"
                    label="">
                        <el-input v-model="vendor.contact"></el-input>
                    </el-form-item>

                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-phone"></i>
                        联系电话
                    </template>
                    <span v-show="!isinputed">{{vendorDetail.phone}}</span>
                    <el-form-item 
                    prop="phone"
                    :rules="rules.phone"
                    v-show="isinputed"
                    style="margin-bottom: 0px;"
                    label="">
                        <el-input v-model="vendor.phone"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-paperclip"></i>
                        供货商官网url
                    </template>
                    <span v-show="!isinputed">{{vendorDetail.vendorUrl}}</span>
                    <el-form-item
                    prop="vendorUrl"
                    :rules="rules.vendorUrl"
                    v-show="isinputed" 
                    style="margin-bottom: 0px;"
                    label="">
                        <el-input v-model="vendor.vendorUrl"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-magic-stick"></i>
                        供应商品数
                    </template>
                    <span v-show="!isinputed">{{vendorDetail.commodities.length}}</span>
                    <el-form-item
                    v-show="isinputed" 
                    style="margin-bottom: 0px;"
                    label="">
                        <el-input v-model="vendor.commodities.length" readonly></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-mouse"></i>
                            操作
                        </template>
                        <el-button v-show="!isinputed" icon="el-icon-s-ticket" style="padding: 5px 4px" type="primary" @click="revise">
                            修改
                        </el-button>
                        <el-button v-show="isinputed" icon="el-icon-s-open" style="padding: 5px 4px" type="primary" @click="updateVendor('vendor')">
                            保存
                        </el-button>
                        <el-button v-show="isinputed" icon="el-icon-brush" style="padding: 5px 4px" type="primary" @click="cancel">
                            取消
                        </el-button>
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-chat-dot-round"></i>
                        供应商简介
                    </template>
                    <span v-show="!isinputed">{{vendorDetail.briefIntroduction}}</span>
                    <el-form-item
                    prop="briefIntroduction"
                    v-show="isinputed" 
                    style="margin-bottom: 0px;"
                    label="">
                        <el-input type="textarea" v-model="vendor.briefIntroduction"></el-input>
                    </el-form-item>
                    </el-descriptions-item>

                </el-descriptions>
            </el-form>

                <div>
                    <hr style="border-color:rgb(0, 0, 245)">
                </div>

                <div class="vendor-detail-list" style="text-align: center">

                    <div class="vendor-commodity-checked" style="margin-right: 5%;">
                        <p class="vendor-commodity-p">
                            <span style="color: rgb(27, 0, 180);font-weight: bolder;"> 已有的供应商品</span>
                        </p>
                        <div class="vendor-commodity-center">
                            <table>
                                <thead>
                                    <tr>
                                        <td></td>
                                        <td>商品名</td>
                                        <td>商品编号</td>
                                        <td>型号</td>
                                        <td>操作</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="commodity in vendorDetail.commodities" :key="commodity.ccommodityCode">
                                        <td><input type="checkbox" v-model="commodity.ischecked"></td>
                                        <td>{{commodity.commodityName}}</td>
                                        <td>{{commodity.commodityCode}}</td>
                                        <td>{{commodity.model}}</td>
                                        <td><el-button type="danger" icon="el-icon-delete" 
                                        @click="deleteCommodity(commodity.commodityCode,commodity.commodityName)" circle></el-button></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="vendor-commodity-btn">
                        <el-button type="primary" @click="addCommdities"><i class="el-icon-arrow-left"></i>添加</el-button>
                        <el-button type="primary" @click="resetCommodities">重置<i class="el-icon-arrow-right"></i></el-button>
                        <div v-show="newCommodities.length !== 0" class="sava-btn">
                            <el-button type="success" style="width: 100%;" @click="saveAddCommodities">保存</el-button>
                        </div>
                    </div>


                    <div class="vendor-commodity-checked">
                        <p class="vendor-commodity-p">
                            <input type="checkbox" @click="checkedAll" v-model="isCheckedAll">全选 &nbsp;&nbsp;&nbsp;&nbsp;
                            <span style="color: rgb(27, 0, 180);font-weight: bolder;"> 没有的供应商品</span>
                        </p>
                        <div class="vendor-commodity-center">
                            <el-input 
                                placeholder="请输入商品名"
                                v-model="searchCommodityName"
                                @blur="searchCommdities"
                                clearable>
                            </el-input>
                            <table>
                                <thead>
                                    <tr>
                                        <td></td>
                                        <td>商品名</td>
                                        <td>商品编号</td>
                                        <td>型号</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="commodity1 in serchCommodities" :key="commodity1.ccommodityCode">
                                        <td><input type="checkbox" v-model="commodity1.ischecked"></td>
                                        <td>{{commodity1.commodityName}}</td>
                                        <td>{{commodity1.commodityCode}}</td>
                                        <td>{{commodity1.model}}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>

            <div class="form-group vendor-detail-btn">
                <el-button type="success" style="width:300px" @click="back">返回</el-button>
            </div>
        </div>
    </transition>


</template>

<script>
import { mapState } from 'vuex';
import axios from 'axios'
import qs from 'qs'
    export default {
        name:'VendorDetail',
        data() {

            return {
                isinputed:false,
                vendor:{
                    vendorCode:'BJ_GYS001',
                    vendorName:'北京三木堂商贸有限公司',
                    contact:'张国强',
                    phone:'13566667777',
                    briefIntroduction:'长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等',
                    vendorUrl:'www.cnblogs.com',
                    commodities:"",
                },
                searchCommodityName:'',
                
                newCommodities:[],
                oldCommodities:[],


                rules: {
                    //手机号的正则表达式
                    phone:[
                        {required: true, message: '电话不能为空',trigger:'blur'},
                        {validator(rule,value,callback){
                            if(/^1[34578]\d{9}$/.test(value) === false){
                                callback(new Error("请输入正确的手机号"))
                            }else{
                                callback()
                            }
                        },trigger:'blur'}
                    ],

                    //供货商url正则表达式
                    vendorUrl:[{required: false, message: '',trigger:'blur'},
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
        //路由传过来的参数
        props:['vendorCode'],

        computed:{
            ...mapState('vendor',['vendorDetail','serchCommodities']),

            isCheckedAll(){
                if(this.serchCommodities.length>0){
                    for(let i in this.serchCommodities){
                        if(this.serchCommodities[i].ischecked === false){
                            return false
                        }
                    }
                    return true
                }
                return false
            }

        },
        methods:{
            //修改标签为input框
            revise(){
                this.isinputed = true
                for(let i in this.vendor){
                    this.vendor[i] = this.vendorDetail[i]
                }
            },
            //更新一个vendor供货商
            updateVendor(vendor){
                this.$refs[vendor].validate((valid) => {
                    if (valid) {
                        if(confirm('确定需要保存吗?')){
                            this.$store.dispatch('vendor/updateVendor',this.vendor)
                            this.cancel()
                        }               
                    } else {
                        console.log('失败')
                        return false;
                    }
                });
            },
            //取消更新
            cancel(){
                this.isinputed = false
            },
            // 回退            
            back(){
                // 返回上一次路由位置
                this.$router.back()
                this.$store.commit('vendor/CHANGE_SEARCH_COMMODITIES',[])
            },

            addCommdities(){
                const commodities = this.serchCommodities.filter((com)=>{
                    return com.ischecked
                })
                for(let i in commodities){
                    for(let j in this.serchCommodities){
                        if(commodities[i].commodityCode === this.serchCommodities[j].commodityCode){
                            this.serchCommodities.splice(j,1)
                        }
                    }
                    this.newCommodities.push(commodities[i])
                    this.vendorDetail.commodities.push(commodities[i])
                }
            },
            
            resetCommodities(){
                for(let i in this.newCommodities){
                    this.newCommodities[i].ischecked = false
                    this.serchCommodities.push(this.newCommodities[i])

                    for(let j in this.vendorDetail.commodities){
                        if(this.newCommodities[i].commodityCode === this.vendorDetail.commodities[j].commodityCode){
                            this.vendorDetail.commodities.splice(j,1)
                        }
                    }
                }
                this.newCommodities = []
            },
            //操作添加的商品
            saveAddCommodities(){
                const commoditiesCodes = [];
                for(let i in this.newCommodities){
                    commoditiesCodes.push(this.newCommodities[i].commodityCode)
                }
                const data = qs.stringify(commoditiesCodes,{indices:false})
                if(confirm('确定需要保存吗?')){
                    axios.get(`${this.BASE_PATH}/vendor/addVendorCommodities/${commoditiesCodes}/${this.vendorDetail.vendorCode}`).then(
                        response=>{
                            alert(response.data)
                            this.newCommodities = []
                        },
                        error=>{
                            // console.log('请求失败了',error.message)
                            alert("保存失败")
                        }
                    ) 
                }
            },
            //删除一个商品
            deleteCommodity(commodityCode,commodityName){
                let flag = true
                for(let i in this.newCommodities){
                    if(this.newCommodities[i].commodityCode === commodityCode){
                        this.newCommodities[i].ischecked = false
                        flag = false
                        this.serchCommodities.push(this.newCommodities[i])
                        this.newCommodities.splice(i,1)
                        for(let j in this.vendorDetail.commodities){
                            if(this.vendorDetail.commodities[j].commodityCode === commodityCode){
                                this.vendorDetail.commodities.splice(j,1)
                            }
                        }
                    }
                }
 

                if(flag){
                    if(confirm('确认要删除已有的供应商品【' + commodityName +'】')){
                        axios.get(`${this.BASE_PATH}/vendor/deleteVendorCommodity/${commodityCode}/${this.vendorDetail.vendorCode}`).then(
                            response=>{
                                for(let j in this.vendorDetail.commodities){
                                    if(this.vendorDetail.commodities[j].commodityCode === commodityCode){
                                        this.vendorDetail.commodities.splice(j,1)
                                    }
                                }
                                alert(response.data)    
                            },
                            error=>{
                                // console.log('请求失败了',error.message)
                                alert("删除失败")
                            }
                        ) 
                    }
                }

            },

            //搜索商品
            searchCommdities(){
                if(this.searchCommodityName !== ''){
                    this.$store.dispatch('vendor/searchCommodities',this.searchCommodityName)   
                }
                
            },
            
            //全选
            checkedAll(){
                for(let i in this.serchCommodities){
                    this.serchCommodities[i].ischecked = true
                }
            }

        },

        watch:{

        },

        created(){
        },

        mounted(){
            this.$store.dispatch('vendor/queryVendorByVendorCode',this.vendorCode)                
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
    .vendor-detail-title{
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
    .vendor-detail{
        background-color: rgb(200, 209, 236);
        width: 79%;
        height: 97%;
        position: absolute;
        top: 0px;
        overflow-x: hidden;
    }
    .vendor-commodity-checked{
        width: 40%;
        height: 100%;
        border: 1px solid #EBEEF5;
        border-radius: 4px;
        overflow: hidden;
        background: #FFF;
        display: inline-block;
        max-height: 100%;
        box-sizing: border-box;
        position: relative;
        overflow-y: auto;
        overflow-x: hidden;
    }
    .vendor-commodity-p{
        height: 40px;
        line-height: 40px;
        background: #F5F7FA;
        margin: 0;
        padding-left: 15px;
        border-bottom: 1px solid #EBEEF5;
        box-sizing: border-box;
        color: #000;
    }
    .vendor-commodity-center{
        padding-bottom: 40px;
    }
    .vendor-commodity-btn{
        right:2%;
        bottom: 10%;
        border-radius: 4px;
        overflow: hidden;
        display: inline-block;
        max-height: 100%;
        box-sizing: border-box;
        position: relative;
        height: 50%;
    }
    .sava-btn{
        position: relative;
        top: 35%;
    }
    .vendor-detail-btn{
        position: absolute;
        left: 35%;
        width: 300px;
        top: 93%;
    }




    .vendorDetail-enter-active{
        animation: handoff 0.5s;
    }

    .vendorDetail-leave-active{
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

    .vendor-detail-list{
        position: relative;
        width: 100%;
        height: 61%;
    }
    table{
        color: #000000;
        width: 100%;
        height:100%;
        border-collapse: collapse;
        border-spacing: 0;
    }
    thead{
        color: #ed0ac0;
        font-size: 16px;
        font-weight: bolder;
        height: 25px;
        background-color: #d0e8b2;
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