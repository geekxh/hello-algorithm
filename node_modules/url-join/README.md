Join all arguments together and normalize the resulting url.

## Install

~~~
npm install url-join
~~~

## Usage

~~~javascript
var urljoin = require('url-join');

var fullUrl = urljoin('http://www.google.com', 'a', '/b/cd', '?foo=123');

console.log(fullUrl);

\\will print:

'http://www.google.com/a/b/cd?foo=123'
~~~

This works similar to [path.join](http://nodejs.org/api/path.html#path_path_join_path1_path2) but you shouldn't use ```path.join``` for urls since it will work different depending of the operative systems but also doesn't work for some cases.

## License

MIT