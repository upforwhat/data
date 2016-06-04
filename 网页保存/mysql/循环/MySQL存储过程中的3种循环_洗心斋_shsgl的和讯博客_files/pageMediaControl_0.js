/* 滑动效果 */
var slideEffect={
    isAnimate:false,
	getStyle:function(obj,css_name){
	    if(obj.currentStyle){ 
	        var css_value = obj.currentStyle[css_name]; 
	    } 
	    else if(window.getComputedStyle) {
	       var css_value = window.getComputedStyle(obj, null)[css_name];
	    }
	    return parseInt(css_value)?parseInt(css_value):0;
	},
    init:function(id_str,dir_str,timer,callback){
	    this.elem=document.getElementById(id_str);
		this.elem.style.display='block';
		this.elem.style.overflow='hidden';
		this.elem.height=this.elem.style.height?parseInt(this.elem.style.height):this.elem.offsetHeight-this.getStyle(this.elem,'borderTopWidth')-this.getStyle(this.elem,'borderBottomWidth')-this.getStyle(this.elem,'paddingTop')-this.getStyle(this.elem,'paddingBottom')
		this.elem.width=this.elem.style.width?parseInt(this.elem.style.width):this.elem.offsetWidth-this.getStyle(this.elem,'borderLeftWidth')-this.getStyle(this.elem,'borderRightWidth')-this.getStyle(this.elem,'paddingLeft')-this.getStyle(this.elem,'paddingRight');
		this.elem.style.width=this.elem.width+'px';
		this.elem.style.height=this.elem.height+'px';
		this.timer=timer?timer:40;
		this.rate=0.4;
		this.callback=callback;
		switch(dir_str){
		    case 'up':this.targetX=this.elem.width;this.targetY=0;this.math=Math.floor;break;
			case 'down':this.targetX=this.elem.width;this.targetY=this.elem.height;this.elem.style.height='0px';this.math=Math.ceil;break;
			case 'left':this.targetX=0;this.targetY=this.elem.height;this.math=Math.floor;break;
			case 'right':this.targetX=this.elem.width;this.targetY=this.elem.height;this.elem.style.width='0px';this.math=Math.ceil;break;
			case 'hide':this.targetX=0;this.targetY=0;this.math=Math.floor;break;
			case 'show':this.targetX=this.elem.width;this.targetY=this.elem.height;this.elem.style.width='0px';this.elem.style.height='0px';this.math=Math.ceil;break;
			default:alert('方向应为up、down、left、right、show或hide');return
		}
	},
	start:function(id_str,dir_str,timer,callback){
	    if(!this.isAnimate){
	        this.init(id_str,dir_str,timer,callback);		
		    var _this=this;
		    this.si=setInterval(function(){_this.animate()},this.timer);
		}
	},
	animate:function(){
	    this.isAnimate=true;
		this.tempX=parseInt(this.elem.style.width);
		this.elem.style.width=this.tempX+this.math((this.targetX-this.tempX)*this.rate)+'px';
		this.tempY=parseInt(this.elem.style.height);
		this.elem.style.height=this.tempY+this.math((this.targetY-this.tempY)*this.rate)+'px';
		if(this.tempX==this.targetX&&this.tempY==this.targetY){
		    clearInterval(this.si);
			this.targetX&&this.targetY?this.elem.style.display='block':this.elem.style.display='none';
			this.elem.style.width=this.elem.width+'px';
			this.elem.style.height=this.elem.height+'px';
			this.isAnimate=false;
			if(this.callback){this.callback()}
		}
	}
}



	/* showOfTime */
function showOfTime(get_str,star_date,end_date){
	var temp_date=new Date();
	if(temp_date>star_date&&temp_date<end_date){
		document.write(get_str);
	}
}
function hexunOutTimer(yr_n,mt_n,dt_n,hr_n,mn_n){
	var temp_date=new Date();
	if(yr_n!=null){temp_date.setYear(yr_n);}
	if(mt_n!=null){temp_date.setMonth(mt_n-1);}
	if(dt_n!=null){temp_date.setDate(dt_n);}
	if(hr_n!=null){temp_date.setHours(hr_n);}
	if(mn_n!=null){temp_date.setMinutes(mn_n);}
	return temp_date;
}
/* date */
	var temp_date=new Date();
