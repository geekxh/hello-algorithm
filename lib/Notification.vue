<template>
  <div class="notification-wrapper" v-if="show">
    <h4 class="notification-title">
      <i class="iconfont reco-tongzhi"></i>
      <span>公告</span>
      <i @click="close" class="btn-close">
        <svg
          t="1573745677073"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="4448"
          width="22"
          height="22"
          class="icon"
          @click="close"
        >
          <path
            d="M512 34.133333a486.4 486.4 0 1 0 486.4 486.4A486.4 486.4 0 0 0 512 34.133333z m209.4848 632.8064l-55.6032 55.466667-151.517867-151.125333-151.517866 151.1168-55.6032-55.466667 151.517866-151.108267L307.242667 364.714667l55.6032-55.466667 151.517866 151.125333 151.517867-151.1168 55.6032 55.466667-151.517867 151.099733z m0 0"
            p-id="4449"
          />
        </svg>
      </i>
    </h4>
    <div class="notification-content">
      <h5>🐳 扫码回复【进群】🐳 </h5>
      <h5>🎉 加入每日刷题群 🎉</h5>
      <h5>🍓 下载高清《图解算法》🍓</h5>
      </br>
      <img src="/code.png" alt />
    </div>
    <div style="padding: 0px 16px;">
      <hr />
      <a href="/0.0.学习须知/01.html" class="btn-donate"> 进群 </a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Notification',
  data() {
    return {
      show: true,
    };
  },
  methods: {
    close() {
      this.show = false;
    },
  },
};
</script>

<style lang='stylus' scoped>
.notification-wrapper {
  position: fixed;
  top: 80px;
  right: 20px;
  z-index: 19;
  width: 260px;
  box-sizing: border-box;
  background: #fff;
  border: 1px solid #0074af;
  border-radius: 0.25rem;
  background: var(--background-color);
  box-shadow: var(--box-shadow);
}

.notification-wrapper .notification-title {
  position: relative;
  box-sizing: border-box;
  padding: 10px;
  margin: 0;
  background: #0074af;
  color: #fff;
}

.notification-wrapper .notification-title i {
  color: #fff;
}

.notification-wrapper .notification-title .btn-close {
  position: absolute;
  display: inline-block;
  width: 22px;
  height: 22px;
  right: 10px;
  top: 0;
  bottom: 0;
  margin: auto;
  cursor: pointer;
}

.notification-wrapper .notification-title .btn-close svg {
  fill: #fff;
}

.notification-wrapper .notification-content {
  box-sizing: border-box;
  padding: 10px 15px 0;
}

.notification-wrapper .notification-content h5 {
  margin: 0.2rem 0;
  text-align: center;
}

.notification-wrapper .notification-content img {
  width: 100%;
}

.notification-wrapper .btn-donate {
  display: block;
  margin: 2rem auto;
  width: 3.4rem;
  line-height: 3.4rem;
  text-align: center;
  background-color: #0074af;
  border-radius: 50%;
  color: #fff;
  font-size: 1rem;
  box-shadow: var(--box-shadow);
  cursor: pointer;
}
</style>