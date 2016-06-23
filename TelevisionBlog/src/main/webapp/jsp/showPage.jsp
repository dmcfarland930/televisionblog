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
        
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <style>
            th {
                text-align: center;
            };
        </style>
    </head>
    <body>
        <%@include file="navBar.jsp"%>
        <div class="container">
        
            <div class="row">
                <div class="col-md-8">
                    <h2>${page.name}</h2>
                    <br />
                    <div class="container" id="display-page-content">
                        <p>${page.content}</p>
                    </div>
                </div>
                <div classs="col-md-4">
                    
                </div>
            </div>
        </div>


        <script>
                        var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/pageApp.js"></script>

    </body>
</html>