/* fscommand */
	var InternetExplorer = navigator.appName.indexOf("Microsoft") != -1;
	function bigFloatMediaInner_DoFSCommand(command, args) {
		//var bigFloat_obj = InternetExplorer ? bigFloatMediaInner : document.bigFloatMediaInner;
                  //备：变量bigFloat_obj的返回方法修改过
                  var bigFloat_obj = InternetExplorer? document.bigFloatMediaInner : document.getElementById('bigFloatMediaInner');
		switch(command){
		case 'closeAD':
			if(document.getElementById('streamMediaBShell').style.display!='none'){
				publicMethod_obj.change('streamMediaBShell','streamMediaSShell');
				clearInterval(publicMethod_obj.streamClearMedia_obj);
                                     var fo=document.getElementById(bigFloatMediaInner);

                                     if(fo!=null){

                                               fo.SetVariable("goon",0);

                                     }  

			}
			break;
		case 'enable':
			bigFloat_obj.SetVariable("enabled_str","1");
			break;
		}
	}
	if (navigator.appName && navigator.appName.indexOf("Microsoft") != -1 && navigator.userAgent.indexOf("Windows") != -1 && navigator.userAgent.indexOf("Windows 3.1") == -1) {
		document.write('<script language=\"VBScript\"\>\n');
		document.write('On Error Resume Next\n');
		document.write('Sub bigFloatMediaInner_FSCommand(ByVal command, ByVal args)\n');
		document.write('	Call bigFloatMediaInner_DoFSCommand(command, args)\n');
		document.write('End Sub\n');
		document.write('</script\>\n');
	}
	var InternetExplorer = navigator.appName.indexOf("Microsoft") != -1;
	function fullScreeMediaInner_DoFSCommand(command, args) {
		var fullScree_obj = InternetExplorer ? fullScreeMediaInner : document.fullScreeMediaInner;
		switch(command){
		case 'closeAD':
			if(document.getElementById('fullScreeMedia').style.display!='none'){
				publicMethod_obj.change('fullScreeMedia');
				clearInterval(publicMethod_obj.fullClearMedia_obj);
			}
			break;
		case 'enable':
			fullScree_obj.SetVariable("enabled_str","1");
			break;
		}
	}
	if (navigator.appName && navigator.appName.indexOf("Microsoft") != -1 && navigator.userAgent.indexOf("Windows") != -1 && navigator.userAgent.indexOf("Windows 3.1") == -1) {
		document.write('<script language=\"VBScript\"\>\n');
		document.write('On Error Resume Next\n');
		document.write('Sub fullScreeMediaInner_FSCommand(ByVal command, ByVal args)\n');
		document.write('	Call fullScreeMediaInner_DoFSCommand(command, args)\n');
		document.write('End Sub\n');
		document.write('</script\>\n');
	}
