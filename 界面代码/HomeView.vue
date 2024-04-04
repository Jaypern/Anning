<template>
  <div style="height:100vh">
    <el-container style="min-height: 100%; border: 1px solid #eee">
      <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246);height: 100%;box-shadow: 2px 0.6px rgb(0 21 41 / 15%);">
        <el-menu :default-openeds="['1', '3']" style="min-height: 283vh;overflow-x: hidden;"
                 background-color="#fff"
                 text-color="rgb(96,99,103)"
                 active-text-color="rgb(32,33,36)"
                 :collapse-transition="false"
                 :collapse="isCollapse">
          <div style="height: 60px;margin-left:20px;line-height: 60px;text-align: left">
            <img src="../assets/logo_anning.png"alt=""style="width:50px;position: absolute;margin-top: 10px;margin-left: -10px">
            <b style="font-size:30px;color: rgb(83,182,223);position:absolute;margin-top: 5px;margin-left: 50px" srv-show="logoText">AnNing</b>
          </div>
          <el-menu-item index="1" >
            <div style="background-color: #eeeeee;width: 150%;height: 100%;margin-left: -50px">
              <i class="el-icon-s-home"style="color: rgb(113,125,216);margin-left: 50px"></i>
              <span slot="title" style="font-weight: 300">首页</span>
              <i class="el-icon-arrow-right" style="margin-left: 80px"></i>
            </div>
          </el-menu-item>
          <el-menu-item index="2"@click.native="ToMap">
              <i class="el-icon-position"></i>
              <span slot="title"style="font-weight: 300">导航</span>
          </el-menu-item>
          <el-menu-item index="3" @click.native="ToResource">
              <i class="el-icon-chat-line-round"></i>
              <span slot="title"style="font-weight: 300">咨讯</span>
          </el-menu-item>
          <el-divider></el-divider><!--横杠-->
          <el-button @click="drawer = true" type="primary" style="margin-left: 10px;margin-bottom: 10px" round>
            <i class="el-icon-phone-outline" style="font-size: 16px"></i>
          </el-button>
          <!--需要实现的表单后端-->
          <el-drawer
            :visible.sync="drawer"
            :with-header="false">
            <el-image
                :src="require('@/assets/图片3.jpg')"
                style="width: 100%; height: 250px">
            </el-image>
            <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            <template>
              <el-form  label-width="80px"
                        :model="ruleForm"
                        status-icon
                        :rules="rules"
                        ref="ruleForm">
                <el-form-item label="Email_qq" prop="uname">
                  <el-input v-model="ruleForm.uname" placeholder="填写你的邮箱，这会方便我们联系您"></el-input>
                </el-form-item>
                <el-form-item label="Feedback" prop="text">
                  <el-input type="textarea" v-model="ruleForm.text" placeholder="期待您的反馈"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit('ruleForm')">完成</el-button>
                </el-form-item>
              </el-form>
            </template>
            <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            <div>
              <b style=" font-weight: 300;margin: 0 0 1.25rem;margin-left: 10px;color: #333333">联系我们</b>
            </div>
            <div>
              <b style=" font-size:12px;font-weight: 300;margin: 0 0 1.25rem;margin-left: 10px;
                    position: relative;top:10px;color: deepskyblue">Email:AnningWorld@gmail.com</b>
            </div>
          </el-drawer>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style=" font-size: 12px;border-bottom: 1px solid #ccc;line-height: 60px;display: flex">
          <div style="flex: 1;font-size: 18px">
            <span :class="collapsBtnClass"style="cursor: pointer"@click="collapse"></span>
          </div>
          <div style="padding: 0 70px;">
            <el-input style="flex:1;width:1000px;top: 0px;" placeholder="Search" suffix-icon="el-icon-search"></el-input>
          </div>
          <el-dropdown  @click.native="mounted" trigger=click style="width: 70px;cursor: pointer">
            <i class="el-icon-arrow-down"style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>ID:{{user.uname}}</el-dropdown-item>
              <el-dropdown-item @click.native="logout">
                <div style="text-align: center; color: #0367a6">
                  登出
                </div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>
        <el-main>
            <div id="app">
              <div class="SwiperBox" ref="SwiperBox" @mouseenter="MouseFun('移入')" @mouseleave="MouseFun('移出')">
                <!-- 图片 -->
                <div class="imgBox" :style="{left:`-${leftVal}px`,transition:`${ition}s`}">
                  <img :src="item.imgUrl" v-for="(item,index) in imgList" :key="index" />
                  <!-- 复制第一张放到最后,以实现无缝无线循环滚动效果 -->
                  <img :src="imgList[0].imgUrl" alt="">
                </div>
                <!-- 左箭头按钮 -->
                <div class="leftBtn" @click="throttle(PrevFun)">
                  <i class="el-icon-arrow-left"></i>
                </div>
                <!-- 右箭头按钮 -->
                <div class="rightBtn" @click="throttle(NextFun)">
                  <i class="el-icon-arrow-right"></i>
                </div>
                <!-- 下方指示点容器 -->
                <div class="instBox">
                  <div @click="instFun(index)" v-for="(item,index) in imgList.length" :key="index"
                       :class="['inst',index==imgShow?'instActv':'']">
                  </div>
                </div>
              </div>
              <div class="contentbox">
                <div class="contentbox">
                  <div class="empty_tangle_2"style="margin-left: 500px;margin-top: 10px"></div>
                  <div class="image_tangle" style="margin-top: -150px;margin-left: 50px">
                  <img class="image_tangle_imgsize" src="../assets/主页展示图4.jpg">
                </div>
                  <div class="empty_tangle_1"style="margin-left: 20px;margin-top: -50px "></div>
                  <div style="margin-left: 700px;margin-top: -300px">
                    <i class="text_1">了解安宁疗护</i>
                  </div>
                  <div style="margin-left: 700px;margin-top: 20px">
                    <i class="text_2">人口老龄化日益严重的现在，安宁疗护有其必要性</i>
                  </div>
                </div>
              </div>
              <div class="shadowbox" style="margin-top: 150px">
                <div>
                  <div style="margin-top: 50px;margin-left: 650px">
                    <i class="text_1">人口现状</i>
                  </div>
                  <div style="margin-top: 20px;margin-left: 650px">
                    <i class="text_2">截至2021年，人口老龄化严重，安宁疗护不再是少数人的议题</i>
                  </div>
                  <div style="margin-left: 600px;margin-top: -20px">
                    <div class="echart" id="mychart" :style="myChartStyle"></div>
                  </div>
                </div>
                <!--那几个小box-->
                <div style="margin-top: 50px;margin-left: 100px">
                  <div>
                    <div style="margin-top: -140px;margin-left: -60px" class="box" shadow="hover">
                      <div class="line" style="position: relative;top: 20px;background-color: rgba(236,91,92,0.8);"></div>
                      <div style="position:relative;top:-450px;left: 40%">
                        <i class="el-icon-magic-stick" style="font-size:35px ;color: rgb(113,125,216);"></i>
                        <p style="font-size:22px;font-weight: 300;position: relative;top: 20px;left:-8px">终 末</p>
                      </div>
                    </div>
                    <div style="margin-top: -130px;margin-left: 200px" class="box" shadow="hover">
                      <div class="line" style="position: relative;top: 20px;background-color: rgba(58,126,151,0.8)"></div>
                      <div style="position:relative;top:-450px;left: 40%">
                        <i class="el-icon-first-aid-kit" style="font-size:35px ;color: rgb(113,125,216);"></i>
                        <p style="font-size:22px;font-weight: 300;position: relative;top: 20px;left:-8px">关 怀</p>
                      </div>
                    </div>
                  </div>
                  <div style="margin-top: 150px">
                    <div style="margin-top: -140px;margin-left: -60px" class="box" shadow="hover">
                      <div class="line" style="position: relative;top: 20px;background-color: rgba(58,126,151,0.8)"></div>
                      <div style="position:relative;top:-310px;left: 40%">
                        <i class="el-icon-office-building" style="font-size:35px ;color: rgb(113,125,216);"></i>
                        <p style="font-size:22px;font-weight: 300;position: relative;top: 20px;left:-8px">舒 适</p>
                      </div>
                    </div>
                    <div style="margin-top: -130px;margin-left: 200px" class="box" shadow="hover">
                      <div class="line" style="position: relative;top: 20px;background-color: rgba(236,91,92,0.8);"></div>
                      <div style="position:relative;top:-310px;left: 40%">
                        <i class="el-icon-table-lamp" style="font-size:35px ;color: rgb(113,125,216);"></i>
                        <p style="font-size:22px;font-weight: 300;position: relative;top: 20px;left:-8px">尊 严</p>
                      </div>
                    </div>
                  </div>
                  <div style="margin-top: 150px">
                    <div style="margin-top: -140px;margin-left: -60px" class="box" shadow="hover">
                      <div class="line" style="position: relative;top: 20px;background-color: rgba(236,91,92,0.8);"></div>
                      <div style="position:relative;top:-170px;left: 40%">
                        <i class="el-icon-user" style="font-size:35px ;color: rgb(113,125,216);"></i>
                        <p style="font-size:22px;font-weight: 300;position: relative;top: 20px;left:-8px">团 队</p>
                      </div>
                    </div>
                    <div style="margin-top: -130px;margin-left: 200px" class="box" shadow="hover">
                      <div class="line" style="position: relative;top: 20px;background-color: rgba(58,126,151,0.8)"></div>
                      <div style="position:relative;top:-170px;left: 40%">
                        <i class="el-icon-connection" style="font-size:35px ;color: rgb(113,125,216);"></i>
                        <p style="font-size:22px;font-weight: 300;position: relative;top: 20px;left:-8px">家 庭</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="info_tangle_all" style="margin-top: 50px">
                <div class="info_tangle" style="">
                  <div class="head" style="margin-left: 100px;position: relative;top: 10px;background-color: rgba(58,126,151,0.6);">
                    <i class="el-icon-sunny" style="position:relative;margin-top:20px;margin-left:20px;font-size: 80px"></i>
                  </div>
                  <p style="font-weight: bolder;margin-top: 30px;margin-left:100px;font-size: 20px">精神上的支持</p>
                  <div style="margin-left: 50px;margin-right: 70px;margin-top:20px;line-height:30px;font-weight: 300">
                    <p>安宁疗护专科医生可以帮助患者来找回自己的信念和价值观，并让他们能够找到和平或他们能接受的观点。</p>
                  </div>
                </div>
                <div class="info_tangle"  style="margin-top: -500px;margin-left: 35%">
                  <div class="head" style="margin-left: 100px;background-color: rgba(111,157,237,0.7);position: relative;top: 10px">
                    <i class="el-icon-guide" style="position:relative;margin-top:20px;margin-left:20px;font-size: 80px"></i>
                  </div>
                  <p style="font-weight: bolder;margin-top: 30px;margin-left:120px;font-size: 20px">应付情绪</p>
                  <div style="margin-left: 70px;margin-right: 70px;margin-top:20px;line-height:30px;font-weight: 300">
                    <p>安宁疗护能帮助患者和家人来应付癌症的诊断和治疗所带来的情绪。 安宁疗护可以帮助病人舒缓抑郁，焦虑和恐惧。</p>
                  </div>
                </div>
                <div class="info_tangle" style="margin-top: -500px;margin-left: 72%">
                  <div class="head" style="margin-left: 100px;background-color: rgba(58,126,151,0.6);position: relative;top: 10px">
                    <i class="el-icon-news" style="position:relative;margin-top:20px;margin-left:20px;font-size: 80px"></i>
                  </div>
                  <p style="font-weight: bolder;margin-top: 30px;margin-left:100px;font-size: 20px">护理人员的需求</p>
                  <div style="margin-left: 50px;margin-right: 70px;margin-top:20px;line-height:30px;font-weight: 300">
                    <p>家庭成员是癌症护理重要的一部分。 护理人员通常会感到压力，照顾生病的亲戚，并处理及承担额外的责任，例如工作，家务和照顾其他家庭成员。 安宁疗护可以帮助家人和朋友应对并给予他们所需的支持。</p>
                  </div>
                </div>
              </div>
            </div>
          <div class="finallilne" style="margin-top:10px;margin-bottom: -20px;margin-left: -05px"></div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import {left, right} from "core-js/internals/array-reduce";
