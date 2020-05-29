'use strict';

var fs = require('fs');
var extend = require('extend-shallow');
var parsers = require('./lib/parsers');

/**
 * Expose `matter()`
 */

module.exports = matter;

/**
 * Parses a `string` of front-matter with the given `options`,
 * and returns an object.
 *
 * ```js
 * matter('---\ntitle: foo\n---\nbar');
 * //=> {data: {title: 'foo'}, content: 'bar', orig: '---\ntitle: foo\n---\nbar'}
 * ```
 *
 * @param {String} `string` The string to parse.
 * @param {Object} `options`
 *   @option {Array} [options] `delims` Custom delimiters formatted as an array. The default is `['---', '---']`.
 *   @option {Function} [options] `parser` Parser function to use. [js-yaml] is the default.
 * @return {Object} Valid JSON
 * @api public
 */

function matter(str, options) {
  if (typeof str !== 'string') {
    throw new Error('gray-matter expects a string');
  }

  // default results to build up
  var res = {orig: str, data: {}, content: str};
  if (str === '') {
    return res;
  }

  // delimiters
  var delims = arrayify((options && options.delims) || '---');
  var a = delims[0];

  // strip byte order marks
  str = stripBom(str);

  // if the first delim isn't the first thing, return
  if (!isFirst(str, a)) {
    return res;
  }

  var b = '\n' + (delims[1] || delims[0]);
  var alen = a.length;

  // if the next character after the first delim
  // is a character in the first delim, then just
  // return the default object. it's either a bad
  // delim or not a delimiter at all.
  if (a.indexOf(str.charAt(alen + 1)) !== -1) {
    return res;
  }

  var len = str.length;

  // find the index of the next delimiter before
  // going any further. If not found, return.
  var end = str.indexOf(b, alen + 1);
  if (end === -1) {
    end = len;
  }

  // detect a language, if defined
  var lang = str.slice(alen, str.indexOf('\n'));
  // measure the lang before trimming whitespace
  var start = alen + lang.length;

  var opts = options || {};
  opts.lang = opts.lang || 'yaml';
  lang = (lang && lang.trim()) || opts.lang;

  // get the front matter (data) string
  var data = str.slice(start, end).trim();
  if (data) {
    // if data exists, see if we have a matching parser
    var fn = opts.parser || parsers[lang];
    if (typeof fn === 'function') {
      // run the parser on the data string
      res.data = fn(data, opts);
    } else {
      throw new Error('gray-matter cannot find a parser for: ' + str);
    }
  }

  // grab the content from the string, stripping
  // an optional new line after the second delim
  var con = str.substr(end + b.length);
  if (con.charAt(0) === '\n') {
    con = con.substr(1);
  } else if (con.charAt(0) === '\r' && con.charAt(1) === '\n') {
    con = con.substr(2);
  }

  res.content = con;
  return res;
}

/**
 * Expose `parsers`
 *
 * @type {Object}
 */

matter.parsers = parsers;

/**
 * Requires cache
 */

var YAML = matter.parsers.requires.yaml || (matter.parsers.requires.yaml = require('js-yaml'));

/**
 * Read a file and parse front matter. Returns the same object
 * as `matter()`.
 *
 * ```js
 * matter.read('home.md');
 * ```
 *
 * @param {String} `fp` file path of the file to read.
 * @param {Object} `options` Options to pass to gray-matter.
 * @return {Object}
 * @api public
 */

matter.read = function(fp, options) {
  var str = fs.readFileSync(fp, 'utf8');
  var obj = matter(str, options);
  return extend(obj, {
    path: fp
  });
};

/**
 * Stringify an object to front-matter-formatted YAML, and
 * concatenate it to the given string.
 *
 * ```js
 * matter.stringify('foo bar baz', {title: 'Home'});
 * ```
 * Results in:
 *
 * ```yaml
 * ---
 * title: Home
 * ---
 * foo bar baz
 * ```
 *
 * @param {String} `str` The content string to append to stringified front-matter.
 * @param {Object} `data` Front matter to stringify.
 * @param {Object} `options` Options to pass to js-yaml
 * @return {String}
 * @api public
 */

matter.stringify = function(str, data, options) {
  var delims = arrayify(options && options.delims || '---');
  var res = '';
  res += delims[0] + '\n';
  res += YAML.safeDump(data, options);
  res += (delims[1] || delims[0]) + '\n';
  res += str + '\n';
  return res;
};

/**
 * Return true if the given `string` has front matter.
 *
 * @param  {String} `string`
 * @param  {Object} `options`
 * @return {Boolean} True if front matter exists.
 */

matter.test = function(str, options) {
  var delims = arrayify(options && options.delims || '---');
  return isFirst(str, delims[0]);
};

/**
 * Return true if the given `ch` the first
 * thing in the string.
 */

function isFirst(str, ch) {
  return str.substr(0, ch.length) === ch;
}

/**
 * Utility to strip byte order marks
 */

function stripBom(str) {
  return str.charAt(0) === '\uFEFF' ? str.slice(1) : str;
}

/**
 * Typecast `val` to an array.
 */

function arrayify(val) {
  return !Array.isArray(val) ? [val] : val;
}
