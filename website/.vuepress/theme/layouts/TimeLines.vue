<template>
  <Common class="timeline-wrapper" :sidebar="false">
    <ul class="timeline-content">
      <ModuleTransition >
        <li v-show="recoShowModule" class="desc">Yesterday Once More!</li>
      </ModuleTransition>
      <ModuleTransition
        :delay="String(0.08 * (index + 1))"
        v-for="(item, index) in $recoPostsForTimeline"
        :key="index">
        <li v-show="recoShowModule">
          <h3 class="year">{{item.year}}</h3>
          <ul class="year-wrapper">
            <li v-for="(subItem, subIndex) in item.data" :key="subIndex">
              <span class="date">{{subItem.frontmatter.date | dateFormat}}</span>
              <span class="title" @click="go(subItem.path)">{{subItem.title}}</span>
            </li>
          </ul>
        </li>
      </ModuleTransition>
    </ul>
  </Common>
</template>

<script>
import Common from '@theme/components/Common'
import ModuleTransition from '@theme/components/ModuleTransition'
import moduleTransitonMixin from '@theme/mixins/moduleTransiton'

export default {
  mixins: [moduleTransitonMixin],
  name: 'TimeLine',
  components: { Common, ModuleTransition },
  filters: {
    dateFormat (date, type) {
      function renderTime (date) {
        const dateee = new Date(date).toJSON()
        return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '').replace(/-/g, '/')
      }
      date = renderTime(date)
      const dateObj = new Date(date)
      const mon = dateObj.getMonth() + 1
      const day = dateObj.getDate()
      return `${mon}-${day}`
    }
  },
  methods: {
    go (url) {
      this.$router.push({ path: url })
    }
  }
}
</script>

<style src="../styles/theme.styl" lang="stylus"></style>

<style lang="stylus" scoped>
@require '../styles/wrapper.styl'

.timeline-wrapper
  max-width: 740px;
  margin: 0 auto;
  padding: 4.6rem 2.5rem 0;
  .timeline-content
    box-sizing border-box
    position relative
    list-style none
    &::after {
      content: " ";
      position: absolute;
      top: 14px;
      left: 0;
      z-index: -1;
      margin-left: -2px;
      width: 4px;
      height: 100%;
      background: var(--border-color);
    }
    .desc, .year {
      position: relative;
      color var(--text-color);
      font-size 16px
      &:before {
        content: " ";
        position: absolute;
        z-index 2;
        left: -20px;
        top: 50%;
        margin-left: -4px;
        margin-top: -4px;
        width: 8px;
        height: 8px;
        background: var(--background-color);
        border: 1px solid var(--border-color);
        border-radius: 50%;
      }
    }
    .year {
      margin: 80px 0 0px;
      color var(--text-color);
      font-weight: 700;
      font-size 26px
    }
    .year-wrapper {
      padding-left 0!important
      li {
        display flex
        padding 30px 0 10px
        list-style none
        border-bottom: 1px dashed var(--border-color);
        position relative
        &:hover {
          .date {
            color $accentColor
            &::before {
              background $accentColor
            }
          }
          .title {
            color $accentColor
          }
        }
        .date {
          width 40px
          line-height 30px
          color var(--text-color-sub)
          font-size 12px
          &::before {
            content: " ";
            position: absolute;
            left: -19px;
            top: 41px;
            width: 6px;
            height: 6px;
            margin-left: -4px;
            background: var(--background-color);
            border-radius: 50%;
            border: 1px solid var(--border-color);
            z-index 2
          }
        }
        .title {
          line-height 30px
          color var(--text-color-sub)
          font-size 16px
          cursor pointer
        }
      }
    }
@media (max-width: $MQMobile)
  .timeline-wrapper
    margin: 0 1.2rem;
</style>
