1.7.0 / 2016-09-27
------------------

- Special thanks to [lemoinem](https://github.com/lemoinem) for adding much needed, and well-written [documentation](docs/)!
- Footnotes are now enabled by default
- Adds support for "passthrough classes", thanks to [matthewmueller](https://github.com/matthewmueller)
- Outlaws data: URLs by default, thanks to [spicyj](https://github.com/spicyj)
- Improves whitespace trimming performance, thanks to [dpkirchner](https://github.com/dpkirchner)
- Image alts are now properly unescaped, thanks to [adam187](https://github.com/adam187)

1.6.2 / 2016-02-04
------------------

- Add support for specifying link target
- StateBlock.getLines should only read from the current line
- Fix missing space after shortcut links
- Fix "Manage rules" sample code in README.md
- Fix encoding of non-ASCII text (fixes #141)
- Option to not alter links provided for href
- Add Node.js v0.12 and v4 to Travis config
- Define bin script for npm
- Fix "Manage rules" sample code in README.md
- Fix typo in github proj url in Makefile
- Fix build by ignoring the demo directory in eslint

1.6.1 / 2015-11-19
------------------

- npm now installs a script so you can access remarkable from the command
line.

1.5.0 / 2014-12-12
------------------

- Added Demo sync scroll, to show how lines mapping can be used.
- Improved IE8 support. Now you need only es5-shim, without es5-sham.
- Fixed errors on refs/attrs/footnoted with special names like `__proto__`.
- Renamed Ruler() private properties, to show those should not be accessed
  directly.
- Fixed Makefile OSX compatibility.


1.4.2 / 2014-11-29
------------------

- Added footnotes support.
- Added definitions lists support.
- Added `fence_custom` renderer extension to easy override
  named fenced blocks (useful for diagrams and so on).
- Exposed `./common/utils` to simplify custom renderers.


1.4.1 / 2014-11-13
------------------

- Moved links decode/encode from renderer to parser.
- Added missed validator call for scoped urls in links.
- Handle exceptions in `decoreURI` (regression).


1.4.0 / 2014-11-09
------------------

- Added `core` chain, to better organize code and improve pluggability.
- Added `renderInline()` and `parseInline()` methods.
- Added abbreviations support.
- Fixed problem with tables, having single column.
- Fixed rendered rules rewrite for inline tags.
- Changed internal api (ruler, inline, block classes).
- Removed typographer chain (rules moved to `core`).
- Removed all typographer options. Quote chars defs moved to `options.quotes`.


1.3.0 / 2014-10-29
------------------

- Fixed problem with minified & mangled browser version.
- Changed ruler API.


1.2.2 / 2014-10-29
------------------

- Fixed regression from 1.2.1 for data without tailing `\n`.
- Fixed blockquote line ranges.
- Added subscript/superscript support.
- Updated CommonMark spec and updated implementation.
- Other minor changes.


1.2.1 / 2014-10-28
------------------

- Fixed speed degradation when linkifier enabled.
- Added coverage reports.
- Added debug view to demo (show internal representation)
- Other minor optimizations and cleanup.


1.2.0 / 2014-10-26
------------------

- Added `<ins>` rule.
- Added `<mark>` rule.
- Added presets support (default, commonmark, full).
- Exposed `.configure()` method to load rules & options config with one command.
- Moved html escaping to renderer.


1.1.2 / 2014-10-23
------------------

- Fixed speed regression.
- Use base64 encoding for permalinks (workaround for github).
- Improved default link validator.
- Updated cache storage logic for inline parser.


1.1.1 / 2014-10-22
------------------

- Fixed `Ruler.after()` method.
- Fixed linkification.
- Simplified loose/tight rendering.
- Refactored inline parser. No close coupled code in rules anymore.


1.1.0 / 2014-10-20
------------------

- Code refactoring, bugfixes, API update.
- Added source lines info to block nodes.


1.0.0 / 2014-10-16
------------------

- First release.
