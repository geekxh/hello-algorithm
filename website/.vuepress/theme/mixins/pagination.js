export default {
  methods: {
    // 获取当前页码
    _getStoragePage () {
      const path = window.location.pathname
      const currentPage = JSON.parse(sessionStorage.getItem('currentPage'))

      if (currentPage === null || path !== currentPage.path) {
        sessionStorage.setItem('currentPage', { page: 1, path: '' })
        return 1
      }

      return parseInt(currentPage.page)
    },
    // 设置当前页码
    _setStoragePage (page) {
      const path = window.location.pathname
      sessionStorage.setItem('currentPage', JSON.stringify({ page, path }))
    }
  }
}
