<template>
  <div>
    <div class="-mob-share-ui-button -mob-share-open">分享</div>
      <div class="-mob-share-ui" style="display: none">
        <ul class="-mob-share-list">
            <li class="-mob-share-weibo"><p>新浪微博</p></li>
            <li class="-mob-share-qzone"><p>QQ空间</p></li>
            <li class="-mob-share-qq"><p>QQ好友</p></li>
            <li class="-mob-share-douban"><p>豆瓣</p></li>
            <li class="-mob-share-facebook"><p>Facebook</p></li>
            <li class="-mob-share-twitter"><p>Twitter</p></li>
        </ul>
        <div class="-mob-share-close">取消</div>
      </div>
      <div class="-mob-share-ui-bg"></div>
  </div>
</template>

<script>
export default {
  mounted () {
    const script = document.createElement('script')
    script.src = 'http://f1.webshare.mob.com/code/mob-share.js?appkey=2d1a37832f835'
    script.id = '-mob-share'

    document.body.append(script)
  }
}
</script>

