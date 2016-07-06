<%-- 
    Document   : sideBar
    Created on : Jul 6, 2016, 11:14:02 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div id="sidebar" class="col-md-4">
        <div id="latest-posts-div" class="col-md-10">
            <br/>
            <p id="latest-head">Latest Posts:</p>
            <hr>
            <c:forEach items="${latestPosts}" var="latestPost">
                <a id="blog-title" href="${pageContext.request.contextPath}/blog/show/${latestPost.url}">${latestPost.title}</a> 
                <br/>
            </c:forEach>
            <br/>
        </div>
        <div id="category-div" class="col-md-10">
            <br/>
            <p id="cat-head">Categories:</p>
            <hr>
            <c:forEach items="${categories}" var="category">
                <c:if test="${category.postCount != 0}">
                    <a id="category-name" href="${pageContext.request.contextPath}/blog/category/${category.id}">${category.name} (${category.postCount})</a>
                    <br/>
                </c:if>
            </c:forEach>
            <br/>
        </div>
        <div id="tag-div" class="col-md-10">
            <br/>
            <p id="tag-head">Tags:</p>
            <hr>
            <c:forEach items="${tags}" var="tag">
                <span class="tag-link"><a id="tag-name" href="${pageContext.request.contextPath}/blog/tag/${tag.name}">${tag.name}</a></span>
                <br/>   
            </c:forEach>
        </div>
        <div id='twitter-container' >
            <div id="twitter" class="col-md-10">
                <a class="twitter-timeline" data-height="360" href="https://twitter.com/tonermovies">Tweets by TonerClassicMovies</a> 
            </div>
        </div>
    </div>
</html>
