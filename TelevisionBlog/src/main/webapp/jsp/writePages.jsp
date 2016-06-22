<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Static Pages</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Dashboard</h1>
            <hr/>
            <div class="row">
                <div class="col-md-4">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/index.jsp">Back to Blog</a></li>
                        <li><a href="${pageContext.request.contextPath}/posts.jsp">Posts</a></li>
                        <li><a href="${pageContext.request.contextPath}/categories.jsp">Categories</a></li>
                        <li><a href="${pageContext.request.contextPath}/pages.jsp">Pages</a></li>
                        <li><a href="${pageContext.request.contextPath}/users.jsp">Users</a></li>
                    </ul>    
                </div>
                <div class="col-md-8">
                    <div style="float: right">
                        <button id="create-submit" class="btn btn-default">Submit</button>
                    </div>
                    <form method="POST" class="form-horizontal">
                        <input type="text" id="page-title-input" class="form-control"/>
                        <textarea id="page-content-input" class="form-control"></textarea>
                    </form>

                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
