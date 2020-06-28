import Notification from './Notification.vue'

export default ({ Vue }) => {
  // eslint-disable-next-line vue/match-component-file-name

  console.log('enhance entered')
  Vue.component('Notification', Notification)
}