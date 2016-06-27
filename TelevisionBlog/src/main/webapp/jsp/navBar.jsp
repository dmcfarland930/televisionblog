<%-- 
    Document   : navBar
    Created on : Jun 22, 2016, 9:37:34 AM
    Author     : apprentice
--%>

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
            <li><a class="navbar-item" href="${pageContext.request.contextPath}/admin/"><span class="navbar-item">Admin</span></a></li>

            <c:forEach items="${pages}" var="p">
                <li><a class="navbar-item" href="${pageContext.request.contextPath}/${p.url}"><span class="navbar-item">${p.name}</span></a></li>
            </c:forEach>
        </ul>

    </div>
</div>
