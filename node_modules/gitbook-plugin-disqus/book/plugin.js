require([
    "gitbook",
    "jQuery"
], function(gitbook, $) {
    var use_identifier = false;

    function resetDisqus() {
        var $disqusDiv = $("<div>", {
            "id": "disqus_thread"
        });
        $(".book-body .page-inner").append($disqusDiv);

        if (typeof DISQUS !== "undefined") {
            DISQUS.reset({
                reload: true,
                config: function () {
                    this.language = $('html').attr('lang') || "en";
                    this.page.url = window.location.href;

                    if (use_identifier) {
                        this.page.identifier = currentUrl();
                    }
                }
            });
        }
    }

    function joinURL(baseUrl, url) {
        var theUrl = new URI(url);
        if (theUrl.is("relative")) {
            theUrl = theUrl.absoluteTo(baseUrl);
        }
        return theUrl.toString();
    }

    function currentUrl() {
        var location = new URI(window.location.href),
            base     = joinURL(window.location.href, gitbook.state.basePath),
            current  = location.relativeTo(base).toString(),
            language = $('html').attr('lang'),
            parent   = joinURL(base, '..'),
            folder   = new URI(base).relativeTo(parent).toString();

        // If parent folder is the same as language, we assume translated books
        if (folder.replace(/\/$/, "") === language) {
            current = folder + current;
        }

        return current;
    }

    gitbook.events.bind("start", function(e, config) {
        config.disqus = config.disqus || {};
        var disqus_shortname = config.disqus.shortName;
        var disqus_config = function() {
            this.language = $('html').attr('lang') || "en";
        };

        if (config.disqus.useIdentifier) {
            use_identifier = true;
            var disqus_identifier = currentUrl();
        }

        /* * * DON'T EDIT BELOW THIS LINE * * */
        (function() {
            var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
            dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
            (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
        })();

        resetDisqus();
    });

    gitbook.events.bind("page.change", resetDisqus);
});
