module.exports = {
    title: "和小浩学编程",
    plugins: [
        require('../../lib/autobar.js'),
        require('../../lib/Notification.js')],
    description: '编程是一件有趣的事情',
    // root : "./hell-algorithm",
    theme: "reco",
    themeConfig: {
        logo: '/logo.png',
        sidebar: "auto",
        searchPlaceholder: "搜索：KMP",
        nav: [
            {
                text: 'GitHub',
                link: 'https://githubd.com/geekxh/hello-algorithm',
                icon: 'reco-github'
            },
            {
                text: 'TimeLine',
                link: '/timeline/',
                icon: 'reco-date'
            }
        ],
        // 博客配置
        blogConfig: {
            tag: {
                location: 3,     // 在导航栏菜单中所占的位置，默认3
                text: 'Tag'      // 默认文案 “标签”
            }
        },
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