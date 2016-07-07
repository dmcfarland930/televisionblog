<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Blog View</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">
    </head>
    <body>
        <%@include file="navBar.jsp"%>
                <div class="container">
            
            <hr/>

            <div class="row">

                <div class="login col-md-4 col-md-offset-4">
                    <h1 style="text-align: center; 
    font-family: 'Cabin', sans-serif;">404</h1>
                    <hr> 
                    <img  class="col-md-8 col-md-offset-2" src="${pageContext.request.contextPath}/img/404.jpg" alt="404" />
                </div>
               
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>
        <!--        <script type="text/javascript">
                    tinymce.init({
                        selector: '#blog-post-input',
                        height: 400,
                        width: 800
                    });
                </script>-->


    </body>
</html>

