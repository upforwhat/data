function isIE() {
    return document.all && window.external;
}
function D(id) {
    return document.getElementById(id);
}
var commenttag = D("commenttag");
function isDel() {
    if (window.confirm('是否删除？')) {
        return true;
    }
    else {
        return false;
    }
}

function doReference(commentID) {
    document.postComment.CommentIDTxt.value = commentID;
    commenttag.innerHTML = '【引用';
    commenttag.innerHTML += '评论】<span onclick=cancelquote(); style="cursor:pointer; color:#ff0000;">【取消引用】</span>';
}

function doRejectAnonymousComment(blogname, commentid) {
    RejectAnonymousComment(blogname, commentid);
}

function cancelquote() {
    document.postComment.CommentIDTxt.value = 0;
    commenttag.innerHTML = '';
}

var currentImgControl = null;
var getOnloadingImage = function () {
    if (!currentImgControl) {
        currentImgControl = document.createElement("img");
        currentImgControl.id = "imageOnlonding";
        currentImgControl.src = "http://blog.hexun.com/img/loading.gif";
        currentImgControl.border = 0;
    }
    return currentImgControl;
}

var RefreshCommentsList = function () {
}

var DeleteComment = function (articleId, commentId) {
    var url = "http://" + _UBN + ".blog.hexun.com/ajaxGetPostData.html";
    var link = document.getElementById('delcomment_' + commentId.toString());
    if (!window.confirm("确定要删除吗？")) return;
    link.href = "javascript:return false;";
    ShowAlertMessageBox("正在删除...", document.getElementById('delcomment_' + commentId.toString()));
    var pars = { Operation: "deletecomment", ArticleId: articleId, CommentId: commentId };
    jQuery.ajax({
        type: "post",
        async: false,
        url: url,
        dataType: "text",
        jsonp: "callback",
        data: pars,
        jsonpCallback: "success_jsonpCallback",
        success: function (res) {
            CloseMessageBox();
            if (res != "1") {
                alert(res);
            }
            else {
                var control = document.getElementById("tblrow_" + commentId);
                if (control) control.style.display = "none";
            }
        },
        error: function () {
            //alert("未知错误");
        }
    });
}

var fastReply = function (quoteID, replyWords) {
    initAddComment();
    var prifix = replyWords.indexOf("支持") > -1 ? "agree_" : "disagree_";
    document.getElementById(prifix + quoteID).href = "javascript:return false;";
    document.postComment.CommentIDTxt.value = quoteID;
    document.postComment.ReplyType.value = "1";
    document.postComment.NoHtmlCommentContent.value = replyWords;
    ShowAlertMessageBox("正在提交评论...", document.getElementById(prifix + quoteID));
    document.getElementById("postComment").submit();
    document.getElementById(prifix + quoteID).href = "javascript:fastReply(" + quoteID + ",\"" + replyWords + "\");";
}



var AddComment = function () {
    if (CheckNoHtmlCommentContent()) {
        ShowAlertMessageBox("评论正在提交，请稍候...");
    }
    else {
        CloseMessageBox();
        return false;
    }
}

var setLocation = function (currentUrl) {
    var url = "";
    if (currentUrl)
        url = currentUrl;
    else
        var url = "http://" + _UBN + ".blog.hexun.com/" + _AID + "_d.html#commentsList";
    window.location = url;
}

var CloseMessageBox = function () {
    var div_uploading = document.getElementById("div_uploading");
    if (div_uploading)
        div_uploading.style.display = "none";
    if (currentImgControl)
        currentImgControl.style.display = "none";
}

var onSubmitCommentComplted = function () {
    document.getElementById("VerificationPic").src = "http://comment.blog.hexun.com/img/vcodepic.gif";
    document.getElementById("vcodeHint").innerHTML = "点这里显示验证码。";
    document.getElementById("VerificationInput").value = "";
    document.getElementById("NoHtmlCommentContent").value = "字数上限为2000字";
    document.getElementById("CommentIDTxt").value = "0";
    document.getElementById("vcodeShow").innerHTML = "0";
}


var ShowAlertMessageBox = function (msg, eventControl) {
    if (eventControl) {
        getOnloadingImage()
        currentImgControl.style.display = "inline";
        currentImgControl.alt = msg;
        eventControl.appendChild(currentImgControl);
    }
    else {
        var uploading_Div = document.getElementById("div_uploading");
        if (!uploading_Div) {
            uploading_Div = document.createElement("div");
            uploading_Div.id = "div_uploading";
            uploading_Div.style.display = "inline";
            uploading_Div.innerHTML = "<img src='http://blog.hexun.com/img/loading.gif' alt='数据正在提交，请稍候...' border='0' align='absmiddle' /><span id='__span_messagebox'></span>";
        }
        jQuery(".PublishSubmit").first().append(uploading_Div);
        document.getElementById("div_uploading").style.display = "inline";
        document.getElementById("__span_messagebox").innerHTML = msg;
    }
}

