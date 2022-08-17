<template>
    <transition name="adminAdd" appear>
        <div class="admin-detail">
            <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <label for="adminCode" class="col-sm-2 control-label">管理员编码：</label>
                        <div class="col-sm-10">
                            <span class="admin-span" v-show="!isEdit">{{adminDetail.adminCode}}</span>
                            <input type="text" class="form-control" v-show="isEdit"  v-model="updateAdmin.adminCode" id="userCode" name="adminCode" required="required" readonly> 
                        </div>                    
                    </div>

                    <div class="form-group">
                        <label for="adminName" class="col-sm-2 control-label">管理员名称：</label>
                        <div class="col-sm-10">
                            <span class="admin-span" v-show="!isEdit">{{adminDetail.adminName}}</span>
                            <input type="text" class="form-control" v-show="isEdit" v-model="updateAdmin.adminName" id="userName" name="adminName"> 
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sex" class="col-sm-2 control-label">管理员性别：</label>
                        <div class="col-sm-10">
                            <span class="admin-span" v-show="!isEdit">{{adminDetail.sex === 1 ? '男' : '女'}}</span>
                            <select class="form-control" name="sex" v-show="isEdit" v-model="updateAdmin.sex" id="sex">
                                <option value="1" selected="selected">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group" v-show="!isEdit">
                        <label for="age" class="col-sm-2 control-label">管理员年龄：</label>
                        <div class="col-sm-10">
                            <span class="admin-span">{{age}}</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="birthday" class="col-sm-2 control-label">出生日期：</label>
                        <div class="block col-sm-10">
                            <span class="admin-span" v-show="!isEdit">{{birthday(adminDetail.birthday)}}</span>
                            <el-date-picker
                            v-show="isEdit"
                            name="birthday"
                            v-model="updateAdmin.birthday"
                            type="date"
                            placeholder="选择日期">
                            </el-date-picker>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">管理员电话：</label>
                        <div class="col-sm-10">
                            <span class="admin-span" v-show="!isEdit">{{adminDetail.phone}}</span>
                            <input v-show="isEdit" type="text" class="form-control" v-model="updateAdmin.phone" id="phone" name="phone"> 
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">管理员地址：</label>
                        <div class="col-sm-10">
                            <span class="admin-span" v-show="!isEdit">{{adminDetail.address}}</span>
                            <input v-show="isEdit" v-model="updateAdmin.address" class="form-control" id="address"  name="address">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">管理员角色：</label>
                        <!-- 列出所有的角色分类 -->
                        <div class="col-sm-10">
                            <span class="admin-span" v-show="!isEdit">{{role(adminDetail.adminRole)}}</span>
                            <select v-show="isEdit" class="form-control" v-model="updateAdmin.adminRole" id="adminRole">
                                <option value="1">经理</option>
                                <option value="2">主管</option>
                                <option value="3" selected="selected">普通员工</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group admin-btn">
                        <div class="edit-btn">
                            <el-button v-show="!isEdit" type="success" @click="edit">编辑</el-button>
                            <el-button v-show="isEdit" type="primary" @click="cancelEdit" >取消编辑</el-button>
                            <el-button v-show="isEdit" type="success" @click="updateAdminById">更新</el-button>
                        </div>

                        <el-button class="back-btn" type="primary" @click="back">返回</el-button>
                    </div>

                    <div v-show="!isFormItemsRight" class="error">请正确填写完所有的表单项！！！</div>
            </form>
        </div>
    </transition>


</template>

<script>
import moment from 'moment'
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
                updateAdmin:{
                    id:'',
                    birthday: '',
                    adminCode:'',
                    adminName:'',
                    sex:'',
                    phone:'',
                    address:'',
                    adminRole:'',
                },
                // 是否编辑
                isEdit:false,
                // 判断表单是否填写
                isFormItemsRight:true,
                // // updateAdmin的数据是否更改
                // isChange:false
  
            };
        },
        computed:{
            ...mapState('admin',['isAdminCode','adminDetail']),

            age(){
                return moment(new Date()).format("YYYY") - moment(this.adminDetail.birthday).format("YYYY") 
            }

        },
        methods:{
            //更新操作
            updateAdminById(){

                if(confirm('确定需要更新吗?')){
                    let isSubmit = true
                    //判断每个表单项是否为空  （保证每个表单项都不能为空）
                    for(let key in this.updateAdmin){
                        if(this.updateAdmin[key] === ''){
                            isSubmit = false
                            // console.log("请填写完所有的表单项")
                            this.isFormItemsRight = false
                            break
                        }
                    }
                    //发送ajax请求
                    if(isSubmit){
                        this.$store.dispatch("admin/updateAdminById",this.updateAdmin);
                    }
                    this.isEdit = false
                }
   
                
            },

            // 编辑
            edit(){
                this.isEdit = true
                this.updateAdmin.id = this.adminDetail.id
                this.updateAdmin.adminCode = this.adminDetail.adminCode
                this.updateAdmin.adminName = this.adminDetail.adminName
                this.updateAdmin.sex = this.adminDetail.sex
                this.updateAdmin.phone = this.adminDetail.phone
                this.updateAdmin.address = this.adminDetail.address
                this.updateAdmin.adminRole = this.adminDetail.adminRole
                this.updateAdmin.birthday = this.adminDetail.birthday
            },
            // 取消编辑
            cancelEdit(){
                // 取消表单框
                this.isEdit = false
                // 取消错误信息提醒
                this.isFormItemsRight = true
            },
            // 回退            
            back(){
                // 返回上一次路由位置
                this.$router.back()
            },

            //用来转换出职位
            role(adminRole){
                if(adminRole === '1'){
                return '经理'
                }else if(adminRole === '2'){
                return '主管'
                }else{
                return '普通员工'
                }
            },
            //将时间戳转换为日期的函数
            birthday(adminBirthday){
                return moment(adminBirthday).format("YYYY-MM-DD")
            },
        },

        watch:{
            // 'updateAdmin':{
            //     deep:true,//开启深度监听
            //     handler(){
            //         console.log("改变了")
            //     }
            // }
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
        background-color: rgb(247, 185, 92);
        width: 70%;
        height: 100%;
        position: absolute;
        top: 0px;
        left: 30%;
        overflow-x: hidden;
        overflow-y: hidden;
    }
    .admin-span{
        font: 16px bolder;
        color: rgb(49, 2, 237);
    }
    .form-horizontal{
        position: relative;
        left: 20%;
        top: 5%;
    }
    .col-sm-10{
        width: 40%;
        line-height: 35px;
    }
    .admin-btn{
        position: relative;
        left: 25%;
        width: 300px;
    }
    .edit-btn{
        position: relative;
        left: -10%;
    }
    .back-btn{
        width: 150px;
        margin-top: 20%
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