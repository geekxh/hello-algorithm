const path = require("path");
module.exports = {
    enhanceAppFiles: [
        path.resolve(__dirname, 'enhanceAppFile.js')
    ],

    globalUIComponents: ['Notification'],
    async ready() {
        console.log('ready')
    },
    async generated(pagePaths) {
        console.log('generated')
    },
    updated() {
        console.log('updated')
    }
}
