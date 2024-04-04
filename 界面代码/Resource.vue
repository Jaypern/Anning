<template>
  <div>
    <el-container style="min-height: 100%; border: 1px solid #eee">
      <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246);height: 100%;box-shadow: 2px 0.6px rgb(0 21 41 / 15%);">
        <el-menu :default-active="1-4-1"
                 style="min-height: 180vh;overflow-x: hidden;"
                 background-color="#fff"
                 text-color="rgb(96,99,103)"
                 active-text-color="rgb(113,125,216)"
                 :collapse-transition="false"
                 :collapse="isCollapse">
          <div style="height: 60px;margin-left:20px;line-height: 60px;text-align: left">
            <img src="../assets/logo_anning.png"alt=""style="width:50px;position: absolute;margin-top: 10px;margin-left: -10px">
            <b style="font-size:30px;color: rgb(83,182,223);position:absolute;margin-top: 5px;margin-left: 50px" srv-show="logoText">AnNing</b>
          </div>
          <el-menu-item index="1" @click.native="ToHome">
              <i class="el-icon-s-home"></i>
              <span slot="title" style="font-weight: 300">首页</span>
          </el-menu-item>
          <el-menu-item index="2"@click.native="ToMap">
            <i class="el-icon-position" ></i>
              <span slot="title"style="font-weight: 300">导航</span>
          </el-menu-item>
          <el-menu-item index="3" style="color:rgb(113,125,216);">
            <div style="background-color: #eeeeee;width: 150%;height: 100%;margin-left: -50px">
              <i class="el-icon-chat-line-round" style="color: rgb(113,125,216);margin-left: 50px"></i>
              <span slot="title"style="font-weight: 300;">咨讯</span>
              <i class="el-icon-arrow-right" style="margin-left: 80px"></i>
            </div>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style=" font-size: 12px;border-bottom: 1px solid #ccc;line-height: 60px;display: flex">
          <div style="flex: 1;font-size: 18px">
            <span :class="collapsBtnClass"style="cursor: pointer"@click="collapse"></span>
          </div>
        </el-header>

        <el-main>
          <div>
            <vue-particles
                color="#9CB6F9"
                :particle-opacity="0.7"
                :particles-number="200"
                shape-type="triangle"
                :particle-size="4"
                lines-color="#D0DDFC"
                :lines-width="1"
                :line-linked="true"
                :line-opacity="0.4"
                :lines-distance="150"
                :move-speed="3"
                :hover-effect="true"
                hover-mode="=grab"
                :click-effect="true"
                click-mode="=push">
            </vue-particles>
          </div>
          <div>
            <div class="test_box"style="position: relative;top: 20px;left: 20px;">
              <div style="position: relative;top: 10px;left:20px;">
                <div><h class="text_title" >三批国家级安宁疗护试点已覆盖全国185个市（区</h></div>
                <div class="text_content_box" ><span class="text"style="position:relative;">新华社北京7月15日电（记者李恒、田晓航）为进一步推动安宁疗护发展，国家卫生健康委办公厅日前印发《关于开展第三批安宁疗护试点工作的通知》，确定北京市、浙江省、湖南省为第三批国家安宁疗护试点省（市）截至目前，三批国家级安宁疗护试点已覆盖全国185个市（区）。</span></div>
              </div>
              <div style="position: relative;top:-50px;left:820px;width:80px;">
                <div style="left: 1000px">
                  <div class="circle"style="background-color: rgba(58,126,151,0.9);position: relative;top: -20px;left:120px"></div>
                  <el-button style="width:80px;position: relative;top: -20px" type="primary"@click="Page1">了解详情</el-button>
                </div>
              </div>
            </div>
            <div class="test_box" style="position: relative;top: 40px;left: 250px">
              <div style="position: relative;top: 10px;right:40px;text-align: right;">
                <div><h class="text_title">全国设有安宁疗护科的医疗卫生机构超4000家</h></div>
                <div class="text_content_box" style="position:relative;right: -230px"><span class="text">近年来我国积极推动安宁疗护服务发展，实施安宁疗护人才服务能力提升项目，已培训4000名安宁疗护骨干医护人员，全国设有安宁疗护科的医疗卫生机构超4000家，不断用心呵护患者“最后一程”。</span></div>
              </div>
              <div style="position: relative;top:-30px;left:30px;width:80px;">
                <div>
                  <div class="circle" style="background-color: rgba(236,91,92,0.9);position: relative;top:-20px;left:5px"></div>
                  <el-button style="position: relative;top: -20px;left: 50px" type="primary"@click="Page2">了解详情</el-button>
                </div>
              </div>
            </div>
            <div class="test_box"style="position: relative;top: 60px;left: 20px;">
              <div style="position: relative;top: 10px;left:20px;">
                <div><h class="text_title">安宁疗护：“余生”很重</h></div>
                <div class="text_content_box"><span class="text"sty>这是河北省沧州市人民医院安宁疗护区，入住患者将在这里度过余生。17名医护人员努力将患者病痛降到最低，让他们获得生命末期的安宁与尊严。</span></div>
              </div>
              <div style="position: relative;top:-30px;left:820px;width:80px;">
                <div style="left: 1000px">
                  <div class="circle"style="background-color: rgba(58,126,151,0.9);position: relative;top: -20px;left:120px"></div>
                  <el-button style="height: 30px;width:80px;position: relative;top: -20px" type="primary"@click="Page3">了解详情</el-button>
                </div>
              </div>
            </div>
            <div class="test_box" style="position: relative;top: 80px;left: 250px">
              <div style="position: relative;top: 10px;right:40px;text-align: right;">
                <div><h class="text_title">关注人生“最后一公里” 安宁疗护正走进公众视野</h></div>
                <div class="text_content_box" style="position:relative;right: -230px"><span class="text">新华社北京8月21日电（记者 温竞华）老林罹患鼻咽癌，治疗手段均已无效后转入安宁疗护中心，在医护人员专业化、人性化的照护下，老林从癌痛中解脱，对生活的感恩替代了对死亡的恐惧</span></div>
              </div>
              <div style="position: relative;top:-30px;left:30px">
                <div>
                  <div class="circle" style="background-color: rgba(236,91,92,0.9);position: relative;top:-20px;left:5px"></div>
                  <el-button style="height: 30px;width:80px;position: relative;top: -20px;left: 50px" type="primary"@click="Page4">了解详情</el-button>
                </div>
              </div>
            </div>
            <div class="test_box"style="position: relative;top: 100px;left: 20px;">
              <div style="position: relative;top: 10px;left:20px;">
                <div><h class="text_title" >泰康安宁疗护 守护生命的尊严</h></div>
                <div class="text_content_box"><span class="text">新华网北京7月20日电（吴诗萌 陈延特 实习生黄梦洁）你听说过安宁疗护吗？安宁疗护又叫做临终关怀、舒缓医疗、姑息治疗，它不是放弃治疗，也不是延缓死亡。</span></div>
              </div>
              <div style="position: relative;top:-30px;left:820px;width:80px;">
                <div style="left: 1000px">
                  <div class="circle"style="background-color: rgba(58,126,151,0.9);position: relative;top: -20px;left:120px"></div>
                  <el-button style="position: relative;top: -20px" type="primary"@click="Page5">了解详情</el-button>
                </div>
              </div>
            </div>
            <div class="test_box" style="position: relative;top: 120px;left: 250px">
              <div style="position: relative;top: 10px;right:40px;text-align: right;">
                <div><h class="text_title">国家卫健委：将尽快在全国范围推广安宁疗护</h></div>
                <div class="text_content_box" style="position:relative;right: -230px"><span class="text">新华社北京6月13日电（记者 屈婷、董小红）记者从国家卫生健康委员会日前召开的例行发布会上获悉，已启动第二批全国安宁疗护试点，将尽快把安宁疗护在全国全面推开。</span></div>
              </div>
              <div style="position: relative;top:-30px;left:30px">
                <div>
                  <div class="circle" style="background-color: rgba(236,91,92,0.9);position: relative;top:-20px;left:5px"></div>
                  <el-button style="height: 30px;width:80px;position: relative;top: -20px;left: 50px" type="primary"@click="Page6">了解详情</el-button>
                </div>
              </div>
            </div>
            <div class="test_box"style="position: relative;top: 140px;left: 20px;">
              <div style="position: relative;top: 10px;left:20px;">
                <div><h class="text_title">安宁疗护护士：温暖生命“最后一程”</h></div>
                <div class="text_content_box"><span class="text">新华社杭州5月12日电（记者黄筱 许舜达 李涛）在床头点亮香薰灯，手机里播放着舒缓的音乐，洪丹丹坐在病床边，缓缓地开始为病人进行手部护理，以提高患者的舒适感、促进血液循环。</span></div>
              </div>
              <div style="position: relative;top:-30px;left:820px;width:80px;">
                <div style="left: 1000px">
                  <div class="circle"style="background-color: rgba(58,126,151,0.9);position: relative;top: -20px;left:120px"></div>
                  <el-button style="position: relative;top: -20px" type="primary"@click="Page7">了解详情</el-button>
                </div>
              </div>
            </div>
            <div class="test_box" style="position: relative;top: 160px;left: 250px">
              <div style="position: relative;top: 10px;right:40px;text-align: right;">
                <div><h class="text_title">全国政协召开双周协商座谈会 围绕“推进安宁疗护工作”建言献策 俞正声主持</h></div>
                <div class="text_content_box" style="position:relative;right: -230px"><span class="text">新华社北京4月21日电 全国政协21日下午在京召开第49次双周协商座谈会，围绕“推进安宁疗护工作”建言献策。全国政协主席俞正声主持会议并讲话。</span></div>
              </div>
              <div style="position: relative;top:-20px;left:30px">
                <div>
                  <div class="circle" style="background-color: rgba(236,91,92,0.9);position: relative;top:-20px;left:5px"></div>
                  <el-button style="position: relative;top: -20px;left: 50px" type="primary"@click="Page8">了解详情</el-button>
                </div>
              </div>
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import pdf from 'vue-pdf'
import * as url from "url";
export default {
  components: {
    pdf
  },
  data() {
    return {
      collapsBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      logoText:true,
    }
  },
  mounted: function() {},
  methods: {
    Page1(){
      window.location.href='http://www.news.cn/2023-07/15/c_1129751823.htm'
    },
    Page2(){
      window.location.href='https://www.gov.cn/yaowen/liebiao/202310/content_6909176.htm'
    },
    Page3(){
      window.location.href='http://he.news.cn/xinwen/2023-05/17/c_1129620313.htm'
    },
    Page4(){
      window.location.href='https://www.gov.cn/xinwen/2019-08/21/content_5423099.htm'
    },
    Page5(){
      window.location.href='http://www.news.cn/2023-07/20/c_1212246502.htm'
    },
    Page6(){
      window.location.href='https://www.gov.cn/xinwen/2019-06/13/content_5399879.htm'
    },
    Page7(){
      window.location.href='http://zj.news.cn/2023-05/13/c_1129611310.htm'
    },
    Page8(){
      window.location.href='https://www.gov.cn/xinwen/2016-04/22/content_5066749.htm'
    },
    Goback(){
      this.$router.push('/home');
    },
    //点击收缩
    collapse(){
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapsBtnClass='el-icon-s-unfold'
        this.logoText=false
      }else{
        this.sideWidth=200
        this.collapsBtnClass='el-icon-s-fold'
        this.logoText=true
      }
    },
    //返回主页
    ToHome(){
      this.$router.push('/home')
    },
    ToMap(){
      this.$router.push('/map')
    }
  }
}
</script>

