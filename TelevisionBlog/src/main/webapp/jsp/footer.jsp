<%-- 
    Document   : footer
    Created on : Jul 1, 2016, 3:17:54 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<footer>
    <div class="blog-footer">
        <div class="col-md-12">
            <p id="copy">&copy Toner Classic Movies 2016</p>
        </div>
        
    </div>
</footer>
<script src='https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyAQlcq0kHABreLBE3VmYcxfemhgDmmQOkc'></script>
<div style='overflow:hidden;height:200px;width:100%;;'><div id='gmap_canvas' style='height:200px;width:100%;'></div><style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div> <a href='https://mapswebsite.net/'>add a google map to website</a> <script type='text/javascript' src='https://embedmaps.com/google-maps-authorization/script.js?id=c62252a9d7f556e0e378f8d8625109eb044cad20'></script><script type='text/javascript'>function init_map(){var myOptions = {zoom:14, center:new google.maps.LatLng(37.568371877570684, 126.9793424910156), mapTypeId: google.maps.MapTypeId.ROADMAP}; map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions); marker = new google.maps.Marker({map: map, position: new google.maps.LatLng(37.568371877570684, 126.9793424910156)}); infowindow = new google.maps.InfoWindow({content:'<strong>Toner Classic Movies</strong><br>103 Software Street<br>039NN Seoul <br>'}); google.maps.event.addListener(marker, 'click', function(){infowindow.open(map, marker); }); infowindow.open(map, marker); }google.maps.event.addDomListener(window, 'load', init_map);</script>
