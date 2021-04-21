<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swboard. 게시글 작성</title>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 네이버 스마트 에디터2 --> 
<script type="text/javascript" src="resources/smartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- notce_write.js -->
<script type="text/javascript" src="resources/js/notce_write.js"></script>

<style type="text/css">
	body {
	color: #363636;
	font-size: 15px;
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
	

</head>
<body>
	
<form id="write" action="insertOK" method="post">
	<table width="750" align="center" border="1" cellpadding="5" cellspacing="0">
		<!-- 메인 -->
		<tr><th colspan="2">Swboard. 게시글 작성</th></tr>
		<tr>
			<td width="100" align="center">이름</td>
			<td width="400"><input type="text" name="name" size="30"/></td>
		</tr>						
		<tr>
			<td align="center">제목</td>
			<td><input type="text" name="subject" size="70"/></td>
		</tr>
		<tr>
			<td align="center">내용</td>
			<td>
			
			<!-- 네이버 스마트 에디터 --> 
			<textarea name="content" id="smartEditor" rows="20" cols="50" style="resize: none;"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" id="savebutton" value="저장하기"/>
				<input type="reset" value="다시쓰기"/>
				<input type="button" value="돌아가기" onclick="history.back()"/>
			</td>
		</tr>
	
	</table>
</form>
</body>
	<script type="text/javascript" src="resources/js/notce_write.js">
	</script>
</html>




















.