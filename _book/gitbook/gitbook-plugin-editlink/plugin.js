require(["gitbook", "jQuery"], function(gitbook, $) {
  gitbook.events.bind('start', function (e, config) {
    var conf = config.editlink
    var label = conf.label
    var base = conf.base
    var multilingual = conf.multilingual || false

    if (base.slice(-1) !== "/") {
      base += "/"
    }

    gitbook.toolbar.createButton({
      icon: 'fa fa-edit',
      text: label,
      onClick: function() {
        var filepath = gitbook.state.filepath
        var lang = multilingual && $('html').attr('lang') ? $('html').attr('lang') + '/' : ''
        
        window.open(base + lang + filepath)
      }
    })
  })
})