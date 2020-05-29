/*!
 * Sitemap
 * Copyright(c) 2011 Eugene Kalinin
 * MIT Licensed
 */

/**
 * URL in SitemapItem does not exists
 */
exports.NoURLError = function (message) {
  this.name = 'NoURLError';
  this.message = message || 'URL is required';
}
exports.NoURLError.prototype = Error.prototype;

/**
 * Protocol in URL does not exists
 */
exports.NoURLProtocolError = function (message) {
  this.name = 'NoURLProtocolError';
  this.message = message || 'Protocol is required';
}
exports.NoURLProtocolError.prototype = Error.prototype;

/**
 * changefreq property in sitemap is invalid
 */
exports.ChangeFreqInvalidError = function (message) {
  this.name = 'ChangeFreqInvalidError';
  this.message = message || 'changefreq is invalid';
}
exports.ChangeFreqInvalidError.prototype = Error.prototype;

/**
 * priority property in sitemap is invalid
 */
exports.PriorityInvalidError = function (message) {
  this.name = 'PriorityInvalidError';
  this.message = message || 'priority is invalid';
}
exports.PriorityInvalidError.prototype = Error.prototype;

/**
 * SitemapIndex target Folder does not exists
 */
exports.UndefinedTargetFolder = function (message) {
  this.name = 'UndefinedTargetFolder';
  this.message = message || 'Target folder must exist';
}
exports.UndefinedTargetFolder.prototype = Error.prototype;
