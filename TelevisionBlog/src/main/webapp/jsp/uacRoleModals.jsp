<%-- 
    Document   : uacRoleModals
    Created on : Jul 6, 2016, 1:36:09 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="create-role-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Role Creation</h4>
            </div>
            <div class="modal-body">
                <form method="POST" class="form-horizontal">

                    <div class="form-group">
                        <label for="" class="col-md-4 control-label">Role:</label>
                        <div class="col-md-8"><input type="text" class="form-control" id="name-input"/><div id="add-role-validation-errors"></div></div>
                        
                    </div>
                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" id="create-role-submit">Create</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="edit-role-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Edit Role</h4>
            </div>
            <div class="modal-body">
                <form method="POST" class="form-horizontal">

                    <input type="hidden" id="edit-role-id" />
                    <input type="hidden" id="edit-role-user-id" />


                    <div class="form-group">
                        <label for="" class="col-md-4 control-label">Role:</label>
                        <div class="col-md-8"><input type="text" class="form-control" id="edit-role-name"/><div id="add-role-validation-errors"></div></div>
                        
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="edit-role-button">Create</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="error-delete-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Users Assigned to Role</h4>
                <h3>Edit User's Role or Delete Users to Continue</h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="delete-role-table">
                    <tr>
                        <th>User</th>
                        <th>Role</th>
                        <th>Delete</th>
                    </tr>

                    <c:forEach items="${users}" var="user">
                        <tr id="edit-user-row-${user.id}">
                            <td>${user.lastName}, ${user.firstName} (${user.username})</td>
                            <td><select id="user-role-${user.id}" data-user-id="${user.id}" class="remove-role-select form-control" name="edit-user-role">
                                    <c:forEach items="${roles}" var="role">
                                        <option name="${role.name} "value="${role.id}" ${role.id == user.groupId ? "selected='selected'":''}class="user-options form-control">${role.name}</option> 
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${fn:contains(customRolesId , user.id)}">
                                            <c:forEach items="${customRoles}" var="custom">
                                                <c:if test="${custom.userId == user.id}">
                                                    <option name="${custom.name}" value="${custom.id}" ${custom.id == user.groupId ? "selected='selected'":''}class="user-options form-control">${custom.name}</option>
                                                </c:if>

                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option name="Custom" value="3" class="user-options form-control">Custom</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select></td>
                                <td><a href="" class="delete-user-link glyphicon glyphicon-remove" data-user-id="${user.id}" style="color:red;"></a></td>
                        </tr>
                    </c:forEach>

                </table>            
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="can-not-remove-role" class="btn btn-danger" data-dismiss="modal">Users still use this Role</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


