<template>
  <Common  class="tags-wrapper" :sidebar="false">
    <!-- 标签集合 -->
    <ModuleTransition>
      <TagList
        v-show="recoShowModule"
        :currentTag="currentTag"
        @getCurrentTag="tagClick"></TagList>
    </ModuleTransition>

    <!-- 博客列表 -->
    <ModuleTransition delay="0.08">
      <note-abstract
        v-show="recoShowModule"
        class="list"
        :data="$recoPosts"
        :currentPage="currentPage"
        :currentTag="currentTag"
        @currentTag="getCurrentTag"></note-abstract>
    </ModuleTransition>

    <!-- 分页 -->
    <ModuleTransition delay="0.16">
      <pagation
        class="pagation"
        :total="$recoPosts.length"
        :currentPage="currentPage"
        @getCurrentPage="getCurrentPage"></pagation>
    </ModuleTransition>
  </Common>
</template>

<script>
import Common from '@theme/components/Common'
import TagList from '@theme/components/TagList'
import NoteAbstract from '@theme/components/NoteAbstract'
import pagination from '@theme/mixins/pagination'
import ModuleTransition from '@theme/components/ModuleTransition'
import moduleTransitonMixin from '@theme/mixins/moduleTransiton'

export default {
  mixins: [pagination, moduleTransitonMixin],
  components: { Common, NoteAbstract, TagList, ModuleTransition },
  data () {
    return {
      tags: [],
      currentTag: '全部',
      currentPage: 1,
      allTagName: '全部'
    }
  },

  created () {
    if (this.$tags.list.length > 0) {
      this.currentTag = this.$route.query.tag ? this.$route.query.tag : this.currentTag
    }
  },

  mounted () {
    this._setPage(this._getStoragePage())
  },

  methods: {

    tagClick (tagInfo) {
      this.$router.push({ path: tagInfo.path })
    },

    getCurrentTag (tag) {
      this.$emit('currentTag', tag)
    },

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
    }
  }
}
</script>

<style src="prismjs/themes/prism-tomorrow.css"></style>
<style src="../styles/theme.styl" lang="stylus"></style>

<style lang="stylus" scoped>
.tags-wrapper
  max-width: 740px;
  margin: 0 auto;
  padding: 4.6rem 2.5rem 0;

@media (max-width: $MQMobile)
  .tags-wrapper
    padding: 5rem 0.6rem 0;
</style>
