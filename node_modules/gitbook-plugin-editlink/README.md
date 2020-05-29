# gitbook-plugin-editlink

GitBook Plugin to add \"Edit this page\" link on every page. Link target will be that page's source file on Github or any repo.

```js
{
  "plugins": ["editlink"],
  "pluginsConfig": {
    "editlink": {
      "base": "https://github.com/zhaoda/webpack-handbook/edit/master/content",
      "label": "Edit This Page",
      "multilingual": false
    }
  }
}

```

If you are using multiple languages in gitbook, set `label` like this:

```js
{
    "label": {
        "en": "Edit This Page",
        "zh-cn": "编辑本页"
    }
}
```

Then run `gitbook install` to download and install the plugin.

