
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://terasoluna.org/functions" prefix="f"%>
<%@ taglib uri="http://terasoluna.org/tags" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<title>Insert title here</title>
<c:url value="/UserList" var="userList" ></c:url>
<c:url value="/PassWordChangeForm" var="passWordChangeForm" ></c:url>
<c:url value="/UserList/EditUserForm" var="userEditForm" >
<c:param name="page" />
</c:url>
</head>
<body>
	<div class="form-wrapper">
		<div class="MenuForm">
			<div class="MenuHeader">
				TOP MENU
			</div>

			<div class="Menu1">
				<table>
					<c:choose>
						<c:when test="${authorityLevel == '1'}">
							<tr>
								<td>
									<a href="${userList}">ユーザ登録</a>
								</td>
							</tr>
						</c:when>
						<c:when test="${authorityLevel == '2'}">
							<tr>
								<td>
									<a href="${userList}">ユーザ登録</a>
								</td>
							</tr>
							<tr>
								<td>
									<a href="javascript:void(0);" onclick="EditFormButtonClick('${loginId}');" >ログイン情報</a>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>
									<a href="javascript:void(0);" onclick="EditFormButtonClick('${loginId}');" >ログイン情報</a>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>

<!--
					<c:if test="${authority == 1}" >
						<tr>
							<td>
								<a href="${userList}">ユーザ登録</a>
							</td>
						</tr>
					</c:if>

					<c:if test="${loginId != 'system'}" >
						<tr>
							<td>
								<a href="javascript:void(0);" onclick="EditFormButtonClick('${loginId}');" >ログイン情報</a>
							</td>
						</tr>
					</c:if>
-->

				</table>
			</div>


			<div class="Menu2">
				<table>
					<c:choose>
						<c:when test="${authorityLevel == 2}">
							<tr>
								<td>
									<a href="${passWordChangeForm}">パスワード変更</a>
								</td>
							</tr>
						</c:when>
						<c:when test="${authorityLevel == 3}">
							<tr>
								<td>
									<a href="${passWordChangeForm}">パスワード変更</a>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>

<!--
					<c:if test="${loginId != 'system'}" >
						<tr>
							<td>
								<a href="${passWordChangeForm}">パスワード変更</a>
							</td>
						</tr>
					</c:if>
 -->

				</table>
			</div>

		</div>
	</div>

  <script type="text/javascript">

    function closeWindow() {
    	window.open('about:blank','_self').close();
    }

    function EditFormButtonClick(userId){
    	var url = "${userEditForm}${UserForm.page}&userId=" + userId + "&mode=2" + "&preScreen=1";
    	location.href=url;
    }

  </script>
</body>
</html>