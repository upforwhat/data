var urlhost = "http://uc.tool.hexun.com/";

HxRelationSet = (function (loginid, viewuserid, viewusername, type) {
    var qsData = { "attentionuserid": viewuserid };
    var seturl = urlhost;
    if (type == 0 || type == 2) { //0:无关系，2：被关注
        seturl = seturl + "addattention/";
        qsData = { attentionuserid: viewuserid, sourceid: 3 };
    }
    else {
        seturl = seturl + "cancelattention/";
    }
    jQuery.ajax({
        type: "get",
        async: false,
        url: seturl,
        dataType: "jsonp",
        jsonp: "callback",
        data: qsData,
        success: function (data) {
            if (data.status == 1) {
                if (type == 1 || type == 3) {
                    HxRelationGet(viewuserid, viewusername, loginid);
                }
                if (type == 0 || type == 2) {
                    if (data.result.UserRelation != 0 || data.result.UserRelation != 2) {
                        HxRelationGet(viewuserid, viewusername, loginid);
                    }
                }
            }
            else {
                if (data.status == -2) {
                    alert("关注人员已满，上限3000人");
                }
                else if (data.status == 0) {
                    alert("关注失败");
                }
                else if (data.status == 1001) {
                    alert("参数错误");
                }
                else if (data.status == 1002) {
                    alert("未登录或者无权限");
                }
                else if (data.status == 1003) {
                    alert("不能关注自己");
                }
                else if (data.status == 1004) {
                    alert("您在黑名单里");
                }
            }
        },
        error: function () {
        }
    });
});

HxRelationGet = (function (viewuserid, viewusername, loginid) {    
    if (loginid == 0) {
        jQuery("#master_ptoto_2").hide();
        return;
    }
    jQuery.ajax({
        type: "post",
        async: false,
        url: urlhost + "getrelation/",
        data: { userid: viewuserid },
        dataType: "jsonp",
        jsonp: "callback",
        success: function (data) {
            if (data.status == 1) {
                jQuery("#master_ptoto_2").show();
                if (data.result.UserRelation == 1 || data.result.UserRelation == 3) {
                    //jQuery("#hx_home_attention").before("<span>已关注，</span>")
                    jQuery("#hx_home_attention").html("取消关注");
                    jQuery("#hx_home_attention").attr("tp", data.result.UserRelation);

                } else if (data.result.UserRelation == 0 || data.result.UserRelation == 2) {
                    jQuery("#hx_home_attention").html("加关注");
                    jQuery("#hx_home_attention").attr("tp", data.result.UserRelation);

                }
                jQuery('#hx_home_attention').unbind("click");
                jQuery("#hx_home_attention").click(function () {
                    HxRelationSet(loginid, viewuserid, viewusername, jQuery("#hx_home_attention").attr("tp"));
                });
                var type = jQuery("#hx_home_attention").attr("tp");
                gpset.init({ userId: loginid, attentionUid: viewuserid, attentionUname: viewusername, attentiontype: type });

            }
            else if (data.status == 1001) {
                jQuery("#master_ptoto_2").hide();
            }
            else {
                //alert(data.status);
            }
        },
        error: function () {
        }
    });
});