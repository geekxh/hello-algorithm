(function (Prism) {

	// CAREFUL!
	// The following patterns are concatenated, so the group referenced by a back reference is non-obvious!

	var strings = [
		// normal string
		// 1 capturing group
		/(["'])(?:\\[\s\S]|\$\([^)]+\)|`[^`]+`|(?!\1)[^\\])*\1/.source,

		// here doc
		// 1 capturing group
		/<<-?\s*(\w+?)\s*(?:\r?\n|\r)[\s\S]*?(?:\r?\n|\r)\2/.source,

		// here doc quoted
		// 2 capturing group
		/<<-?\s*(["'])(\w+)\3\s*(?:\r?\n|\r)[\s\S]*?(?:\r?\n|\r)\4/.source
	].join('|');

	Prism.languages['shell-session'] = {
		'info': {
			// foo@bar:~/files$ exit
			// foo@bar$ exit
			pattern: /^[^\r\n$#*!]+(?=[$#])/m,
			alias: 'punctuation',
			inside: {
				'path': {
					pattern: /(:)[\s\S]+/,
					lookbehind: true
				},
				'user': /^[^\s@:$#*!/\\]+@[^\s@:$#*!/\\]+(?=:|$)/,
				'punctuation': /:/
			}
		},
		'command': {
			pattern: RegExp(/[$#](?:[^\\\r\n'"<]|\\.|<<str>>)+/.source.replace(/<<str>>/g, function () { return strings; })),
			greedy: true,
			inside: {
				'bash': {
					pattern: /(^[$#]\s*)[\s\S]+/,
					lookbehind: true,
					alias: 'language-bash',
					inside: Prism.languages.bash
				},
				'shell-symbol': {
					pattern: /^[$#]/,
					alias: 'important'
				}
			}
		},
		'output': /.(?:.*(?:\r\n?|\n|.$))*/
	};

}(Prism));
