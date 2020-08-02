import postMixin from '@theme/mixins/posts'
import localMixin from '@theme/mixins/locals'

export default ({
  Vue
}) => {
  Vue.mixin(postMixin)
  Vue.mixin(localMixin)
}
