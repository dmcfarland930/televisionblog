<%-- 
    Document   : tagBlogs
    Created on : Jun 28, 2016, 1:55:32 PM
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
        <title>Posts Matching Tag</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <%@include file="navBar.jsp"%>

        <div id="blog-content" class="container">
            <div class="row" style="display: inline">
                <div  class="col-md-8">
                    <div id="viewing-by">
                        <h1>Tag: ${tag}</h1>
                    </div>
                    <c:forEach items="${posts}"  var="post" varStatus="count">
                        <div id="blog-post-div">
                            <a id="blog-title" href="${pageContext.request.contextPath}/blog/${post.title}"><h1>${post.title}</h1></a>
                            <a id="author-name" href="${pageContext.request.contextPath}/blog/author/${post.user.id}"> Posted by ${authors[count.index]} on ${post.stringDateDisplay}</a>
                            <hr>
                            ${post.content}
                            <hr>
                            <a id="category-name" href="${pageContext.request.contextPath}/blog/category/${post.category.id}"> Category: ${post.category.name}</a>
                            <br/>
                            <br/>
                        </div>
                    </c:forEach>


                    <div class="row col-md-8" >
                        <div style="display: inline-block;">

                            <a class="${hidden} btn bg-white" id="last-page" href="${pageContext.request.contextPath}/blog/tag/${tag}/page/${pageLast}" >Last Page</a>

                            <c:if test="${nextPage}">
                                <a class="btn bg-white" id="next-page" href="${pageContext.request.contextPath}/blog/tag/${tag}/page/${pageNext}" >Next Page <span class="glyphicon glyphicon-chevron-right"></span></a>
                                </c:if>
                        </div>
                    </div>
                </div> 
                <%@include file="sideBar.jsp"%>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
                var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>

        
        <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


    </body>
</html>
