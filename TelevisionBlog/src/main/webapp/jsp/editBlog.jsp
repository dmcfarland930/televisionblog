<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Blog</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <div class="row">

                <h1>Blog Edit</h1>
                <hr/>

                <div>
                    <form action="${pageContext.request.contextPath}/blog/editsubmit/" name="editForm" method="POST">

                        <input type="hidden" name="id" value="${id}"/>
                        <input type="hidden" name="date" value="${date}"/>

                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Title: </label>
                                <input name="title" type="text" id="title-edit" class="form-control ${hasError}" value="${title}"/>
                            </div>
                            <div class="error-message" id="name-error" class="col-md-8">
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Author: </label>
                                <select name="author" class="form-control" value="${author}" id="author-edit">
                                    <c:forEach items="${authors}" var="author">
                                        <option value="${author.id}">${author.firstName} ${author.lastName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset> 

                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Category: </label>
                                <select name="category" class="form-control" value="${category}" id="category-edit">
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <div id="blog-div"  class="col-md-8">
                                <div>
                                    <textarea name="content" style="width: 100%;" rows="10" id="blog-post-edit" >${content}</textarea>
                                </div>
                            </div>
                        </fieldset>

                        <div style="display: inline-block;" class="col-md-8 pull">
                            <input id="blog-edit-submit" class="btn bg-primary button-size" type="submit" value="Update Blog Post"/>



                            <input id="blog-draft-button" class="btn bg-primary button-size" type="submit" value="Save Draft"/>

                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>

        <script type="text/javascript">
            tinymce.init({
                selector: '#blog-post-edit',
                height: 400,
                width: 800
            });
        </script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>

    </body>
</html>

