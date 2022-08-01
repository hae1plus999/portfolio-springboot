<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="items"
	type="com.portfolio.mve.domain.BaseCodeLabelEnum[]" required="true"%>
<%@ attribute name="values" type="java.lang.Object" required="false"%>

<c:forEach var="boardType" items="${items}" varStatus="status">
	<div>
		<input type="checkbox" name="boardTypes" value="${boardType.code()}" id="boardType${status.count}">
		<label for="boardType${status.count}">${boardType.label()}</label>
	</div>
</c:forEach>
