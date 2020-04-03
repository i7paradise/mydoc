<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <jsp:include page="includes.jsp"/>
    <link rel="stylesheet" href="/static/css/index.css">
    <title>hello my doc</title>
</head>
<body class="text-center">

	<h1>MyDoc</h1>
	<h2 class="h3 mb-3 font-weight-normal">Please sign in</h2>
    
    <form class="form-signin" action="/authenticate" method="post">
    	<c:if test="${not empty errorMsg}">
    		<div class="alert alert-danger" role="alert">${errorMsg}</div>
    	</c:if>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus />
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required />
        <div class="checkbox mb-3">
            <label>
                <input name="rememberMe" type="checkbox" value="remember-me" /> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">© 2017-2018</p>
    </form>


</body>
</html>
