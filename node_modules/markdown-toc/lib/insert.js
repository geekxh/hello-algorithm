'use strict';

var toc = require('..');
var utils = require('./utils');

/**
 * The basic idea:
 *
 *  1. when front-matter exists, we need to avoid turning its properties into headings.
 *  2. We need to detect toc markers on the page. For now it's a simple HTML code comment
 *     to ensure the markdown is compatible with any parser.
 *
 * @param  {String} `str` Pass a string of markdown
 * @param  {Object} `options` Pass options to toc generation
 * @return {String} Get the same string back with a TOC inserted
 */

module.exports = function insert(str, options) {
  options = options || {};

  var regex = options.regex || /(?:<!-- toc(?:\s*stop)? -->)/g;
  var open = options.open   || '<!-- toc -->\n\n';
  var close = options.close || '<!-- tocstop -->';
  var file;

  var newlines = '';
  var m = /\n+$/.exec(str);
  if (m) newlines = m[0];

  // does the file have front-matter?
  if (/^---/.test(str)) {
    // extract it temporarily so the syntax
    // doesn't get mistaken for a heading
    file = utils.matter(str);
    str = file.content;
  }

  var sections = split(str, regex);
  if (sections.length > 3) {
    throw new Error('markdown-toc only supports one Table of Contents per file.');
  }

  var last = sections[sections.length - 1];
  if (sections.length === 3) {
    sections.splice(1, 1, open + (options.toc || toc(last, options).content));
    sections.splice(2, 0, close);
  }

  if (sections.length === 2) {
    sections.splice(1, 0, open + toc(last, options).content + '\n\n' + close);
  }

  var res = sections.join('\n\n') + newlines;
  // if front-matter was found, put it back now
  if (file) {
    return utils.matter.stringify(res, file.data);
  }
  return res;
};

function split(str, re) {
  return str.split(re).map(trim);
}

function trim(str) {
  return str.trim();
}
