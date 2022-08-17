<template>
    <div class="vendor-detail-list" style="text-align: center">

        <div class="vendor-commodity-checked" style="margin-right: 5%;">
            <p class="vendor-commodity-p">
                <span style="color: rgb(27, 0, 180);font-weight: bolder;"> 已有的供应商</span>
            </p>
            <div class="vendor-commodity-center">
                <table>
                    <thead>
                        <tr>
                            <td></td>
                            <td>供应商编号</td>
                            <td>供应商名称</td>
                            <td>操作</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="vendor in commodityDetail.vendors" :key="vendor.vendorCode">
                            <td><input type="checkbox" v-model="vendor.ischecked"></td>
                            <td>{{vendor.vendorCode}}</td>
                            <td>{{vendor.vendorName}}</td>
                            <td><el-button type="danger" icon="el-icon-delete" 
                            @click="deleteVendor(vendor.vendorName,vendor.vendorCode)" circle></el-button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="vendor-commodity-btn">
            <el-button type="primary" @click="addVendors"><i class="el-icon-arrow-left"></i>添加</el-button>
            <el-button type="primary" @click="resetVendors">重置<i class="el-icon-arrow-right"></i></el-button>
            <div class="sava-btn" v-show="newVendors.length !== 0">
                <el-button type="success" style="width: 100%;" @click="saveAddVendors">保存</el-button>
            </div>
        </div>


        <div class="vendor-commodity-checked">
            <p class="vendor-commodity-p">
                <span style="color: rgb(27, 0, 180);font-weight: bolder;"> 没有的供应商</span>
            </p>
            <div class="vendor-commodity-center">
                <table>
                    <thead>
                        <tr>
                            <td></td>
                            <td>供应商编号</td>
                            <td>供应商名称</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="vendor in allVendors" :key="vendor.vendorCode">
                            <td><input type="checkbox" v-model="vendor.ischecked"></td>
                            <td>{{vendor.vendorCode}}</td>
                            <td>{{vendor.vendorName}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
export default {
    name:'CommodityDetailVendor',
    data(){
        return{
            newVendors:[]
        }
    },

    computed:{
        ...mapState('commodity',['commodityDetail','allVendors']),
    },

    methods:{
        addVendors(){
            const vendorsChecked = this.allVendors.filter((v)=>{
                return v.ischecked
            })
            for(let i in vendorsChecked){
                for(let j in this.allVendors){
                    if(vendorsChecked[i].vendorCode === this.allVendors[j].vendorCode){
                        this.allVendors.splice(j,1)
                    }
                }
                this.newVendors.push(vendorsChecked[i])
                this.commodityDetail.vendors.push(vendorsChecked[i])
            }
        },
        resetVendors(){
            for(let i in this.newVendors){
                this.newVendors[i].ischecked = false
                this.allVendors.push(this.newVendors[i])

                for(let j in this.commodityDetail.vendors){
                    if(this.newVendors[i].vendorCode === this.commodityDetail.vendors[j].vendorCode){
                        this.commodityDetail.vendors.splice(j,1)
                    }
                }
            }
            this.newVendors = []
        },
        saveAddVendors(){
            const vendorCodes = [];
            for(let i in this.newVendors){
                vendorCodes.push(this.newVendors[i].vendorCode)
            }
            if(confirm('确定需要保存吗?')){
                axios.get(`${this.BASE_PATH}/commodity/addCommodityVendors/${vendorCodes}/${this.commodityDetail.commodityCode}`).then(
                    response=>{
                        alert(response.data)
                        this.newVendors = []
                    },
                    error=>{
                        alert("保存失败")
                    }
                ) 
            } 
        },

        deleteVendor(vendorName,vendorCode){
            let flag = true
            for(let i in this.newVendors){
                if(this.newVendors[i].vendorCode === vendorCode){
                    this.newVendors[i].ischecked = false
                    flag = false
                    this.allVendors.push(this.newVendors[i])
                    this.newVendors.splice(i,1)
                    for(let j in this.commodityDetail.vendors){
                        if(this.commodityDetail.vendors[j].vendorCode === vendorCode){
                            this.commodityDetail.vendors.splice(j,1)
                        }
                    }
                }
            }
            if(flag){
                if(confirm('确认要删除已有的供应商【' + vendorName +'】')){
                    axios.get(`${this.BASE_PATH}/commodity/deleteCommodityVendor/${vendorCode}/${this.commodityDetail.commodityCode}`).then(
                        response=>{
                            for(let j in this.commodityDetail.vendors){
                                if(this.commodityDetail.vendors[j].vendorCode === vendorCode){
                                    this.allVendors.push(this.commodityDetail.vendors[j])
                                    this.commodityDetail.vendors.splice(j,1)
                                }
                            }
                            alert(response.data)    
                        },
                        error=>{
                            alert("删除失败")
                        }
                    ) 
                }
            }
        }

    },

    mounted(){
        
    }
}
</script>

<style scoped>
    .vendor-commodity-checked{
        width: 39%;
        height: 160px;
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

     .vendor-detail-list{
        position: relative;
        width: 100%;
        height: 80%;
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