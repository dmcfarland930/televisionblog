<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Blog View</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/rrssb-master/css/rrssb.css" />
        <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <%@include file="navBar.jsp"%>
        
        <div id="blog-content" class="container">

            <div class="row" style="display: inline">

                <div class="col-md-8">
                
                <div id="blog-post-div">
                    <h1>${post.title}</h1>
                    <p>Posted by ${post.user.firstName} ${post.user.lastName} on ${date}</p>
                    <%@include file="socialShare.jsp"%>
                    <hr>
                    ${post.content}
                    </br>
                    <p>Category: ${post.category.name}</p>
                    <hr>
                    <div id="disqus_thread"></div>
                    <br/>
                    <br/>
                </div>
                <div class="row col-md-8" >
                </div>
            </div>
                <%@include file="sideBar.jsp" %>
        </div>
        </div>
                <%@include file="footer.jsp" %>
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            /**
             *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
             *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables
             */
            /*
             var disqus_config = function () {
             this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
             this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
             };
             */
            (function () {  // DON'T EDIT BELOW THIS LINE
                var d = document, s = d.createElement('script');

                s.src = '//tonerclassicmovies.disqus.com/embed.js';

                s.setAttribute('data-timestamp', +new Date());
                (d.head || d.body).appendChild(s);
            })();
        </script>
        <noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a></noscript>

        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/hashtags.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery.1.10.2.min.js"><\/script>')</script>
        <script src="${pageContext.request.contextPath}/rrssb-master/js/rrssb.min.js"></script>
        <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
        
    </body>
</html>