/* public */
	var publicMethod_obj=new Object();
	publicMethod_obj.location = location.href;
	publicMethod_obj.exchangeInner = function(){
		if(document.getElementById(this.id)){
			if(this.enabled){
			      document.getElementById(this.id).innerHTML=this.inner;
                                  if(this.paddingTop){
				     document.getElementById(this.id).style.paddingTop=this.paddingTop+'px';
			       }
			       if(this.paddingBottom){
			              document.getElementById(this.id).style.paddingBottom=this.paddingBottom+'px';
			       }
                                  if(this.paddingLeft){
			              document.getElementById(this.id).style.paddingLeft=this.paddingLeft+'px';
			       }
			}
		}
	}
	publicMethod_obj.onResize=function(){
		if(this.maxWidth!=null&&hexunPageMode_obj.clientWidth>this.maxWidth){
			var tempFrame=(hexunPageMode_obj.clientWidth-this.maxWidth)/2;
		}else{
			var tempFrame=0;
		}
		for(var loops in this.list_array){
			if(document.getElementById(this.list_array[loops])&&document.getElementById(this.list_array[loops]).style.display!='none'){
                                    var _scrollTop=window.pageYOffset?document.documentElement.scrollTop || window.pageYOffset:hexunPageMode_obj.scrollTop//2010.06.28修改
				switch(this.align){
					case 'left':
						document.getElementById(this.list_array[loops]).style.left=tempFrame+this.marginX+'px';
						break;
					case 'right':
						document.getElementById(this.list_array[loops]).style.left=hexunPageMode_obj.scrollLeft+hexunPageMode_obj.clientWidth-tempFrame-this.width-(this.marginX?this.marginX:0)+'px';
						break;
					case 'center':
						document.getElementById(this.list_array[loops]).style.left=(hexunPageMode_obj.clientWidth-this.width)/2+hexunPageMode_obj.scrollLeft+'px';
						break;
				}
				switch(this.valign){
					case 'top':
						document.getElementById(this.list_array[loops]).style.top=_scrollTop+(this.marginY?this.marginY:270)+'px';
						break;
					case 'bottom':
						document.getElementById(this.list_array[loops]).style.top=hexunPageMode_obj.clientHeight+_scrollTop-this.height-(this.marginY?this.marginY:0)+'px';
						break;
					case 'center':
						document.getElementById(this.list_array[loops]).style.top=(hexunPageMode_obj.clientHeight-this.height)/2+_scrollTop+'px';
						break;
					default :
						document.getElementById(this.list_array[loops]).style.top=this.marginY+'px';
						break;
				}
			}
		}
	}
	publicMethod_obj.writeDiv=function(id1_str,id2_str,get_w,get_h,back_str,close_obj,replay_obj,static_bool){
		var temp_str='<div id="'+id1_str+'" style="display:block;';
		if(static_bool==null){
			temp_str+='position:absolute; z-index:9999;';
		}
		temp_str+='width:'+get_w+'px;';
		if(back_str!=null){
			temp_str+='background:'+back_str+';';
		}
		temp_str+='"><div id="'+id2_str+'" style="';
		temp_str+='height:'+get_h+'px;" class="adcLoadingTip"><br />内容装载中...</div>';
		if(replay_obj!=null){
			temp_str+=publicMethod_obj.creatReplayBtn(replay_obj);
		}
		if(close_obj!=null){
			temp_str+=publicMethod_obj.creatCloseBtn(close_obj);
		}
		temp_str+='<div style="clear:both"></div></div>';
		return temp_str;
	}
	publicMethod_obj.creatReplayBtn=function(replay_obj){
		var temp_str='<div style="cursor:hand;width:'+replay_obj.width+'px;line-height:14px;height:14px;float:'+replay_obj.align+';text-align:center;margin:0px;padding:2px 0px;';
		if(replay_obj.backgroud!=null){
			temp_str+='background:'+replay_obj.backgroud+';';
		}
		temp_str+='"';
		temp_str+=' onclick="publicMethod_obj.replay(\''+replay_obj.instance+'\''
		if(replay_obj.closed!=null){
			temp_str+=',\''+replay_obj.closed+'\'';
		}
		if(replay_obj.flash!=null){
			temp_str+=',\''+replay_obj.flash+'\'';
		}
        if(replay_obj.maxTimer!=null){
			temp_str+=',\''+replay_obj.maxTimer+'\'';
		}
		temp_str+=')"';
		temp_str+='><a onclick="return false" href="#" style="color:'+replay_obj.color+';font-size:12px;font-weight:'+replay_obj.bold+';text-decoration:'+replay_obj.decoration+'">重播</a></div>';
		return temp_str;
	}
	publicMethod_obj.creatCloseBtn=function(close_obj){
		var temp_str='<div style="cursor:hand;width:'+close_obj.width+'px;line-height:14px;height:14px;float:'+close_obj.align+';text-align:center;margin:0px;padding:2px 0px;';
		if(close_obj.backgroud!=null){
			temp_str+='background:'+close_obj.backgroud+';';
		}
		temp_str+='"';
		temp_str+=' onclick="publicMethod_obj.change(\''+close_obj.instance+'\'';
		if(close_obj.next!=null){
			temp_str+=',\''+close_obj.next+'\'';
		}
		temp_str+=')"><a onclick="return false" href="#" style="color:'+close_obj.color+';font-size:12px;font-weight:'+close_obj.bold+';text-decoration:'+close_obj.decoration+'">关闭</a></div>'
		return temp_str;
	}
	publicMethod_obj.change=function(close_str,next_str){ 
		var _bool=true;
		if(close_str!=null){
			if(close_str=='streamMediaBShell'&&document.getElementById(close_str).style.display=='none'){
				_bool=false;				
			}			
			document.getElementById(close_str).style.display='none';
			if(close_str=='streamMediaBShell'){
			    if(publicMethod_obj.streamClearMedia_obj){clearInterval(publicMethod_obj.streamClearMedia_obj)};
			}
			//当关闭全屏时
			if(close_str=='fullScreeMedia'){slideEffect.start('fullScreeMedia','up');
			    if(publicMethod_obj.fullClearMedia_obj){clearInterval(publicMethod_obj.fullClearMedia_obj)};
			    document.getElementById('topFullWidthBanner').style.display='block';
				      //如果对联广告存在，打开对联
				      if(document.getElementById('hexunCouplet01')){document.getElementById('hexunCouplet01').style.display="block"};
				      //如果翻卷广告存在，打开翻卷
				      if(document.getElementById('hexunFanJuan01')){
				        	       document.getElementById('hexunFanJuan01').style.display="block";
					                publicMethod_obj.change('scrollMediaSShell','scrollMediaBShell');
					                publicMethod_obj.gotoFrame('scrollMediaBInner',2);
		                     if(document.getElementById('scrollMediaInner')&&InternetExplorer){
		 	                              document.getElementById('scrollMediaInner').GotoFrame(6);	 
		                	              document.getElementById('scrollMediaInner').play();	 
		 	                    }
				      	};
				      	//如果左侧logo存在，打开左logo
				       if(document.getElementById('leftFloatLogoShell01')){document.getElementById('leftFloatLogoShell01').style.display="block"};
				       //如果右侧logo存在，打开右logo
               if(document.getElementById('rightFloatLogoShell01')){document.getElementById('rightFloatLogoShell01').style.display="block"};
               //如果流媒体广告存在
			       	if(document.getElementById('streamMediaBShell')&&document.getElementById('streamMediaSShell')){
			       		          if(document.getElementById('scrollMediaInner')&&InternetExplorer){
		 	                              document.getElementById('bigFloatMediaInner').GotoFrame(1);	 
		                	              document.getElementById('bigFloatMediaInner').play();	 
		 	                    }
					                publicMethod_obj.change('streamMediaSShell','streamMediaBShell');//关闭小logo,打开大logo					                
					               //播放时间结束后，关闭大logo，打开小logo
		                     publicMethod_obj.streamClearMedia_obj=setInterval(function(){
			                              clearInterval(publicMethod_obj.streamClearMedia_obj);
			                              publicMethod_obj.change('streamMediaBShell','streamMediaSShell');
		          },publicMethod_obj.streamClearMedia_maxTimer);
					}
			}
		}
		if(next_str!=null&&_bool){
			document.getElementById(next_str).style.display='block';
			   if(document.getElementById('streamMediaS')&&document.getElementById('streamMediaS').innerHTML=='空'){
				 document.getElementById('streamMediaSShell').style.display='none';
			}
		}
		return false;
	}
	publicMethod_obj.replay=function(get_str,close_str,flash_str,maxTimer){
		document.getElementById(get_str).style.display='block';
		//var bigFloat_obj = InternetExplorer ? bigFloatMediaInner : document.bigFloatMediaInner;
		//备：变量bigFloat_obj的返回方法修改过
		var bigFloat_obj = InternetExplorer? document.bigFloatMediaInner : document.getElementById('bigFloatMediaInner');
		if(close_str!=null){
			document.getElementById(close_str).style.display='none';
		}
		if(close_str=='fullScreeMediaSShell'){  //fullScreen+LOGO
		    document.documentElement.scrollTop='0px';
		    document.getElementById('topFullWidthBanner').style.display='none';
		    if(document.getElementById('hexunCouplet01')){document.getElementById('hexunCouplet01').style.display="none"};
		    if(document.getElementById('hexunFanJuan01')){document.getElementById('hexunFanJuan01').style.display="none"};
		    if(document.getElementById('leftFloatLogoShell01')){document.getElementById('leftFloatLogoShell01').style.display="none"};
		    if(document.getElementById('rightFloatLogoShell01')){document.getElementById('rightFloatLogoShell01').style.display="none"};
		}

		if(flash_str=='dlLoaderBLeft01'){
			publicMethod_obj.change('rightCoupletSShell01','rightCoupletBShell01');
			if(document.getElementById('dlLoaderBLeft01'))	{document.getElementById('dlLoaderBLeft01').GOTOTEST(2);}
			if(document.getElementById('dlLoaderBRight01')) {document.getElementById('dlLoaderBRight01').GOTOTEST(2);}
		}
		if(flash_str=='dlLoaderBRight01'){
			publicMethod_obj.change('leftCoupletSShell01','leftCoupletBShell01');
			if(document.getElementById('dlLoaderBLeft01'))	{document.getElementById('dlLoaderBLeft01').GOTOTEST(2);}
			if(document.getElementById('dlLoaderBRight01')) {document.getElementById('dlLoaderBRight01').GOTOTEST(2);}
		}
		
		if(document.getElementById(flash_str)&&InternetExplorer){
			document.getElementById(flash_str).GotoFrame(1);
			document.getElementById(flash_str).Play();
			var fo=document.getElementById(flash_str);
			if(fo!=null){
			    fo.SetVariable("goon",1);
			    fo.SetVariable("firstPlay",0);
			 }
        }
		
         if(maxTimer&&close_str=='streamMediaSShell'){
             if(publicMethod_obj.streamClearMedia_obj){
                clearInterval(publicMethod_obj.streamClearMedia_obj);
            }
            publicMethod_obj.streamClearMedia_obj=setInterval(function(){
                clearInterval(publicMethod_obj.streamClearMedia_obj);
                publicMethod_obj.change(get_str,close_str);
            },maxTimer);
        }
        if(maxTimer&&close_str=='fullScreeMediaSShell'){
             if(publicMethod_obj.fullClearMedia_obj){
                clearInterval(publicMethod_obj.fullClearMedia_obj);
            }
            publicMethod_obj.fullClearMedia_obj=setInterval(function(){
                clearInterval(publicMethod_obj.fullClearMedia_obj);
                publicMethod_obj.change(get_str,close_str);
            },maxTimer);
        }
	}
	publicMethod_obj.creatMediaAD=function(flash_bool,id_str,src_str,w_num,h_num,wmode,url_str){
		if(flash_bool){
			var temp_str='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" '
			+'width="'+w_num+'" height="'+h_num+'" id="'+id_str+'">'
			+'<param name="movie" value="'+src_str+'" />'
			+'<param name="quality" value="high" /><param name="allowScriptAccess" value="always" /><param name="allowFullScreen" value="true" />';
			if(wmode){
				temp_str+='<param name="wmode" value="'+wmode+'">';
			}
			temp_str+='<embed allowscriptaccess="always" src="'+src_str+'" width="'+w_num+'" height="'+h_num+'" '
			+'quality="high" allowFullScreen="true" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" ';
			if(wmode){
				temp_str+='wmode="'+wmode+'"';
			}
			temp_str+=' name="'+id_str+'" id="'+id_str+'"></embed></object>'
		}else{
			var temp_str='<a href="'+url_str+'" target="_blank">'
			+'<img src="'+src_str+'" width="'+w_num+'" height="'+h_num+'" border="0" /></a>';
		}
		return temp_str;
	}
	publicMethod_obj.openWindowBack=function() {		
		if( publicMethod_obj.location.indexOf('cms')<0 ) {
		    var popUpWin2 = open("/backscreen.htm", "popUpWin2", "width=770,height=550,top=4000,left=3000");
			self.focus()
		}                  
	}
	publicMethod_obj.showOfTime=function(){
		for(var loops=0;loops<arguments.length;loops++){
			if(temp_date>arguments[loops].s_date&&temp_date<arguments[loops].e_date){
				return true;
			}
		}
		return false;
	}
	publicMethod_obj.gotoFrame=function(_flashObj,_frame){//20110120	GotoFrame(),play()方法比较原始，容易出错。采用flash内部定义方法控跳帧。
		if(document.getElementById(_flashObj))document.getElementById(_flashObj).GOTOTEST(_frame);			// GOTOTEST为flash内部自定义的供外部调用的函数。		
	}

	/* List */
	var pageMedia_array=new Array();
	var pageFloatMedia_array=new Array();
	var rightFloatYet=false;
	var leftFloatYet=false;
	var quanPingShow=false;

