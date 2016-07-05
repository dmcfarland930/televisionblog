<%-- 
    Document   : showProductModal
    Created on : Jun 8, 2016, 10:14:14 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <div class="modal fade" id="deleteCategoryModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Delete Category</h4>
                </div>
                <div class="modal-body">

                    <h1>You have blog posts currently containing this category.</h1>
                    <p>Please change the category of these posts in order to delete this category.</p>
                    <p>You may edit the category of these posts by viewing them in the "Blog List" tab.
                    <p>If you wish to continue, all blog posts belonging to this category will be set to the default category "News".</p>

                    <div class="modal-footer">
                        <button value="${id}" id="category-delete-button" type="button" class="btn btn-default" data-dismiss="modal">DELETE</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</html>

<!--.encodeURIcomponent()-->