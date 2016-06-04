function hxShare2011(title) {
  var context = (title && HX.$S.trim(title)!='')?title:document.title;
  var id = 'page';
  context = context.replace(/%/g,'£¥');
  var url = document.location.href;
  var cu = url;
  var brower = navigator.userAgent.toLowerCase();
  var strongIE = (HX.$B.IE && (brower.indexOf('se')!=-1 || brower.indexOf('maxthon')!=-1 || brower.indexOf('tencent')!=-1));
  url = (url.indexOf('?')!=-1)?(url+'&fromweb=share'):(url+'?fromweb=share');
  var left = 0;
  if(HX.$(id)) {
	  var obj = HX.$(id);
	  left = (HX.$E.left(obj)<=21)?0:(HX.$E.left(obj)-21);
	  if(left!=0 && HX.$B.IE && !strongIE) left-=2;
	  }
  var fragment = document.createDocumentFragment();
  var con = HX.$E.create('div',fragment);
  con.id = 'hx_share20110711';
  con.style.left = left+'px';
  var panel = HX.$E.create('div',con);
  panel.id = 'share_panel0711';
  panel.style.display = 'none';
  panel.style.width = '1px';
  var panel_con = HX.$E.create('div',panel);
  var opt = HX.$E.create('div',con);
  opt.id = 'share_opt0711';
  panel_con.innerHTML = '<iframe src="" id="shareFrame0711" style="display:none;"></iframe><ul class="bdshare_t bds_tools get-codes-bdshare" id="bdshare"><li><a class="bds_thx thx hx0711" href="#">ºÍÑ¶Î¢²©</a></li><li><a class="bds_tsohu shwb sh0711" href="#">ËÑºüÎ¢²©</a></li><li><a class="bds_tsina sina0711" href="#">ÐÂÀËÎ¢²©</a></li><li><a class="bds_renren rr0711" href="#">ÈËÈËÍø</a></li><li><a class="bds_tqq qqwb0711" href="#">ÌÚÑ¶Î¢²©</a></li><li><a class="bds_douban db db0711" href="#">¶¹°êÍø</a></li><li><a class="bds_qzone qqkj0711" href="#">QQ¿Õ¼ä</a></li><li><a class="bds_more" href="#">¸ü¶à·ÖÏí</a></li></ul>';
  var optSpan = HX.$E.create('span',opt);
  document.body.appendChild(fragment);
   var fx = new HX.Animator({
           onComplete:function() {
			   if(parseInt(panel.style.width)==190) optSpan.className = 'open';
			   else {
				   optSpan.className = '';
				   panel.style.display = 'none';
			      }
			   }
        });										  
  fx.addSubject(new NumSubject(panel, 'width',1,190));
  HX.$V.bind(opt,'mouseover',function(){
	  panel.style.display = '';
	  if(parseInt(panel.style.width)==1) 
	  fx.toggle();
  });
  HX.$V.bind(opt,'click',function(){
	  panel.style.display = '';
	  if(panel.style.width && parseInt(panel.style.width)==1) 
	  fx.toggle();
  });  
  HX.$V.bind(document,'mouseover',function(e){
	var e = e?e:window.event;
	var s = e.srcElement || e.target;
	if((s.parentNode && s.parentNode.id && s.parentNode.id=='hx_share20110711') || (s.parentNode && s.parentNode.parentNode && s.parentNode.parentNode.parentNode && s.parentNode.parentNode.parentNode.parentNode && s.parentNode.parentNode.parentNode.parentNode.id && s.parentNode.parentNode.parentNode.parentNode.id=='share_panel0711')) return;
	else if(panel.style.width && parseInt(panel.style.width)==190) fx.toggle();
  });
  HX.$V.bind(window,'resize',function(){
	  var left = 0;
      if(HX.$(id)) {
	    var obj = HX.$(id);
	    left = (HX.$E.left(obj)<=21)?0:(HX.$E.left(obj)-21);
		if(left!=0 && HX.$B.IE && !strongIE) left-=2;
	    }
	  con.style.left = left+'px';
	});

	addBaiduShare();
}

function hxShareCout2011(url,id) {
	  HX.$('shareFrame0711').src = 'http://law.hexun.com/tempfolder/c.jsp?url='+url+'&from='+id;
	}


function addBaiduShare(){
	var headNode = document.getElementsByTagName('head')[0];
	var sNode1= document.createElement('script');
	sNode1.setAttribute('id','bdshare_js');
	sNode1.setAttribute('data','type=tools&amp;uid=657298');
	headNode.appendChild(sNode1);
	var sNode2= document.createElement('script');
	sNode2.setAttribute('id','bdshell_js');
	sNode2.setAttribute('src','http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=' + new Date().getHours());
	headNode.appendChild(sNode2);
}