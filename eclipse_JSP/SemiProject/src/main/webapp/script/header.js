/**
 * 메뉴 활성화 및 사용자 관련 기능 추가
 */
window.onload = () => {
	// 메뉴 활성화 로직
	const menuItems = document.querySelectorAll('.menu_bar li');
	const currentUrl = window.location.href; // 현재 URL

	// 페이지 로드 시 URL과 일치하는 메뉴에 active 클래스를 추가
	menuItems.forEach(item => {
		const link = item.querySelector('a');
		// 메뉴 링크의 href 값이 현재 URL과 일치하면 active 클래스를 추가
		if (currentUrl.includes(link.href)) {
			item.classList.add('active'); // 현재 페이지의 메뉴 항목에 active 클래스 추가
		}

		// 마우스를 올리면 글씨 크기와 굵기 증가
		item.addEventListener('mouseenter', () => {
			if (!item.classList.contains('active')) { // active 클래스가 없으면만 적용
				item.style.fontSize = '22px'; // 글씨 크기 변경
				item.style.fontWeight = 'bolder'; // 글씨 굵기 증가
			}
		});

		// 마우스를 떼면 원래 크기와 굵기로 돌아가게 설정
		item.addEventListener('mouseleave', () => {
			if (!item.classList.contains('active')) { // active 클래스가 없으면만 적용
				item.style.fontSize = '20px'; // 원래 크기로 복원
				item.style.fontWeight = 'bold'; // 원래 굵기로 복원
			}
		});

		// 메뉴 클릭 시 active 클래스를 추가하여 클릭한 메뉴 스타일 적용
		item.addEventListener('click', () => {
			// 클릭된 메뉴에만 active 클래스를 추가하고 나머지 메뉴에서 제거
			menuItems.forEach(menu => menu.classList.remove('active'));
			item.classList.add('active');
		});
	});

	/*  
	// 사용자 닉네임 및 로그아웃 관련 로직
		const userNicknameElement = document.querySelector('.user-nickname');
		const logoutLink = document.querySelector('.user-icon a[href="./logout.do"]');
	
		// 닉네임 표시가 필요한 경우
		if (userNicknameElement) {
			console.log("로그인된 사용자 닉네임:", userNicknameElement.textContent); // 디버깅 용도
		}
	
		// 로그아웃 버튼 클릭 시 동작
		if (logoutLink) {
			logoutLink.addEventListener('click', (e) => {
				const confirmLogout = confirm('로그아웃 하시겠습니까?');
				if (!confirmLogout) {
					e.preventDefault(); // 로그아웃 취소
				}
			});
		}
	*/
	const logoutLink = document.querySelector('.logout-btn');
	if (logoutLink) {
		logoutLink.addEventListener('click', e => {
			if (!confirm('로그아웃 하시겠습니까?')) {
				e.preventDefault();
			}
		});
	}
};
