<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Static Pages</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">


        <style>
            th {
                text-align: center;
            };
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Dashboard</h1>
            <hr/>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8">
                    <div style="float: right">
                        <button class="btn btn-default"><a href="${pageContext.request.contextPath}/page/create">Create New Page</a></button>
                    </div>
                </div>
            </div>
            <br />
            <div class="row">
                <div class="col-md-4">
                    <ul class="list list-group">
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}">Back to Blog</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/">Pending Posts</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/post/">Blog List</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/categories">Category List</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/page/">Page List</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/user/">User List</a></li>
                    </ul>    
                </div>

                <div class="col-md-8">
                    <table class="table table-bordered" style="text-align: center;">                       

                        <tr>
                            <th colspan="3">Static Page List</th>
                        </tr>

                        <tr>
                            <th>Title</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${pages}" var="page">
                            <tr id="page-row-${page.id}">
                                <td><a href="${pageContext.request.contextPath}/page/show/${page.url}">${page.name}</a></td>
                                <td><a href="${pageContext.request.contextPath}/page/create">Edit</a></td>
                                <td><a href="" data-page-id="${page.id}" class="delete-page-link">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>


        <script>
                        var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/pageApp.js"></script>

    </body>
</html>