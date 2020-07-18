module.exports = {
    title: "小浩算法",
    plugins: [require('../../lib/autobar.js'), require('../../lib/Notification.js')],
    description: '和小浩一起学算法',
    // root : "./hell-algorithm",
    theme: "reco",
    themeConfig: {
        sidebar: "auto",
        searchPlaceholder: "搜索：KMP",
        nav: [
          { text: 'GitHub', link: 'https://github.com/geekxh/hello-algorithm', icon: 'reco-github' }
        ],
        type: 'blog',
        search: true,
        searchMaxSuggestions: 10,
        lastUpdated: '文章修订于',
        author: '程序员小浩',
        authorAvatar: '/code.png',
        startYear: '2019',
        valineConfig: {
            appId: 'sINkW5sfpPxoqDyBqbpvTN79-gzGzoHsz',
            appKey: '5tkDVulRNyjuAbCUekxU6zHW',
        },
        noFoundPageByTencent: false
    },
    markdown: {
        lineNumbers: true,
    }
}