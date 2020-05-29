(function (Prism) {

	var stringPattern = /(?:"(?:""|[^"])*"(?!")|'(?:''|[^'])*'(?!'))/.source;

	var number = /\b(?:\d[\da-f]*x|\d+(?:\.\d+)?(?:e[+-]?\d+)?)\b/i;
	var numericConstant = {
		pattern: RegExp(stringPattern + '[bx]'),
		alias: 'number'
	};

	var step = {
		pattern: /(^|\s+)(?:proc\s+\w+|quit|run|data(?!\=))\b/i,
		alias: 'keyword',
		lookbehind: true
	};

	var comment = [
		/\/\*[\s\S]*?\*\//,
		{
			pattern: /(^\s*|;\s*)\*[^;]*;/m,
			lookbehind: true
		}
	];

	var string = {
		pattern: RegExp(stringPattern),
		greedy: true
	};

	var punctuation = /[$%@.(){}\[\];,\\]/;

	var func = {
		pattern: /%?\w+(?=\()/,
		alias: 'keyword'
	};

	var args = {
		'function': func,
		'arg-value': {
			pattern: /(\s*=\s*)[A-Z\.]+/i,
			lookbehind: true
		},
		'operator': /=/,
		'macro-variable': {
			pattern: /&[^\.]*\./i,
			alias: 'string'
		},
		'arg': {
			pattern: /[A-Z]+/i,
			alias: 'keyword'
		},
		'number': number,
		'numeric-constant': numericConstant,
		'punctuation': punctuation,
		'string': string
	};

	var format = {
		pattern: /\b(?:format|put)\b=?[\w'$.]+/im,
		inside: {
			'keyword': /^(?:format|put)(?=\=)/i,
			'equals': /=/,
			'format': {
				pattern: /(?:\w|\$\d)+\.\d?/i,
				alias: 'number'
			}
		}
	};

	var altformat = {
		pattern: /\b(?:format|put)\s+[\w']+(?:\s+[$.\w]+)+(?=;)/i,
		inside: {
			'keyword': /^(?:format|put)/i,
			'format': {
				pattern: /[\w$]+\.\d?/,
				alias: 'number'
			}
		}
	};

	var globalStatements = {
		pattern: /((?:^|[\s])=?)(?:catname|checkpoint execute_always|dm|endsas|filename|footnote|%include|libname|%list|lock|missing|options|page|resetline|%run|sasfile|skip|sysecho|title\d?)\b/i,
		lookbehind: true,
		alias: 'keyword'
	};

	var submitStatement = {
		pattern: /(^|\s)(?:submit(?:\s+(?:load|parseonly|norun))?|endsubmit)\b/i,
		lookbehind: true,
		alias: 'keyword'
	};

	var actionSets = /accessControl|cdm|aggregation|aStore|ruleMining|audio|autotune|bayesianNetClassifier|bioMedImage|boolRule|builtins|cardinality|sccasl|clustering|copula|countreg|dataDiscovery|dataPreprocess|dataSciencePilot|dataStep|decisionTree|deepLearn|deepNeural|varReduce|simSystem|ds2|deduplication|ecm|entityRes|espCluster|explainModel|factmac|fastKnn|fcmpact|fedSql|freqTab|gam|gleam|graphSemiSupLearn|gVarCluster|hiddenMarkovModel|hyperGroup|image|iml|ica|kernalPca|langModel|ldaTopic|sparseML|mlTools|mixed|modelPublishing|mbc|network|optNetwork|neuralNet|nonlinear|nmf|nonParametricBayes|optimization|panel|pls|percentile|pca|phreg|qkb|qlim|quantreg|recommend|tsReconcile|deepRnn|regression|reinforcementLearn|robustPca|sampling|sparkEmbeddedProcess|search(?:Analytics)?|sentimentAnalysis|sequence|configuration|session(?:Prop)?|severity|simple|smartData|sandwich|spatialreg|stabilityMonitoring|spc|loadStreams|svDataDescription|svm|table|conditionalRandomFields|text(?:Rule(?:Develop|Score)|Mining|Parse|Topic|Util|Filters|Frequency)|tsInfo|timeData|transpose|uniTimeSeries/.source;

	var casActions = {
		pattern: RegExp(/(^|\s)(?:action\s+)?(?:<act>)\.[a-z]+\b[^;]+/.source.replace(/<act>/g, function () { return actionSets; }), 'i'),
		lookbehind: true,
		inside: {
			'keyword': RegExp(/(?:<act>)\.[a-z]+\b/.source.replace(/<act>/g, function () { return actionSets; }), 'i'),
			'action': {
				pattern: /(?:action)/i,
				alias: 'keyword'
			},
			'function': func,
			'arg-value': args['arg-value'],
			'operator': args.operator,
			'comment': comment,
			'argument': args.arg,
			'number': number,
			'numeric-constant': numericConstant,
			'punctuation': punctuation,
			'string': string
		}
	};

	var keywords = {
		pattern: /((?:^|\s)=?)(?:after|analysis|and|array|barchart|barwidth|begingraph|by|call|cas|cbarline|cfill|class(?:lev)?|close|column|computed?|contains|continue|data(?=\=)|define|delete|describe|document|do\s+over|do|dol|drop|dul|end(?:source|comp)?|entryTitle|else|eval(?:uate)?|exec(?:ute)?|exit|fill(?:attrs)?|file(?:name)?|flist|fnc|function(?:list)?|goto|global|group(?:by)?|headline|headskip|histogram|if|infile|keep|keylabel|keyword|label|layout|leave|legendlabel|length|libname|loadactionset|merge|midpoints|name|noobs|nowd|_?null_|ods|options|or|otherwise|out(?:put)?|over(?:lay)?|plot|put|print|raise|ranexp|rannor|rbreak|retain|return|select|set|session|sessref|source|statgraph|sum|summarize|table|temp|terminate|then\s+do|then|title\d?|to|var|when|where|xaxisopts|yaxisopts|y2axisopts)\b/i,
		lookbehind: true,
	};

	Prism.languages.sas = {
		'datalines': {
			pattern: /^(\s*)(?:(?:data)?lines|cards);[\s\S]+?^\s*;/im,
			lookbehind: true,
			alias: 'string',
			inside: {
				'keyword': {
					pattern: /^(?:(?:data)?lines|cards)/i
				},
				'punctuation': /;/
			}
		},

		'proc-sql': {
			pattern: /(^proc\s+(?:fed)?sql(?:\s+[\w|=]+)?;)[\s\S]+?(?=^(?:proc\s+\w+|quit|run|data);|(?![\s\S]))/im,
			lookbehind: true,
			inside: {
				'sql': {
					pattern: RegExp(/^[ \t]*(?:select|alter\s+table|(?:create|describe|drop)\s+(?:index|table(?:\s+constraints)?|view)|create\s+unique\s+index|insert\s+into|update)(?:<str>|[^;"'])+;/.source.replace(/<str>/g, function () { return stringPattern; }), 'im'),
					alias: 'language-sql',
					inside: Prism.languages.sql
				},
				'global-statements': globalStatements,
				'sql-statements': {
					pattern: /(^|\s)(?:disconnect\s+from|exec(?:ute)?|begin|commit|rollback|reset|validate)\b/i,
					lookbehind: true,
					alias: 'keyword'
				},
				'number': number,
				'numeric-constant': numericConstant,
				'punctuation': punctuation,
				'string': string
			}
		},

		'proc-groovy': {
			pattern: /(^proc\s+groovy(?:\s+[\w|=]+)?;)(?:\s*submit)[\s\S]+?(?=^(?:proc\s+\w+|quit|run|data);|(?![\s\S]))/im,
			lookbehind: true,
			inside: {
				'groovy': {
					pattern: RegExp(/(^[ \t]*submit(?:\s+(?:load|parseonly|norun))?)(?:<str>|[^"'])+?(?=endsubmit;)/.source.replace(/<str>/g, function () { return stringPattern; }), 'im'),
					lookbehind: true,
					alias: 'language-groovy',
					inside: Prism.languages.groovy
				},
				'submit-statement': submitStatement,
				'global-statements': globalStatements,
				'number': number,
				'numeric-constant': numericConstant,
				'punctuation': punctuation,
				'string': string
			}
		},

		'proc-lua': {
			pattern: /(^proc\s+lua(?:\s+[\w|=]+)?;)(?:\s*submit)[\s\S]+?(?=^(?:proc\s+\w+|quit|run|data);|(?![\s\S]))/im,
			lookbehind: true,
			inside: {
				'lua': {
					pattern: RegExp(/(^[ \t]*submit(?:\s+(?:load|parseonly|norun))?)(?:<str>|[^"'])+?(?=endsubmit;)/.source.replace(/<str>/g, function () { return stringPattern; }), 'im'),
					lookbehind: true,
					alias: 'language-lua',
					inside: Prism.languages.lua
				},
				'submit-statement': submitStatement,
				'global-statements': globalStatements,
				'number': number,
				'numeric-constant': numericConstant,
				'punctuation': punctuation,
				'string': string
			}
		},

		'proc-cas': {
			pattern: /(^proc\s+cas(?:\s+[\w|=]+)?;)[\s\S]+?(?=^(?:proc\s+\w+|quit|data);|(?![\s\S]))/im,
			lookbehind: true,
			inside: {
				'statement-var': {
					pattern: /((?:^|\s)=?)saveresult\s+[^;]+/im,
					lookbehind: true,
					inside: {
						'statement': {
							pattern: /^saveresult\s+\S+/i,
							inside: {
								keyword: /^(?:saveresult)/i
							}
						},
						rest: args
					}
				},
				'cas-actions': casActions,
				'statement': {
					pattern: /((?:^|\s)=?)(?:default|(?:un)?set|on|output|upload)[^;]+/im,
					lookbehind: true,
					inside: args
				},
				'step': step,
				'keyword': keywords,
				'function': func,
				'comment': comment,
				'format': format,
				'altformat': altformat,
				'global-statements': globalStatements,
				'number': number,
				'numeric-constant': numericConstant,
				'punctuation': punctuation,
				'string': string
			}
		},

		'proc-args': {
			pattern: RegExp(/(^proc\s+\w+\s+)(?!\s)(?:[^;"']|<str>)+;/.source.replace(/<str>/g, function () { return stringPattern; }), 'im'),
			lookbehind: true,
			inside: args
		},

		/*Special keywords within macros*/
		'macro-keyword': {
			pattern: /((?:^|\s)=?)%(?:ABORT|BQUOTE|BY|CMS|COPY|DISPLAY|DO|ELSE|END|EVAL|GLOBAL|GO|GOTO|IF|INC|INCLUDE|INDEX|INPUT|KTRIM|LENGTH|LET|LIST|LOCAL|NRBQUOTE|NRQUOTE|NRSTR|PUT|QKTRIM|QSCAN|QSUBSTR|QSYSFUNC|QUOTE|QUPCASE|RETURN|RUN|SCAN|STR|SUBSTR|SUPERQ|SYMDEL|SYMGLOBL|SYMLOCAL|SYMEXIST|SYSCALL|SYSEVALF|SYSEXEC|SYSFUNC|SYSGET|SYSRPUT|THEN|TO|TSO|UNQUOTE|UNTIL|UPCASE|WHILE|WINDOW)\b/i,
			lookbehind: true,
			alias: 'keyword'
		},
		'macro-declaration': {
			pattern: /^%macro[^;]+(?=;)/im,
			inside: {
				'keyword': /%macro/i,
			}
		},
		'macro-end': {
			pattern: /^%mend[^;]+(?=;)/im,
			inside: {
				'keyword': /%mend/i,
			}
		},
		/*%_zscore(headcir, _lhc, _mhc, _shc, headcz, headcpct, _Fheadcz); */
		'macro': {
			pattern: /%_\w+(?=\()/,
			alias: 'keyword'
		},
		'input': {
			pattern: /\binput\s+[-\w\s/*.$&]+;/i,
			inside: {
				'input': {
					alias: 'keyword',
					pattern: /^input/i,
				},
				'comment': comment,
				'number': number,
				'numeric-constant': numericConstant
			}
		},
		'options-args': {
			pattern: /(^options)[-'"|/\\<>*+=:()\w\s]*(?=;)/im,
			lookbehind: true,
			inside: args
		},
		'cas-actions': casActions,
		'comment': comment,
		'function': func,
		'format': format,
		'altformat': altformat,
		'numeric-constant': numericConstant,
		'datetime': {
			// '1jan2013'd, '9:25:19pm't, '18jan2003:9:27:05am'dt
			pattern: RegExp(stringPattern + '(?:dt?|t)'),
			alias: 'number'
		},
		'string': string,
		'step': step,
		'keyword': keywords,
		// In SAS Studio syntax highlighting, these operators are styled like keywords
		'operator-keyword': {
			pattern: /\b(?:eq|ne|gt|lt|ge|le|in|not)\b/i,
			alias: 'operator'
		},
		// Decimal (1.2e23), hexadecimal (0c1x)
		'number': number,
		'operator': /\*\*?|\|\|?|!!?|¦¦?|<[>=]?|>[<=]?|[-+\/=&]|[~¬^]=?/i,
		'punctuation': punctuation
	};

}(Prism));
