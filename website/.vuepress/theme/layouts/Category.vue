<template>
  <Common class="categories-wrapper" :sidebar="false">
    <!-- 分类集合 -->
    <ModuleTransition>
      <ul v-show="recoShowModule" class="category-wrapper">
        <li
          class="category-item"
          :class="title == item.name ? 'active': ''"
          v-for="(item, index) in this.$categories.list"
          :key="index">
          <router-link :to="item.path">
            <span class="category-name">{{ item.name }}</span>
            <span class="post-num" :style="{ 'backgroundColor': getOneColor() }">{{ item.pages.length }}</span>
          </router-link>
        </li>
      </ul>
    </ModuleTransition>

    <!-- 博客列表 -->
    <ModuleTransition delay="0.08">
      <note-abstract
        v-show="recoShowModule"
        class="list"
        :data="posts"
        :currentPage="currentPage"
        @currentTag="getCurrentTag"></note-abstract>
    </ModuleTransition>

    <!-- 分页 -->
    <ModuleTransition delay="0.16">
      <pagation
        class="pagation"
        :total="posts.length"
        :currentPage="currentPage"
        @getCurrentPage="getCurrentPage"></pagation>
    </ModuleTransition>
  </Common>
</template>

<script>
import Common from '@theme/components/Common'
import NoteAbstract from '@theme/components/NoteAbstract'
import ModuleTransition from '@theme/components/ModuleTransition'
import pagination from '@theme/mixins/pagination'
import { sortPostsByStickyAndDate, filterPosts } from '@theme/helpers/postData'
import { getOneColor } from '@theme/helpers/other'
import moduleTransitonMixin from '@theme/mixins/moduleTransiton'

export default {
  mixins: [pagination, moduleTransitonMixin],
  components: { Common, NoteAbstract, ModuleTransition },

  data () {
    return {
      currentPage: 1
    }
  },

  computed: {
    // 时间降序后的博客列表
    posts () {
      let posts = this.$currentCategories.pages
      posts = filterPosts(posts)
      sortPostsByStickyAndDate(posts)
      return posts
    },
    // 标题只显示分类名称
    title () {
      return this.$currentCategories.key
    }
  },

  mounted () {
    this._setPage(this._getStoragePage())
  },

  methods: {
    // 获取当前tag
    getCurrentTag (tag) {
      this.$emit('currentTag', tag)
    },
    // 获取当前页码
    getCurrentPage (page) {
      this._setPage(page)
      setTimeout(() => {
        window.scrollTo(0, 0)
      }, 100)
    },
    _setPage (page) {
      this.currentPage = page
      this.$page.currentPage = page
      this._setStoragePage(page)
    },
    getOneColor
  },

  watch: {
    $route () {
      this._setPage(this._getStoragePage())
    }
  }
}
</script>

<style src="../styles/theme.styl" lang="stylus"></style>

<style src="prismjs/themes/prism-tomorrow.css"></style>
<style lang="stylus" scoped>
@require '../styles/mode.styl'
.categories-wrapper
  max-width: 740px;
  margin: 0 auto;
  padding: 4.6rem 2.5rem 0;
  .category-wrapper {
    list-style none
    padding-left 0
    .category-item {
      vertical-align: middle;
      margin: 4px 8px 10px;
      display: inline-block;
      cursor: pointer;
      border-radius: $borderRadius
      font-size: 13px;
      box-shadow var(--box-shadow)
      transition: all .5s
      background-color var(--background-color)
      &:hover, &.active {
        background $accentColor
        a span.category-name {
          color #fff
          .post-num {
            color $accentColor
          }
        }
      }
      a {
        display flex
        box-sizing border-box
        width 100%
        height 100%
        padding: 8px 14px;
        justify-content: space-between
        align-items center
        color: #666
        .post-num {
          margin-left 4px
          width 1.2rem;
          height 1.2rem
          text-align center
          line-height 1.2rem
          border-radius $borderRadius
          font-size .7rem
          color #fff
        }
      }
    }
  }

@media (max-width: $MQMobile)
  .categories-wrapper
    padding: 4.6rem 1rem 0;
  .page-edit
    .edit-link
      margin-bottom .5rem
    .last-updated
      font-size .8em
      float none
      text-align left
</style>
