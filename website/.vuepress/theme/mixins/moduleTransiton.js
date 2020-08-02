export default {
  data () {
    return {
      recoShowModule: false
    }
  },
  mounted () {
    this.recoShowModule = true
  },
  destroyed () {
    this.recoShowModule = false
  }
}