<style scoped>
.button{
  background-color: #fff;
  border-radius: 10px;
  width: 400px;
  height: 40px;
  font-size: 10px;
  font-weight: lighter;
  border: white;
  box-shadow: 8px 8px 5px #BBB7C5;;
}
.header{
  width: 100%;
  height: 50px;
  background-color: #ffffff;
  border-bottom:1px solid #ccc;
  box-shadow: 8px 8px 5px rgba(0,0,0,0.1);
  color: #000;
}
.text{
  font-weight: 300;
  line-height: 20px;
  margin: 0 0 1.25rem;
  font-size: 14px;
}
.divider{
  width: 1px;
  height: 30px;
  border-radius: 30px;
  background-color: #000;
}
.text_box{
  width: 80%;
  height: 100px;
  border-radius: 30px;
  box-shadow: 4px 0px 5px rgba(0,0,0,0.1),-4px 0px 5px rgba(0,0,0,0.1);
}
.text_content_box{
  width:80%;
  position: relative;
}
.circle{
  width: 20px;
  height: 20px;
  border-radius: 100%;
  box-shadow: 4px 4px 5px rgba(0,0,0,0.1);
}
.text_title{
  font-size: 18px;
  color: #5280F4;
}
#particles-js{
  width: 100%;
  height: 170%;
  position: absolute;
}
.test_box{
  width: 80%;
  height: 100px;
  border-radius: 30px;
  background-color: #ffffff;
  position: relative;
  box-shadow: 4px 0px 5px rgba(0,0,0,0.1),-4px 0px 5px rgba(0,0,0,0.1);
}
</style>
<!--再试试，应该能找到覆盖的方法，位置改成那啥,要么就是挨个调咯-->