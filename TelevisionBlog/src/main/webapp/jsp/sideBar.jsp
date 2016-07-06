<%-- 
    Document   : sideBar
    Created on : Jul 6, 2016, 11:14:02 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <div id="sidebar" class="col-md-4">
        <div id="latest-posts-div">
            <br/>
            <p id="latest-head">Latest Posts:</p>
            <hr>
            <c:forEach items="${latestPosts}" var="latestPost">
                <a id="blog-title" href="${pageContext.request.contextPath}/blog/show/${latestPost.url}">${latestPost.title}</a> 
                <br/>
            </c:forEach>
            <br/>
        </div>
        <div id="category-div">
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
        <div id="tag-div">
            <br/>
            <p id="tag-head">Tags:</p>
            <hr>
            <c:forEach items="${tags}" var="tag">
                <span class="tag-link"><a id="tag-name" href="${pageContext.request.contextPath}/blog/tag/${tag.name}">${tag.name}</a></span>
                <br/>   
            </c:forEach>
        </div>
        <div id="archive-div">
            <div id="archive-head">
                <p>Post Archive:</p>
            </div>
            <div id="archive-content">
                <c:forEach items="${months}" var="month">
                    <a href="<c:url value="/blog/archive/${month.key}" />">${month.key} (${month.value})</a><br>
                    
                </c:forEach>
            </div>
        </div>
        <div id='twitter-container'>
            <div id="twitter">
                <a class="twitter-timeline" data-height="360" href="https://twitter.com/tonermovies">Tweets by TonerClassicMovies</a> 
            </div>
        </div>
        
    </div>