import * as echarts from "echarts";
export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data() {
    return{
      collapsBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      logoText:true,
      drawer :false,
      //点击后后面信息窗格的动画效果：
      width_move:430,
      //折线图的信息
      myChart:{},
      xData:["2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022"],
      yData:[14.3,14.9,15.0,16.1,16.7,17.3,17.9,18.1,18.7,18.9,19.8],
      myChartStyle:{float:"left",width:"100%",height:"400px"},
      //登陆注册的信息
      user:{
        uname:"",
        uid:null,
      },
      //表单的信息
      ruleForm: {
        uname: '',
        text:'',
        delivery: false,
      },
      //一些操作提示_表单
      rules:{
        uname:[{required:true,message:"NULL",trigger:"blur"},],
        text:[{required:true,message:"NULL",trigger:"blur"}],
      },
      loading:false,
      //主页图片滚动数据：
      imgList: [
        {imgUrl: require("../assets/主页展示图1.jpg")},
        {imgUrl: require("../assets/主页展示图2.jpg")},
        {imgUrl: require("../assets/主页展示图3.jpg")},
      ],
      leftVal: 0, // 轮播图盒子的偏移值
      flag: true, // 用来节流防止重复点击
      start: null, // 自动执行下一张定的时器
      imgWidth: 1230, // 在这里填写你需要的图片宽度
      ition: 0.6, // 设置轮播图过度时间
      imgShow: 0, // 表示当前显示的图片索引
      activeName: '1'
    };
  },
  mounted() {
    this.timer();
    this.initEcharts();
  },
  methods:{
    right,
    left,
    //折线图函数
    initEcharts(){
      const option={
        xAxis:{
          data:this.xData
        },
        yAxis:{
          min: 12,
          max:20,
          axisTick:{
            show:true,
            alignWithLabel:true,
            interval:6,
          },
        },
        series:[
          {
            data:this.yData,
            type:"line",//类型
            smooth:true
          }
        ]
      };
      this.myChart =echarts.init(document.getElementById("mychart"));
      this.myChart.setOption(option);
      /*window.addEventListener("risize",()=>{
        this.myChart.resize();
      });*/
    },
    //点击收缩
    collapse(){
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapsBtnClass='el-icon-s-unfold'
        this.logoText=false
        this.imgWidth=1368
        this.ition = 0.6
        this.leftVal += this.imgWidth
        this.imgShow = 0
        this.width_move=200
        info_tangle[1].style.marginLeft=this.width_move
        this.$nextTick(() => {
          setTimeout(() => {
            this.ition = 0
            this.leftVal = 0
          }, this.ition * 1000)
        })
      }else{
        this.sideWidth=200
        this.collapsBtnClass='el-icon-s-fold'
        this.logoText=true
        this.imgWidth=1230
        this.ition = 0.6
        this.leftVal += this.imgWidth
        this.imgShow = 0
        this.$nextTick(() => {
          setTimeout(() => {
            this.ition = 0
            this.leftVal = 0
          }, this.ition * 1000)
        })
      }
    },
    //用来跳转到resource
    ToResource(){
      this.$router.push('/resource');
    },
    ToMap(){
      this.$router.push('/map');
    },
    //用户登出
    logout(){
      sessionStorage.removeItem('userInfo');
      this.$router.push('/login');
    },
    //获得注册表中的用户姓名
    mounted(){
      if(sessionStorage.getItem('userInfo')){
        //将用户信息存储到sessionStorage
        this.user=JSON.parse(sessionStorage.getItem('userInfo'));
      }
    },
    //用来提交表单
    onSubmit(formName) {//用来提交表单部分
      this.$refs[formName].validate((valid) => {
        this.loading = true;  // 提交按钮显示加载动画
        if (valid) {
          let _this = this;
          this.axios({     // axios 向后端发起请求
            url: "/api/caller/feedback",  // 请求地址
            method: "post",             // 请求方法
            headers: {                  // 请求头
              "Content-Type": "application/json",
            },
            data: { // 请求参数，为 data，与登录的 params 不太一样
              uname: _this.ruleForm.uname,
              text: _this.ruleForm.text,
            },
          }).then((res) => { // 当收到后端的响应时执行该括号内的代码，res 为响应信息，也就是后端返回的信息
            if (res.data.code === '0') {  // 当响应的编码为 0 时，说明注册成功
              // 显示后端响应的成功信息
              this.$message({
                message: res.data.msg,
                type: "success",
              });
            }else{  // 当响应的编码不为 0 时，说明注册失败
              // 显示后端响应的失败信息
              this.$message({
                message: res.data.msg,
                type: "warning",
              });
            }
            // 不管响应成功还是失败，收到后端响应的消息后就不再让登录按钮显示加载动画了
            _this.loading = false;
            console.log(res);
          });
        } else { // 如果账号或密码有一个没填，就直接提示必填，不向后端请求
          console.log("error submit!!");
          this.loading = false;
          return false;
        }
      });
    },
    // 这里定义一个鼠标移入移出事件，鼠标悬停时停止自动轮播，鼠标移出则重新执行自动轮播
    MouseFun(type) { // 停止定时器            // 重新执行定时器
      type == '移入' ? clearTimeout(this.start) : this.timer()
    },
    // 此为自动轮播定时器
    timer() {
      this.start = setInterval(() => {
        this.NextFun()
      }, 1500)
    },
    // 这里通过额外封装的节流函数触发 PrevFun() 和 NextFun(),以达到防止重复点击的效果
    throttle(fun) {
      if (this.flag) {
        this.flag = false;
        fun(); // 此为模板中传递进来的PrevFun()或NextFun()函数
        setTimeout(() => {
          this.flag = true;
        }, 650); // 设置节流间隔时间,不得小于图片过渡时间
      }
    },
    // 上一张
    PrevFun() {
      if (this.leftVal == 0) { // 判断显示的图片 是 第一张时执行
        // this.imgList.length是指循环图片数组的图片个数
        this.ition = 0 // 将过渡时间变成0，瞬间位移到最后一张图
        this.imgShow = this.imgList.length - 1 // 将高亮小点改为最后一张图
        this.leftVal = this.imgList.length * this.imgWidth // 瞬间移动
        this.$nextTick(() => {	// $nextTick是一个vue内置函数,是一个等待dom元素更新后执行的回调函数
          setTimeout(() => { // 通过延时障眼法,归原过渡时间,执行真正的“上一张”函数
            this.ition = 0.6
            this.leftVal -= this.imgWidth
          }, this.ition * 1000)
        })
      } else { // 判断显示的图片 不是 第一张时执行
        this.ition = 0.6
        this.leftVal -= this.imgWidth
        this.imgShow--
      }
    },
    // 下一张
    NextFun() {
      if (this.leftVal == (this.imgList.length - 1) * this.imgWidth) { // 判断显示的图片 是 最后一张时执行
        this.ition = 0.6
        this.leftVal += this.imgWidth
        this.imgShow = 0
        this.$nextTick(() => {
          setTimeout(() => {
            this.ition = 0
            this.leftVal = 0
          }, this.ition * 1000)
        })
      } else { // 判断显示的图片 不是 最后一张时执行
        this.ition = 0.6
        this.leftVal += this.imgWidth
        this.imgShow++
      }
    },
    // 点击小圆点
    instFun(index) {
      this.ition = 0.6
      this.leftVal = index * this.imgWidth
      this.imgShow = index
    },
  },
}
</script>

