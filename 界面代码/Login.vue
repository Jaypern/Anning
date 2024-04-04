<template>
  <div>
    <div class="backgroud"></div>

      <img src="../assets/logo_anning.png" style="width:100px;position: relative;left: 500px;top:100px;">
      <span class="form__title" style="color: #ffffff;font-size: 50px;position: absolute;top:115px;margin-left: 500px">
        Anning World!
      </span>
      <span style="font-weight: 300;color:#eeeeee;position: relative;top: 130px;left: 580px">走好生命最后一公里</span>

    <div class="login-register-container" style="position: relative;top: -100px">
      <div class="container right-panel-active" ref="containerRef">
        <div class="container__form container--signup">
          <el-form action="#" class="form" id="form1" :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
            <h2 class="form__title">Register</h2>
            <el-form-item prop="uname">
              <el-input size="large" type="text" placeholder="User" v-model="ruleForm.uname" class="input"/>
            </el-form-item>
            <el-form-item  prop="pass">
              <el-input size="large" type="password" placeholder="Password" v-model="ruleForm.password" class="input"/>
            </el-form-item>
            <el-button size="large" class="btn" @click="submitSignUp('ruleForm')">Submit</el-button>
          </el-form>
        </div>

        <div class="container__form container--signin">
          <el-form action="#" class="form" id="form2" :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
            <h2 class="form__title">Login</h2>
            <el-form-item prop="uname">
              <el-input size="large" type="email"  placeholder="Email" v-model="ruleForm.uname" class="input"/>
            </el-form-item>
            <el-form-item prop="pass">
              <el-input size="large" type="password" placeholder="Password" v-model="ruleForm.password" class="input"/>
            </el-form-item>
            <el-button size="large" class="btn" @click="submitSignIn('ruleForm')">Submit</el-button>
          </el-form>
        </div>

        <div class="container__overlay">
          <div class="overlay">
            <div class="overlay__panel overlay--left">
              <el-button size="large" round class="btn" id="signIn" @click="handleSignIn">Login</el-button>
            </div>
            <div class="overlay__panel overlay--right">
              <el-button size="large" round class="btn" id="signUp" @click="handleSignUp">Register</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginRegister",
  data(){
    return {
      ruleForm:{
        uname:"",
        password:"",
      },
      rules:{
        uname:[{required: true, message: "NULL！", trigger: "blur"},],
        password:[{required: true, message: "NULL！", trigger: "blur" },],
      },
      loading:false
    }
  },
  methods:{
    submitSignIn(formName){
      // 验证表单中的账号密码是否有效，因为在上面rules中定义为了必填 required: true
      this.$refs[formName].validate((valid) => {
        // 点击登录后，让登录按钮开始转圈圈（展示加载动画）
        this.loading = true;
        // 如果经过校验，账号密码都不为空，则发送请求到后端登录接口
        if (valid) {
          let _this = this;
          // 使用 axios 将登录信息发送到后端
          this.axios({
            url: "/api/user/login",               // 请求地址
            method: "post",                       // 请求方法
            headers: {                            // 请求头
              "Content-Type": "application/json",
            },
            params: {                             // 请求参数
              uname: _this.ruleForm.uname,
              password: _this.ruleForm.password,
            },
          }).then((res) => { // 当收到后端的响应时执行该括号内的代码，res 为响应信息，也就是后端返回的信息
            if (res.data.code === "0") {  // 当响应的编码为 0 时，说明登录成功
              // 将用户信息存储到sessionStorage中
              sessionStorage.setItem("userInfo", JSON.stringify(res.data.data));
              // 跳转页面到首页
              this.$router.push('/home');
              // 显示后端响应的成功信息
              this.$message({
                message: res.data.msg,
                type: "success",
              });
            } else {  // 当响应的编码不为 0 时，说明登录失败
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
        } else {  // 如果账号或密码有一个没填，就直接提示必填，不向后端请求
          console.log("error submit!!");
          this.loading = false;
          return false;
        }
      });
    },
    submitSignUp(formName){
      this.$refs[formName].validate((valid) => {
        this.loading = true;  // 提交按钮显示加载动画
        if (valid) {
          let _this = this;
          this.axios({     // axios 向后端发起请求
            url: "/api/user/register",  // 请求地址
            method: "post",             // 请求方法
            headers: {                  // 请求头
              "Content-Type": "application/json",
            },
            data: { // 请求参数，为 data，与登录的 params 不太一样
              uname: _this.ruleForm.uname,
              password: _this.ruleForm.password,
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
  },
}
</script>

<script setup>
import {ref} from "vue";
const containerRef=ref()
const handleSignIn = () => {
  containerRef.value.classList.remove("right-panel-active");
}
const handleSignUp = () => {
  containerRef.value.classList.add("right-panel-active");
}
</script>
<style scoped>
.backgroud{
  background: url("../assets/图片2.jpg") no-repeat fixed center;
  width: 100%;
  height: 100%;
  z-index: -1;
  position: absolute;
}

.login-register-container{
  --white: #e9e9e9;
  --gray: #333;
  --blue: #0367a6;
  --lightblue: #008997;
  --button-radius: 0.7rem;
  height: 66vh;
  width: 96vw;
}

.container {
  background-color: var(--white);
  border-radius: var(--button-radius);
  box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25),
  0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
  height: 50vh;
  width: 50vw;
  overflow: hidden;
  position: relative;
  left: 27vw;
  top:35vh
}


.form__title {
  font-weight: 300;
  margin: 0 0 1.25rem;
}

.link {
  color: var(--gray);
  font-size: 0.9rem;
  margin: 1.5rem 0;
  text-decoration: none;
}


.container__form {
  height: 100%;
  position: absolute;
  top: 0;
  transition: all 0.6s ease-in-out;
}

.container--signin {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .container--signin {
  transform: translateX(100%);
}

.container--signup {
  left: 0;
  opacity: 0;
  width: 50%;
  z-index: 1;
}

.container.right-panel-active .container--signup {
  animation: show 0.6s;
  opacity: 1;
  transform: translateX(100%);
  z-index: 5;
}

.container__overlay {
  height: 100%;
  left: 50%;
  overflow: hidden;
  position: absolute;
  top: 0;
  transition: transform 0.6s ease-in-out;
  width: 50%;
  z-index: 100;
}

.container.right-panel-active .container__overlay {
  transform: translateX(-100%);
}

.overlay {
  background: var(--lightblue) url("../assets/图片1.jpg") no-repeat fixed center;
  background-size: cover;
  height: 100%;
  left: -100%;
  position: relative;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
  width: 200%;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay__panel {
  align-items: center;
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: center;
  position: absolute;
  text-align: center;
  top: 0;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
  width: 50%;
}

.overlay--left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay--left {
  transform: translateX(0);
}

.overlay--right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay--right {
  transform: translateX(20%);
}

.btn {
  background-color: var(--blue);
  background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
  border-radius: 20px;
  margin-top: 190px;
  border: 1px solid var(--blue);
  color: var(--white);
  cursor: pointer;
  font-size: 0.8rem;
  font-weight: bold;
  letter-spacing: 0.1rem;
  padding: 0.9rem 4rem;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

.form > .btn {
  margin-top: 1.5rem;
}

.btn:active {
  transform: scale(0.95);
}

.btn:focus {
  outline: none;
}

.form {
  background-color: var(--white);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 3rem;
  height: 100%;
  text-align: center;
}

.input {
  background-color: #fff;
  border: none;
  //padding: 0.9rem 0.9rem;
  margin: 0.5rem 0;
  width: 100%;
}

@keyframes show {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}
</style>