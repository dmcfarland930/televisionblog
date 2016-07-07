<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LogIn</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <%@include file="navBar.jsp"%>
        <div class="container">
            
            <hr/>

            <div class="row">

                <div class="login col-md-4 col-md-offset-4">
                    <h1 style="text-align: center">User Login</h1>
                    <hr>
                    <form action="${pageContext.request.contextPath}/authenticate/j_spring_security_check" method="POST" class="form-horizontal">
                        
                        <div class="form-group">
                            <label for="username" class="control-label col-md-4">Username:</label>
                            <div class="col-md-8"><input type="text" name="username" class="form-control" /></div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="control-label col-md-4">Password:</label>
                            <div class="col-md-8"><input type="password" name="password" class="form-control" /></div>
                        </div>

                <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->

                        <input type="submit" value="Login" class="btn login-btn btn-default center-block" />


                        <c:if test="${loginError == 1}">
                            <div id="error-login">Error logging in. Please supply valid credentials.</div>
                        </c:if>

                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>





        <script>
            var contextRoot = "${pageContext.request.contextPath}";

        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>

    </body>
</html>
