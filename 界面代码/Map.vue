<template>
  <div>
    <el-container style="min-height: 100%; border: 1px solid #eee">
      <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246);height: 100%;box-shadow: 2px 0.6px rgb(0 21 41 / 15%);">
        <el-menu :default-active="1-4-1"
                 style="min-height: 110vh;overflow-x: hidden;"
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
          <el-menu-item index="2"style="color:rgb(113,125,216);">
            <div style="background-color: #eeeeee;width: 150%;height: 100%;margin-left: -50px">
              <i class="el-icon-position" style="color: rgb(113,125,216);margin-left: 50px"></i>
              <span slot="title"style="font-weight: 300;">导航</span>
              <i class="el-icon-arrow-right" style="margin-left: 80px"></i>
            </div>
          </el-menu-item>
          <el-menu-item index="3" @click.native="ToResource">
              <i class="el-icon-chat-line-round" ></i>
              <span slot="title"style="font-weight: 300;">咨讯</span>
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
            <bm-control :offset="{width:'10px',height:'10px'}">
              <input class="input_box" v-model="keyword"placeholder="  请输入">
            </bm-control>
            <!--一些注释：scroll-wheel-zoom可以实现滚轮缩放-->
            <baidu-map class="bm-view" :center="center" :zoom="zoom" mapType="BMAP_NORMAL_MAP":scroll-wheel-zoom="true" @moving="syncCenterAndZoom" @moveend="syncCenterAndZoom">
              <!--实现经度和纬度的前端显示-->
              <div>
                <div style="position: relative;top: -20px;left: 70%;">
                  <div>
                    <p class="text">经度</p>
                    <div class="line_map" >
                      <l style="position: relative;top: -22px;left:5px">{{ center.lng }}</l>
                    </div>
                  </div>
                  <div style="position: relative;top:-22px;left: 200px">
                    <p class="text">维度</p>
                    <div class="line_map">
                      <l style="position: relative;top: -22px;left:5px">{{ center.lat }}</l>
                    </div>
                  </div>
                </div>
              </div>
              <bm-view style="width: 90%;height: 600px;flex: 1;position: relative;left: 5%"></bm-view>
              <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_LEFT"></bm-map-type>
              <bm-marker :position="center" :dragging="true" animation="BMAP_ANIMATION_BOUNCE"></bm-marker>
              <bm-geolocation anchor="BMAP_ANCHOR_TOP_RIGHT" :show-address-bar="true":auto-location="true"></bm-geolocation>
              <bm-local-search :keyword="keyword":auto-viewport="true" style="width:200px;position: relative;top: -600px"></bm-local-search>
            </baidu-map>
          </div>
        </el-main>
        <div style="position:relative;margin-left: 1200px;margin-top: -250px;">
          <el-popover placement="bottom"
                      title="广州市内推荐机构"
                      width="200"
                      trigger="click">
            <p>1. 广州市海珠区沙园社区卫生服务中心</p>
            <p>2. 广州市友好护理院</p>
            <p>3. 广州市荔湾区华林街社区卫生服务中心</p>
            <p>4. 广州市越秀区六榕街社区卫生服务中心</p>
            <p>5. 广州市天河区珠吉护理院</p>
            <p>6. 广州市天河区岑村护理院</p>
            <p>7. 广州市番禺区市桥医院</p>
            <p>8. 广州市番禺区石楼人民医院</p>
            <el-button  style="border-radius: 100%;" class="circle_recommend"slot="reference">
              <i class="el-icon-star-off" style="font-size: 30px;margin-left: -30%;"></i>
            </el-button>
          </el-popover>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      collapsBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      logoText:true,
      center:{
        lng:116.404,
        lat:39.915
      },
      keyword:'',
      zoom:15
    }
  },
  methods: {
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
    syncCenterAndZoom(e){
      const{lng,lat}=e.target.getCenter()
      this.center.lng=lng
      this.center.lat=lat
    },
    ToHome(){
      this.$router.push('/home')
    },
    ToResource() {
      this.$router.push('/resource')
    },
  }
}
</script>
<style>
.bm-view {
  width: 100%;
  height: 800px;
}
.line_map{
  width: 100px;
  height: 1px;
  border-radius: 10px;
  background-color: rgba(127,127,127,0.6);
  position: relative;
  left:40px;
}
.text{
  font-weight: 300;
}
.input_box{
  border-radius: 30px;
  border-color: rgba(127,127,127,0.5);
  width: 60%;
  height: 30px;
  position: relative;
  top: 5px;
  left:5%;
}
.circle_recommend{
  width: 50px;
  height:50px;
  position: relative;
  background-color: black;
}
</style>