/*!
 * gray-matter <https://github.com/jonschlinkert/gray-matter.git>
 *
 * Copyright (c) 2014-2015, Jon Schlinkert.
 * Licensed under the MIT License.
 */

'use strict';


/**
 * Module dependencies
 */

var extend = require('extend-shallow');
var red = require('ansi-red');

/**
 * Expose `parser` module
 */

var parser = module.exports;

/**
 * Requires cache.
 */

parser.requires = {};

/**
 * Parse YAML front matter
 *
 * @param  {String} `str` The string to parse.
 * @param  {Object} `options` Options to pass to [js-yaml].
 * @return {Object} Parsed object of data.
 * @api public
 */

parser.yaml = function(str, options) {
  var opts = extend({strict: false, safeLoad: false}, options);
  try {
    var YAML = parser.requires.yaml || (parser.requires.yaml = require('js-yaml'));
    return opts.safeLoad ? YAML.safeLoad(str, options) : YAML.load(str, options);
  } catch (err) {
    if (opts.strict) {
      throw new SyntaxError(msg('js-yaml', err));
    } else {
      return {};
    }
  }
};


/**
 * Parse JSON front matter
 *
 * @param  {String} `str` The string to parse.
 * @return {Object} Parsed object of data.
 * @api public
 */

parser.json = function(str, options) {
  var opts = extend({strict: false}, options);
  try {
    return JSON.parse(str);
  } catch (err) {
    if (opts.strict) {
      throw new SyntaxError(msg('JSON', err));
    } else {
      return {};
    }
  }
};


/**
 * Parse JavaScript front matter. To use javascript front-matter, you must
 * set `options.eval` to `true`.
 *
 * By default, javascript code is wrapped in a function that is immediately
 * executed when the parser is called. Thus, to be returned as a useful object,
 * code should be written as object properties.
 *
 * **Example:**
 *
 * ```markdown
 * ---js
 * title: 'autodetect-javascript',
 * // this function won't be invoked when the parser is called
 * fn: {
 *   reverse: function(str) {
 *     return str.split('').reverse().join('');
 *   }
 * }
 * ---
 * ```
 *
 * @param  {String} `str` The string to parse.
 * @param  {Object} `options` Set `options.wrapped` to `false` to enable writing raw, un-wrapped javascript.
 * @return {Object} Parsed object of data.
 * @api public
 */

parser.javascript = function(str, options) {
  var opts = extend({wrapped: true, eval: false, strict: false}, options);
  if (opts.eval) {
    if (opts.wrapped) {
      str = 'function data() {return {' + str + '}; } data();';
    }
    try {
      return eval(str);
    } catch (err) {
      throw new SyntaxError(msg('javascript', err));
    }
    return {};
  } else {

    // if `eval` isn't set
    if (opts.strict) {
      throw new Error(evalError('javascript'));
    } else {
      console.error(evalError('javascript', true));
    }
  }
};


/**
 * Alias for `parse.javascript()`.
 *
 * @api public
 */

parser.js = parser.javascript;


/**
 * Parse Coffee-Script front matter. To use coffee front-matter, you must
 * set `options.eval` to `true`.
 *
 * @param  {String} `str` The string to parse.
 * @param  {Object} `options` Options to pass to [coffee-script].
 * @return {Object} Parsed object of data.
 * @api public
 */

parser.coffee = function(str, options) {
  var opts = extend({eval: false, strict: false}, options);
  if (opts.eval) {
    try {
      var coffee = parser.requires.coffee || (parser.requires.coffee = require('coffee-script'));
      return coffee['eval'](str, options);
    } catch (err) {
      throw new SyntaxError(msg('coffee-script', err));
    }
  } else {

    // if `eval` isn't set
    if (opts.strict) {
      throw new Error(evalError('coffee'));
    } else {
      console.error(evalError('coffee', true));
    }
  }
};

/**
 * Alias for `parse.coffee()`.
 *
 * @api public
 */

parser.cson = parser.coffee;

/**
 * Parse TOML front matter.
 *
 * @param  {String} `str` The string to parse.
 * @param  {Object} `options` Options to pass to [toml-node].
 * @return {Object} Parsed object of data.
 * @api public
 */

parser.toml = function(str, opts) {
  try {
    var toml = parser.requires.toml || (parser.requires.toml = require('toml'));
    return toml.parse(str);
  } catch (err) {
    if (opts.strict) {
      throw new SyntaxError(msg('TOML', err));
    } else {
      return {};
    }
  }
};

/**
 * Normalize error messages
 */

function msg(lang, err) {
  return 'gray-matter parser [' + lang + ']: ' + err;
}

function evalError(lang, color) {
  var msg = '[gray-matter]: to parse ' + lang + ' set `options.eval` to `true`';
  return color ? red(msg) : msg;
}
