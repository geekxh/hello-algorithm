/*!
 * Sitemap
 * Copyright(c) 2011 Eugene Kalinin
 * MIT Licensed
 */

var ut = require('./utils')
  , err = require('./errors')
  , urlparser = require('url')
  , fs = require('fs')
  , urljoin = require('url-join')
  , _ = require('underscore');

exports.Sitemap = Sitemap;
exports.SitemapItem = SitemapItem;
exports.createSitemap = createSitemap;
exports.createSitemapIndex = createSitemapIndex;

/**
 * Shortcut for `new Sitemap (...)`.
 *
 * @param   {Object}        conf
 * @param   {String}        conf.hostname
 * @param   {String|Array}  conf.urls
 * @param   {Number}        conf.cacheTime
 * @param   {String}        conf.xslUrl
 * @return  {Sitemap}
 */
function createSitemap(conf) {
  return new Sitemap(conf.urls, conf.hostname, conf.cacheTime, conf.xslUrl);
}

function safeUrl(conf) {
  var loc = conf['url'];
  if ( !conf['safe'] ) {
    var url_parts = urlparser.parse(conf['url']);
    if ( !url_parts['protocol'] ) {
      throw new err.NoURLProtocolError();
    }

    loc = ut.htmlEscape(conf['url']);
  }
  return loc;
}

/**
 * Item in sitemap
 */
function SitemapItem(conf) {
  var conf = conf || {}
    , is_safe_url = conf['safe'];

  if ( !conf['url'] ) {
    throw new err.NoURLError();
  }

  // URL of the page
  this.loc = safeUrl(conf);

  // If given a file to use for last modified date
  if ( conf['lastmodfile'] ) {
      //console.log('should read stat from file: ' + conf['lastmodfile']);
      var file = conf['lastmodfile'];

      var stat = fs.statSync( file );

      var mtime = stat.mtime;

      var dt = new Date( mtime );
      this.lastmod = ut.getTimestampFromDate(dt, conf['lastmodrealtime']);

  }
  // The date of last modification (YYYY-MM-DD)
  else if ( conf['lastmod'] ) {
    // append the timezone offset so that dates are treated as local time.
    // Otherwise the Unit tests fail sometimes.
    var timezoneOffset = 'UTC-' + (new Date().getTimezoneOffset()/60) + '00';
    var dt = new Date( conf['lastmod'] + ' ' + timezoneOffset );
    this.lastmod = ut.getTimestampFromDate(dt, conf['lastmodrealtime']);
  } else if ( conf['lastmodISO'] ) {
    this.lastmod = conf['lastmodISO'];
  }

  // How frequently the page is likely to change
  this.changefreq = conf['changefreq'] || 'weekly';
  if ( !is_safe_url ) {
    if ( [ 'always',  'hourly', 'daily', 'weekly', 'monthly',
           'yearly', 'never' ].indexOf(this.changefreq) === -1 ) {
      throw new err.ChangeFreqInvalidError();
    }
  }

  // The priority of this URL relative to other URLs
  this.priority = typeof conf['priority'] === 'number' ? conf['priority'] : (conf['priority'] || 0.5);
  if ( !is_safe_url ) {
    if ( !(this.priority >= 0.0 && this.priority <= 1.0) ) {
      throw new err.PriorityInvalidError();
    }
  }

  this.news = conf['news'] || null;
  this.img = conf['img'] || null;
  this.links = conf['links'] || null;
  this.mobile = conf['mobile'] || null;
}

/**
 *  Create sitemap xml
 *  @return {String}
 */
SitemapItem.prototype.toXML = function () {
  return this.toString();
}

/**
 *  Alias for toXML()
 *  @return {String}
 */
