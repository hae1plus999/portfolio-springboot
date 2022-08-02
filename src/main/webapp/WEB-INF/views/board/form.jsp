<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 화면</title>
</head>
<body>
	<div class="container">
	<form id="form" method="post" action="/save">
		<input type="hidden" name="boardSeq" value="${board == null ? 0 : board.boardSeq}"/>
		<!-- <input type="hidden" name="boardType" value="COMMUNITY"/> -->
		  <div class="row mb-3">
		    <label for="title" class="col-sm-2 col-form-label">제목</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" name="title" value="${board.title}" id="title">
		    </div>
		  </div>
		  <div class="row mb-3">
		    <label for="contents" class="col-sm-2 col-form-label">내용</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" name="contents" id="contents">${board.contents}</textarea>
		    </div>
		  </div>
	  	  <button type="submit" class="btn btn-primary">저장하기</button>
	</form>
	</div>
	<script type="text/javascript">
	$(function() {
		
		var $form = $('#form');
		$form.bind('submit', function() {
			$.ajax({
				url: '/${menuType}/save',
				type: 'post',
				data: $form.serialize(),
				dataType: 'json',
				success: function(response) {
					console.log(response);
					if (response.code == 'SUCCESS'){
						alert('저장되었습니다.');
						location.href = '/${menuType}/' + response.data;
					}else{
						alert(response.message);
					}
					console.log(response);
				}
			});
			/* 페이지가 안넘어가도록 하기위해서 리턴해줌 */
			return false;
		});
	});
	
	</script>
</body>
</html>