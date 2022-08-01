<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>목록 화면</title>
</head>
<body>
	<div class="container">
		<form id="form" method="get" action="/list">
			<div class="row mb-3">
				<label for="keyword" class="col-sm-2 col-form-label">검색어</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="keyword"
						value="${parameter.keyword}" id="keyword">
				</div>
			</div>
			<button type="submit" class="btn btn-primary">검색하기</button>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">순서</th>
					<th scope="col">제목</th>
					<th scope="col">조회수</th>
					<th scope="col">등록일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}" varStatus="status">
				<tr>
					<th scope="row">${status.count}</th>
					<td>${board.title}</td>
					<td>${board.viewCount}</td>
					<td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm"/></td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(boardList) == 0}">
				<tr>
					<td colspan="4"></td>
				</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<script type="text/javascript">
	$(function() {
		
		var $form = $('#form');
		$form.bind('submit', function() {
			$.ajax({
				url: '/board/save',
				type: 'post',
				data: $form.serialize(),
				dataType: 'json',
				success: function(data) {
					if (data.code == 'SUCCESS'){
						
					}else{
						alert(data.message);
					}
					console.log(data);
				}
			});
			/* 페이지가 안넘어가도록 하기위해서 리턴해줌 */
			return false;
		});
	});
	
	</script>
</body>
</html>