<%-- 
    Document   : searchresults
    Created on : Jun 30, 2016, 1:49:04 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Results</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <%@include file="navBar.jsp"%>

        <h1>Showing matching results for "${searchValue}":</h1>
        <div class="container">
            <c:forEach items="${posts}"  var="post" varStatus="count">
                <div class="col-md-8">
                    <a id="blog-title" href="${pageContext.request.contextPath}/blog/${post.title}"><h1>${post.title}</h1></a>
                    <a id="author-name" href="${pageContext.request.contextPath}/blog/author/${post.user.id}"> Posted by ${authors[count.index]} on ${post.stringDateDisplay}</a>
                    ${post.content}<br>
                    <a id="category-name" href="${pageContext.request.contextPath}/blog/category/${post.category.id}"> Category: ${post.category.name}</a>
                    <hr/>
                </div>
            </c:forEach>


            <div class="row col-md-8" >
                <div style="display: inline-block;">

                    <a class="${hidden}" id="last-page" href="${pageContext.request.contextPath}/blog/tag/${tagId}/page/${pageLast}" >Last Page</a>

                    <c:if test="${nextPage}">
                        <a id="next-page" href="${pageContext.request.contextPath}/blog/tag/${tag}/page/${pageNext}" >Next Page <span class="glyphicon glyphicon-chevron-right"></span></a>
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
