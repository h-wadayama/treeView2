
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Password Change</title>
<c:url value="/Menu" var="menu" ></c:url>
<link href="<c:url value="/resources/css/PassWordChange.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/message.css" />" rel="stylesheet">
</head>
<body>
	<form id="PassWordChangeForm" method="post" action="/${project}/PassWordChangeForm">
		<div class="PassWordChange">
			<div align="center" class="NewPassword">
				<p><input type="hidden" name="loginId" value="${loginId}"></p>
				<table>
					<tr>
						<td>旧パスワード </td>
						<td><input name ="oldPassword" type="password" name="oldPassword"></td>
					</tr>

					<tr>
						<td>新パスワード </td>
						<td><input name = "newPassword" type="password" name="newPassword"></td>
					</tr>

					<tr>
						<td>新パスワード（確認用）</td>
						<td><input name ="newPasswordSeccond" type="password" name="newPassword2"></td>
					</tr>

				</table>
				<table class="Button">
					<tr>
						<td><input type="submit" name="login" value="変更" /></td>
						<td><input type="button" value="戻る" onClick="document.location='${menu}';"></td>
					</tr>
				</table>
			</div>

		</div>

		<div class="PassWordChangeMessage">
			<t:messagesPanel />
		</div>
	</form>
</body>
</html>