<template>
<transition-group name="login" appear>
    <div class="home" :key="1">
        <div id="box">

            <div class="owl" :class="[flag === true ? 'password':'']">
                <div class="hand"></div>
                <div class="hand hand-r"></div>
                <div class="arms">
                    <div class="arm"></div>
                    <div class="arm arm-r"></div>
                </div>
            </div>

            <h2>登录</h2>

            <div class="msg_cont" v-show="flagError">
                <span style="color:red" class="errorMsg">{{msg}}</span>
            </div>

            <el-form method="post"
                    class="login-form"
                    label-position="right"
                    label-width="100px"
                    ref="admin"
                    :model="admin" 
                    size="medium" >

                <el-form-item
                class="login-input"
                prop="adminCode"
                label="账号">
                    <el-input type="text" placeholder="请输入用户名" v-model="admin.adminCode"></el-input>
                </el-form-item>

                <el-form-item 
                class="login-input"
                prop="adminPassword"
                label="密码">
                    <el-input type="password" v-model="admin.adminPassword" @focus="changeFlagTrue" @blur="changeFlagFalse" placeholder="请输入密码"></el-input>
                </el-form-item>

                <el-button class="login-btn" type="primary" @click="login" >登入</el-button>
                <el-button class="login-btn" type="success" @click="reset('admin')" >重置</el-button>

            </el-form>

        </div>
    </div>
</transition-group>


</template>

<script>
import axios from 'axios'
    export default {
        name:'Login',
        data(){
            return{
                flag:false,
                admin:{
                    adminCode:'',
                    adminPassword:'',
                },
                flagError:false,
                msg:'',
            }
        },
        computed:{

        },
        methods:{
            changeFlagTrue(){
                this.flag = true
            },
            changeFlagFalse(){
                this.flag = false
            },
            login(){
                if(this.admin.adminCode !== '' && this.admin.adminPassword !== ''){
                    const adminString = JSON.stringify(this.admin);
                        axios.post(`${this.BASE_PATH}/admin/login/${adminString}`).then(
                            response=>{
                                if(response.data.msg !== ''){
                                    this.flagError = true
                                    this.msg = response.data.msg
                                }
                                if(response.data.admin !== null && response.data.admin !== '' && response.data.admin !== undefined){
                                    localStorage.setItem("admin",response.data.admin)
                                    localStorage.setItem("header-token",response.data["header-token"])
                                    this.$router.push({
                                        name:'manage'
                                    })
                                }

                            },
                            error=>{
                                alert('登入失败！！')
                            }
                    )
                } else {
                    this.flagError = true
                    this.msg='请输入账号和密码！！！'
                }
            },
            reset(admin){
                this.$refs[admin].resetFields()
                this.flagError = false
                this.msg=''
            }
        }
    }
</script>

<style scoped>
.home{
    height: 710px;
    background-image: url("../assets/bjqx.jpeg");
    background-size: 50%,50%;
}
#box{
    /* background: linear-gradient(200deg, #8b72d3, #99fb96); */
    background-color: rgba(87, 175, 239, 0.2);
    margin: 0 auto;/*margin: 上 右 下 左*/
    border-radius: 10px;
    width: 300px;
    height: 250px;
    position: relative;
    top: 28%;
}

.owl{
    width: 211px;
    height: 108px;
    background: url("../assets/mty2.png") no-repeat;
    position: absolute;
    top: -99px;
    left: 45px;
}
.owl .hand{
    width: 34px;
    height: 34px;
    border-radius: 40px;
    background-color: #472d20;
    position: absolute;
    left: 12px;
    top: 77px;
    transform: scaleY(0.6);
    transition: 0.3s ease-out;
}
.owl .hand.hand-r{
    left: 168px;
}
.owl.password .hand{
    transform: translateX(42px) translateY(-15px) scale(0.5);
}
.owl.password .hand.hand-r{
    transform: translateX(-42px) translateY(-15px) scale(0.7);
}
.owl .arms{
    position: absolute;
    top: 58px;
    width: 100%;
    height: 41px;
    overflow: hidden;
}
.owl .arms .arm{
    width: 40px;
    height: 65px;
    position: absolute;
    left: 20px;
    top: 40px;
    background: url("../assets/mty1.png") no-repeat;
    transform: rotate(-20deg);
    transition: 0.3s ease-out;
}
.owl .arms .arm.arm-r{
    transform: rotate(20deg) scaleX(-1);
    left: 158px;
}
.owl.password .arms .arm{
    transform: translateY(-40px) translateX(40px);
}
.owl.password .arms .arm.arm-r{
    transform: translateY(-40px) translateX(-40px) scaleX(-1);
}

.login-form{
    position: relative;
    top: 8%;
}
.login-input{
    position: relative;
    left: -11%;
}
.login-btn{
    position: relative;
    left: 28%;
    width: 70px;
}

h2{
    color: aliceblue;
    margin: 0px;
    font-size: 30px;
    text-align: center;
}
.msg_cont{
    position: relative;
    text-align: center;
    background-color: rgba(245, 241, 5, 0.5);
    top: 5%;
}
.msg_cont b{
    position: relative;
    left: 7px;
    top: 2px;
}


.login-enter-active{
    animation: handoff 0.5s;
}

.login-leave-active{
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
</style>>
