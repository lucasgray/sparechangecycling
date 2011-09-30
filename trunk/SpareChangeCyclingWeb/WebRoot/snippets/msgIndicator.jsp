
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp" %>

<c:choose>
<c:when test="${hasMsgs}"><img src="images/letter_16.png" alt="you have unread messages" title="you have unread messages"/></c:when>
<c:otherwise><img src="images/letter_unread_16.png" alt="you have no unread messages" title="you have no unread messages"/></c:otherwise>
</c:choose>