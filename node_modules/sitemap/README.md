sitemap.js
==========

**sitemap.js** is a high-level sitemap-generating framework that
makes creating [sitemap XML](http://www.sitemaps.org/) files easy.

Table of Contents
=================

  * [Table of Contents](#table-of-contents)
  * [sitemap.js](#sitemapjs)
    * [Installation](#installation)
    * [Usage](#usage)
      * [Example of using sitemap.js with <a href="https://github.com/visionmedia/express">express</a>:](#example-of-using-sitemapjs-with-express)
      * [Example of synchronous sitemap.js usage:](#example-of-synchronous-sitemapjs-usage)
      * [Example of dynamic page manipulations into sitemap:](#example-of-dynamic-page-manipulations-into-sitemap)
      * [Example of pre-generating sitemap based on existing static files:](#example-of-pre-generating-sitemap-based-on-existing-static-files)
      * [Example of indicating alternate language pages:](#example-of-indicating-alternate-language-pages)
      * [Example of Sitemap Styling](#example-of-sitemap-styling)
      * [Example of mobile URL](#example-of-mobile-url)
    * [License](#license)

TOC created by [gh-md-toc](https://github.com/ekalinin/github-markdown-toc)

Installation
------------

It's recommended to install via [npm](https://github.com/isaacs/npm/):

    npm install --save sitemap

Usage
-----
The main functions you want to use in the sitemap module are

```javascript
var sm = require('sitemap')
// Creates a sitemap object given the input configuration with URLs
var sitemap = sm.createSitemap({ options });
// Generates XML with a callback function
sitemap.toXML( function(xml){ console.log(xml) });
// Gives you a string containing the XML data
var xml = sitemap.toString();
```

###Example of using sitemap.js with [express](https://github.com/visionmedia/express):

```javascript
var express = require('express')
  , sm = require('sitemap');

var app = express()
  , sitemap = sm.createSitemap ({
      hostname: 'http://example.com',
      cacheTime: 600000,        // 600 sec - cache purge period
      urls: [
        { url: '/page-1/',  changefreq: 'daily', priority: 0.3 },
        { url: '/page-2/',  changefreq: 'monthly',  priority: 0.7 },
        { url: '/page-3/'},    // changefreq: 'weekly',  priority: 0.5
        { url: '/page-4/',   img: "http://urlTest.com" }
      ]
    });

app.get('/sitemap.xml', function(req, res) {
  sitemap.toXML( function (err, xml) {
      if (err) {
        return res.status(500).end();
      }
      res.header('Content-Type', 'application/xml');
      res.send( xml );
  });
});

app.listen(3000);
```

###Example of synchronous sitemap.js usage:

```javascript
var express = require('express')
  , sm = require('sitemap');

var app = express()
  , sitemap = sm.createSitemap ({
      hostname: 'http://example.com',
      cacheTime: 600000,  // 600 sec cache period
      urls: [
        { url: '/page-1/',  changefreq: 'daily', priority: 0.3 },
        { url: '/page-2/',  changefreq: 'monthly',  priority: 0.7 },
        { url: '/page-3/' } // changefreq: 'weekly',  priority: 0.5
      ]
    });

app.get('/sitemap.xml', function(req, res) {
  res.header('Content-Type', 'application/xml');
  res.send( sitemap.toString() );
});

app.listen(3000);
```

###Example of dynamic page manipulations into sitemap:

```javascript
var sitemap = sm.createSitemap ({
      hostname: 'http://example.com',
      cacheTime: 600000
    });
sitemap.add({url: '/page-1/'});
sitemap.add({url: '/page-2/', changefreq: 'monthly', priority: 0.7});
sitemap.del({url: '/page-2/'});
sitemap.del('/page-1/');
```



###Example of pre-generating sitemap based on existing static files:

```javascript
var sm = require('sitemap')
    , fs = require('fs');

var sitemap = sm.createSitemap({
    hostname: 'http://www.mywebsite.com',
    cacheTime: 600000,  //600 sec (10 min) cache purge period
    urls: [
        { url: '/' , changefreq: 'weekly', priority: 0.8, lastmodrealtime: true, lastmodfile: 'app/assets/index.html' },
        { url: '/page1', changefreq: 'weekly', priority: 0.8, lastmodrealtime: true, lastmodfile: 'app/assets/page1.html' },
        { url: '/page2'    , changefreq: 'weekly', priority: 0.8, lastmodrealtime: true, lastmodfile: 'app/templates/page2.hbs' } /* useful to monitor template content files instead of generated static files */
    ]
});

fs.writeFileSync("app/assets/sitemap.xml", sitemap.toString());
```

###Example of indicating alternate language pages:

[Description](https://support.google.com/webmasters/answer/2620865?hl=en) in
the google's Search Console Help.

```javascript
var sm = sm.createSitemap({
      urls: [{
        url: 'http://test.com/page-1/',
        changefreq: 'weekly',
        priority: 0.3,
        links: [
          { lang: 'en', url: 'http://test.com/page-1/', },
          { lang: 'ja', url: 'http://test.com/page-1/ja/', },
        ]
      },]
    });
```


###Example of Sitemap Styling

```javascript
var sm = sm.createSitemap({
      urls: [{
        url: 'http://test.com/page-1/',
        changefreq: 'weekly',
        priority: 0.3,
        links: [
          { lang: 'en', url: 'http://test.com/page-1/', },
          { lang: 'ja', url: 'http://test.com/page-1/ja/', },
        ]
      },],
      xslUrl: 'sitemap.xsl'
    });

```

### Example of mobile URL

[Description](https://support.google.com/webmasters/answer/34648?hl=en) in
the google's Search Console Help.
```javascript
var sm = sm.createSitemap({
      urls: [{
        url: 'http://mobile.test.com/page-1/',
        changefreq: 'weekly',
        priority: 0.3,
        mobile: true
      },],
      xslUrl: 'sitemap.xsl'
    });
```

License
-------

See [LICENSE](https://github.com/ekalinin/sitemap.js/blob/master/LICENSE)
file.
