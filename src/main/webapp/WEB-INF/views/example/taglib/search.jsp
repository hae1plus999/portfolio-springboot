<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>게시물 목록</h2>
		<form action="" method="get">
			<label for="">종류</label>
			<div>
				<tag:bootstrap-checkbox items="${boardTypes}" values="${parameter.boardTypes}"></tag:bootstrap-checkbox>
			</div>
		</form>
	</div>
</body>
</html>