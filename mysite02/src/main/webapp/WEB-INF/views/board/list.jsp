<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus="status">	
						<tr>
							<td>${vo.no }</td>
							<td style="text-align:left; padding-left:${(vo.depth)*15}px">
							<c:choose>
								<c:when test='${vo.depth>0 }'>
									<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
								</c:when>
							</c:choose>
							<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a></td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<c:choose>
								<c:when test='${authUser.no==vo.userNo }'>
							<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}" class="del"><img src="${pageContext.servletContext.contextPath }/assets/images/recycle.png" /></a></td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="${pageContext.servletContext.contextPath }/board?p=${prevPageNo}">◀</a></li>

						<c:forEach begin="${firstPageNo }" end="${lastPageNo }" step="1" varStatus="i">
							<c:choose>
								<c:when test='${i.count+firstPageNo-1 > lastPageNo }'></c:when>
								<c:when test='${i.count+firstPageNo-1 == currentPageNo }'><li class="selected"><a href="${pageContext.servletContext.contextPath }/board?p=${i.count+firstPageNo-1}">${i.count+firstPageNo-1}</a></li></c:when>
								<c:otherwise><li><a href="${pageContext.servletContext.contextPath }/board?p=${i.count+firstPageNo-1}">${i.count+firstPageNo-1}</a></li></c:otherwise>
							</c:choose>
   							
						</c:forEach>
						<li><a href="${pageContext.servletContext.contextPath }/board?p=${nextPageNo}">▶</a></li>
					</ul>
				</div>				
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>