<%@page import="app.co.jp.entity.IdEntity"%>
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
<meta http-equiv="Content-Type" content="multipart/form-data; boundary=---------------------------102852708831426\r\n">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/message.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jstree/_lib/jquery.js" />" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/jstree/_lib/jstree.min.js" />" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/js/test.js" />" type="text/javascript" charset="utf-8"></script>
<link href="<c:url value="/resources/jstree/css/style.min.css" />" rel="stylesheet">
<c:url value="/jsTree/Download" var="FileDownLoad" />
<title>ツリービュー表示</title>
</head>

<body>
	<form id = "treeForm" method="post" action="/${project}/jsTree/Edit" enctype="multipart/form-data">
		<button id="button1">実行</button><input type="file" name="fileUpload" accept="*">
		<div id="divTree"></div>
		<div id="divParentId">
			<input type="text" id="element" name="element" value="">
		</div>
		<div id="divJsonData">
			<table>
				<tr>
					<textarea name="jsonData" rows="30" cols="46">${jsonData}</textarea>
				</tr>
			</table>
		</div>

	</form>
    <script type="text/javascript">
    document.getElementById('divJsonData').style.visibility = 'hidden';
//    document.getElementById('divParentId').style.visibility = 'hidden';

    var object = ${jsonData};
    console.log(object);

    $('#divTree').jstree({ 'core':
	{
	   'data': object,
	   "check_callback" : true
	},
	"plugins" : [ "contextmenu" ],
	"contextmenu":{
	 	"items":function($node){
	 		return {
	 			//フォルダ作成
	 			"createFolder":{
	 				"separator_before": false,
	 				"separator_after": false,
	 				"label": "新規フォルダ作成",
	 				"_disabled": function(data){
	 					 return $.jstree.reference(data.reference)
	 					.get_node(data.reference).icon != "jstree-folder";
	 				},
	 				"action": function(data){
	 				 var inst = $.jstree.reference(data.reference),
	 					obj = inst.get_node(data.reference);
	 					inst.create_node(obj, { text:'New Folder', 'icon':'jstree-folder' }, "last", function(new_node){
	 					try{
	 				   		//inst.edit(new_node);
	 				   		inst.edit(new_node, new_node.text, function(data){
	 				   			//クライアント側で↓これをやると整合性が取れなくなってしまうみたいなので
	 				   			//サーバ側で実施するように修正。2020/5/5 H.wadayama
		 				   		//var id_After = data.id.replace('j1_','');
		 				   		//data.id = id_After;

		 				   		console.log("create  id = " + data.id  );
	 				   		});

	 					}catch(ex){
	 						setTimeout(function(){ inst.edit(new_node); },0);
	 					}

	 				  });

	 				}
	 			},
	 			"rename":{
	 				"separator_before": true,
	 				"separator_after": false,
	 				"label": "名称の変更",
	 				"_disabled": false,
	 				"action": function(data){
	 					var inst = $.jstree.reference(data.reference),
	 					obj = inst.get_node(data.reference);
	 					inst.edit(obj);
	 				}
	 			},
	 			"remove":{
	 				"separator_before": false,
	 				"separator_before": false,
	 				"label": "削除",
	 				"_disabled": function(data){
	 					return $.jstree.reference(data.reference).get_node(data.reference).parent == "#";
	 				},
	 				"action": function(data){
	 					var inst = $.jstree.reference(data.reference),obj = inst.get_node(data.reference);
	 					if (inst.is_selected(obj)){
	 						inst.delete_node(inst.get_selected());
	 					}else{
	 						inst.delete_node(obj);
	 					}
	 				}

	 			},
	 			"download":{
	 				"separator_before": false,
	 				"separator_before": false,
	 				"label": "ダウンロード",
	 				"_disabled": function(data){
	 					return $.jstree.reference(data.reference).get_node(data.reference).icon != "jstree-file";
	 				},
	 				"action": function(data){
	 					var id = $.jstree.reference(data.reference).get_node(data.reference).id;
	 					var url = "${FileDownLoad}?Id="+id;

	 					window.location.href = url;
	 				}
	 			}
	 		}
	 	}
	 }

})
	.on("select_node.jstree", function(e, data){
	   //console.log("selected is : id =" + data.node.id +"  "+ data.node.text);

	   var i, j, r = [];

	    for(i = 0, j = data.selected.length; i < j; i++) {
	      r.push(data.instance.get_node(data.selected[i]).id);
	      r.push(data.instance.get_node(data.selected[i]).icon);
	    }

	    $('#element').val(r.join(', '));


	}
)
	.on("changed.jstree", function(e, data){
	    //console.log("changed is : id =" + data.node.id +"  "+ data.node.text);
	}

);

$('#button1').click(function() {
  var v = $("#divTree").jstree(true).get_json();
  var jonStr = JSON.stringify(v);

  console.log(v);
  console.log(jonStr);
  var jsonData = $('textarea[name="jsonData"]').val();
  $('textarea[name="jsonData"]').val(jonStr);

  //var jsonData = document.forms.treeForm.jsonData.value;
  //var jsonData = $('textarea[name="jsonData"]').val();
  //if (jsonData == null || jsonData == ''){
	  //$("#jsonData").val(jonStr);
  //var jsonData = $('textarea[name="jsonData"]').val();
  // $('textarea[name="jsonData"]').val(jonStr);
  //}

   $('#treeForm').submit();
  // $('#uploadFile').submit();


});


    </script>

</body>

</html>