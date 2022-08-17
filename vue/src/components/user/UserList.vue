<template>

  <div class="user-list">

    <table>
      <thead>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>手机号</td>
            <td>地址</td>
            <td>操作</td>
        </tr>
      </thead>

      <tbody>
        <tr v-for="user in userList" :key="user.userCode">
          <td>{{user.userCode}}</td>
          <td>{{user.userName}}</td>
          <td>{{user.phone}}</td>
          <td>{{user.address}}</td>
          <td>
            <el-button type="primary" icon="el-icon-edit" @click="viewUserDetail(user.userCode,user.userName)" circle>详情信息</el-button>
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

    name:'UserList',

    data() {
        return {
        };
      },

    components:{},

    computed:{
        // 对象写法 从store中获取数据
        // ...mapState({adminList:'adminList'})

        // 从store中接受adminList的数据 （数组写法）
        ...mapState('user',['userList'])
      },

    methods:{
        viewUserDetail(userCode,userName){
          this.$router.push({
            name:'userDetail',
            params:{
              userCode:userCode,
              userName:userName
            }
          })
        }
      },

    mounted(){

      },

    beforeDestory(){

      }

    }
</script>

<style scoped>
  .user-list{
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
    background-color: #d9e836;
    background-image: linear-gradient(45deg, #d9e836 0%, #FFFB7D 100%);
  }
  tr {
      border-bottom: #660add solid 1px;
      text-align: center;
  }
 tbody tr:nth-of-type(even){
    background-color: rgb(152, 233, 239);
  }
 tbody tr:nth-of-type(odd){
    background-color: rgb(239, 169, 237);
  }
  tbody tr:hover{
    background-color: rgb(228, 72, 20);
  }
</style>