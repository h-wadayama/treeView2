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
<title>Insert title here</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/message.css" />" rel="stylesheet">
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

				<c:if test="${authorityLevel == '1' or authorityLevel == '2'}">
					<td><input type="button" onClick="OnButtonClick();" value="ユーザリスト" ></td>
				</c:if>
				<c:if test="${mode == 1 or mode == 4}">
				<!-- 	<td><input type="submit" name="insert" value="追加" /></td> -->
					<td><input type="button" onclick="insUpd();" name="insert" value="追加" /></td>
				</c:if>
				<c:if test="${mode == 2}">
				<!-- <td><input type="submit" name="update" value="更新" /></td> -->
					<td><input type="button" onclick="insUpd();" name="update" value="更新" /></td>
				</c:if>

			</tr>
		</table>
	</div>

	<div class="messageArea">
		<t:messagesPanel />
	</div>

	<div class="main">
		<p class="headerLine"></p>

		<form id="UserForm" name="userForm" method="post" action="/${project}/UserForm/Edit">

			<p><input type="hidden" name="mode" value="${mode}"></p>
			<p><input type="hidden" name="page" value="${page}"></p>
			<p><input type="hidden" name="preScreen" value="${preScreen}"></p>
			<table class = "userForm">
				<tr>
					<c:choose>
						<c:when test="${mode == 1}">
							<td class="item">ユーザID</td>
							<td><input type="text" name="userid" value = "${userModel.userid}">
							</td>
						</c:when>
						<c:otherwise>
							<td class="item">ユーザID</td>
							<input type="hidden" name="userid" value = "${userModel.userid}" >
							<td><input type="text" name="userid" value = "${userModel.userid}" disabled="disabled" >
							</td>
						</c:otherwise>
					</c:choose>

					<c:if test="${mode == 1}">

					</c:if>

				</tr>
				<tr>
					<td class="item">氏名</td><td><input type="text" name="name"
					value = "${userModel.name}"></td>
				</tr>
				<tr>
					<td class="item">郵便番号</td><td><input type="text" name="zipcode"
					value = "${userModel.zipcode}"></td>
				</tr>
				<tr>
					<td class="item">住所１</td><td><input type="text" name="address1"
					value = "${userModel.address1}"></td>
				</tr>
				<tr>
					<td class="item">住所２</td><td><input type="text" name="address2"
					value = "${userModel.address2}"></td>
				</tr>
				<tr>
					<td class="item">住所３</td><td><input type="text" name="address3"
					value = "${userModel.address3}"></td>
				</tr>
				<tr>
					<td class="item">電話番号</td><td><input type="text" name="telno"
					value = "${userModel.telno}"></td>
				</tr>
				<tr>
					<td class="item">Mail Address</td><td><input type="text" name="mailaddress"
					value = "${userModel.mailaddress}"></td>
				</tr>
				<tr>
					<td class="item">管理者権限</td>
					<c:choose>
						<c:when test = "${authority == '1'}">
							<c:choose>
								<c:when test = "${userModel.authority == '1'}">
									<td><input type="checkbox" name="authority" checked="checked"></td>
								</c:when>
								<c:otherwise>
									<td><input type="checkbox" name="authority"></td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test = "${userModel.authority == '1'}">
									<td><input type="checkbox" name="authority" checked="checked" disabled="disabled"></td>
								</c:when>
								<c:otherwise>
									<td><input type="checkbox" name="authority" disabled="disabled"></td>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>

		</form>

	</div>

</body>
</html>

<script type="text/javascript">
//	var i = 0;
//	if( "${insUpd}" == '1' ){
//		alert("${message}");
//	}

    function OnButtonClick() {
    	var url = "${userListLink}&page=${page}";
    	var menuUrl = "${menu}";

    	if( "${preScreen}" == '1' ){
    		//遷移前画面がメニュー画面の場合
    		location.href=menuUrl;
    	}else{
    		//遷移前画面がユーザリスト画面の場合
    		location.href=url;
    	}

    }

    function insUpd(){

    	var action = '/${project}/UserForm/Edit';
    	document.userForm.action = action;
    	document.userForm.method="post";
    	document.userForm.submit();

    }

    function executionUrl(url){

    	location.href=url;
    }


</script>