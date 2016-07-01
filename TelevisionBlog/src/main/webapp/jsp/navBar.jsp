<%-- 
    Document   : navBar
    Created on : Jun 22, 2016, 9:37:34 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="navbar navbar-inverse navbar-static-top" role="navigation">

    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" rel="home" href="${pageContext.request.contextPath}/" title="Blog Homepage"><span class="navbar-item">Toner Classic Movies</span></a>
    </div>

    <div class="collapse navbar-collapse navbar-ex1-collapse">

        <ul class="nav navbar-nav">
            <li><a class="navbar-item" href="${pageContext.request.contextPath}/contact/send-script"><span class="navbar-item">Send Script</span></a></li>
            <li><a class="navbar-item" href="${pageContext.request.contextPath}/admin/"><span class="navbar-item">Admin</span></a></li>
            <c:forEach items="${pages}" var="p">
                <c:choose>
                    <c:when test="${p.active}">
                        <li><a class="navbar-item" href="${pageContext.request.contextPath}/${p.url}"><span class="navbar-item">${p.name}</span></a></li>
                            </c:when>
                        </c:choose>
                    </c:forEach>
        </ul>
        <div class="col-sm-4 col-md-4 pull-right">
            <form class="navbar-form" method="POST" action="${pageContext.request.contextPath}/search" role="search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" name="search-value">
                    <div class="input-group-btn" style="width: 15%; overflow-x: auto">
                        <select class="form-control" name="search-type">
                            <option value="All">All</option>
                            <option value="Posts">Post Titles</option>
                            <option value="Titles">Post Content</option>
                        </select>
                    </div>
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
