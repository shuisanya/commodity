import Vue from 'vue'
import App from './App.vue'

//引入路由
import VueRouter from 'vue-router'
//引入一个路由器
import router from './router'
//引入store
import store from './store'
import axios from "axios"

Vue.use(VueRouter)


//按需引入
import { Button, Row , Menu , Submenu , MenuItemGroup , Col , MenuItem , 
  Table , TableColumn , Container , Header , Main , Drawer , DatePicker , 
  Pagination , Select , Option , Input , Icon , InputNumber , Descriptions,
  DescriptionsItem, Card, Form, FormItem, Transfer,Tabs,TabPane

} from 'element-ui';

Vue.component(Button.name, Button);
Vue.component(Select.name, Select);
Vue.component(Option.name, Option);
Vue.component(Input.name, Input);
Vue.component(Row.name, Row);
Vue.component(Menu.name, Menu);
Vue.component(Submenu.name, Submenu);
Vue.component(MenuItemGroup.name, MenuItemGroup);
Vue.component(Col.name, Col);
Vue.component(MenuItem.name, MenuItem);
Vue.component(Table.name, Table);
Vue.component(TableColumn.name, TableColumn);
Vue.component(Container.name, Container);
Vue.component(Header.name, Header);
Vue.component(Main.name, Main);
Vue.component(Drawer.name, Drawer);
Vue.component(DatePicker.name, DatePicker);
Vue.component(Pagination.name, Pagination);
Vue.component(Icon.name, Icon);
Vue.component(InputNumber.name, InputNumber);
Vue.component(Descriptions.name, Descriptions);
Vue.component(DescriptionsItem.name, DescriptionsItem);
Vue.component(Card.name, Card);
Vue.component(Form.name, Form);
Vue.component(FormItem.name, FormItem);
Vue.component(Transfer.name, Transfer);
Vue.component(Tabs.name, Tabs);
Vue.component(TabPane.name, TabPane);



Vue.config.productionTip = false


new Vue({
  render: h => h(App),
  router:router,
  store:store,
  beforeCreate(){
    Vue.prototype.$bus = this
    Vue.prototype.BASE_PATH = 'http://localhost:8081/commodity'
  },

}).$mount('#app')

axios.interceptors.request.use( config => {
  const token = localStorage.getItem("header-token");
  if(token !== null && token !== ""){
      config.headers["header-token"] = token;
  }
  return config
})
