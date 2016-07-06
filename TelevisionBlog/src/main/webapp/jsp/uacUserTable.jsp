<%-- 
    Document   : uacUserTable
    Created on : Jul 6, 2016, 1:32:58 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table class="table table-bordered" style="text-align: center;" id="user-table">                       

    <tr>
        <th colspan="7">Users</th>
    </tr>

    <tr>
        <th>User</th>
        <th>Role</th>
        <th>Read</th>
        <th>Write</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.lastName}, ${user.firstName} (${user.username})</td>
            <td><select id="user-role-${user.id}" data-user-id="${user.id}" class="user-role-select form-control" name="user-role">
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
        <c:forEach items="${rights}" var="right">
            <c:choose>
                <c:when test="${fn:contains(user.roles, right.id)}">
                    <td><input type="checkbox" checked data-user-id="${user.id}" class="user-checkbox checkbox checkbox-inline" value="${right.id}"></td>
                </c:when>
                <c:otherwise>
                    <td><input type="checkbox" data-user-id="${user.id}" class="user-checkbox checkbox checkbox-inline" value="${right.id}"></td>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <div id="group-id"></div>
        </tr>
    </c:forEach>
</table>
