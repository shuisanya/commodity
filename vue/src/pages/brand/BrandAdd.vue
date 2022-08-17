<template>
    <transition name="brandAdd" appear>
        <div class="brand-add">

            <el-form method="post" class="brand-form" ref="brand" label-position="right" label-width="100px" size="medium" :model="brand">
                <el-form-item 
                prop="brandCode"
                :rules="rules.brandCode"
                label="品牌编码">
                    <el-input v-model="brand.brandCode"></el-input>
                </el-form-item>

                <el-form-item
                prop="brandName"
                :rules="[
                {required: true, message: '品牌名称不能为空',trigger:'blur'}]"
                label="品牌名称">
                    <el-input v-model="brand.brandName"></el-input>
                </el-form-item>

                <el-form-item 
                label="品牌图片地址">
                    <el-input v-model="brand.brandImgUrl"></el-input>
                </el-form-item>

                <el-form-item
                prop="brandUrl"
                :rules="rules.brandUrl"
                label="品牌官网">
                    <el-input v-model="brand.brandUrl"></el-input>
                </el-form-item>

                <el-form-item class="brand-btn">
                    <el-button type="primary" @click="submitForm('brand')">添加</el-button>
                    <el-button @click="resetForm('brand')">重置</el-button>
                </el-form-item>

            </el-form>
            
            <div class="brand-add-back">
                <el-button type="success" style="width:300px" @click="back">返回</el-button>
            </div>
        
        </div>
    </transition>

</template>

<script>
import axios from 'axios'
    export default {
        name:'VendorAdd',
        data() {
        return {
            brand: {
                brandCode: '',
                brandName: '',
                brandUrl:'',
                brandImgUrl:'',
            },

            rules: {
                //品牌编号的正则表达式
                brandCode:[{required: true, message: '供货商编码不能为空',trigger:'blur'},
                    {validator:function(rule,value,callback){
                        if(/^[a-zA-Z][a-zA-Z0-9_]{1,15}$/.test(value) === false){
                            callback(new Error('字母开头，允许2-16字节，允许字母数字下划线组合'))
                        }else{
                            axios.get(`${this.BASE_PATH}/brand/isBrandExistByBrandCode/${value}`).then(
                                response=>{
                                    if(response.data){
                                        callback(new Error('该品牌编号已经存在！'))      
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
                //url正则表达式
                brandUrl:[{required: false, message: '',trigger:'blur'},
                    {validator(rule,value,callback){
                        if(/^(((ht|f)tps?):\/\/)?[\w-]+(\.[\w-]+)+([\w.,@?^=%&:/~+#-]*[\w@?^=%&/~+#-])?$/.test(value) === false && value !== ''){
                            callback(new Error('请输入正确的url,非必须填写'))
                        }else{
                            callback()
                        }
                    },trigger:'blur'}
                ],

            },

        };
        },

        computed:{
        },

        methods:{
            submitForm(brand) {
                this.$refs[brand].validate((valid) => {
                    if (valid) {
                        if(confirm('确定需要添加吗?')){
                            this.$store.dispatch('brand/addBrand',this.brand)
                            this.resetForm('brand')
                        }               
                    } else {
                        return false;
                    }
                });
            },
            resetForm(brand) {
                this.$refs[brand].resetFields()
            },
            back(){
                this.$router.back()
            }
        }
    }
</script>

<style scoped>
    .brand-add{
        background-color: lavender;
        width: 75%;
        height: 100%;
        position: absolute;
        top: 0px;
        overflow-x: hidden;
    }
    .brand-form{
        position: relative;
        left: 20%;
        width:50%;
        height: 92%;
    }
    .brand-btn{
        position: relative;
        left: 30%;
    }
    .brand-add-back{
        position: relative;
        left: 35%;
    }


    .brandAdd-enter-active{
        animation: handoff 0.5s;
    }

    .brandAdd-leave-active{
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