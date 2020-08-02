const sidebar = require('./lib/sidebar/')

module.exports = {
    title: "小浩算法",
    plugins: [
        require('./lib/Notification.js'),
        [require('./plugin/enhanced-search'), { // 可以添加第三方搜索链接的搜索框（原官方搜索框的参数仍可用）
            thirdparty: [ // 可选，默认 []
                {
                    title: '在 <span style="color: blue; ">Baidu</span> &nbsp;&nbsp;中搜索 ',
                    frontUrl: 'https://www.baidu.com/s?wd=',
                },
                {
                    title: '在 <span style="color: red; ">Google</span> 中搜索 ',
                    frontUrl: 'https://www.google.com/search?q=',
                }
            ]
        }]
    ],
    description: '和小浩一起学算法',
    // root : "./hell-algorithm",
    theme: "reco",
    themeConfig: {
        logo: '/logo.png',
        sidebar,
        searchPlaceholder: "搜索：KMP",
        nav: [
            {text: 'GitHub', link: 'https://github.com/geekxh/hello-algorithm', icon: 'reco-github'}
        ],
        head: [
            ['link', {rel: 'shortcut icon', href: '/favicon.ico'}]
        ],
        type: 'blog',
        search: true,
        searchMaxSuggestions: 10,
        lastUpdated: '文章修订于',
        author: '程序员小浩',
        authorAvatar: '/code.png',
        startYear: '2019',
        valineConfig: {
            visitor: false,
            enable: true,
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