// left 80x80 01
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2012,9,6,7,30),e_date:hexunOutTimer(2016,9,21,8,00)}))){
	var temp_obj=new Object({id:'leftFloatLogo01',enabled:true});
	temp_obj.inner='<iframe width="100" height="100" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="http://h02.hxsame.hexun.com/s?z=hexun&c=1164&op=1" ></iframe>';
	temp_obj.init=publicMethod_obj.exchangeInner;
	temp_obj.width=temp_obj.height=100;
	temp_obj.align='left';
	temp_obj.valign='bottom';
	temp_obj.marginX=10;
	temp_obj.marginY=40;
	temp_obj.backgroud='#BFBFBF';
	temp_obj.list_array=new Array('leftFloatLogoShell01');
	temp_obj.onStage=publicMethod_obj.onResize;
	var closeBottom_obj=new Object({color:'#333333',backgroud:'#BFBFBF',bold:'bold',decoration:'none',width:50,align:'left',instance:'leftFloatLogoShell01'});
	pageMedia_array.push(temp_obj);
	pageFloatMedia_array.push(temp_obj);
	document.write(publicMethod_obj.writeDiv('leftFloatLogoShell01','leftFloatLogo01',temp_obj.width,temp_obj.height,temp_obj.backgroud,closeBottom_obj));         
}




