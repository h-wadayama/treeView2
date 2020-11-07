
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
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/message.css" />" rel="stylesheet">
<title>Login</title>
</head>
<body>
	<div class="form-wrapper">
		<h1>LogIn</h1>
		<form id="LoginForm" method="post" action="/${project}/LoginForm">

		    <div class="form-item">
		      <label for="loginId"></label>
		      <input type="text" name="loginId" required="required" placeholder="LoginId"></input>
		    </div>
		    <div class="form-item">
		      <label for="password"></label>
		      <input type="password" name="password" required="required" placeholder="Password"></input>
		    </div>
		    <div class="button-panel">
		      <input type="submit" class="button" title="LogIn" value="LogIn"></input>
		    </div>

			<div class="LoginMessage">
				<t:messagesPanel />
			</div>

		</form>
	</div>
</body>

 <script type="text/javascript">
    function closeWindow() {
    	window.open('about:blank','_self').close();
    }
</script>
</html>