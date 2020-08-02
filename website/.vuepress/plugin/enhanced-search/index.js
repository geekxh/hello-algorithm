const { path } = require('@vuepress/shared-utils')

module.exports = (options) => ({
  alias: {
    '@SearchBox':
      path.resolve(__dirname, 'EnhancedSearchBox.vue')
  },

  define: {
    SEARCH_MAX_SUGGESTIONS: options.searchMaxSuggestions || 5,
    SEARCH_PATHS: options.test || null,
    SEARCH_HOTKEYS: options.searchHotkeys || ['s', '/'],
    SEARCH_THIRDPARTY: options.thirdparty || []
  }
})