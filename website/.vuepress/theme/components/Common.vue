<template>
  <div
    class="theme-container"
    :class="pageClasses"
    @touchstart="onTouchStart"
    @touchend="onTouchEnd">
    <div v-if="!absoluteEncryption">
      <transition name="fade">
        <LoadingPage v-show="firstLoad" class="loading-wrapper" />
      </transition>
      <transition name="fade">
        <Password v-show="!isHasKey" class="password-wrapper-out" key="out" />
      </transition>
      <div :class="{ 'hide': firstLoad || !isHasKey }">
        <Navbar
        v-if="shouldShowNavbar"
        @toggle-sidebar="toggleSidebar"/>

        <div
          class="sidebar-mask"
          @click="toggleSidebar(false)"></div>

        <Sidebar
          :items="sidebarItems"
          @toggle-sidebar="toggleSidebar">
          <slot
            name="sidebar-top"
            slot="top"/>
          <slot
            name="sidebar-bottom"
            slot="bottom"/>
        </Sidebar>

        <Password v-show="!isHasPageKey" :isPage="true" class="password-wrapper-in" key="in"></Password>
        <div :class="{ 'hide': !isHasPageKey }">
          <slot></slot>
        </div>
      </div>
    </div>
    <div v-else>
      <transition name="fade">
        <LoadingPage v-if="firstLoad" />
        <Password v-else-if="!isHasKey" />
        <div v-else>
          <Navbar
          v-if="shouldShowNavbar"
          @toggle-sidebar="toggleSidebar"/>

          <div
            class="sidebar-mask"
            @click="toggleSidebar(false)"></div>

          <Sidebar
            :items="sidebarItems"
            @toggle-sidebar="toggleSidebar">
            <slot
              name="sidebar-top"
              slot="top"/>
            <slot
              name="sidebar-bottom"
              slot="bottom"/>
          </Sidebar>

          <Password v-if="!isHasPageKey" :isPage="true"></Password>
          <div v-else>
            <slot></slot>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import Navbar from '@theme/components/Navbar'
import Sidebar from '@theme/components/Sidebar'
import { resolveSidebarItems } from '@theme/helpers/utils'
import Password from '@theme/components/Password'
import { setTimeout } from 'timers'
import moduleTransitonMixin from '@theme/mixins/moduleTransiton'

export default {
  mixins: [moduleTransitonMixin],

  components: { Sidebar, Navbar, Password },

  props: {
    sidebar: {
      type: Boolean,
      default: true
    }
  },

  data () {
    return {
      isSidebarOpen: false,
      isHasKey: true,
      isHasPageKey: true,
      firstLoad: true
    }
  },

  computed: {
    absoluteEncryption () {
      return this.$themeConfig.keyPage && this.$themeConfig.keyPage.absoluteEncryption === true
    },

    shouldShowNavbar () {
      const { themeConfig } = this.$site
      const { frontmatter } = this.$page

      if (
        frontmatter.navbar === false ||
        themeConfig.navbar === false
      ) return false

      return (
        this.$title ||
        themeConfig.logo ||
        themeConfig.repo ||
        themeConfig.nav ||
        this.$themeLocaleConfig.nav
      )
    },

    shouldShowSidebar () {
      const { frontmatter } = this.$page
      return (
        this.sidebar !== false &&
        !frontmatter.home &&
        frontmatter.sidebar !== false &&
        this.sidebarItems.length
      )
    },

    sidebarItems () {
      return resolveSidebarItems(
        this.$page,
        this.$page.regularPath,
        this.$site,
        this.$localePath
      )
    },

    pageClasses () {
      const userPageClass = this.$frontmatter.pageClass
      return [
        {
          'no-navbar': !this.shouldShowNavbar,
          'sidebar-open': this.isSidebarOpen,
          'no-sidebar': !this.shouldShowSidebar
        },
        userPageClass
      ]
    }
  },

  mounted () {
    this.$router.afterEach(() => {
      this.isSidebarOpen = false
    })

    this.hasKey()
    this.hasPageKey()
    this.handleLoading()
  },

  methods: {
    hasKey () {
      const keyPage = this.$themeConfig.keyPage
      if (!keyPage || !keyPage.keys || keyPage.keys.length === 0) {
        this.isHasKey = true
        return
      }

      let { keys } = keyPage
      keys = keys.map(item => item.toLowerCase())
      this.isHasKey = keys && keys.indexOf(sessionStorage.getItem('key')) > -1
    },
    hasPageKey () {
      let pageKeys = this.$frontmatter.keys
      if (!pageKeys || pageKeys.length === 0) {
        this.isHasPageKey = true
        return
      }

      pageKeys = pageKeys.map(item => item.toLowerCase())

      this.isHasPageKey = pageKeys.indexOf(sessionStorage.getItem(`pageKey${window.location.pathname}`)) > -1
    },
    toggleSidebar (to) {
      this.isSidebarOpen = typeof to === 'boolean' ? to : !this.isSidebarOpen
    },

    // side swipe
    onTouchStart (e) {
      this.touchStart = {
        x: e.changedTouches[0].clientX,
        y: e.changedTouches[0].clientY
      }
    },

    onTouchEnd (e) {
      const dx = e.changedTouches[0].clientX - this.touchStart.x
      const dy = e.changedTouches[0].clientY - this.touchStart.y
      if (Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > 40) {
        if (dx > 0 && this.touchStart.x <= 80) {
          this.toggleSidebar(true)
        } else {
          this.toggleSidebar(false)
        }
      }
    },

    handleLoading () {
      const time = this.$frontmatter.home && sessionStorage.getItem('firstLoad') == undefined ? 1000 : 0
      setTimeout(() => {
        this.firstLoad = false
        if (sessionStorage.getItem('firstLoad') == undefined) sessionStorage.setItem('firstLoad', false)
      }, time)
    }
  },

  watch: {
    $frontmatter (newVal, oldVal) {
      this.hasKey()
      this.hasPageKey()
    }
  }
}
</script>

<style lang="stylus" scoped>
.theme-container
  .loading-wrapper
    position absolute
    z-index 22
    top 0
    bottom 0
    left 0
    right 0
    margin auto
  .password-wrapper-out
    position absolute
    z-index 21
    top 0
    bottom 0
    left 0
    right 0
    margin auto
  .password-wrapper-in
    position absolute
    z-index 8
    top 0
    bottom 0
    left 0
    right 0
  .hide
    height 100vh
    overflow hidden

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>
