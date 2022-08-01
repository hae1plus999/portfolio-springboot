<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/utils.tld" %>
<%@ attribute name="items" type="com.portfolio.mvc.domain.BaseCodeLabelEnum[]" required="true"%>
<%@ attribute name="values" type="com.portfolio.mvc.domain.BaseCodeLabelEnum[]" required="false"%>

<c:forEach var="boardType" items="${items}" varStatus="status">
	<div>
		<input 
			type="checkbox" 
			name="boardTypes" 
			value="${boardType.code()}"
			${utils:isSelected(values, boardType) ? 'checked="checked"' : '' } 
			id="board-type${status.count}">
		<label for="board-type${status.count}">
			${boardType.label()}
		</label>
	</div>
</c:forEach>
