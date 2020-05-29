#gitbook-plugin-toc
This plugin will add table of content to the page and provide navigation function inside a page.

Add `<!-- toc -->` to the markdown files. When you build the book, it will insert a table of content where you insert `<!-- toc -->`


`book.json` Config:


```
{
	"plugins": ["toc"],
	"pluginsConfig": {
		"toc": {
			"addClass": true,
			"className": "toc"
		}
	}
}
```

You can add this config to add a HTML ClassName to the TOC `ul` element
