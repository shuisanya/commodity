<template>
    <transition name="adminAdd" appear>
        <div class="admin-detail">
            <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <label for="adminCode" class="col-sm-2 control-label">管理员编码：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control"  @blur="isAdminCodeExist"  v-model="admin.adminCode" id="userCode" name="adminCode" required="required"> 
                        </div>
                        <font class="icon" color="red" v-show="isAdminCode">
                            <i class="el-icon-error icon-font"></i>
                            <span class="icon-span">管理员名存在</span> 
                        </font>                        
                    </div>

                    <div class="form-group">
                        <label for="adminName" class="col-sm-2 control-label">管理员名称：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" v-model="admin.adminName" id="userName" name="adminName"> 
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="adminPassword" class="col-sm-2 control-label">管理员密码：</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" @blur="isPasswordSame" v-model="admin.adminPassword" id="userPassword" name="adminPassword"> 
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="ruserPassword" class="col-sm-2 control-label">确认密码：</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" @blur="isPasswordSame" v-model="admin.ruserPassword" id="ruserPassword" name="ruserPassword"> 
                        </div>
                        <font class="icon" color="red" v-show="isPassword">
                            <i class="el-icon-error icon-font"></i>
                            <span class="icon-span">密码不一致</span> 
                        </font>  
                    </div>

                    <div class="form-group">
                        <label for="sex" class="col-sm-2 control-label">管理员性别：</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="sex" v-model="admin.sex" id="sex">
                                <option value="1" selected="selected">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="birthday" class="col-sm-2 control-label">出生日期：</label>
                        <div class="block col-sm-10">
                            <el-date-picker
                            name="birthday"
                            v-model="admin.birthday"
                            type="date"
                            placeholder="选择日期">
                            </el-date-picker>
                        </div>
                        <font color="red"></font>
                    </div>

                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">管理员电话：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" v-model="admin.phone" id="phone" name="phone"> 
                            <font color="red"></font>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">管理员地址：</label>
                        <div class="col-sm-10">
                            <input v-model="admin.address" class="form-control" id="address"  name="address">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">管理员角色：</label>
                        <!-- 列出所有的角色分类 -->
                        <div class="col-sm-10">
                            <select class="form-control" v-model="admin.adminRole" id="adminRole">
                                <option value="1">经理</option>
                                <option value="2">主管</option>
                                <option value="3" selected="selected">普通员工</option>
                            </select>
                        </div>

                    </div>

                    <div class="form-group admin-btn">
                        <el-button type="primary" @click="addAdmin">添加</el-button>
                        <el-button type="success" @click="back">返回</el-button>
                    </div>

                    <div v-show="!isFormItemsRight" class="error">请正确填写完所有的表单项！！！</div>
            </form>
        </div>
    </transition>


</template>

<script>
import { mapState } from 'vuex';
    export default {
        name:'AdminAdd',
        data() {
            return {
                // 计算日期需要的数据
                pickerOptions: {
                disabledDate(time) {
                    return time.getTime() > Date.now();
                },
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                    picker.$emit('pick', new Date());
                    }
                }, {
                    text: '昨天',
                    onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                    }
                }, {
                    text: '一周前',
                    onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', date);
                    }
                }]
                },
                value2: '',
                admin:{
                    birthday: '',
                    adminCode:'',
                    adminName:'',
                    adminPassword:'',
                    ruserPassword:'',
                    sex:'',
                    phone:'',
                    address:'',
                    adminRole:'',
                },

                isPassword:false,

                isFormItemsRight:true
  
            };
        },
        computed:{
            ...mapState('admin',['isAdminCode','isAddAdminOk','pageTotal','pageSize','currentPage'])
        },
        methods:{
            // 点击一个admin管理员
            addAdmin(){
                let isSubmit = true

                // 判断密码是否一致  用户名是否存在  都满足才放行
                if(this.isPassword || this.isAdminCode){
                    isSubmit = false
                    // close.log('请保证用户名不存在或者密码一致',isSubmit)
                    this.isFormItemsRight = false
                    retrun
                }

                //判断每个表单项是否为空  （保证每个表单项都不能为空）
                for(let key in this.admin){
                    if(this.admin[key] === ''){
                        isSubmit = false
                        // console.log("请填写完所有的表单项")
                        this.isFormItemsRight = false
                        break
                    }
                }

                //发送ajax请求
                if(isSubmit){
                    this.$store.dispatch("admin/addAdmin",this.admin);
                }
                
            },
            // 回退
            back(){
                this.$router.back()
            },
            //判断密码是否一致
            isPasswordSame(){
                if(this.admin.adminPassword !== this.admin.ruserPassword){
                    this.isPassword = true
                }else{
                    this.isPassword = false
                }
            },
            //判断用户名是否存在
            isAdminCodeExist(){
                //去跳转到方法store
                if(this.admin.adminCode !== ''){
                    this.$store.dispatch("admin/isAdminCodeExist",this.admin.adminCode);
                }else{
                    this.$store.commit("admin/IS_ADMIN_CODE",false)
                }
                
            }
        },

        watch:{
            //监听是否添加admin成功  （如果数据改变就表示添加成功）
            isAddAdminOk(){
                //计算最后一页
                const lastPage = Math.floor(this.pageTotal / this.pageSize) + 1
                // 查询最后一页并更改数据
                this.$store.dispatch("admin/queryAdmins",{currentPage:lastPage,pageSize:this.pageSize});
                // 回退到List页面
                this.back()
            }

        }

  }
</script>

<style scoped>
    .icon-font{
        font-size: 25px;
    }
    .icon-span{
        font-size: 20px;
    }
    .admin-detail{
        background-color: rgb(238, 163, 163);
        width: 70%;
        height: 100%;
        position: absolute;
        top: 0px;
        overflow-x: hidden;
        overflow-y: hidden;
    }
    .form-horizontal{
        position: relative;
        left: 20%;
        top: 5%;
    }
    .col-sm-10{
        width: 40%;
    }
    .admin-btn{
        position: relative;
        left: 25%;
        width: 300px;
    }
    .error{
        position: relative;
        color: red;
        font-size: 25px;
        left: 10%;
    }


    .adminAdd-enter-active{
        animation: handoff 0.5s;
    }

    .adminAdd-leave-active{
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