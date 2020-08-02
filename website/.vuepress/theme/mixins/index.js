export default {
  methods: {
    _tagColor () {
      const tagColorArr = ['#e15b64', '#f47e60', '#f8b26a', '#abbd81', '#849b87', '#e15b64', '#f47e60', '#f8b26a', '#f26d6d', '#67cc86', '#fb9b5f', '#3498db']
      const index = Math.floor(Math.random() * tagColorArr.length)
      return tagColorArr[index]
    },
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
