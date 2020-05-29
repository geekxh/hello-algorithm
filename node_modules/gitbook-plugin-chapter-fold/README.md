#Chapter fold for GitBook
==============

Extended from the **expandable-chapters** plugin, and make a little modify. Thx for the original author.  
Tiny change to the expandable-chapters plugin from https://github.com/DomainDrivenArchitecture/ to use smaller arrows.  

### Anything Change?

Modify the feature of the chapter links, make the links could be clicked to fold or expand their content.  
And add a new feature in order to keep the last chapter which the user browses at last time.  

### How to use it?

Add it to your `book.json` configuration:

```
{
    plugins: ["chapter-fold"]
}
```

Install your plugins using:

```
$ gitbook install
```

### Configuration

There is _no_ configuration needed at the moment, can be left empty.

```
{
	"pluginsConfig": {
		"chapter-fold":{}
	}
}
```

