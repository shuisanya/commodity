<template>
    <transition name="vendorAdd" appear>
        <div class="vendor-add">

            <el-form method="post" class="vendor-form" ref="Vendor" label-position="right" label-width="100px" size="medium" :model="Vendor">
                <el-form-item 
                prop="vendorCode"
                :rules="rules.vendorCode"
                label="供货商编码">
                    <el-input v-model="Vendor.vendorCode"></el-input>
                </el-form-item>

                <el-form-item
                prop="vendorName"
                :rules="[
                {required: true, message: '供货商名称不能为空',trigger:'blur'}]"
                label="供货商名称">
                    <el-input v-model="Vendor.vendorName"></el-input>
                </el-form-item>

                <el-form-item 
                prop="contact"
                :rules="[
                {required: true, message: '联系人姓名不能为空',trigger:'blur'}]"
                label="联系人姓名">
                    <el-input v-model="Vendor.contact"></el-input>
                </el-form-item>

                <el-form-item 
                prop="phone"
                :rules="rules.phone"
                label="联系电话">
                    <el-input v-model.number="Vendor.phone"></el-input>
                </el-form-item>

                <el-form-item
                prop="vendorUrl"
                :rules="rules.vendorUrl"
                label="供货商官网">
                    <el-input v-model="Vendor.vendorUrl"></el-input>
                </el-form-item>

                <el-form-item
                prop="briefIntroduction"  
                label="供货商简介">
                    <el-input type="textarea" v-model="Vendor.briefIntroduction"></el-input>
                </el-form-item>

                <el-form-item class="vendor-btn">
                    <el-button type="primary" @click="submitForm('Vendor')">添加</el-button>
                    <el-button @click="resetForm('Vendor')">重置</el-button>
                </el-form-item>

            </el-form>
            
            <div class="vendor-add-back">
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
            Vendor: {
                vendorCode: '',
                vendorName: '',
                phone: '',
                contact:'',
                vendorUrl:'',
                briefIntroduction:'',
            },

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
                //供货商编号的正则表达式
                vendorCode:[{required: true, message: '供货商编码不能为空',trigger:'blur'},
                    {validator:function(rule,value,callback){
                        if(/^[a-zA-Z][a-zA-Z0-9_]{4,15}$/.test(value) === false){
                            callback(new Error('字母开头，允许5-16字节，允许字母数字下划线组合'))
                        }else{
                            axios.get(`${this.BASE_PATH}/vendor/isVendorExistByVendorCode/${value}`).then(
                                response=>{
                                    if(response.data){
                                        callback(new Error('该供货商编号已经存在！'))      
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

        computed:{
            ...mapState('vendor',['BASE_PATH'])
        },

        methods:{
            submitForm(Vendor) {
                this.$refs[Vendor].validate((valid) => {
                    if (valid) {
                        if(confirm('确定需要添加吗?')){
                            this.$store.dispatch('vendor/addVendor',this.Vendor)
                            this.resetForm('Vendor')
                        }               
                    } else {
                        return false;
                    }
                });
            },
            resetForm(Vendor) {
                this.$refs[Vendor].resetFields()
            },
            back(){
                this.$router.back()
            }
        }
    }
</script>

<style scoped>
    .vendor-add{
        background-color: lavender;
        width: 75%;
        height: 100%;
        position: absolute;
        top: 0px;
        overflow-x: hidden;
    }
    .vendor-form{
        position: relative;
        left: 20%;
        width:50%;
        height: 92%;
    }
    .vendor-btn{
        position: relative;
        left: 30%;
    }
    .vendor-add-back{
        position: relative;
        left: 35%;
    }


    .vendorAdd-enter-active{
        animation: handoff 0.5s;
    }

    .vendorAdd-leave-active{
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