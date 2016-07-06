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
        <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <%@include file="navBar.jsp"%>

        <div id="blog-content" class="container">
            <div class="row" style="display: inline">
                <div id="viewing-by" class='col-md-8'>
                    <h1>Posts by ${author}</h1>
                </div>
                <c:forEach items="${posts}" var="post">
                    <div id="blog-post-div" class="col-md-8">
                        <a id="blog-title" href="${pageContext.request.contextPath}/blog/show/${post.url}"><h1>${post.title}</h1></a>
                        <a id="author-name" href="${pageContext.request.contextPath}/blog/author/${post.user.id}"> Posted by ${author} on ${post.stringDateDisplay}</a>
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

                        <a class="${hidden} btn bg-white" id="last-page" href="${pageContext.request.contextPath}/blog/author/${authorId}/page/${pageLast}" > < Last Page</a>

                        <c:if test="${nextPage}">
                            <a class="btn bg-white" id="next-page" href="${pageContext.request.contextPath}/blog/author/${authorId}/page/${pageNext}" >Next Page > </a>
                        </c:if>
                    </div>
                </div>

                <%@include file="sideBar.jsp"%>

            </div> 
        </div>
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
