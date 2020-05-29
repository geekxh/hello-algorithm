/*!
 * Sitemap
 * Copyright(c) 2011 Eugene Kalinin
 * MIT Licensed
 */

module.exports = require('./lib/sitemap');
module.exports.utils = require('./lib/utils');
module.exports.errors = require('./lib/errors');

/**
 * Framework version.
 */
var fs = require('fs')
  , path = require('path')
  , pack_file = path.join(__dirname, 'package.json');

if ( !module.exports.version ) {
  module.exports.version = JSON.parse(
    fs.readFileSync(pack_file, 'utf8')).version;
}
