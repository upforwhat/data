var gpset = (function () {
    //var $ = jQuery;
    var defaults = {
        oneId: "tt",
        userId: 18452236,
        attentionUid: 18395658,
        attentionUname: 'beiling02',
        moreBtnId: 'more',
        moreBoxId: 'more-box',
        panelId: "group-box",
        ulId: "group", //列表面板
        nameId: "master_name",
        classSetGroup: "setGroup",
        attentiontype: 0,
        seekingattention: "blog_addFocus",
        ssetgroup: 'setgroup',
        layerId: 'layerX',
        classcheckbox: 'toggle',
        classClose1: 'group-close',
        fetchUrl: 'http://uc.tool.hexun.com/getgroupsbyuserid', //获取分组
        pushUrl: 'http://uc.tool.hexun.com/creategroup', //创建分组
        blackpushUrl: 'http://hexun.com/newhome/set/blacklist', //添加黑名单
        seekingattentionpushUrl: 'http://hexun.com/newhome/User/UserPushNotice', //求关注
        setgroupsUrl: 'http://uc.tool.hexun.com/setattentiongroup', //设置分组

        templateId: 'item-template',
        hoverClass: 'nowBG',
        classlistItem: 'groupListItem',
        classCreateBtn: 'newGroup-create',
        classBtnSave: 'btnSave',
        classCancle: 'btnCancel',
        classInput: 'yoursGroup',
        defaultText: '新分组名称',
        classWarning: 'newGroup-box-warning',
        classCancle2: 'gcom-btn11',
        classToGroup: 'gcom-btn1',
        classSuccess: 'group-add-success',
        nameIdT: 'name-s',
        classNum: 'g-nums',
        classBlankList: 'addBlankList',
        classPlus: 'plus',
        classFocus: 'addFocus'
    };

    function bindEvent() {
        var moreBtn = jQuery('#' + defaults.moreBtnId),
            moreBox = jQuery('#' + defaults.moreBoxId),
            seekingattention = jQuery("#" + defaults.seekingattention),
            fsetgroup = jQuery("#" + defaults.ssetgroup),
            btnGroup = moreBox.find('.' + defaults.classSetGroup),
            layer = jQuery('#' + defaults.layerId),
            panel = jQuery('#' + defaults.panelId),
            closeBtn = panel.find('.' + defaults.classClose1),

            temp = jQuery('#' + defaults.templateId),
            listPanel = jQuery('#' + defaults.ulId),
            list = listPanel.find('.' + defaults.classlistItem),
            classcheckbox = list.find('.' + defaults.classcheckbox), //分组list中的checkbox
            oinputs = list.find('input'), //列表chenkbox
            btnCreate = panel.find('.' + defaults.classCreateBtn),
            btnSave = panel.find('.' + defaults.classBtnSave),
            btnCancle = panel.find('.' + defaults.classCancle),
            input = panel.find('.' + defaults.classInput),
            warning = panel.find('.' + defaults.classWarning),
            btnCancle2 = panel.find('.' + defaults.classCancle2),
            groups = panel.find('.' + defaults.classToGroup),
            successNotice = panel.find('.' + defaults.classSuccess),
            name = panel.find('#' + defaults.nameIdT),
            nums = panel.find('.' + defaults.classNum),
            nameS = jQuery('#' + defaults.nameId),
            timer = null,
            blackList = moreBox.find('.' + defaults.classBlankList),
            focusBtn = moreBox.find('#blog_addFocus');
        // 黑名单
        blackList.unbind("click");
        blackList.click(function () {
            window.open(defaults.blackpushUrl);
            //            var _this = this;
            //            if (jQuery(this).is('.' + defaults.classPlus)) {
            //                groupAjax(function (data, status) {
            //                    if (status == 1) {
            //                        jQuery(_this).toggleClass(defaults.classPlus);
            //                    }
            //                    else {
            //                        alert(status + "错误");
            //                    }
            //                }, defaults.blackpushUrl, { black: 1, id: '111' })
            //            }
            //            else {
            //                groupAjax(function (data, status) {
            //                    if (status == 1) {
            //                        jQuery(_this).toggleClass(defaults.classPlus);
            //                    }
            //                    else {
            //                        alert(status + "错误");
            //                    }
            //                }, defaults.blackpushUrl, { black: 0, id: '222' })
            //            }

        });
        // 求关注
        focusBtn.unbind("click");
        focusBtn.click(function () {
            groupAjax(function (data, status) {
                if (status == 1) {
                    var mes = "已经成功发送关注请求";
                    moreBox.hide();
                }
                if (status != 1) {
                    mes = status + "错误";
                }
                var a = jQuery('<div>' + mes + '</div>').css({
                    "position": 'absolute',
                    "width": '300px',
                    "line-height": "50px",
                    "text-align": "center",
                    "font-size": "16px",
                    "height": '50px',
                    "border": '4px solid #ACACAC',
                    "background-color": "#fff",
                    "border-radius": "6px"
                }).appendTo(jQuery("body"));
                setPos(a);
                setTimeout(function () {
                    a.remove();
                }, 1000)
            }, defaults.seekingattentionpushUrl, { receiveUserID: defaults.attentionUid, receiveUserName: defaults.attentionUname, objectType: 2, noticeType: 3 });
        });
        //更多初始面板
        moreBtn.unbind("mouseover");
        moreBtn.mouseover(function () {
            moreBox.show();
            if (defaults.attentiontype == 1) { //关注，显示求关注按钮
                seekingattention.show();
            }
            else {
                seekingattention.hide();
            }
            if (defaults.attentiontype == 1 || defaults.attentiontype == 3) {

                fsetgroup.show();
            }
            else if (defaults.attentiontype == 0 || defaults.attentiontype == 2) {
                fsetgroup.hide();
            }
            setPos2(jQuery(this), moreBox, 5);
        });
        moreBtn.unbind("mouseout");
        moreBtn.mouseout(function () {
            timer = setTimeout(function () { moreBox.hide(); }, 100);
        });


        moreBox.unbind("hover");
        moreBox.hover(function () {
            moreBox.show();
            if (defaults.attentiontype == 1) {
                seekingattention.show();
            }
            else {
                seekingattention.hide();
            }
            if (defaults.attentiontype == 1 || defaults.attentiontype == 3) {
                fsetgroup.show();
            }
            else if (defaults.attentiontype == 0 || defaults.attentiontype == 2) {
                fsetgroup.hide();
            }
            if (timer) clearInterval(timer);
        }, function () {
            moreBox.hide();
        });
        //设置分组
        btnGroup.unbind("click");
        btnGroup.click(function () {
            layer.show();
            moreBox.hide();
            panel.show();
            jQuery('#layerX').css({ height: jQuery(document).height() });
            setPos(panel);
            // 数据请求
            groupAjax(function (data) {
                var text = viewList(data);
                listPanel.html(text);
            }, defaults.fetchUrl, { attentionuserid: defaults.attentionUid });
        });
        closeBtn.unbind("click");
        closeBtn.click(function () {
            closePanel();
            return false;
        });

        // 悬浮背景效果
        list.unbind("mouseover");
        list.live('mouseover', function () {
            jQuery(this).toggleClass(defaults.hoverClass);
        });
        list.unbind("mouseout");
        list.live('mouseout', function () {
            jQuery(this).toggleClass(defaults.hoverClass);
        });

        // 创建组
        btnCreate.unbind("click");
        btnCreate.click(function () {
            jQuery(this).hide().next().show();
            return false;
        });
        // 创建记录
        btnSave.unbind("click");
        btnSave.click(function () {
            var valid = validate();
            if (!valid) return;
            groupAjax(function (data, status) {
                data.title = decodeURI(data.title);
                if (status == 1) {
                    var str = " <li class='groupListItem'> <div class='view'><input class='toggle' type='checkbox' id='" + data.result.GroupID + "' /> ";
                    str += " <label for='" + status + "'>" + input[0].value + "</label></div></li>";
                    //listPanel.html(str);
                    jQuery(str).appendTo(listPanel);
                }
                else if (status == -1) {
                    //                    hexun.home.alert().init({
                    //                        txt: '分组重复',
                    //                        addClass: "w228"
                    //                    });
                    var mes = "存在重名";
                    alert(mes);

                }
                else if (status == -2) {
                    //                    hexun.home.alert().init({
                    //                        txt: '分组数不能超过20',
                    //                        addClass: "w228"
                    //                    });
                    var mes = "分组数不能超过20";
                    alert(mes);
                }
            }, defaults.pushUrl, { groupname: encodeURI(input[0].value) });


            return false;
        });

        // 未分组
        oinputs.unbind("change");
        oinputs.live('change', function () {
            if (this.checked) {
                if (this.id == 0) {
                    listPanel.find('input:gt(0)').removeAttr('checked');
                } else {
                    listPanel.find('input').eq(0).removeAttr('checked');
                }
            }
            else {

            }
        });
        // 取消记录视图

        btnCancle.unbind("click");
        btnCancle.click(function () {

            input[0].value = defaults.defaultText;
            jQuery(this.parentNode).hide().prev().show();
            warning.hide();
            input.removeClass('onInput');
            return false;
        });
        // 发送到所属组
        groups.unbind("click");
        groups.click(function () {
            var a = listPanel.find('input').filter(':checked'), b = [];
            a.each(function () {
                b.push(this.id);
            });
            if (!(b.length > 0)) {
                return;
            }
            groupAjax(function (data, status) {
                if (status == 1) {
                    name.text(defaults.attentionUname);
                    nums.html(b.length);
                    successNotice.show();
                }
                else {
                    alert(status + "设置分组失败");
                }
            }, defaults.setgroupsUrl, { attentionuserid: defaults.attentionUid, groupids: b.join(',') })

        });
        // 焦点事件
        input.unbind("focus");
        input.focus(function () {
            if (this.value == defaults.defaultText) {
                this.value = '';
            }
            jQuery(this).addClass('onInput')
        });
        // 失去焦点
        input.unbind("blur");
        input.blur(function () {
            if (this.value == '') {
                this.value = defaults.defaultText;
            }
            jQuery(this).removeClass('onInput');
            validate();
        });
        // 校验记录值
        input.unbind("keyup");
        input.keyup(function () {
            validate();
        });

        //
        btnCancle2.unbind("click");
        btnCancle2.click(function () {
            closePanel();
        });

        // 定义校验函数
        function validate() {
            var value = input[0].value;
            if (value && value.byteLen() > 12) {
                warning.show();
                input.focus();
                return false;
            }
            else {
                warning.hide();
                if (value == '') { return false; }
                if (jQuery.trim(value) == '') { return false; }
                if (value == defaults.defaultText) { return false; }
            }
            return true;
        }


        // 定义位置函数
        function setPos(o) {
            var vw, vh, st, sl, ow, oh;
            vw = jQuery(window).width(),
                vh = jQuery(window).height(),
                sl = jQuery(document).scrollLeft(),
                st = jQuery(document).scrollTop(),
                ow = o.outerWidth(),
                oh = o.outerHeight();
            o.css({ left: sl + (vw - ow) / 2 + 'px', top: st + (vh - oh) / 2 + 'px' });

        }

        // 定义位置函数2
        function setPos2(obj, box, x) {
            var w = obj.outerWidth(),
                h = obj.outerHeight(),
                l = obj.offset().left,
                t = obj.offset().top,
                w1 = box.outerWidth(),
                h1 = box.outerHeight(),
                st = jQuery(document).scrollTop(),
                pH = jQuery(window).height(),
                st2 = t - h1 - x - st,
                st3 = pH - h1 - h - x - (t - st);
            if (st3 < 0) {
                //    box.removeClass('user-info2');
                box.css('left', l + 'px');
                box.css('top', t - h1 - x + 'px');
            }
            else {
                box.css('left', l + 'px');
                box.css('top', t + h + x + 'px');
                //   box.addClass('user-info2');
            }
        }

        // 视图
        function viewList(data) {
            var pagefn = doT.template(temp[0].innerHTML);
            return pagefn(data);
        }

        function closePanel() {
            panel.hide();
            layer.hide();
            btnCreate.show().next().hide();
            input[0].value = defaults.defaultText;
            successNotice.hide();

        }

        // 处理请求
        function groupAjax(fn, url, data) {
            var url = url;
            jQuery.ajax({
                url: url,
                type: 'get',
                dataType: 'jsonp',
                data: data || {},
                jsonp: 'callback',
                scriptCharset: 'utf-8',
                success: function (data) {
                    fn(data, data.status);
                },
                error: function () {
                }
            }
            );
        }
    }

    return {
        init: function (options) {
            jQuery.extend(defaults, options);
            bindEvent();
        }
    }
})();

String.prototype.byteLen = function () {
    var len = 0,
        i = this.length;
    while (i--) {
        len += (this.charCodeAt(i) > 255 ? 2 : 1);
    }
    return len;
};
