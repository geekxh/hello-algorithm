module.exports = {
    title: "小浩算法",
    plugins: [require('../../lib/autobar.js')],
    description: '和小浩一起学算法',
    root : "./hell-algorithm",
    theme: "reco",
    themeConfig: {
        sidebar: "auto",
        nav: [
            { text: 'GitHub', link: 'https://github.com/geekxh/hello-algorithm', icon: 'reco-github' },
        ],
        type: 'blog',
        search: true,
        searchMaxSuggestions: 10,
        lastUpdated: '文章修订于',
        author: '程序员小浩',
        authorAvatar: '/avatar.png',
        startYear: '2019',
        valineConfig: {
            appId: 'sINkW5sfpPxoqDyBqbpvTN79-gzGzoHsz',
            appKey: '5tkDVulRNyjuAbCUekxU6zHW',
        }
    },
    markdown: {
        lineNumbers: true,
    }
}