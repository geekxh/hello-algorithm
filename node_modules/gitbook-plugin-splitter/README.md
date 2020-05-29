gitbook-plugin-splitter
==============

![splitter](https://raw.githubusercontent.com/yoshidax/gitbook-plugin-splitter/master/gitbook-splitter-demo.gif)

this plug-in provides a vertical bar that divides the summary and main content.

[![NPM](https://nodei.co/npm/gitbook-plugin-splitter.png?downloads=true&downloadRank=true&stars=true)](https://nodei.co/npm/gitbook-plugin-splitter/)

### other feature

* It will save the position information of the splitter to sessionStorage.

### How to use it?

Add it to your `book.json` configuration:

```json
{
    "plugins": ["splitter"]
}
```

Install your plugins using:

```bash
$ gitbook install
```

### change logs

#### version 0.0.6 (2016-12-06T16:54:38)

* fix: Fixed not to do anything when the width of the screen size was 600px or less (#6) 
* fix: changed the position of the bar position from "localStorage" to "sessiongStorage" (#10)

#### version 0.0.6 (2015-11-06T16:54:38)

* supported Gitbook v3

#### version 0.0.5 (2015-11-06T16:54:38)

* fixed don't work toggle button of summary view display from Gitbook V2.5.x

This software is released under the MIT License, see LICENSE.txt.