SitemapItem.prototype.toString = function () {
      // result xml
  var xml = '<url> {loc} {img} {lastmod} {changefreq} {priority} {links} {mobile} {news}</url>'
      // xml property
    , props = ['loc', 'img', 'lastmod', 'changefreq', 'priority', 'links', 'mobile','news']
      // property array size (for loop)
    , ps = props.length
      // current property name (for loop)
    , p;

  while ( ps-- ) {
    p = props[ps];

    if(this[p] && p == 'img') {
      // Image handling
      imagexml = '<image:image><image:loc>'+this[p]+'</image:loc></image:image>';
      if(typeof(this[p])=='object'){
        if(this[p]&&this[p].length>0){
          imagexml = '';
          this[p].forEach(function(image){
            imagexml += '<image:image><image:loc>'+image+'</image:loc></image:image>';
          });
        }
      }

      xml = xml.replace('{' + p + '}',imagexml);

    } else if (this[p] && p == 'links') {
      xml = xml.replace('{' + p + '}',
          this[p].map(function(link) {
            return '<xhtml:link rel="alternate" hreflang="'+link.lang+'" href="'+safeUrl(link)+'" />';
          }).join(" "));
    } else if (this[p] && p == 'mobile') {
      xml = xml.replace('{' + p + '}', '<mobile:mobile/>');
    } else if (p == 'priority' && (this[p] >= 0.0 && this[p] <= 1.0)) {
      xml = xml.replace('{'+p+'}',
          '<'+p+'>'+parseFloat(this[p]).toFixed(1)+'</'+p+'>');
    } else if (this[p] && p == 'news') {
      var newsitem = '<news:news>';

      if (this[p].publication) {
        newsitem += '<news:publication>';
        if (this[p].publication.name)     { newsitem += '<news:name>'     + this[p].publication.name     + '</news:name>'     ;}
        if (this[p].publication.language) { newsitem += '<news:language>' + this[p].publication.language + '</news:language>' ;}
        newsitem += '</news:publication>';
      }

      if (this[p].access)           { newsitem += '<news:access>'           + this[p].access           + '</news:access>'           ;}
      if (this[p].genres)           { newsitem += '<news:genres>'           + this[p].genres           + '</news:genres>'           ;}
      if (this[p].publication_date) { newsitem += '<news:publication_date>' + this[p].publication_date + '</news:publication_date>' ;}
      if (this[p].title)            { newsitem += '<news:title>'            + this[p].title            + '</news:title>'            ;}
      if (this[p].keywords)         { newsitem += '<news:keywords>'         + this[p].keywords         + '</news:keywords>'         ;}
      if (this[p].stock_tickers)    { newsitem += '<news:stock_tickers>'    + this[p].stock_tickers    + '</news:stock_tickers>'    ;}

      newsitem += '</news:news>';

      xml = xml.replace('{' + p + '}', newsitem);
    } else if (this[p]) {
      xml = xml.replace('{'+p+'}',
                  '<'+p+'>'+this[p]+'</'+p+'>');
    } else {
      xml = xml.replace('{'+p+'}', '');
    }
    xml = xml.replace('  ', ' ');
  }

  return xml.replace('  ', ' ');
}

/**
 * Sitemap constructor
 * @param {String|Array}  urls
 * @param {String}        hostname    optional
 * @param {Number}        cacheTime   optional in milliseconds; 0 - cache disabled
 * @param {String}        xslUrl			optional
 */
function Sitemap(urls, hostname, cacheTime, xslUrl) {

  // This limit is defined by Google. See:
  // http://sitemaps.org/protocol.php#index
  this.limit = 50000

  // Base domain
  this.hostname = hostname;

  // URL list for sitemap
  this.urls = [];

  // Make copy of object
  if(urls) _.extend(this.urls, (urls instanceof Array) ? urls : [urls]);

  // sitemap cache
  this.cacheResetPeriod = cacheTime || 0;
  this.cache = '';
  
  this.xslUrl = xslUrl;
}

/**
 *  Clear sitemap cache
 */
Sitemap.prototype.clearCache = function () {
  this.cache = '';
}

/**
 *  Can cache be used
 */
