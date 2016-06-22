<%-- 
    Document   : categories
    Created on : Jun 21, 2016, 7:50:58 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <jsp:include page="navBar.jsp"/>
        <table class="table" id="category-table">
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.name}</td>
                </tr>
            </c:forEach>
        </table>
        <div id="add-category-form" class="col-md-4" class="form-horizontal">
            <div class="form-group">
                <form method="POST">
                    <label for="category-name">Add Category:</label>
                    <input id="name-input" class="form-control"/>
                </form>
                <input type="submit" id="create-submit" value="Submit" class="btn btn-primary">
            </div>
        </div>
        <script>
              contextRoot="/TelevisionBlog";
              
        </script>
        
            
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/categories.js"></script>
    </body>
</html>
