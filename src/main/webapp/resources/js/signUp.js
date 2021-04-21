// 	비밀번호 일치 체크 함수
	function passwordCheckFunction() {
		var userPassword1 = $('#userPassword1').val();
		var userPassword2 = $('#userPassword2').val();
		if (userPassword1 != userPassword2) {
			$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
		} else {
			$('#passwordCheckMessage').html('');
		}
	}

// 	아이디 중복 체크 함수
	function registerCheckFunction() {
		var userID = $('#userID').val();
		$.ajax({
			type: 'POST',					
			url: 'signUpForm/UserRegister',		
			data: {							
				userID: userID
			},
			success: function(result) {		
				switch (result) {
				case '0':
// 					alert('이미 존재하는 회원 아이디 입니다.');
					$('#idCheckMessage').html('사용할 수 없는 아이디 입니다.');
					$('#checkMessage').html('사용할 수 없는 아이디 입니다.');
					$('#checkType').attr("class", "modal-content panel-warning");
					
					break;
				case '1':
// 					alert('가입 가능한 아이디 입니다.');
					$('#idCheckMessage').html('가입 가능한 아이디 입니다.');
					$('#checkMessage').html('가입 가능한 아이디 입니다.');
					$('#checkType').attr("class", "modal-content panel-success");
					break;
				case '2':
// 					alert('아이디를 입력하고 중복체크 버튼을 클릭하세요.');
					$('#idCheckMessage').html('아이디를 입력하고 중복체크 버튼을 클릭하세요.');
					$('#checkMessage').html('아이디를 입력하고 중복체크 버튼을 클릭하세요.');
					$('#checkType').attr("class", "modal-content panel-warning");
					break;
				default:
					$('#idCheckMessage').html('');
					break;
				}
				$('#checkModal').modal('show');
			},
			error: function(error) {		// ajax 요청이 실패하면 실행할 함수
				alert('에러 발생!!');
			}
		});	
	}