<template>
  <nav
    class="nav-links"
    v-if="userLinks.length || repoLink"
  >
    <!-- user links -->
    <div
      class="nav-item"
      v-for="item in userLinks"
      :key="item.link">
      <DropdownLink
        v-if="item.type === 'links'"
        :item="item"/>
      <NavLink
        v-else
        :item="item"/>
    </div>

    <!-- repo link -->
    <a
      v-if="repoLink"
      :href="repoLink"
      class="repo-link"
      target="_blank"
      rel="noopener noreferrer">
      <i :class="`iconfont reco-${repoLabel.toLowerCase()}`"></i>
      {{ repoLabel }}
      <OutboundLink/>
    </a>
  </nav>
</template>

<script>
import DropdownLink from '@theme/components/DropdownLink'
import { resolveNavLinkItem } from '@theme/helpers/utils'
import NavLink from '@theme/components/NavLink'

export default {
  components: { NavLink, DropdownLink },

  computed: {
    userNav () {
      return this.$themeLocaleConfig.nav || this.$themeConfig.nav || []
    },

    nav () {
      const { $site: { locales }, userNav } = this
      if (locales && Object.keys(locales).length > 1) {
        const currentLink = this.$page.path
        const routes = this.$router.options.routes
        const themeLocales = this.$themeConfig.locales || {}
        const languageDropdown = {
          text: this.$themeLocaleConfig.selectText || 'Languages',
          items: Object.keys(locales).map(path => {
            const locale = locales[path]
            const text = themeLocales[path] && themeLocales[path].label || locale.lang
            let link
            // Stay on the current page
            if (locale.lang === this.$lang) {
              link = currentLink
            } else {
              // Try to stay on the same page
              link = currentLink.replace(this.$localeConfig.path, path)
              // fallback to homepage
              if (!routes.some(route => route.path === link)) {
                link = path
              }
            }
            return { text, link }
          })
        }
        return [...userNav, languageDropdown]
      }

      // blogConfig 的处理，根绝配置自动添加分类和标签
      const blogConfig = this.$themeConfig.blogConfig || {}
      const isHasCategory = userNav.some(item => {
        if (blogConfig.category) {
          return item.text === (blogConfig.category.text || '分类')
        } else {
          return true
        }
      })
      const isHasTag = userNav.some(item => {
        if (blogConfig.tag) {
          return item.text === (blogConfig.tag.text || '标签')
        } else {
          return true
        }
      })

      if (!isHasCategory && Object.hasOwnProperty.call(blogConfig, 'category')) {
        const category = blogConfig.category
        const $categories = this.$categories
        userNav.splice(parseInt(category.location || 2) - 1, 0, {
          items: $categories.list.map(item => {
            item.link = item.path
            item.text = item.name
            return item
          }),
          text: category.text || '分类',
          type: 'links',
          icon: 'reco-category'
        })
      }
      if (!isHasTag && Object.hasOwnProperty.call(blogConfig, 'tag')) {
        const tag = blogConfig.tag
        userNav.splice(parseInt(tag.location || 3) - 1, 0, {
          link: '/tag/',
          text: tag.text || '标签',
          type: 'links',
          icon: 'reco-tag'
        })
      }

      return userNav
    },

    userLinks () {
      return (this.nav || []).map(link => {
        return Object.assign(resolveNavLinkItem(link), {
          items: (link.items || []).map(resolveNavLinkItem)
        })
      })
    },

    repoLink () {
      const { repo } = this.$themeConfig
      if (repo) {
        return /^https?:/.test(repo)
          ? repo
          : `https://github.com/${repo}`
      }
      return ''
    },

    repoLabel () {
      if (!this.repoLink) return
      if (this.$themeConfig.repoLabel) {
        return this.$themeConfig.repoLabel
      }

      const repoHost = this.repoLink.match(/^https?:\/\/[^/]+/)[0]
      const platforms = ['GitHub', 'GitLab', 'Bitbucket']
      for (let i = 0; i < platforms.length; i++) {
        const platform = platforms[i]
        if (new RegExp(platform, 'i').test(repoHost)) {
          return platform
        }
      }

      return 'Source'
    }
  }
}
</script>

<style lang="stylus">
.nav-links
  display inline-block
  a
    line-height 1.4rem
    color var(--text-color)
    &:hover, &.router-link-active
      color $accentColor
      .iconfont
        color $accentColor
  .nav-item
    position relative
    display inline-block
    margin-left 1.5rem
    line-height 2rem
    &:first-child
      margin-left 0
  .repo-link
    margin-left 1.5rem

@media (max-width: $MQMobile)
  .nav-links
    .nav-item, .repo-link
      margin-left 0

@media (min-width: $MQMobile)
  .nav-item > a:not(.external)
    &:hover, &.router-link-active
      margin-bottom -2px
      border-bottom 2px solid lighten($accentColor, 8%)
</style>
