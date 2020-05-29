# Disqus integration for GitBook

To use the Disqus plugin in your Gitbook project, add the disqus plugin to the `book.json` file, along with your shortname (you create a shortname for disqus by creating a new website on the [disqus.com](https://disqus.com/) website)

```
{
    "plugins": ["disqus"],
    "pluginsConfig": {
        "disqus": {
            "shortName": "XXXXXXX"
        }
    }
}
```

Then run `gitbook install` to download and install the plugin.
