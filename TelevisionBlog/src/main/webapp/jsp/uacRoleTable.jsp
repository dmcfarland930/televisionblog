<%-- 
    Document   : uacRoleTable
    Created on : Jul 6, 2016, 1:29:37 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table class="table table-bordered" style="text-align: center;" id="user-table">                       


    <tr>
        <th colspan="3">Roles</th>
        <th colspan="4">User Rights</th>
    <tr>

        <th>Edit</th>
        <th>Remove</th>
        <th>Role</th>
        <th>Create</th>
        <th>Read</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>

    <c:forEach items="${roles}" var="role">

        <tr id="role-row-${role.id}" value="${role.id}">

            <c:choose>
                <c:when test="${role.id == 1}">
                    <td><span class="glyphicon glyphicon-edit" style="color:grey;"></span></td>
                    <td><span class="glyphicon glyphicon-remove" style="color:grey;"></span></td>
                    </c:when>
                    <c:otherwise>
                    <td><a href="" class="glyphicon glyphicon-edit" data-role-id="${role.id}" data-target="#edit-role-modal" data-toggle="modal" style="color:green;"></a></td>
                    <td><a href="" class="delete-role-link glyphicon glyphicon-remove" data-role-id="${role.id}" style="color:red;"></a></td>
                    </c:otherwise>
                </c:choose>                           
            <td>${role.name}</td>
            <c:forEach items="${rights}" var="right">
                <c:choose>
                    <c:when test="${fn:contains(role.userRights, right.id)}">
                        <td style="width: 12.5%"><input type="checkbox" checked data-role-id="${role.id}" class="role-checkbox checkbox checkbox-inline" name="${role.name}" value="${right.id}"></td>
                        </c:when>
                        <c:otherwise>
                        <td style="width: 12.5%"><input type="checkbox" data-role-id="${role.id}" class="role-checkbox checkbox checkbox-inline" name="${role.name}" value="${right.id}"></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

        </tr>
    </c:forEach>
</table>
