module.exports = {
    book: {
        assets: "./book",
        js: [
            "plugin.js"
        ],
        html: {
            "body:end": function() {
                return "<script>var _hmt = _hmt || [];"
                    + "(function() {"
                    + "var hm = document.createElement('script');"
                    + "hm.src = '//hm.baidu.com/hm.js?" + this.options.pluginsConfig.baidu.token + "';"
                    + "var s = document.getElementsByTagName('script')[0];"
                    + "s.parentNode.insertBefore(hm, s);"
                    + "})();</script>";
            }
        }
    }
};
