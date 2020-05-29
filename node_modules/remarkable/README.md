# remarkable

[![Build Status](https://travis-ci.org/jonschlinkert/remarkable.svg?branch=master)](https://travis-ci.org/jonschlinkert/remarkable)
[![NPM version](https://img.shields.io/npm/v/remarkable.svg)](https://www.npmjs.org/package/remarkable)
[![jsDelivr Hits](https://data.jsdelivr.com/v1/package/npm/remarkable/badge?style=rounded)](https://www.jsdelivr.com/package/npm/remarkable)
[![Coverage Status](https://img.shields.io/coveralls/jonschlinkert/remarkable.svg)](https://coveralls.io/r/jonschlinkert/remarkable?branch=dev)

> Markdown parser done right. Fast and easy to extend.

__[Live demo](http://jonschlinkert.github.io/remarkable/demo/)__

- Supports the [CommonMark](http://commonmark.org/) spec +
  [syntax extensions](#syntax-extensions) + sugar (URL autolinking, typographer).
- Configurable syntax! You can add new rules and even replace existing ones.
- [High speed](#benchmark)!
- [Community plugins](https://www.npmjs.org/browse/keyword/remarkable) on npm.


## Install

**node.js:**

```bash
npm install remarkable --save
```

**bower:**

```bash
bower install remarkable --save
```

**browser (CDN):**

- [jsDeliver CDN](http://www.jsdelivr.com/#!remarkable "jsDelivr CDN")
- [cdnjs](https://cdnjs.com/libraries/remarkable "cdnjs")


## Usage

```js
var Remarkable = require('remarkable');
var md = new Remarkable();

console.log(md.render('# Remarkable rulezz!'));
// => <h1>Remarkable rulezz!</h1>
```

If installed globally with `npm`:

```sh
cat myfile.md | remarkable
remarkable --file myfile.md

# get options
remarkable -h
```

## Documentation

See the [docs](docs/) directory for documentation on the following topics:

- [parser](docs/parser.md)
- [parsing_block](docs/parsing_block.md)
- [parsing_core](docs/parsing_core.md)
- [parsing_inline](docs/parsing_inline.md)
- [plugins](docs/plugins.md)
- [renderer](docs/renderer.md)

### Options

By default, remarkable is configured to be similar to GFM, but with HTML disabled.
This is easy to change if you prefer different settings.

There are two ways to define options.

#### constructor

Define options in the constructor:

```js
// Actual default values
var md = new Remarkable({
  html:         false,        // Enable HTML tags in source
  xhtmlOut:     false,        // Use '/' to close single tags (<br />)
  breaks:       false,        // Convert '\n' in paragraphs into <br>
  langPrefix:   'language-',  // CSS language prefix for fenced blocks
  linkify:      false,        // Autoconvert URL-like text to links

  // Enable some language-neutral replacement + quotes beautification
  typographer:  false,

  // Double + single quotes replacement pairs, when typographer enabled,
  // and smartquotes on. Set doubles to '«»' for Russian, '„“' for German.
  quotes: '“”‘’',

  // Highlighter function. Should return escaped HTML,
  // or '' if the source string is not changed
  highlight: function (/*str, lang*/) { return ''; }
});

console.log(md.render('# Remarkable rulezz!'));
// => <h1>Remarkable rulezz!</h1>
```

#### .set

Or define options via the `.set()` method:

```js
var Remarkable = require('remarkable');
var md = new Remarkable();

md.set({
  html: true,
  breaks: true
});
```

**Note:** To achieve the best possible performance, don't modify a `Remarkable`
instance on the fly. If you need multiple configurations, create
multiple instances and initialize each with a configuration that is ideal for
that instance.


### Presets

Remarkable offers some "presets" as a convenience to quickly enable/disable
active syntax rules and options for common use cases.

#### commonmark

Enable strict [CommonMark](http://commonmark.org/) mode with the `commonmark` preset:

```js
var Remarkable = require('remarkable');
var md = new Remarkable('commonmark');
```

#### full

Enable all available rules (but still with default options, if not set):

```js
var Remarkable = require('remarkable');
var md = new Remarkable('full');

// Or with options:
var md = new Remarkable('full', {
  html: true,
  linkify: true,
  typographer: true
});
```


### Syntax highlighting

Apply syntax highlighting to fenced code blocks with the `highlight` option:

```js
var Remarkable = require('remarkable');
var hljs       = require('highlight.js') // https://highlightjs.org/

// Actual default values
var md = new Remarkable({
  highlight: function (str, lang) {
    if (lang && hljs.getLanguage(lang)) {
      try {
        return hljs.highlight(lang, str).value;
      } catch (err) {}
    }

    try {
      return hljs.highlightAuto(str).value;
    } catch (err) {}

    return ''; // use external default escaping
  }
});
```


### Syntax extensions

Enabled by default:

- [Footnotes](http://johnmacfarlane.net/pandoc/README.html#footnotes)
- [Tables](https://help.github.com/articles/github-flavored-markdown/#tables) (GFM)
- [\<del>](https://help.github.com/articles/github-flavored-markdown/#strikethrough)
  (GFM strikethrough) - `~~deleted text~~`

Disabled by default:

- [\<sup>](http://johnmacfarlane.net/pandoc/README.html#superscripts-and-subscripts) - `19^th^`
- [\<sub>](http://johnmacfarlane.net/pandoc/README.html#superscripts-and-subscripts) - `H~2~O`
- [abbreviations](https://michelf.ca/projects/php-markdown/extra/#abbr)
- __\<ins>__ - `++inserted text++` (experimental)
- __\<mark>__ - `==marked text==` (experimental)

**HEADS UP!**: Experimental extensions can be changed later for something like [Critic Markup](http://criticmarkup.com/), but you will still be able to use old-style rules via external plugins if you prefer.


### Manage rules

```js
var md = new Remarkable();
md.inline.ruler.enable([ 'ins', 'mark' ]);
md.block.ruler.disable([ 'table', 'footnote' ]);

// Enable everything
md = new Remarkable('full', {
  html: true,
  linkify: true,
  typographer: true,
});

//
// Manually enable rules, disabled by default:
//
var md = new Remarkable();
md.core.ruler.enable([
  'abbr'
]);
md.block.ruler.enable([
  'footnote',
  'deflist'
]);
md.inline.ruler.enable([
  'footnote_inline',
  'ins',
  'mark',
  'sub',
  'sup'
]);
```


### Typographer

Although full-weight typographical replacements are language specific, `remarkable`
provides coverage for the most common and universal use cases:

```js
var Remarkable = require('remarkable');
var md = new Remarkable({
  typographer: true,
  quotes: '“”‘’'
});

// Disable rules at all:
md.core.ruler.disable([ 'replacements', 'smartquotes' ]);

// Actual default replacements:
//
// '' → ‘’
// "" → “”. Set '«»' for Russian, '„“' for German, empty to disable
// (c) (C) → ©
// (tm) (TM) → ™
// (r) (R) → ®
// +- → ±
// (p) (P) -> §
// ... → … (also ?.... → ?.., !.... → !..)
// ???????? → ???, !!!!! → !!!, `,,` → `,`
// -- → &ndash;, --- → &mdash;
//
```

Of course, you can also add your own rules or replace the defaults with something
more advanced or specific to your language.


### Plugins

Easily load plugins with the `.use()` method:

```js
var md = new Remarkable();

md.use(plugin1)
  .use(plugin2, opts)
  .use(plugin3);
```

Please refer to the [plugin documentation](docs/plugins.md) to create your own
plugins.

## References / Thanks

Big thanks to [John MacFarlane](https://github.com/jgm) for his work on the
CommonMark spec and reference implementations. His work saved us a lot of time
during this project's development.

**Related Links:**

1. https://github.com/jgm/CommonMark - reference CommonMark implementations in C & JS,
   also contains latest spec & online demo.
2. http://talk.commonmark.org - CommonMark forum, good place to collaborate
   developers' efforts.


## Development / Modification

[Parser](docs/parser.md) consists of several responsibility chains filled with
rules. You can reconfigure any of them as you wish. [Renderer](docs/renderer.md) also
can be modified and extended. See source code to understand details. Pay
attention to these properties:

```js
Remarkable.core
Remarkable.core.ruler
Remarkable.block
Remarkable.block.ruler
Remarkable.inline
Remarkable.inline.ruler
Remarkable.renderer
Remarkable.renderer.rules
```

## Benchmark

Here is result of CommonMark spec parse at Core i5 2.4 GHz (i5-4258U):

```bash
$ benchmark/benchmark.js spec
Selected samples: (1 of 27)
 > spec

Sample: spec.txt (110610 bytes)
 > commonmark-reference x 40.42 ops/sec ±4.07% (51 runs sampled)
 > current x 74.99 ops/sec ±4.69% (67 runs sampled)
 > current-commonmark x 93.76 ops/sec ±1.23% (79 runs sampled)
 > marked-0.3.2 x 22.92 ops/sec ±0.79% (41 runs sampled)
```

As you can see, `remarkable` doesn't pay with speed for its flexibility. Because
it's written in monomorphic style and uses JIT inline caches effectively.


## Authors

- Jon Schlinkert [github/jonschlinkert](https://github.com/jonschlinkert)
- Alex Kocharin [github/rlidwka](https://github.com/rlidwka)
- Vitaly Puzrin [github/puzrin](https://github.com/puzrin)


## License

[MIT](https://github.com/jonschlinkert/remarkable/blob/master/LICENSE)