Sitemap.prototype.isCacheValid = function() {
  var currTimestamp = ut.getTimestamp();
  return this.cacheResetPeriod && this.cache &&
         (this.cacheSetTimestamp + this.cacheResetPeriod) >= currTimestamp;
}

/**
 *  Fill cache
 */
Sitemap.prototype.setCache = function(newCache) {
  this.cache = newCache;
  this.cacheSetTimestamp = ut.getTimestamp();
  return this.cache;
}

/**
 *  Add url to sitemap
 *  @param {String} url
 */
Sitemap.prototype.add = function (url) {
  return this.urls.push(url);
}

/**
 *  Delete url from sitemap
 *  @param {String} url
 */
Sitemap.prototype.del = function (url) {
  var index_to_remove = [],
      key = '',
      self=this;

  if (typeof url == 'string') {
    key = url;
  } else {
    key = url['url'];
  }

  // find
  this.urls.forEach( function (elem, index) {
    if ( typeof elem == 'string' ) {
        if (elem == key) {
            index_to_remove.push(index);
        }
    } else {
        if (elem['url'] == key) {
            index_to_remove.push(index);
        }
    }
  });

  // delete
  index_to_remove.forEach(function (elem) {
    self.urls.splice(elem, 1);
  });

  return index_to_remove.length;
}

/**
 *  Create sitemap xml
 *  @param {Function}     callback  Callback function with one argument — xml
 */
Sitemap.prototype.toXML = function (callback) {
  if (typeof callback === 'undefined') {
    return this.toString();
  }
  var self = this;
  process.nextTick( function () {
    try {
      return callback(null, self.toString());
    } catch (err) {
      return callback(err);
    }
  });
}

var reProto = /^https?:\/\//i;

/**
 *  Synchronous alias for toXML()
 *  @return {String}
 */
Sitemap.prototype.toString = function () {
  var self = this
    , xml = [ '<?xml version="1.0" encoding="UTF-8"?>',
              '<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9" ' +
              'xmlns:news="http://www.google.com/schemas/sitemap-news/0.9" ' +
              'xmlns:xhtml="http://www.w3.org/1999/xhtml" ' +
              'xmlns:mobile="http://www.google.com/schemas/sitemap-mobile/1.0" ' +
              'xmlns:image="http://www.google.com/schemas/sitemap-image/1.1">'
            ];
            
  if(self.xslUrl) {
      xml.splice(1, 0,
          '<?xml-stylesheet type="text/xsl" href="' + self.xslUrl + '"?>');
  }

  if (self.isCacheValid()) {
    return self.cache;
  }

  // TODO: if size > limit: create sitemapindex

  self.urls.forEach( function (elem, index) {
    // SitemapItem
    var smi = elem;

    // create object with url property
    if ( typeof elem == 'string' ) {
      smi = {'url': elem};
    }
    // insert domain name
    if ( self.hostname ) {
      if ( !reProto.test(smi.url) ) {
        smi.url = urljoin(self.hostname, smi.url);
      }
      if ( smi.links ) {
        smi.links.forEach(function(link) {
            if ( !reProto.test(link.url) ) {
              link.url = urljoin(self.hostname, link.url);
            }
        });
      }
    }
    xml.push( new SitemapItem(smi) );
  })
  // close xml
  xml.push('</urlset>');

  return self.setCache(xml.join('\n'));
}

Sitemap.prototype.toGzip = function(callback) {
  var zlib = require('zlib');

  if (typeof callback === 'function') {
    zlib.gzip(this.toString(), callback);
  } else {
    return zlib.gzipSync(this.toString());
  }
}

/**
 * Shortcut for `new SitemapIndex (...)`.
 *
 * @param   {Object}        conf
 * @param   {String|Array}  conf.urls
 * @param   {String}        conf.targetFolder
 * @param   {String}        conf.hostname
 * @param   {Number}        conf.cacheTime
 * @param   {String}        conf.sitemapName
 * @param   {Number}        conf.sitemapSize
 * @param   {String}        conf.xslUrl
 * @return  {SitemapIndex}
 */