<style>
//实现图片的滚动样式
* {
  padding: 0px;
  margin: 0px;
  box-sizing: border-box;
}
/* 图片容器样式 */
.SwiperBox {
  position: relative;
  width: 100%;
  height: 400px;
  box-sizing: border-box;
  cursor: pointer;
  overflow: hidden;
}
.imgBox {
  position: absolute;
  top: 0px;
  left: 0px;
  min-width: 500px;
  width: 100%;
  height: 400px;
  display: flex;
  justify-content: flex-start;
}
/* 图片默认样式 */
.imgBox img {
  flex-shrink: 0;
  width: 100%;
  height:400px;
}
/* 两个按钮共有的样式,也可直接使用箭头图片替代 */
.leftBtn,
.rightBtn {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 30px;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(109, 109, 109, 0.445);
  color: #fff;
  border-radius: 50%;
  cursor: pointer;
  font-size: 12px;
  font-weight: 500;
  user-select: none;
}
.leftBtn {
  left: 10px;
}
.rightBtn {
  right: 10px;
}
/* 下方指示器盒子 */
.instBox {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  bottom: 10px;
  display: flex;
}
/* 小圆点 */
.inst {
  width: 10px;
  height: 10px;
  border: 1px solid #ccc;
  margin-right: 8px;
  background: #fff;
  border-radius: 50%;
  cursor: pointer;
}
.inst:last-child {
  margin-right: 0px;
}
.instActv {
  border: 1px solid #ccc;
  background: #ccc;
}
.contentbox{
  width: 100%;
  height: 300px;
  background-color: #fff;
}
.empty_tangle_1{
  width:100px;
  height:100px;
  padding: 8px 12px;
  background-image: linear-gradient(180deg, #5899F3, #8AB8F7 40%, #fff);
}
.empty_tangle_2{
  width: 100px;
  height:200px;
  padding: 8px 12px;
  background-image: linear-gradient(180deg, #5899F3, #8AB8F7 40%, #fff);
}
.image_tangle{
  width:500px;
  height: 334px;
  background-color: #333333;
}
.image_tangle_imgsize{
  width: 500px;
  height: 334px;
}
.shadowbox {
  width: 100%;
  height: 500px;
  //border: 1px solid #333;
  box-shadow: 8px 8px 5px #BBB7C5;
  border-radius: 15px;
  padding: 8px 12px;
  background-image: linear-gradient(180deg, #5899F3, #8AB8F7 40%, #fff);
}
.text_1{
  font-size: 36px;
  color: #000;
}
.text_2{
  font-size: 18px;
  font-weight: 300;
  margin: 0 0 1.25rem;
  color:#000;
}
.info_tangle_all{
  height: 500px;
  width: 100%;
  background-color: #fff;
}
.info_tangle{
  height: 500px;
  width: 350px;
  border-radius: 30px;
  background-color: #fff;
  box-shadow: 4px 4px 5px rgba(0,0,0,0.1),-4px 4px 5px rgba(0,0,0,0.1),-4px -4px 5px rgba(0,0,0,0.1),4px -4px 5px rgba(0,0,0,0.1);
}
.head{
  border-radius: 100%;
  height: 120px;
  width: 120px;
  background-color: #F5F5F4;
}
.finallilne{
  width: 100%;
  height: 100px;
  background-color: #0367a6;
}
.box{
  height: 130px;
  width: 250px;
  border-radius: 30px;
  box-shadow: 4px 4px 5px rgba(0,0,0,0.1),-4px 4px 5px rgba(0,0,0,0.1),-4px -4px 5px rgba(0,0,0,0.1),4px -4px 5px rgba(0,0,0,0.1);
  background-color: #fff;
}
.line{
  width:5px;
  height:90px;
  border-radius: 30px;
}
#app {
}
</style>