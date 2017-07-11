<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="url" value="${pageContext.request.contextPath}/" />
	<ul>
		<c:forEach items="${secondMenu }" var="list">
	  	<li>
	  		<c:if test="${!empty(list.childList) }">
		  		<a href="#abc${list.id }" class="${list.ico_class }">${list.menu_name }</a>
		      	<span class="arrow"></span>
		      	<ul id="abc${list.id }">
		      		<c:forEach items="${list.childList }" var="child">
		         		<li><a href="${url }${child.menu_url }" target="right">${child.menu_name }</a></li>
		            </c:forEach>
		        </ul>
	        </c:if>
	        <c:if test="${empty(list.childList) }">
	        	<a href="${url }${list.menu_url }" target="right" class="${list.ico_class }">${list.menu_name }</a>
	        </c:if>
      </li>
      </c:forEach>
  </ul>
  <a class="togglemenu"></a>
  <br /><br />
  <script>
  
  $('.vernav > ul li a, .vernav2 > ul li a').each(function(){
		var url = $(this).attr('href');
		$(this).click(function(){
			if(url.indexOf("#") == -1){
				return;
			}
			if($(url).length > 0) {
				if($(url).is(':visible')) {
					if(!$(this).parents('div').hasClass('menucoll') &&
					   !$(this).parents('div').hasClass('menucoll2'))
							$(url).slideUp();
				} else {
					$('.vernav ul ul, .vernav2 ul ul').each(function(){
							$(this).slideUp();
					});
					if(!$(this).parents('div').hasClass('menucoll') &&
					   !$(this).parents('div').hasClass('menucoll2'))
							$(url).slideDown();
				}
				return false;	
			}
		});
	});
  
  </script>
</body>