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
                        <button class="btn btn-default"><a href="" data-toggle="modal" data-target="#createCategoryModal">Create Category</a></button>
                    </div>
                </div>
            </div>
            <br />
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>

                <div class="col-md-8">
                    <table class="table table-bordered" style="text-align: center;" id="category-table">                       

                        <tr>
                            <th colspan="6">Category List</th>
                        </tr>

                        <tr>
                            <th>Name</th>
                            <th>Edit</th>
                            <th>Post Count</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${categories}" var="category">
                            <tr id="category-row-${category.id}">
                                <td>${category.name}</td>
                                <td><a href="" class="glyphicon glyphicon-edit" style="color:green;" data-toggle="modal" data-category-id="${category.id}" data-target="#editCategoryModal"></a></td>
                                <td>${category.postCount}</td>
                                <td><a href="" data-category-id="${category.id}" class="glyphicon glyphicon-remove delete-category-link" style="color:red;"></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div id="createCategoryModal" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Create Category</h4>
                    </div>
                    <div class="modal-body">
                        <div id="add-category-form" class="col-md-4" style="float:none; margin: 0 auto" class="form-horizontal">
                            <div class="form-group">
                                <form method="POST">
                                    <label for="category-name">Add Category:</label>
                                    <input id="name-input" class="form-control"/>
                                    <div id="add-category-validation-errors"></div>
                                    <div style = "text-align: center; margin-top: 15px">

                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-danger" id="create-submit" value="Create"/>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
                            <div id="edit-category-validation-errors"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="edit-category-button">Edit</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="deleteCategoryModal.jsp"%>

        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/categories.js"></script>

    </body>
</html>
