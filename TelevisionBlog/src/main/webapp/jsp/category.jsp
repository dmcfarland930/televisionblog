<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Categories</title>
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
                        <button class="btn btn-default"><a href="${pageContext.request.contextPath}/blog/writeBlog">Create User</a></button>
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
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/category">Category List</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/page/">Page List</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/user/">User List</a></li>
                    </ul>    
                </div>

                <div class="col-md-8">
                    <table class="table table-bordered" style="text-align: center;" id="pending-post-table">                       

                        <tr>
                            <th colspan="6">Category List</th>
                        </tr>

                        <tr>
                            <th>Name</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${categories}" var="category">
                            <tr id="category-row-${category.id}">
                                <td>${category.name}</td>
                                <td><a href="" class="glyphicon glyphicon-edit" style="color:green;"><span  /></a></td>
                                <td><a href="" data-post-id="${category.id}" class="glyphicon glyphicon-remove delete-category-link" style="color:red;"></a></td>
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
        <script src="${pageContext.request.contextPath}/js/adminApp.js"></script>

    </body>
</html>
