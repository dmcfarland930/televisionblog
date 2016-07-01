/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
   var content = $('#blog-post-div').html();
   console.log(content);
   content = content.replace(/(#)([a-z\d-]+)/gi, "<a href='" + contextRoot + "/blog/tag/" + "$2" + "' ><span class=\'hash_tag\'>$1$2</span></a>");
   $('#blog-post').html(content);
});

