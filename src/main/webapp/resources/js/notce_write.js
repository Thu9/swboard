	
// 네이버 에디터2
var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({ 
		oAppRef : oEditors, 
		// elPlaceHoder와 <textarea> id값과 일치해야 적용된다
		elPlaceHolder : "smartEditor", 
		sSkinURI : "resources/smartEditor/SmartEditor2Skin.html",  
		fCreator : "createSEditor2", 
		htParams : { 
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseToolbar : true, 
			
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer : false,
			
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseModeChanger : false 
			}     
	}); 
//글쓰기
$("#savebutton").click(function() {
	if (confirm("글 작성을 완료하시겠습니까?")) {
		// 네이버 에디터에서 작성한 내용을 textarea 값으로 저장한다
		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		// submit 전 validation 검사 필요

		// 서버로 전송
		$("#write").submit();
	} else {
		return;
	}

});

	

















