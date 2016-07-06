<%-- 
    Document   : showProductModal
    Created on : Jun 8, 2016, 10:14:14 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <div class="modal fade" id="submitBlogModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Submit Blog</h4>
                </div>
                <div class="modal-body">

                    <h4>This blog post will be posted now unless you set a date!</h4>
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Schedule Blog Post</a>
                                </h4>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <input value="${date}" type="text" name="schedule-date" id="post-date-input"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Set Expiration Date (Default None)</a>
                                </h4>
                            </div>
                            <div id="collapse2" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <input type="text" name="schedule-date" id="expiration-date-input"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">

                        <input id="blog-post-button" class="btn bg-primary" type="submit" value="Create Blog Post"/>

                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
</html>
