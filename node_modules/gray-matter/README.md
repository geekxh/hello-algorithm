# gray-matter [![NPM version](https://img.shields.io/npm/v/gray-matter.svg?style=flat)](https://www.npmjs.com/package/gray-matter) [![NPM monthly downloads](https://img.shields.io/npm/dm/gray-matter.svg?style=flat)](https://npmjs.org/package/gray-matter)  [![NPM total downloads](https://img.shields.io/npm/dt/gray-matter.svg?style=flat)](https://npmjs.org/package/gray-matter) [![Linux Build Status](https://img.shields.io/travis/jonschlinkert/gray-matter.svg?style=flat&label=Travis)](https://travis-ci.org/jonschlinkert/gray-matter)

> Parse front-matter from a string or file. Fast, reliable and easy to use. Parses YAML front matter by default, but also has support for YAML, JSON, TOML or Coffee Front-Matter, with options to set custom delimiters. Used by metalsmith, assemble, verb and many other projects.

## Install

Install with [npm](https://www.npmjs.com/):

```sh
$ npm install --save gray-matter
```

See the [benchmarks](#benchmarks). gray-matter is 20-30x faster than [front-matter](https://github.com/jxson/front-matter).

## Highlights

* Reliable and battle-tested by [metalsmith](https://github.com/segmentio/metalsmith), [assemble](https://github.com/assemble/assemble), [verb](https://github.com/assemble/verb), and many other projects!
* Extracts and parses:
  - [YAML](http://github.com/nodeca/js-yaml)
  - [JSON](http://en.wikipedia.org/wiki/Json)
  - [TOML](http://github.com/mojombo/toml)
  - [CoffeeScript](http://coffeescript.org) when `options.eval` is set to `true`
  - [CSON](https://github.com/bevry/cson) when `options.eval` is set to `true`
  - JavaScript: when `options.eval` is set to `true`
* Easy to add additional parsers! pull requests welcome!

## Usage

```js
var matter = require('gray-matter');
matter('---\ntitle: Front Matter\n---\nThis is content.');
```

Returns:

```js
{ 
  orig: '---\ntitle: Front Matter\n---\nThis is content.',
  data: { title: 'Front Matter' },
  content: '\nThis is content.' 
}
```

That's it! Just pass a string and gray-matter returns an object.

***

## API

### [matter](index.js#L30)

Parses a `string` of front-matter with the given `options`, and returns an object.

**Example**

```js
matter('---\ntitle: foo\n---\nbar');
//=> {data: {title: 'foo'}, content: 'bar', orig: '---\ntitle: foo\n---\nbar'}
```

**Params**

* `string` **{String}**: The string to parse.
* `options` **{Object}**  

- `delims` **{Array}**: Custom delimiters formatted as an array. The default is `['---', '---']`.
- `parser` **{Function}**: Parser function to use. [js-yaml] is the default.
* `returns` **{Object}**: Valid JSON

### [.read](index.js#L136)

Read a file and parse front matter. Returns the same object as `matter()`.

**Example**

```js
matter.read('home.md');
```

**Params**

* `fp` **{String}**: file path of the file to read.
* `options` **{Object}**: Options to pass to gray-matter.
* `returns` **{Object}**

### [.stringify](index.js#L167)

Stringify an object to front-matter-formatted YAML, and concatenate it to the given string.

Results in:

**Examples**

```js
matter.stringify('foo bar baz', {title: 'Home'});
```

```yaml
---
title: Home
---
foo bar baz
```

**Params**

* `str` **{String}**: The content string to append to stringified front-matter.
* `data` **{Object}**: Front matter to stringify.
* `options` **{Object}**: Options to pass to js-yaml
* `returns` **{String}**

## Options

> All methods exposed on the API accept an options object passed as the last argument

## options.parser

Type: `Function`

Default: `undefined`

Pass a custom parser on the options. This is useful if you need to, for example, define custom schemas for [js-yaml].

**Example**

```js
matter(str, {
  parser: require('js-yaml').safeLoad
});
```

## options.eval

Type: `Boolean`

Default: `false`

Evaluate coffee-script, CSON or JavaScript in front-matter. If you aren't aware of the dangers, google is your friend.

However, if you are aware and you only use front-matter on, say, blog posts for a static site... this feature can be pretty useful.

## options.lang

Type: `String`

Default: `yaml`

The parser to use on the extracted front matter.

YAML is parsed by default, and the languages listed below are parsed automatically if the language is specified after the first delimiter (e.g. `---`).

Valid languages are:

* `yaml`
* `json`
* `coffee`
* `cson`
* `toml`
* `js`|`javascript`

**Example**

To parse coffee front matter, you would define it as follows:

```js
---coffee
title: 'coffee functions'
user: 'jonschlinkert'
fn:
  reverse = (src) ->
    src.split('').reverse().join('')
---

<%= description %>
<%= reverse(user) %>
```

## options.delims

Type: `String`

Default: `---`

Open and close delimiters can be passed in as an array of strings.

**Example:**

```js
// format delims as a string
matter.read('file.md', {delims: '~~~'});
// or an array (open/close)
matter.read('file.md', {delims: ['~~~', '~~~']});
```

would parse:

<pre>

```
title: Home
```

This is the  page.
</pre>

## Example usage

Given we have a page, `abc.html`, containing:

```html
---
title: YAML Front matter
description: This is a page
---
<h1></h1>
```

then running the following in the command line:

```js
matter('abc.html');
```

returns

```json
{
  "data": {
    "title": "YAML Front matter",
    "description": "This is a page"
  },
  "content": "<h1></h1>",
  "original": "---\ntitle: YAML Front matter\n---\n<h1></h1>"
}
```

## Benchmarks

**Benchmarks for building the [bootstrap-blog](https://github.com/twbs/bootstrap-blog/tree/gh-pages/_posts)**

gray-matter would process all markdown posts in the [bootstrap-blog](https://github.com/twbs/bootstrap-blog/tree/gh-pages/_posts) **20 times** before the [front-matter](https://github.com/jxson/front-matter) library finished processing it once.

```
front-matter.js x 271 ops/sec ±2.68% (80 runs sampled)
gray-matter.js x 4,294 ops/sec ±0.86% (91 runs sampled)
```

**Misc**

gray-matter is 12-20x faster than [front-matter](https://github.com/jxson/front-matter) when content or front matter actually exist.

```bash
#1: complex
  front-matter x 338 ops/sec ±1.60% (85 runs sampled)
  gray-matter x 10,608 ops/sec ±1.97% (86 runs sampled)

#2: empty
  front-matter x 5,755,004 ops/sec ±0.88% (94 runs sampled)
  gray-matter x 15,157,998 ops/sec ±0.81% (95 runs sampled)

#3: matter
  front-matter x 10,256 ops/sec ±2.18% (92 runs sampled)
  gray-matter x 202,026 ops/sec ±0.71% (93 runs sampled)

#4: no-content
  front-matter x 10,136 ops/sec ±2.00% (91 runs sampled)
  gray-matter x 206,548 ops/sec ±1.16% (94 runs sampled)

#5: no-matter
  front-matter x 3,540,817 ops/sec ±0.68% (95 runs sampled)
  gray-matter x 7,959,809 ops/sec ±0.73% (91 runs sampled)
```

## Why?

> Why another YAML Front Matter library?

Because other libraries we tried failed to meet our requirements with [Assemble](https://github.com/assemble/assemble). Some most of the libraries met most of the requirements, but _none had all of them_. Here are the most important:

* Be usable, if not simple
* Allow custom delimiters
* Use a dependable and well-supported library for parsing YAML and other languages
* Don't fail when no content exists
* Don't fail when no front matter exists
* Have no problem reading YAML files directly
* Have no problem with complex content, including **non-front-matter** fenced code blocks that contain examples of YAML front matter. Other parsers fail on this.
* Should return an object with three properties:
  - `data`: the parsed YAML front matter, as a JSON object
  - `content`: the contents as a string, without the front matter
  - `orig`: the "original" content

## About

### Related projects

* [assemble](https://www.npmjs.com/package/assemble): Get the rocks out of your socks! Assemble makes you fast at creating web projects… [more](https://github.com/assemble/assemble) | [homepage](https://github.com/assemble/assemble "Get the rocks out of your socks! Assemble makes you fast at creating web projects. Assemble is used by thousands of projects for rapid prototyping, creating themes, scaffolds, boilerplates, e-books, UI components, API documentation, blogs, building websit")
* [metalsmith](https://www.npmjs.com/package/metalsmith): An extremely simple, pluggable static site generator. | [homepage](https://github.com/segmentio/metalsmith#readme "An extremely simple, pluggable static site generator.")
* [verb](https://www.npmjs.com/package/verb): Documentation generator for GitHub projects. Verb is extremely powerful, easy to use, and is used… [more](https://github.com/verbose/verb) | [homepage](https://github.com/verbose/verb "Documentation generator for GitHub projects. Verb is extremely powerful, easy to use, and is used on hundreds of projects of all sizes to generate everything from API docs to readmes.")

### Contributing

Pull requests and stars are always welcome. For bugs and feature requests, [please create an issue](../../issues/new).

### Contributors

| **Commits** | **Contributor**<br/> | 
| --- | --- |
| 121 | [jonschlinkert](https://github.com/jonschlinkert) |
| 7 | [RobLoach](https://github.com/RobLoach) |
| 2 | [doowb](https://github.com/doowb) |
| 2 | [moozzyk](https://github.com/moozzyk) |
| 1 | [Ajedi32](https://github.com/Ajedi32) |
| 1 | [ianstormtaylor](https://github.com/ianstormtaylor) |

### Building docs

_(This document was generated by [verb-generate-readme](https://github.com/verbose/verb-generate-readme) (a [verb](https://github.com/verbose/verb) generator), please don't edit the readme directly. Any changes to the readme must be made in [.verb.md](.verb.md).)_

To generate the readme and API documentation with [verb](https://github.com/verbose/verb):

```sh
$ npm install -g verb verb-generate-readme && verb
```

### Running tests

Install dev dependencies:

```sh
$ npm install -d && npm test
```

### Author

**Jon Schlinkert**

* [github/jonschlinkert](https://github.com/jonschlinkert)
* [twitter/jonschlinkert](http://twitter.com/jonschlinkert)

### License

Copyright © 2016, [Jon Schlinkert](https://github.com/jonschlinkert).
Released under the [MIT license](https://github.com/jonschlinkert/gray-matter/blob/master/LICENSE).

***

_This file was generated by [verb-generate-readme](https://github.com/verbose/verb-generate-readme), v0.2.0, on October 25, 2016._