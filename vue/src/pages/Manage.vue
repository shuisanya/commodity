<template>
    <transition-group name="manage" appear>
        <div :key="1">
            <HeadNav></HeadNav>
            <div class="nav-main">
                <div class="side-nav">
                    <SideNav></SideNav>
                </div>
                <div class="mian-pincipal">
                    <Pincipal></Pincipal>
                </div>      
            </div>
            <Footer></Footer>
        </div>
    </transition-group>
</template>

<script>  
    import SideNav from '../components/SideNav'
    import HeadNav from '../components/HeadNav'
    import Pincipal from '../components/Pincipal'
    import Footer from '../components/Footer'

    export default {
        name:'Manager',
        components:{SideNav,HeadNav,Pincipal,Footer},
        
               //路由到该页面需要查询数据
        mounted(){
            // this.$router.push({
            //     name:'home'
            // })
        }
    }
        
</script>

<style scoped>
    .nav-main{
        width: 100%;
        height: 600px;
        position: relative;
    }
    .side-nav{
        width: 20%;
        height: 100%;
        float: left;
        overflow-y: auto;
        overflow-x: hidden;
        background-color: #FFE53B;
        background-image: linear-gradient(147deg, #FFE53B 0%, #FF2525 74%);
    }
    .mian-pincipal{
        width: 80%;
        height: 100%;
        float: left;
        overflow-y: auto;
        overflow-x: hidden;
        background-color: #85FFBD;
        background-image: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);
    }


    .manage-enter-active{
        animation: handoff 0.5s;
    }

    .manage-leave-active{
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