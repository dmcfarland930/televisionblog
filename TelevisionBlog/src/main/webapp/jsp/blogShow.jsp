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

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Blog Viewer</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/blog/writeBlog">Write Post</a></li>
                </ul>    
            </div>


            <div class="container">
                <c:forEach items="${posts}" var="post">
                    <div class="col-md-8">
                    <h1>${post.title}</h1>
                    <p>Posted on: ${date} by ${author}</p>
                    ${post.content}</br></br>
                    </div>
                </c:forEach>

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