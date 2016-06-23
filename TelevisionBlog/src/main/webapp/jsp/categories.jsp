<%-- 
    Document   : categories
    Created on : Jun 21, 2016, 7:50:58 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        
        <%@include file="navBar.jsp"%>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-3" style="float:none; margin: 0 auto"  id="category-table-container">
                    <table class="table" id="category-table">
                        <tr>
                            <th>Category Name</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach items="${categories}" var="category">
                            <tr id="category-row-${category.id}">
                                <td>${category.name}</td>
                                <td><a class="btn btn-info btn-sm" data-category-id="${category.id}" data-toggle="modal" data-target="#editCategoryModal">Edit</a></td>
                                <td><a class="btn btn-danger btn-sm delete-link" data-category-id="${category.id}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="row">
                <div id="add-category-form" class="col-md-4" style="float:none; margin: 0 auto" class="form-horizontal">
                    <div class="form-group">
                        <form method="POST">
                            <label for="category-name">Add Category:</label>
                            <input id="name-input" class="form-control"/>
                        </form>
                        <div style = "text-align: center; padding-top: 5px">
                            <input type="submit" id="create-submit" value="Submit" class="btn btn-primary">
                        </div>
                    </div>
                </div>
            </div>
        </div>
                <!-- Modal -->
    <div id="editCategoryModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Category</h4>
            </div>
            <div class="modal-body">
                <input hidden="true" id="edit-category-id">
                <div class="form-group">
                    <label for="edit-category-name">Name:</label>
                    <input class="form-control" id="edit-category-name">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="edit-category-button">Edit</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
        </div>
    </div>
        <script>
              contextRoot="/TelevisionBlog";
              
        </script>
        
            
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/categories.js"></script>
    </body>
</html>
