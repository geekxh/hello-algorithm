'use strict';

/**
 * Module dependencies
 */

var utils = require('lazy-cache')(require);
var fn = require;
require = utils;

/**
 * Lazily required module dependencies
 */

require('minimist');
require('remarkable', 'Remarkable');
require('repeat-string', 'repeat');
require('markdown-link', 'mdlink');
require('concat-stream', 'concat');
require('gray-matter', 'matter');
require('object.pick', 'pick');
require('mixin-deep', 'merge');
require = fn;

/**
 * Expose `utils` modules
 */

module.exports = utils;