var vote_Article = function (control) {
    initVoteArticle();
    ShowAlertMessageBox("", control);
    document.getElementById("form_voteArticle").submit();
    return;
}

var showVoteResult = function (count, aid, blogname) {
    document.getElementById("articleVoteCount").innerHTML = "<a href=\"http://cache-sidebar.blog.hexun.com/i/votelist.aspx?articleid=" + aid + "&blogName=" + blogname + "\" target=\"_blank\">" + count + "</a>";
}

var createIframe = function (name) {
    var f = isIE() ?
	document.createElement("<iframe name='" + name + "'></iframe>") :
	document.createElement("iframe");
    f.name = name;
    f.id = name;
    return f;
}

var deleteIframe = function (name) {
    var f = document.getElementById(name);
    if (f) {
        f.parentNode.removeChild(f);
    }
}

var initJs = function (path) {
    var prototypeJs = document.createElement("script");
    with (prototypeJs) {
        src = path;
        type = "text/javascript";
    }
    var headerCtrl = document.getElementsByTagName("head")[0];
    headerCtrl.appendChild(prototypeJs);
}

var initAddComment = function () {
    var blogname = _UBN;
    var aid = articleId;
    var iframe2 = document.getElementById("iframe_addComment");
    if (iframe2 == null) {
        iframe2 = createIframe("iframe_addComment");
        with (iframe2) {
            id = "iframe_addComment";
            name = "iframe_addComment";
            frameborder = 0;
            height = 0;
            width = 0;
            scrolling = "no";
        }
        iframe2.style.display = "none";
        document.getElementById("BlogPageRight").appendChild(iframe2);
    }
    if (document.getElementsByName("postComment")[0]) {
        document.getElementsByName("postComment")[0].action = "http://comment.blog.hexun.com/" + blogname + "/postComment.aspx?RawOp=iframe&ArticleID=" + aid + "";
        document.getElementsByName("postComment")[0].target = "iframe_addComment";
        document.getElementsByName("postComment")[0].id = "postComment";
        document.getElementsByName("postComment")[0].onsubmit = function () { return AddComment(); };
    }
}

var initVoteArticle = function () {
    var iframe1 = document.getElementById("iframe_voteArticle");
    if (iframe1 == null) {
        iframe1 = createIframe("iframe_voteArticle");
        with (iframe1) {
            id = "iframe_voteArticle";
            frameborder = 0;
            height = 0;
            width = 0;
            scrolling = "no";
        }
        iframe1.style.display = "none";
        document.getElementById("BlogPageRight").appendChild(iframe1);
    }
    initVoteForm();
}

var initVoteForm = function (aid, blogname, blogid) {
    var voteForm = document.getElementById("form_voteArticle");
    if (voteForm == null) {
        voteForm = document.createElement("form");
        with (voteForm) {
            id = "form_voteArticle";
            method = "post";
            action = "http://cache-sidebar.blog.hexun.com/voteArticle.aspx?RawOperation=iframe&articleID=" + aid + "&blogname=" + blogname + "&blogid=" + blogid + "";
            target = "iframe_voteArticle";
        }
        document.getElementById("BlogPageRight").appendChild(voteForm);
        var vote_link_top = document.getElementById("link_VoteArticle_Top");
        var vote_link_bottom = document.getElementById("link_VoteArticle_Bottom");
        if (vote_link_top && vote_link_bottom) {
            vote_link_top.href = "#";
            vote_link_bottom.href = "#";
            vote_link_top.onclick = function () { vote_Article(this); return false; };
            vote_link_bottom.onclick = function () { vote_Article(this); return false; };
        }
        else {
            var linkList = document.getElementsByTagName("a");
            for (var i = 0; i < linkList.length; i++) {
                if (linkList[i].href.indexOf("cache-sidebar.blog.hexun.com") > 0) {
                    linkList[i].href = "#";
                    linkList[i].onclick = function () { vote_Article(this); return false; };
                }
            }
        }
    }
    else {
        with (voteForm) {
            action = "http://cache-sidebar.blog.hexun.com/voteArticle.aspx?RawOperation=iframe&articleID=" + aid + "&blogname=" + blogname + "&blogid=" + blogid + "";
            target = "iframe_voteArticle";
        }
    }
}

