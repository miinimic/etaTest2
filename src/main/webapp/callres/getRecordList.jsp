<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="mt-5 row">
${2 + 2}

			<c:forEach var="item" items="${list}" varStatus="status">
				
				
						<div class="product-content">
						
							<p>가격: ${item.callNo}</p>
							
						</div>

			</c:forEach>
</body>
</html>