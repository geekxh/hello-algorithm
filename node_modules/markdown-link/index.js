'use strict';

/**
 * Create a markdown-formatted link from the given values.
 *
 * ```js
 * mdlink('micromatch', 'https://github.com/jonschlinkert/micromatch', 'hover title');
 * //=> [micromatch](https://github.com/jonschlinkert/micromatch "hover title")
 * ```
 *
 * @name link
 * @param  {String} `anchor`
 * @param  {String} `href`
 * @param  {String} `title`
 * @api public
 */

module.exports = function link(anchor, href, title) {
  if (typeof anchor !== 'string') {
    throw new TypeError('markdown-link expects anchor to be a string.');
  }
  if (typeof href !== 'string') {
    throw new TypeError('markdown-link expects href to be a string.');
  }

  title = title ? ' "' + title + '"' : '';
  return '[' + anchor + '](' + href + title + ')';
};
