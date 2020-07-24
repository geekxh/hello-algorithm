module.exports = {
    title: "小浩算法",
    plugins: [require('../../lib/autobar.js'), require('../../lib/Notification.js')],
    description: '和小浩一起学算法',
    // root : "./hell-algorithm",
    theme: "reco",
    themeConfig: {
        logo: '/logo.jpeg',
        sidebar: "auto",
        searchPlaceholder: "搜索：KMP",
        nav: [
            {text: '动态规划', link: '/1.2.动态规划系列' },
            {text: 'GitHub', link: 'https://github.com/geekxh/hello-algorithm', icon: 'reco-github'}
        ],
        head:[
            ['link',{rel:'shortcut icon',href:'/favicon.ico'}]
        ],
        type: 'blog',
        search: true,
        searchMaxSuggestions: 10,
        lastUpdated: '文章修订于',
        author: '程序员小浩',
        authorAvatar: '/code.png',
        startYear: '2019',
        valineConfig: {
            visitor:false,
            enable: false,
            placeholder: "留下你的问题，或者你想要的资源...",
            avatar: "wavatar",
            requiredFields: ['nick'],
            appId: 'sINkW5sfpPxoqDyBqbpvTN79-gzGzoHsz',
            appKey: '5tkDVulRNyjuAbCUekxU6zHW',
        },
        noFoundPageByTencent: false
    },
    markdown: {
        lineNumbers: true,
    }
}