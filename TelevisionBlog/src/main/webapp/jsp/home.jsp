<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    </head>
    <body>
        <%@include file="navBar.jsp"%>

        <div class="container">
            <div class="col-md-8">
                <c:forEach items="${posts}" var="post">
                    <a id="blog-title" href="${pageContext.request.contextPath}/blog/${post.title}"><h1>${post.title}</h1></a>
                    <a id="author-name" href="${pageContext.request.contextPath}/blog/author/${post.user.id}"> Posted by ${post.user.firstName} ${post.user.lastName} on ${post.stringDateDisplay}</a>
                    ${post.content}</br>
                    <a id="category-name" href="${pageContext.request.contextPath}/blog/category/${post.category.id}"> Category: ${post.category.name}</a>
                    <hr/>
                </c:forEach>
            </div>

            <div id="category-div" class="col-md-4">
                <p>Categories:</p>
                <hr>
                <c:forEach items="${categories}" var="category">
                    <c:if test="${category.postCount != 0}">
                    <a id="category-name" href="${pageContext.request.contextPath}/blog/category/${category.id}">${category.name} (${category.postCount})</a>
                    </c:if>
                    <br/>
                </c:forEach>
            </div>
            <div id="tag-div" class="col-md-4">
            <br/>
            <br/>
                <p>Tags:</p>
                <hr>
                <c:forEach items="${tags}" var="tag">
                    <a id="tag-name" href="${pageContext.request.contextPath}/blog/tag/${tag.id}">${tag.name}</a>
                    <br/>
                </c:forEach>
            </div>

            <div class="row col-md-8" >
                <div style="display: inline-block;">

                    <a class="${hidden}" id="last-page" href="${pageContext.request.contextPath}/blog/page/${pageLast}" > < Last Page</a>

                    <c:if test="${nextPage}">
                        <a id="next-page" href="${pageContext.request.contextPath}/blog/page/${pageNext}" >Next Page > </a>
                    </c:if>
                </div>
            </div>
        </div> 
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>



    </body>
</html>
