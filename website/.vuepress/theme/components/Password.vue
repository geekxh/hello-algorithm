<template>
  <div class="password-shadow">
    <ModuleTransition>
      <h3 v-show="recoShowModule" class="title">{{isPage ? $frontmatter.title : $site.title || $localeConfig.title}}</h3>
    </ModuleTransition>

    <ModuleTransition delay="0.08">
      <p class="description" v-if="recoShowModule && !isPage">{{$site.description || $localeConfig.description}}</p>
    </ModuleTransition>

    <ModuleTransition delay="0.16">
      <label v-show="recoShowModule" class="inputBox" id="box">
        <input
          v-model="key"
          type="password"
          @keyup.enter="inter"
          @focus="inputFocus"
          @blur="inputBlur">
        <span>{{warningText}}</span>
        <button ref="passwordBtn" @click="inter">OK</button>
      </label>
    </ModuleTransition>

    <ModuleTransition delay="0.24">
      <div v-show="recoShowModule" class="footer">
        <span>
          <i class="iconfont reco-theme"></i>
          <a target="blank" href="https://vuepress-theme-reco.recoluan.com">vuePress-theme-reco</a>
        </span>
        <span>
          <i class="iconfont reco-copyright"></i>
          <a>
            <span v-if="$themeConfig.author || $site.title">{{ $themeConfig.author || $site.title }}</span>
            &nbsp;&nbsp;
            <span v-if="$themeConfig.startYear && $themeConfig.startYear != year">{{ $themeConfig.startYear }} - </span>
            {{ year }}
          </a>
        </span>
      </div>
    </ModuleTransition>
  </div>
</template>

<script>
import md5 from 'md5'
import ModuleTransition from '@theme/components/ModuleTransition'
import moduleTransitonMixin from '@theme/mixins/moduleTransiton'

export default {
  mixins: [moduleTransitonMixin],
  components: { ModuleTransition },
  props: {
    isPage: {
      type: Boolean,
      default: false
    }
  },
  name: 'Password',
  data () {
    return {
      warningText: 'Konck! Knock!',
      key: ''
    }
  },
  computed: {
    year () {
      return new Date().getFullYear()
    }
  },
  methods: {
    inter () {
      const {
        key,
        isPage,
        isHasPageKey,
        isHasKey,
        $refs: { passwordBtn }
      } = this
      const keyVal = md5(key.trim())
      const pageKey = `pageKey${window.location.pathname}`
      const keyName = isPage ? pageKey : 'key'
      sessionStorage.setItem(keyName, keyVal)
      const isKeyTrue = isPage ? isHasPageKey() : isHasKey()
      if (!isKeyTrue) {
        this.warningText = 'Key Error'
        return
      }

      this.warningText = 'Key Success'

      const width = document.getElementById('box').style.width

      passwordBtn.style.width = `${width - 2}px`
      passwordBtn.style.opacity = 1
      setTimeout(() => {
        window.location.reload()
      }, 800)
    },
    inputFocus () {
      this.warningText = 'Input Your Key'
    },
    inputBlur () {
      this.warningText = 'Konck! Knock!'
    },
    isHasKey () {
      let { keys } = this.$themeConfig.keyPage
      keys = keys.map(item => item.toLowerCase())
      return keys.indexOf(sessionStorage.getItem('key')) > -1
    },
    isHasPageKey () {
      const pageKeys = this.$frontmatter.keys.map(item => item.toLowerCase())
      const pageKey = `pageKey${window.location.pathname}`

      return pageKeys && pageKeys.indexOf(sessionStorage.getItem(pageKey)) > -1
    }
  }
}
</script>

<style lang="stylus" scoped>
@require '../styles/mode.styl'

.password-shadow {
  overflow hidden
  position relative
  background #fff
  background var(--background-color)
  box-sizing border-box
  .title {
    margin 8rem auto 2rem
    width 100%
    text-align center
    font-size 30px
    box-sizing: border-box;
    text-shadow $textShadow
    color $textColor
    color var(--text-color)
  }
  .description {
    margin 0 auto 6rem
    text-align center
    color $textColor
    color var(--text-color)
    font-size 22px
    box-sizing: border-box;
    padding: 0 10px;
    text-shadow $textShadow
  }
  .inputBox{
    position absolute
    top 40%
    left 0
    right 0
    margin auto
    display block
    max-width:700px;
    height: 100px;
    background: $accentColor;
    border-radius: $borderRadius
    padding-left 20px
    box-sizing border-box
    opacity 0.9
    input{
      width:600px;
      height:100%;
      border:none;
      padding:0;
      padding-left:5px;
      color: #fff;
      background: none;
      outline: none;
      position: absolute;
      bottom:0;
      left 20px
      opacity 0
      font-size 50px
      &:focus {
        opacity 1
      }
      &:focus~span{
        transform: translateY(-80px);
        color $accentColor
        font-size 30px
        opacity:0.8;
      }
      &:focus~button{
        opacity:1;
        width:100px;
      }
    }
    span{
      width:200px;
      height: 100%;
      display: block;
      position: absolute;
      line-height:100px;
      top:0;
      left:20px;
      color: #fff;
      cursor: text;
      transition: 0.5s;
      transform-origin: left top;
      font-size 30px
    }
    button{
      overflow hidden
      width:0px;
      height:98px;
      border-radius: $borderRadius
      position: absolute;
      border 1px solid $accentColor
      background var(--background-color)
      right:1px;
      top 1px
      border:0;
      padding:0;
      color: $accentColor;
      font-size:18px;
      outline:none;
      cursor: pointer;
      opacity:0;
      transition: 0.5s;
      z-index: 1;
    }
  }
  .footer {
    position: absolute;
    left 0
    right 0
    bottom 10%
    padding: 2.5rem;
    text-align: center;
    color: lighten($textColor, 25%);
    > span {
      margin-left 1rem
      > i {
        margin-right .5rem
      }
    }
  }
  @media (max-width: $MQMobile) {
    .inputBox{
      max-width:700px;
      height: 60px;
      background: $accentColor;
      border-radius: $borderRadius
      position: absolute;
      left 0
      right 0
      top 43%
      margin auto 20px
      padding-left 0
      box-sizing border-box
      opacity 0.9
      input{
        width: 60%;
        height:100%;
        border:none;
        padding:0;
        padding-left:5px;
        color: #fff;
        background: none;
        outline: none;
        position: absolute;
        bottom:0;
        opacity 0
        font-size 30px
        &:focus {
          opacity 1
        }
        &:focus~span{
          transform: translateY(-60px);
          color $accentColor
          font-size 20px
          opacity:0.8;
        }
        &:focus~button{
          opacity:1;
          width:60px;
        }
      }
      span{
        width:200px;
        height: 100%;
        display: block;
        position: absolute;
        line-height:60px;
        top:0;
        left:20px;
        color: #fff;
        cursor: text;
        transition: 0.5s;
        transform-origin: left top;
        font-size 20px
      }
      button{
        width:0px;
        height:58px;
        border-radius: $borderRadius
        position: absolute;
        border 1px solid $accentColor
        right:1px;
        top 1px
        border:0;
        padding:0;
        background: #fff;
        color: $accentColor;
        font-size:18px;
        outline:none;
        cursor: pointer;
        opacity:0;
        transition: 0.5s;
        z-index: 1;
      }
    }
    .footer {
      margin-left 0

    }
  }
  @media (max-width: $MQNarrow) {
    .footer {
      margin-left 0
    }
  }
}
</style>