/* page timeline */
	if(document.getElementById('hexunBodyBaseDiv')==null){
		document.write('<div id="hexunBodyBaseDiv" style="position:absolute; z-index:10; left:0px; top:0px; width:10px;"></div>');
	}
	document.getElementById('hexunBodyBaseDiv').style.height=2000+'px';
	window.scrollBy(0,1);
	//var hexunPageMode_obj=(document.documentElement.scrollTop>document.body.scrollTop?document.documentElement:document.body);
         var hexunPageMode_obj=document.documentElement;//2010.06.28修改
	window.scrollBy(0,-1);
	document.getElementById('hexunBodyBaseDiv').style.height=10+'px';
	function pageFloatMediaInit(){
		for(var loops=0;loops<pageFloatMedia_array.length;loops++){
			pageFloatMedia_array[loops].onStage();
		}
	}
	function hexunTimeline(){
		if(pageFloatMedia_array.length>0){
			pageFloatMediaInit();
			setInterval(function (){
				pageFloatMediaInit();
			},100);
		}
	}
/* Init */
	function pageMediaInit(){
		for(var loops=0;loops<pageMedia_array.length;loops++){
			pageMedia_array[loops].init();
			if(pageMedia_array[loops].id=='fullScreeMedia'){
                if(document.getElementById('hexunCouplet01')){document.getElementById('hexunCouplet01').style.display="none"};
                if(document.getElementById('hexunFanJuan01')){document.getElementById('hexunFanJuan01').style.display="none"};
                if(document.getElementById('leftFloatLogoShell01')){document.getElementById('leftFloatLogoShell01').style.display="none"};
                if(document.getElementById('rightFloatLogoShell01')){document.getElementById('rightFloatLogoShell01').style.display="none"}; 
			}
		}
	}
	var pageLoading;
	if(document.getElementById('pageTail')!=null){
		clearInterval(pageLoading);
		pageMediaInit();
		hexunTimeline();
	}else{
		pageLoading=setInterval(function(){
			if(document.getElementById('pageTail')!=null){
				clearInterval(pageLoading);
				pageMediaInit();
				hexunTimeline();
			}
		},100);
	}


