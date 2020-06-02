require(['gitbook', 'jQuery'], function(gitbook, $) {

  const TERMINAL_HOOK = '**[terminal]'

  var pluginConfig = {};
  var timeouts = {};

  function addCopyButton(wrapper) {
    wrapper.append(
        $('<i class="fa fa-clone t-copy"></i>')
            .click(function() {
              copyCommand($(this));
            })
    );
  }

  function addCopyTextarea() {

    /* Add also the text area that will allow to copy */
    $('body').append('<textarea id="code-textarea" />');
  }

  function copyCommand(button) {
    pre = button.parent();
    textarea = $('#code-textarea');
    textarea.val(pre.text());
    textarea.focus();
    textarea.select();
    document.execCommand('copy');
    pre.focus();
    updateCopyButton(button);
  }

  function initializePlugin(config) {
    pluginConfig = config.code;
  }

  function format_code_block(block) {
    /*
     * Add line numbers for multiline blocks.
     */
    code = block.children('code');
    lines = code.html().split('\n');

    if (lines[lines.length - 1] == '') {
      lines.splice(-1, 1);
    }

    if (lines.length > 1) {
      console.log(lines);
      lines = lines.map(line => '<span class="code-line">' + line + '</span>');
      console.log(lines);
      code.html(lines.join('\n'));
    }

    // Add wrapper to pre element
    wrapper = block.wrap('<div class="code-wrapper"></div>');

    if (pluginConfig.copyButtons) {
      addCopyButton(wrapper);
    }
  }

  function updateCopyButton(button) {
    id = button.attr('data-command');
    button.removeClass('fa-clone').addClass('fa-check');

    // Clear timeout
    if (id in timeouts) {
      clearTimeout(timeouts[id]);
    }
    timeouts[id] = window.setTimeout(function() {
      button.removeClass('fa-check').addClass('fa-clone');
    }, 1000);
  }

  gitbook.events.bind('start', function(e, config) {
    initializePlugin(config);

    if (pluginConfig.copyButtons) {
      addCopyTextarea();
    }
  });

  gitbook.events.bind('page.change', function() {
    $('pre').each(function() {
      format_code_block($(this));
    });
  });

});
