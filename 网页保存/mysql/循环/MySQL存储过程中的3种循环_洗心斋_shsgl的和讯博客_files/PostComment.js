//�жϱ������Ƿ�Ϊ��
function CheckTitle(e)
{
    if(typeof(initAddComment)=="function")
    {
        initAddComment();
    }
    CopyToClipboard('NoHtmlCommentContent');
	if(e.CommentTitle.value=="")
	{
		alert("���ⲻ��Ϊ�ա�");
		return false;
	}
	return true;
}

function CopyToClipboard(theField) 
{
    try
    {
        if (document.all && window.external)
        {
	        eval("tempval = document.getElementById('"+theField+"')");			
	        if(tempval)
	        {
		        tempval.focus();
		        tempval.document.execCommand("SelectAll");
		        tempval.document.execCommand("Copy");
	        }
	    }
	}
	catch(e)
	{
	}
}

//�����·��ﳤ�ȡ�
function SymError()
{
	return true;
}
window.onerror = SymError;

function strlength(str)
{
	var l=str.length;
	var n=l;
	for (var i=0;i<l;i++)
	{
		if (str.charCodeAt(i)<0||str.charCodeAt(i)>255)
		{
			n++;
		}
	}
	return n;
}
			
function changebyte1(num,length)
{
	var l=strlength(document.getElementById("NoHtmlCommentContent").value);
	if (l<=length) {
		if (document.all!=null)
		document.getElementById("byte"+num).innerHTML="��������������"+(length-l)/2+"���֣�";
	}
	else
	{
		document.getElementById("byte"+num).innerHTML="(����2000�����������ˣ������±༭��)";
	}
	return true;
}

function CheckNoHtmlCommentContent()
{
    var content = document.getElementById("NoHtmlCommentContent");
    var title = document.getElementById("CommentTitle");
    if(!title.value)
    {
        alert("��������⡣");
        title.focus();
        return false;
    }
	if(content.value=="")
	{
		alert("����д���ݡ�");
		content.focus();
		return false;
	}
	if(content.value=="��������Ϊ2000��")
	{
		alert("����д���ݡ�");
		content.focus();
		return false;
	}
	
	var CheckL = strlength(content.value);
	if(CheckL>4000)
	{
		alert("����2000�����������ˣ������±༭��");
		content.focus();
		return false;
	}
	
	if(document.getElementById("VerificationDiv").style.display != "none")
	{
	    var vInput = document.getElementById("VerificationInput");
	    if(vInput)
	    {
	        if (vInput.value == "")
	        {
		        alert("��������֤�롣");
		        vInput.focus();
		        return false;
		    }
	    }
	}
	return true;
}