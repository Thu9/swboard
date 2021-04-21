<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SWboard. Portfolio</title>
<link rel="stylesheet" type="text/css" href="resources/css/list.css">

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<!-- header include -->
	<jsp:include page="include/header.jsp" />
	
	<table class="list" width="1200" height="800" align="center" border="1" cellpadding="10" cellspacing="0">
		<tr><th colspan="5">Swboard.</th></tr>
		<tr>
			<td colspan="5" align="right">
				${swboardList.totalCount}(${swboardList.currentPage} / ${swboardList.totalPage})Page
			</td>
		</tr>
		<tr>
			<th width="80">글번호</th>
			<th width="760">제목</th>
			<th width="130">이름</th>
			<th width="150">작성일</th>
			<th width="80">조회수</th>
		</tr>
	
		<jsp:useBean id="date" class="java.util.Date"/>
		<c:set var="list" value="${swboardList.list}"/>
	
		<c:if test="${list.size() == 0}">
		<tr>
			<td colspan="5">
				<marquee>테이블에 저장된 글이 없습니다.</marquee>
			</td>
		</tr>
		</c:if>
		
		<c:if test="${list.size() != 0}">
		<c:forEach var="vo" items="${list}">
		<tr class="content">
			<td align="center">${vo.idx}</td>
			<td>
				<c:set var="subject" value="${fn:replace(fn:trim(vo.subject), '<', '&lt;')}"/>
				<c:set var="subject" value="${fn:replace(subject, '>', '&gt;')}"/>
				
				<c:if test="${vo.lev > 0}">
				<c:forEach var="i" begin="1" end="${vo.lev}" step="1">
					&nbsp;
				</c:forEach>
				<img src="images/reply.png"/>&nbsp;
				</c:if>
				
				<a class="subject" href="increment?idx=${vo.idx}&currentPage=${swboardList.currentPage}">${subject}</a>
				
				<c:if test="${vo.hit > 9}">
					&nbsp;<img src="images/hot.gif"/>
				</c:if>
				<c:if test="${date.year == vo.writeDate.year && date.month == vo.writeDate.month && date.date == vo.writeDate.date}">
					&nbsp;<img src="images/ico_new.png"/>
				</c:if>
			
			</td>
			<td align="center">
				<c:set var="name" value="${fn:replace(fn:trim(vo.name), '<', '&lt;')}"/>
				<c:set var="name" value="${fn:replace(name, '>', '&gt;')}"/>
				${name}
			</td>
			<td align="center">
				<c:if test="${date.year == vo.writeDate.year && date.month == vo.writeDate.month && date.date == vo.writeDate.date}">
					<fmt:formatDate value="${vo.writeDate}" pattern="a h:mm"/>
				</c:if>
				<c:if test="${date.year != vo.writeDate.year || date.month != vo.writeDate.month || date.date != vo.writeDate.date}">
					<fmt:formatDate value="${vo.writeDate}" pattern="yyyy.MM.dd(E)"/>
				</c:if>
			</td>
			<td align="center">${vo.hit}</td>
		</tr>
		</c:forEach>
		</c:if>
		
		<tr>
			<td class="pageBar" colspan="5" align="center">
				<c:if test="${swboardList.currentPage > 1}">
					<input class="button" type="image" src="images/bt_page_first.gif" value="맨앞" onclick="location.href='?currentPage=1'" title="첫 페이지 목록으로 이동하기"/>
				</c:if>
				<c:if test="${swboardList.currentPage <= 1}">
					<input class="button" type="image" src="images/bt_page_first.gif" value="맨앞" disabled="disabled" title="이미 첫 번째 목록입니다."/>
				</c:if>
				
				<c:if test="${swboardList.startPage > 1}">
					<input class="button" type="image" src="images/bt_page_prev.gif" value="이전" onclick="location.href='?currentPage=${swboardList.startPage - 1}'" 
						title="이전 페이지 목록으로 이동하기"/>
				</c:if>
				<c:if test="${swboardList.startPage <= 1}">
					<input class="button" type="image" src="images/bt_page_prev.gif" value="이전" disabled="disabled" title="이미 첫 번째 페이지 목록입니다."/>
				</c:if>
				
				<c:forEach var="i" begin="${swboardList.startPage}" end="${swboardList.endPage}" step="1">
					<c:if test="${swboardList.currentPage == i}">
						<strong class="pageButton1">${i}</strong>
					</c:if>
					<c:if test="${swboardList.currentPage != i}">
						<a class="pageButton2" href='?currentPage=${i}' title="${i}페이지로">${i}</a>
					</c:if>
				</c:forEach>
				
				<c:if test="${swboardList.endPage < swboardList.totalPage}">
					<input class="button" type="image" src="images/bt_page_next.gif" value="다음" onclick="location.href='?currentPage=${swboardList.endPage + 1}'" 
						title="다음 페이지 목록으로 이동하기"/>
				</c:if>
				<c:if test="${swboardList.endPage >= swboardList.totalPage}">
					<input class="button" type="image" src="images/bt_page_next.gif" value="다음" disabled="disabled" title="이미 마지막 페이지 목록입니다."/>
				</c:if>
				
	 			<c:if test="${swboardList.currentPage < swboardList.totalPage}">
					<input class="button" type="image" src="images/bt_page_last.gif" value="맨뒤" onclick="location.href='?currentPage=${swboardList.totalPage}'" 
						title="마지막 페이지 목록으로 이동하기"/>
				</c:if>
				<c:if test="${swboardList.currentPage >= swboardList.totalPage}">
					<input class="button" type="image" src="images/bt_page_last.gif" value="맨뒤" disabled="disabled" title="이미 마지막 목록입니다."/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="right">
				<a class="writeButton" href='insert'>
					<img src="images/ico_write.png">
				 글쓰기</a>
			</td>
		</tr>
	</table>
	
	<!-- footer include -->
	<jsp:include page="include/footer.jsp" />
</body>
</html>














