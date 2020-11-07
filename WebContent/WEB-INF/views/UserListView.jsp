
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://terasoluna.org/functions" prefix="f"%>
<%@ taglib uri="http://terasoluna.org/tags" prefix="t"%>
<!DOCTYPE HTML PUBLIC "ISO/IEC 15445:2000//DTD HTML//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/message.css" />" rel="stylesheet">
<title>UserListe</title>
<c:url value="/Menu" var="menu" ></c:url>
<c:url value="/UserList/EditUserForm" var="userEditForm" >
<c:param name="page" />
</c:url>
<c:url value="/UserList" var="userListLink" >
<c:param name="p" />
</c:url>
<c:url value="/Menu" var="menu" ></c:url>
</head>
<body>

	<div class="title">
	  ユーザ登録
	  <hr>
	</div>

	<div class="menuArea">
		<table>
			<tr>
				<!-- <td><a href="${menu}">メニューに戻る</a></td> -->
				<td><input type="button" onclick="executionUrl('${menu}');" value="TOPメニュー" > </td>
			</tr>
		</table>
	</div>

	<div class="messageArea">
		<t:messagesPanel />
	</div>

	<div class="main">
		<p class="headerLine"></p>
		<!-- ページング -->
		<div class="pageing">
			<table>
				<tr>
					<c:forEach begin="${UserForm.firstLinkedPage}" end="${UserForm.lastLinkedPage}" var="i">
						<c:choose>
							<c:when test="${UserForm.page == i}">
								<td><B>${i+1}</B></td>
							</c:when>
							<c:when test="${UserForm.page != i}">
							<td>
								<a href="${userListLink}&page=${i}">${i+1}</a>
							</td>
							</c:when>
						</c:choose>

					</c:forEach>
				</tr>
			</table>
		</div>

		<div class="userListButton">
			<table>
				<tr>
					<td><input type="button" onClick="OnButtonClick();" value="追加" ></td>
				</tr>
			</table>
		</div>

		<div class="userList">
			<table class="tbUserList" border="1" style="border-collapse: collapse">
				<tr>
					<th bgcolor="#191970" width="100"><font color="#FFFFFF">ユーザID</font></th>
					<th bgcolor="#191970" width="200"><font color="#FFFFFF">氏名</font></th>
					<th bgcolor="#191970" width="80"><font color="#FFFFFF">郵便番号</font></th>
					<th bgcolor="#191970" width="200"><font color="#FFFFFF">住所１</font></th>
				<!--
					<th>住所２</th>
					<th>住所３</th>
					<th>TEL</th>
				-->
				</tr>

				<c:forEach var="varList" items="${UserForm.pageList}">
					<tr>
						<td class="tdUserList"><c:out value="${varList.userid}"/></td>
						<td class="tdUserList"><c:out value="${varList.name}"/></td>
						<td class="tdUserList"><c:out value="${varList.zipcode}"/></td>
						<td class="tdUserList"><c:out value="${varList.address1}"/></td>
				<!--
						<td class="tdUserList"><c:out value="${varList.address2}"/></td>
						<td class="tdUserList"><c:out value="${varList.address3}"/></td>
						<td class="tdUserList"><c:out value="${varList.telno}"/></td>
				-->
						<td class="tdUserList"><input type="button" onClick="EditFormButtonClick('${varList.userid}');" value="編集" ></td>
						<td class="tdUserList"><input type="button" onClick="DeleteFormButtonClick('${varList.userid}');" value="削除" ></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

	<script type="text/javascript">
			var i = 0;

		    function OnButtonClick() {
		    	var url = "${userEditForm}${UserForm.page}&mode=1";
		    	location.href=url;
		    }

		    function EditFormButtonClick(userId){
		    	var url = "${userEditForm}${UserForm.page}&userId=" + userId + "&mode=2" + "&preScreen=2";
		    	location.href=url;
		    }
		    function DeleteFormButtonClick(userId){
		    	var url = "${userEditForm}${UserForm.page}&userId=" + userId + "&mode=3";
		    	location.href=url;
		    }

		    function executionUrl(url){

		    	location.href=url;
		    }

	</script>

</body>
</html>