function createSitemapIndex(conf) {
    return new SitemapIndex(conf.urls,
                            conf.targetFolder,
                            conf.hostname,
                            conf.cacheTime,
                            conf.sitemapName,
                            conf.sitemapSize,
                            conf.xslUrl,
                            conf.gzip,
                            conf.callback);
}

/**
 * Sitemap index (for several sitemaps)
 * @param {String|Array}  urls
 * @param {String}        targetFolder
 * @param {String}        hostname      optional
 * @param {Number}        cacheTime     optional in milliseconds
 * @param {String}        sitemapName   optional
 * @param {Number}        sitemapSize   optional
 * @param {Number}        xslUrl				optional
 * @param {Boolean}       gzip          optional
 * @param {Function}      callback      optional
 */
function SitemapIndex(urls, targetFolder, hostname, cacheTime, sitemapName, sitemapSize, xslUrl, gzip, callback) {

  var self = this;

  self.fs = require('fs');

  // Base domain
  self.hostname = hostname;

  if(sitemapName === undefined) {
    self.sitemapName = 'sitemap';
  }
  else {
    self.sitemapName = sitemapName;
  }

  // This limit is defined by Google. See:
  // http://sitemaps.org/protocol.php#index
  self.sitemapSize = sitemapSize;
  
  self.xslUrl = xslUrl;

  self.sitemapId = 0;

  self.sitemaps = [];

  self.targetFolder = '.';

  if(!self.fs.existsSync(targetFolder)) {
    throw new err.UndefinedTargetFolder();
  }

  self.targetFolder = targetFolder;

  // URL list for sitemap
  self.urls = urls || [];
  if ( !(self.urls instanceof Array) ) {
    self.urls = [ self.urls ]
  }

  self.chunks = ut.chunkArray(self.urls, self.sitemapSize);

  self.callback = callback;

  var processesCount = self.chunks.length + 1;

  self.chunks.forEach( function (chunk, index) {
    var extension = '.xml' + (gzip ? '.gz' : ''),
        filename = self.sitemapName + '-' + self.sitemapId++ + extension;

    self.sitemaps.push(filename);

    var sitemap = createSitemap ({
      hostname: self.hostname,
      cacheTime: self.cacheTime, // 600 sec - cache purge period
      urls: chunk,
      xslUrl: self.xslUrl
    });

    var stream = self.fs.createWriteStream(targetFolder + '/' + filename);
    stream.once('open', function(fd) {
      stream.write(gzip ? sitemap.toGzip() : sitemap.toString());
      stream.end();
      processesCount--;
      if(processesCount === 0 && typeof self.callback === 'function') {
        self.callback(null, true);
      }
    });

  });

  var xml = [];

  xml.push('<?xml version="1.0" encoding="UTF-8"?>');
  if(self.xslUrl) {
	xml.push('<?xml-stylesheet type="text/xsl" href="' + self.xslUrl + '"?>');
  }
  xml.push('<sitemapindex xmlns="http://www.sitemaps.org/schemas/sitemap/0.9" ' +
      'xmlns:mobile="http://www.google.com/schemas/sitemap-mobile/1.0" ' +
      'xmlns:image="http://www.google.com/schemas/sitemap-image/1.1">');

  self.sitemaps.forEach( function (sitemap, index) {
    xml.push('<sitemap>');
    xml.push('<loc>' + hostname + '/' + sitemap + '</loc>');
//    xml.push('<lastmod>' + new Date() + '</lastmod>');
    xml.push('</sitemap>');
  });

  xml.push('</sitemapindex>');

  var stream = self.fs.createWriteStream(targetFolder + '/' +
                                            self.sitemapName + '-index.xml');
  stream.once('open', function(fd) {
    stream.write(xml.join('\n'));
    stream.end();
    processesCount--;
    if(processesCount === 0 && typeof self.callback === 'function') {
      self.callback(null, true);
    }
  });

}
