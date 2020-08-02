<script>
import { isActive, hashRE, groupHeaders } from '@theme/helpers/utils'

export default {
  functional: true,

  props: ['item', 'sidebarDepth'],

  render (h,
    {
      parent: {
        $page,
        $site,
        $route,
        $themeConfig,
        $themeLocaleConfig
      },
      props: {
        item,
        sidebarDepth
      }
    }) {
    // use custom active class matching logic
    // due to edge case of paths ending with / + hash
    const selfActive = isActive($route, item.path)
    // for sidebar: auto pages, a hash link should be active if one of its child
    // matches
    const active = item.type === 'auto'
      ? selfActive || item.children.some(c => isActive($route, item.basePath + '#' + c.slug))
      : selfActive
    const link = renderLink(h, item.path, item.title || item.path, active)

    const configDepth = $page.frontmatter.sidebarDepth ||
      sidebarDepth ||
      $themeLocaleConfig.sidebarDepth ||
      $themeConfig.sidebarDepth

    const maxDepth = configDepth == null ? 1 : configDepth

    const displayAllHeaders = $themeLocaleConfig.displayAllHeaders ||
      $themeConfig.displayAllHeaders

    if (item.type === 'auto') {
      return [link, renderChildren(h, item.children, item.basePath, $route, maxDepth)]
    } else if ((active || displayAllHeaders) && item.headers && !hashRE.test(item.path)) {
      const children = groupHeaders(item.headers)
      return [link, renderChildren(h, children, item.path, $route, maxDepth)]
    } else {
      return link
    }
  }
}

function renderLink (h, to, text, active) {
  return h('router-link', {
    props: {
      to,
      activeClass: '',
      exactActiveClass: ''
    },
    class: {
      active,
      'sidebar-link': true
    }
  }, text)
}

function renderChildren (h, children, path, route, maxDepth, depth = 1) {
  if (!children || depth > maxDepth) return null
  return h('ul', { class: 'sidebar-sub-headers' }, children.map(c => {
    const active = isActive(route, path + '#' + c.slug)
    return h('li', { class: 'sidebar-sub-header' }, [
      renderLink(h, path + '#' + c.slug, c.title, active),
      renderChildren(h, c.children, path, route, maxDepth, depth + 1)
    ])
  }))
}
</script>

<style lang="stylus">
.sidebar .sidebar-sub-headers
  padding-left 1.5rem
  font-size 0.95em

.sidebar-sub-headers a.sidebar-link
  margin 0 1rem 0 0.6rem

a.sidebar-link
  font-size 1em
  font-weight 400
  display block!important
  color var(--text-color)
  padding 0.35rem 1rem 0.35rem .75rem
  line-height 1.4
  margin 0 1rem 0 1.5rem
  box-sizing: border-box
  border-radius .25rem
  &:hover
    color $accentColor
  &.active
    font-weight 600
    color #fff
    background $accentColor
    // border-left-color $accentColor
  .sidebar-group &
    // padding-left 2rem
  .sidebar-sub-headers &
    padding-top 0.25rem
    padding-bottom 0.25rem
    border-left none
    &.active
      font-weight 500
      background transparent
      color $accentColor
</style>
