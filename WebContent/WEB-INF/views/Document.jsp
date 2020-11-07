<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://terasoluna.org/functions" prefix="f"%>
<%@ taglib uri="http://terasoluna.org/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "ISO/IEC 15445:2000//DTD HTML//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/message.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/dtree.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/dtree.js" />" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/js/test.js" />" type="text/javascript" charset="utf-8"></script>
<title>Insert title here</title>
</head>

<body>

<div class="dtree">
    <p>
      <a href="javascript: treeObj.openAll();">open all</a> |
      <a href="javascript: treeObj.closeAll();">close all</a> |
      <a href="javascript: treeObj.openTo(4, true);">open to: Node 4</a>
    </p>
	<form name = "frmTreeView">


		<c:forEach items="${documentModel}" var="obj" varStatus="status">

				<!--
				JavaのModelに格納してる値をJavaScriptに渡す。
				 -->
	    		<script type="text/javascript">

					var listSize = "${fn:length(documentModel)}";
					var listCount = "${status.index}"
					var object = new Array();
					var objType ="";

					if (Number(listCount) == 0){
						var treeObj=new dTree('treeObj');
					}

					object.push(String("${obj.id}"));            //0 id
					object.push(String("${obj.objectTipe}"));    //1 属性（1:フォルダ 2:ファイル）
					object.push(String("${obj.objectName}"));    //2 表示名
					object.push(String("${obj.pathl}"));         //3 ファイル格納先パス
					object.push(String("${obj.objLevel}"));      //4 階層レベル
					object.push(String("${obj.parentId}"));      //5 親ID

					if (object[1]==1){
						objType = treeObj.icon.folder;
					}else{
						objType = treeObj.icon.node;
					}

					if (!object[5]){
						//親IDがないときは、親IDを「-1」に設定。「TOP」
						treeObj.add(object[0],-1,object[2]);
					}

					treeObj.add(object[0],object[5],object[2],'','','',objType);



					if (listSize == Number(listCount)+1){
						document.write(treeObj);
					}


				</script>

		</c:forEach>

	</form>


</div>

</body>

</html>