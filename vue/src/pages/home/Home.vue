<template>

    <transition-group name="home" appear>
        <div class="myh-home" :key="1">
            <h2>欢迎：<span>水三丫</span> 管理员</h2>
            <h2>职位：<span>经理</span></h2>
            <h2>以下是该系统的信息</h2>
        </div>

        <div :key="2">
            <hr style="border-color:rgb(0, 0, 245)">
        </div>

        <el-descriptions :column="3" size="medium" border :key="3">
                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-check"></i>
                        管理员人数
                    </template>
                    {{allCounts.adminCounts}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-user-solid"></i>
                        用户数量
                    </template>
                    {{allCounts.userCounts}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-shopping-bag-1"></i>
                        商品数量
                    </template>
                    {{allCounts.commodityCounts}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-truck"></i>
                        供应商数量
                    </template>
                    {{allCounts.vendorCounts}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-present"></i>
                        品牌数量
                    </template>
                    {{allCounts.brandCounts}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-data"></i>
                        类别数量
                    </template>
                    {{allCounts.classifyCounts}}
                    </el-descriptions-item>

                    <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-eleme"></i>
                        订单数量
                    </template>
                    {{allCounts.orderCounts}}
                    </el-descriptions-item>

                </el-descriptions>

    </transition-group>

</template>

<script>
import axios from 'axios'

export default {
    name:'Home',
    data(){
        return{
            allCounts:''
        }
    },

    //路由到该页面需要查询数据
    mounted(){
        axios.get(`${this.BASE_PATH}/home/queryAllCounts`).then(
            response=>{
                this.allCounts = response.data
            },
            error=>{
                console.log('请求失败了',error.message)
            }
        )
    }

}
</script>

<style scoped>
    .myh-home{
        height: 200px;
        margin-bottom: 2%;
    }
    .myh-home span{
        color: red;
    }
    .myh-home h2{
        position: relative;
        left: 35%;
    }


    .home-enter-active{
        animation: handoff 0.5s;
    }

    .home-leave-active{
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