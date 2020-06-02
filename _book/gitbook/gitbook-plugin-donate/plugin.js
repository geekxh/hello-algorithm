require(['gitbook', 'jQuery'], function(gitbook, $) {
	var wechatURL;
	var alipayURL;
	var titleText;
	var buttonText;
	var wechatText;
	var alipayText;

	function insertDonateLink() {
    if ($('.gitbook-donate').length === 0 && wechatURL !== undefined && (wechatURL !== '' || alipayURL !== '')) {
			var html = [
        '<div class="gitbook-donate">',
        '<div>' + titleText + '</div>',
        '<button id="rewardButton" disable="enable" onclick="var qr = document.getElementById(\'QR\'); if (qr.style.display === \'none\') {qr.style.display=\'block\';} else {qr.style.display=\'none\'}">',
        '<span>' + buttonText + '</span>',
        '</button>',
        '<div id="QR" style="display: none;">'
      ];
			if (wechatURL !== '') {
				html = html.concat([
          '<div id="wechat" style="display: inline-block">',
					'<a href="' + wechatURL + '" class="fancybox" rel="group">',
          '<img id="wechat_qr" src="' + wechatURL + '" alt="WeChat Pay"/>',
					'</a>',
          '<p>' + wechatText + '</p>',
          '</div>'
        ]);
			}
			if (alipayURL !== '') {
				html = html.concat([
          '<div id="alipay" style="display: inline-block">',
					'<a href="' + alipayURL+ '" class="fancybox" rel="group">',
          '<img id="alipay_qr" src="' + alipayURL + '" alt="Alipay"/>',
					'</a>',
          '<p>' + alipayText + '</p>', '</div>'
        ]);
			}
			html = html.concat(['</div>', '</div>']);
			$('.page-inner section.normal:last').after(html.join(''));
		}
	}

	gitbook.events.bind('start', function(e, config) {
    wechatURL = config.donate.wechat || '';
		wechatText = config.donate.wechatText || '微信捐赠';
		alipayURL = config.donate.alipay || '';
		alipayText = config.donate.alipayText || '支付宝捐赠';
		titleText = config.donate.title || '';
		buttonText = config.donate.button || '赏';
		insertDonateLink();
	});

	gitbook.events.bind('page.change', function() {
		insertDonateLink();
	});
});
