import { zhHans, zhHant, en, ja, ko } from '../locals/index'

export default {
  computed: {
    $recoLocals () {
      const recoLocals = this.$themeLocaleConfig.recoLocals
      if (recoLocals) {
        return recoLocals
      }
      if (/^zh\-(CN|SG)$/.test(this.$lang)) {
        return zhHans
      }
      if (/^zh\-(HK|MO|TW)$/.test(this.$lang)) {
        return zhHant
      }
      if (/^ja\-JP$/.test(this.$lang)) {
        return ja
      }
      if (/^ko\-KR$/.test(this.$lang)) {
        return ko
      }
      return en
    }
  }
}
