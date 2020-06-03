var moment = require('moment');
module.exports = {
    book: {
        assets: './assets',
        css: [
            'footer.css'
        ],
    },
    hooks: {
        'page:before': function(page) {
            var _label = '修订时间: ',
                _format = 'YYYY-MM-DD HH:mm:ss',
                _copy = 'powered by Gitbook'
            if (this.options.pluginsConfig['tbfed-pagefooter']) {
                _label = this.options.pluginsConfig['tbfed-pagefooter']['modify_label'] || _label;
                _format = this.options.pluginsConfig['tbfed-pagefooter']['modify_format'] || _format;

                var _c = this.options.pluginsConfig['tbfed-pagefooter']['copyright'];
                _copy = _c
            }
            var _copy = '<span class="copyright">' + _copy + '</span>'
            var str = ' \n\n<footer class="page-footer">' + _copy +
                '<span class="footer-modification">' +
                _label +
                '\n{{file.mtime | date("' + _format +
                '")}}\n</span></footer>'
            page.content = page.content + str;
            return page;
        }
    },
    filters: {
        date: function(d, format) {
            return moment(d).format(format)
        }
    }
};