<template>

  <div class="admin-list">

    <table>
      <thead>
        <tr>
            <td>账号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>生日</td>
            <td>手机号</td>
            <td>地址</td>
            <td>职位</td>
            <td>操作</td>
        </tr>
      </thead>

      <tbody>
        <tr v-for="admin in adminList" :key="admin.id">
          <td>{{admin.adminCode}}</td>
          <td>{{admin.adminName}}</td>
          <td>{{admin.sex === 1 ? '男' : '女'}}</td>
          <td>{{birthday(admin.birthday)}}</td>
          <td>{{admin.phone}}</td>
          <td>{{admin.address}}</td>
          <td>{{role(admin.adminRole)}}</td>
          <td>
            <el-button type="primary" icon="el-icon-edit" @click="lookAdminDetail(admin.id,admin.adminName)" circle>详情</el-button>
            <el-button type="danger" icon="el-icon-close" @click="deleteAdminById(admin.id,admin.adminName)" circle>删除</el-button> 
          </td>
        </tr>
      </tbody>

    </table>
    
  </div>
</template>

<script>
// 将时间戳转换为日期的库
import moment from 'moment'
//引用vuex中的map映射
import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'

    export default {

    name:'AdminList',

    data() {
      return {
      };
    },

    components:{},

    computed:{
      // 对象写法 从store中获取数据
      // ...mapState({adminList:'adminList'})

      // 从store中接受adminList的数据 （数组写法）
      ...mapState('admin',['adminList'])
    },

    methods:{
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

      // 查看一个admin的详情
      lookAdminDetail(id,adminName){

        this.$store.commit('admin/GET_ADMIN_DETAIL',id);
        //路由到该组件
          this.$router.push({
          name:'adminDetail',
          params:{
            adminName:adminName,
          }
        })
      },

      //删除一个admin
      deleteAdminById(id,name){
        if(confirm('确定删除【' + name + '】吗?')){
          this.$store.dispatch('admin/deleteAdminById',id);
        }
      }
      
      
    },

    mounted(){
      // this.$bus.$on('queryAdmins',this.currentChange)
      // this.currentChange(1)
    },

    beforeDestory(){
        // this.$bus,$off(['queryAdmins'])
      }

  }
</script>

<style scoped>
  .admin-list{
    width: 100%;
    height: 500px;
    overflow-y: auto;
  }
  table{
    color: #000000;
    width: 100%;
    height:100%;
    border-collapse: collapse;
    border-spacing: 0;
  }
  thead{
    color: #000000;
    font-size: 20px;
    font-weight: bolder;
    height: 50px;
    background-color: #85FFBD;
    background-image: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);
  }
  tr {
      border-bottom: #57626f solid 1px;
      text-align: center;
  }
 tbody tr:nth-of-type(even){
    background-color: bisque;
  }
 tbody tr:nth-of-type(odd){
    background-color: azure;
  }
  tbody tr:hover{
    background-color: aqua;
  }
</style>