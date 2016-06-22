<%-- 
    Document   : home
    Created on : Jun 22, 2016, 9:34:17 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <jsp:include page="navBar.jsp"/>
        <div class="container">
            <h2>Home Page</h2>
            <form method="post">
                <input type ="text" id="title-input"
                <textarea id="blog-entry">Blog Post</textarea>
                <fieldset class="form-group ">
                    <input id="blog-post-input" class="btn bg-primary button-size" type="submit" value="Create Blog Post"/>
                </fieldset>
            </form>

        </div>
        <table id="post-table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Category</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${blogPosts}" var="blogPost">
                    <tr>
                        <td>${blogPost.title}</td>
                        <td>${blogPost.category.name}</td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script>
              contextRoot="/TelevisionBlog";
              
          </script>
        
            
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/tinymce/js/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>
        <script src="${pageContext.request.contextPath}/js/categories.js"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#blog-entry',
                height: 400,
                width: 800
            });
        </script>
    </body>
    
</html>
