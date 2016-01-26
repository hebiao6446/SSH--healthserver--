<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%
  //  request.getSession().setAttribute("user", null);
 
%>
<c:choose>
	<c:when test="${user == null}">
		<script>
if(top !=null)
	top.location.href="<%= request.getContextPath()%>/login.jsp";
else
	location.href="login.jsp";	
</script>

	</c:when>
</c:choose>
