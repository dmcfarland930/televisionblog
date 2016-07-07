<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pending Posts</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker-master/jquery.datetimepicker.css">


        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker-master/jquery.datetimepicker.css">



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
              
                </div>
            </div>
            <br />
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel panel-default">

                            <div class="panel panel-heading text-center">
                                <h3>Administration Panel</h3>
                            </div>
                            <div class="panel panel-body">

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="panel panel-info">
                                            <div class="panel panel-heading text-center">
                                                Total # of Blogs on your site
                                            </div>
                                            <div class="panel panel-body text-center">                                  
                                                ${numBlogs}
                                            </div>
                                        </div>

                                    </div>

                                    <div class="col-md-6">
                                        <div class="panel panel-info">
                                            <div class="panel panel-heading text-center">
                                                Current # of Users registered with your Blog Site
                                            </div>
                                            <div class="panel panel-body text-center">                                  
                                                ${numUsers}
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div class="row">

                                    <div class="col-md-6">
                                        <div class="panel panel-info">
                                            <div class="panel panel-heading text-center">
                                                Current # of Blogs pending approval
                                            </div>
                                            <div class="panel panel-body text-center">                                  
                                                ${numBlogsNeedApproved}
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-md-6">
                                        <div class="panel panel-info">
                                            <div class="panel panel-heading text-center">
                                                Current # of unique Tags associated with Blogs
                                            </div>
                                            <div class="panel panel-body text-center">                                  
                                                ${numTags}
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row">

                                    <div class="col-md-6">
                                        <div class="panel panel-info">
                                            <div class="panel panel-heading text-center">
                                                Current # of active Blogs
                                            </div>
                                            <div class="panel panel-body text-center">                                  
                                                ${numBlogsApproved}
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="panel panel-info">
                                            <div class="panel panel-heading text-center">
                                                Current # of unique Categories associated with Blogs
                                            </div>
                                            <div class="panel panel-body text-center">                                  
                                                ${numCategories}
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
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
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>
        <script src="${pageContext.request.contextPath}/datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script>


    </body>
</html>