function showArticleComments(blogname, aid, blogid, page) {
    var qsData = { "blogname": blogname, "articleid": aid, "blogid": blogid, "page": page };
    jQuery.ajax({
        type: "get",        
        url: "http://comment.blog.hexun.com/comment/get.aspx",
        dataType: "jsonp",
        jsonp: "callbackparam",
        data: qsData,
        success: function (json) {
            var tempcommentlist = json.comment;
            jQuery("#hk_page").html(json.pagestr[0].str);
            jQuery("#tbl_commentsList").html('');
            var str = "";
            if (jQuery("#tbl_commentsList").length == 0) {
                str += "<div class=\"Reply\">";
                str += "<a name=\"commentsList\"></a><span id=\"CommentAuditTip\"></span>";
                str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"ReplyTable\" id=\"tbl_commentsList\">";
                str += "</table>";
                str += "</div>";
                jQuery("#articleCommentDisplay").html(str);
            }

            for (var i = 0; i < tempcommentlist.length; i++) {
                str = "";
                str += "<tr valign=\"top\" id=\"tblrow_" + tempcommentlist[i].CID + "\">";
                str += "<td class=\"ReplyTable_A\">";
                str += "<a name=\"#comment_" + tempcommentlist[i].CID + "\"></a>";
                str += "<div>" + tempcommentlist[i].Logo + "</div>";
                str += "<div class=\"ReplyTable_A_1\">" + tempcommentlist[i].UserNameNewTemp + "</div>";
                str += tempcommentlist[i].ActStrNewTemp;
                str += "</td>";
                str += "<td class=\"ReplyTable_B\">";
                str += "<div class=\"ReplyTable_B_1\">";
                str += "<div class=\"ReplyTable_B_1_1\">";
                str += tempcommentlist[i].CommentDisplayTitle + "[" + tempcommentlist[i].PostTime + "]";
                str += "</div>";
                str += "</div>";
                str += "<div id=\"ReplyTableAll\">";
                str += tempcommentlist[i].Content;
                str += "</div>";
                str += "<div class=\"ReplyTable_B_4\">";
                str += tempcommentlist[i].ReferDivShowNewTemp;
                str += "<a href=\"#comment\" onclick=\"doReference(" + tempcommentlist[i].CID + ");\">[引用]</a>&nbsp;";
                str += "<a id=\"agree_" + tempcommentlist[i].CID + "\" href=\"javascript:fastReply(" + tempcommentlist[i].CID + ",&quot;支持！&quot;);\">[支持]</a>&nbsp;";
                str += "<a id=\"disagree_" + tempcommentlist[i].CID + "\" href=\"javascript:fastReply(" + tempcommentlist[i].CID + ",&quot;反对！&quot;);\">[反对]</a>&nbsp;";
                str += "</span>"; //---------
                if ((blog_loginUserId == blog_lookUserId && blog_loginUserId > 0) || isadmin == "True") {
                    str += tempcommentlist[i].DelLink + "&nbsp;";
                }
                if (blog_loginUserId > 0 && blog_loginUserId != tempcommentlist[i].UserId && tempcommentlist[i].UserId > 0) {
                    str += tempcommentlist[i].BlackList;
                }
                str += "</div>";
                str += "</td>";
                str += "</tr>";
                jQuery("#tbl_commentsList").append(str);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            var m = XMLHttpRequest;
            m = textStatus;
            m = errorThrown;
        }
    });
}

function showArticleCommentCount(aid, blogid) {
    var qsData = { "articleid": aid, "blogid": blogid, "gettype": 2 };
    jQuery.ajax({
        type: "get",
        async: false,
        url: "http://comment.blog.hexun.com/comment/get.aspx",
        dataType: "jsonp",
        jsonp: "callbackparam",
        data: qsData,
        jsonpCallback: "success_jsonpCallback",
        success: function (json) {
            var Num = json.Num;
            if (parseInt(Num) == 0) {
                jQuery("#commenttab li").each(function () {
                    if (jQuery.trim(jQuery(this).html()) == "旧版评论") {
                        jQuery(this).hide();
                    } else {
                        jQuery(this).css({ border: "0px" });
                    }
                });
            }
        },
        error: function () {
            //alert("未知错误");
        }
    });
}

var initPage = function (blogname, aid, blogid, page) {
    //initJs("http://comment.blog.hexun.com/js/prototype.js");
    initAddComment(blogname, aid);
    initVoteArticle();
    showArticleComments(blogname, aid, blogid, page);
}