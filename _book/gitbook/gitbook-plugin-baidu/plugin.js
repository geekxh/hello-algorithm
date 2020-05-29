require(["gitbook"], function(gitbook) {
    gitbook.events.bind("start", function(e, config) {
        config.baidu = config.baidu || {};
    });
});
