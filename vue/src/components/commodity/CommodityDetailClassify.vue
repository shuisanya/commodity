<template>
    <div class="vendor-detail-list" style="text-align: center">

        <div class="vendor-commodity-checked" style="margin-right: 5%;">
            <p class="vendor-commodity-p">
                <span style="color: rgb(27, 0, 180);font-weight: bolder;"> 已有类别</span>
            </p>
            <div class="vendor-commodity-center">
                <table>
                    <thead>
                        <tr>
                            <td></td>
                            <td>类别ID</td>
                            <td>类别名称</td>
                            <td>操作</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="classify in commodityDetail.classifies" :key="classify.classifyId">
                            <td><input type="checkbox" v-model="classify.ischecked"></td>
                            <td>{{classify.classifyId}}</td>
                            <td>{{classify.name}}</td>
                            <td><el-button type="danger" icon="el-icon-delete" 
                            @click="deleteClassify(classify.classifyId,classify.name)" circle></el-button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="vendor-commodity-btn">
            <el-button type="primary" @click="addClassifies"><i class="el-icon-arrow-left"></i>添加</el-button>
            <el-button type="primary" @click="resetClassifies">重置<i class="el-icon-arrow-right"></i></el-button>
            <div class="sava-btn" v-show="newClassifies.length !== 0">
                <el-button type="success" style="width: 100%;" @click="saveAddClassifies">保存</el-button>
            </div>
        </div>


        <div class="vendor-commodity-checked">
            <p class="vendor-commodity-p">
                <span style="color: rgb(27, 0, 180);font-weight: bolder;"> 未有类别</span>
            </p>
            <div class="vendor-commodity-center">
                <table>
                    <thead>
                        <tr>
                            <td></td>
                            <td>类别ID</td>
                            <td>类别名称</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="classify in allClassifies" :key="classify.classifyId">
                            <td><input type="checkbox" v-model="classify.ischecked"></td>
                            <td>{{classify.classifyId}}</td>
                            <td>{{classify.name}}</td>
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
    name:'CommodityDetailClassify',
    data(){
        return{
            newClassifies:[]
        }
    },

    computed:{
        ...mapState('commodity',['commodityDetail','allClassifies']),
    },

    methods:{
        addClassifies(){
            const classifiesChecked = this.allClassifies.filter((v)=>{
                return v.ischecked
            })
            for(let i in classifiesChecked){
                for(let j in this.allClassifies){
                    if(classifiesChecked[i].classifyId === this.allClassifies[j].classifyId){
                        this.allClassifies.splice(j,1)
                    }
                }
                this.newClassifies.push(classifiesChecked[i])
                this.commodityDetail.classifies.push(classifiesChecked[i])
            }
        },
        resetClassifies(){
            for(let i in this.newClassifies){
                this.newClassifies[i].ischecked = false
                this.allClassifies.push(this.newClassifies[i])

                for(let j in this.commodityDetail.classifies){
                    if(this.newClassifies[i].classifyId === this.commodityDetail.classifies[j].classifyId){
                        this.commodityDetail.classifies.splice(j,1)
                    }
                }
            }
            this.newClassifies = []
        },
        saveAddClassifies(){
            const classifyIds = [];
            for(let i in this.newClassifies){
                classifyIds.push(this.newClassifies[i].classifyId)
            }
            if(confirm('确定需要保存吗?')){
                axios.get(`${this.BASE_PATH}/commodity/addCommodityWithClassifies/${classifyIds}/${this.commodityDetail.commodityCode}`).then(
                    response=>{
                        alert(response.data)
                        this.newClassifies = []
                    },
                    error=>{
                        alert("保存失败")
                    }
                ) 
            } 
        },

        deleteClassify(classifyId,name){
            let flag = true
            for(let i in this.newClassifies){
                if(this.newClassifies[i].classifyId === classifyId){
                    this.newClassifies[i].ischecked = false
                    flag = false
                    this.allClassifies.push(this.newClassifies[i])
                    this.newClassifies.splice(i,1)
                    for(let j in this.commodityDetail.classifies){
                        if(this.commodityDetail.classifies[j].classifyId === classifyId){
                            this.commodityDetail.classifies.splice(j,1)
                        }
                    }
                }
            }
            if(flag){
                if(confirm('确认要删除已有的类别【' + name +'】')){
                    axios.get(`${this.BASE_PATH}/commodity/deleteCommodityClassify/${classifyId}/${this.commodityDetail.commodityCode}`).then(
                        response=>{
                            for(let j in this.commodityDetail.classifies){
                                if(this.commodityDetail.classifies[j].classifyId === classifyId){
                                    this.allClassifies.push(this.commodityDetail.classifies[j])
                                    this.commodityDetail.classifies.splice(j,1)
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