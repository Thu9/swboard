	window.onload = function() {
		var img = document.querySelector('img');
		var archs = document.querySelectorAll('a.prev, a.next');
		var count = 4;
		
		archs[0].onclick = function() {
			if (--count < 0) {
				count = 4;
			}
			img.setAttribute('alt', 'img0' + count);
			img.setAttribute('src', 'resources/images/bg_main0' + count + '.jpg');
		}
		
		archs[1].onclick = () => {
			if (++count > 4) {
				count = 0;
			}
			img.setAttribute('alt', 'img0' + count);
			img.setAttribute('src', 'resources/images/bg_main0' + count + '.jpg');
		}
	}