var tempAdvArray = new Array();
//tempAdvArray start

if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,29,8,0),e_date:hexunOutTimer(2016,1,3,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
 //tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h03.hxsame.hexun.com/s?z=hexun&c=456&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//汽车
 //tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h04.hxsame.hexun.com/s?z=hexun&c=761&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//新闻
 tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h05.hxsame.hexun.com/s?z=hexun&c=787&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//股票
 //tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h06.hxsame.hexun.com/s?z=hexun&c=541&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//港股
 //tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h07.hxsame.hexun.com/s?z=hexun&c=503&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//保险
 //tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h08.hxsame.hexun.com/s?z=hexun&c=723&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//银行
 //tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h09.hxsame.hexun.com/s?z=hexun&c=490&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//理财
 //tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h10.hxsame.hexun.com/s?z=hexun&c=1488&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');//房产
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,12,18,8,0),e_date:hexunOutTimer(2016,1,7,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="956" height="90" frameborder="0" src="http://h04.hxsame.hexun.com/s?z=hexun&amp;c=1144&amp;op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,12,18,8,0),e_date:hexunOutTimer(2016,1,9,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h07.hxsame.hexun.com/s?z=hexun&c=1474&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,12,18,8,0),e_date:hexunOutTimer(2016,1,8,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h04.hxsame.hexun.com/s?z=hexun&c=1354&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,12,18,8,0),e_date:hexunOutTimer(2016,1,12,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h02.hxsame.hexun.com/s?z=hexun&c=1285&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,12,18,8,0),e_date:hexunOutTimer(2016,1,5,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h07.hxsame.hexun.com/s?z=hexun&c=1474&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,12,18,8,0),e_date:hexunOutTimer(2016,1,5,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="956" height="90" frameborder="0" src="http://h07.hxsame.hexun.com/s?z=hexun&amp;c=479&amp;op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,29,8,0),e_date:hexunOutTimer(2016,1,12,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="100" height="100" frameborder="0" src="http://h03.hxsame.hexun.com/s?z=hexun&amp;c=762&amp;op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}

if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,29,8,0),e_date:hexunOutTimer(2015,12,8,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h04.hxsame.hexun.com/s?z=hexun&c=1144&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,29,8,0),e_date:hexunOutTimer(2015,12,31,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h08.hxsame.hexun.com/s?z=hexun&c=1153&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,12,18,8,0),e_date:hexunOutTimer(2015,12,29,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h03.hxsame.hexun.com/s?z=hexun&c=480&op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,29,8,0),e_date:hexunOutTimer(2015,11,30,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" src="http://h01.hxsame.hexun.com/s?z=hexun&amp;c=620&amp;op=1" marginheight="0" marginwidth="0" scrolling="no"></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,29,8,0),e_date:hexunOutTimer(2015,11,1,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="100" height="100" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="http://h02.hxsame.hexun.com/s?z=hexun&c=565&op=1" ></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,18,8,0),e_date:hexunOutTimer(2015,10,17,8,0)}),new Object({s_date:hexunOutTimer(2007,9,18,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="100" height="100" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="http://h02.hxsame.hexun.com/s?z=hexun&c=762&op=1" ></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,16,8,0),e_date:hexunOutTimer(2015,10,24,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="100" height="100" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="http://h08.hxsame.hexun.com/s?z=hexun&c=1369&op=1" ></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,9,1,8,0),e_date:hexunOutTimer(2015,11,1,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="140" height="55" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="http://h03.hxsame.hexun.com/s?z=hexun&c=1097&op=1" ></iframe>');
}
if(publicMethod_obj.showOfTime(new Object({s_date:hexunOutTimer(2015,6,1,8,0),e_date:hexunOutTimer(2016,1,1,8,0)}),new Object({s_date:hexunOutTimer(2007,11,22,8,30),e_date:hexunOutTimer(2007,12,26,8,29)}))){
    tempAdvArray.push('<iframe width="300" height="250" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" src="http://h04.hxsame.hexun.com/s?z=hexun&c=789&op=1" ></iframe>');
}


//tempAdvArray end
document.write('<div style="height:1px;overflow:hidden"><div style="margin-top:1px">' + tempAdvArray.join("")  + '</div></div>');
tempAdvArray = null;
/*主体链接/标签云广告位*/
var cpro_id="u1605200";
(window["cproStyleApi"]=window["cproStyleApi"]||{})[cpro_id]={
per:0.7,//自动弹出侧滑广告的位置，默认为0.7(滚轮滑到页面70%时，自动弹出侧滑)，取值为[0,1]
per2:1.0,//自动收回的位置，默认为1.0，必须保证per<per2
pos:"left",//侧滑广告出现的位置，默认为"left"(侧滑广告出现在页面左侧)
ver:50//侧滑广告离底部的距离，默认为50(侧滑广告位离底部的距离为50px)
};
document.write('<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/s.js"><\/script>');

if(typeof(setpageview_showing)=="undefined")
{	
	var setpageview_showing = true;
	document.write('<script type="text/javascript" src="http://itv.hexun.com/lbi-html/ly/2011/allPages/setpageview.js"><\/script>');
}
