/**
 * 메뉴 활성화 및 사용자 관련 기능 추가
 */
    // 메뉴 항목 선택
    const menuItems = document.querySelectorAll('.menu_bar li'); // 모든 메뉴 항목 (li 요소) 선택
    const currentUrl = window.location.href; // 현재 페이지 URL 가져오기

    /**
     * 메뉴 활성화 로직
     * - 현재 페이지 URL과 메뉴의 링크(href)를 비교하여 현재 페이지 메뉴에 'active' 클래스를 추가
     */
    menuItems.forEach(item => {
        const link = item.querySelector('a'); // 메뉴 항목 안의 <a> 태그를 가져옴
        if (currentUrl.includes(link.href)) { 
            // 현재 URL이 링크와 일치하면 'active' 클래스 추가
            item.classList.add('active'); 
        }

        /**
         * 마우스 이벤트 추가
         * - 메뉴 항목에 마우스를 올리면 글씨 크기와 굵기를 일시적으로 변경
         * - active 클래스가 없는 항목에만 적용
         */
        item.addEventListener('mouseenter', () => {
            if (!item.classList.contains('active')) { 
                // 'active' 클래스가 없는 경우에만 스타일 변경
                item.style.fontSize = '22px'; // 글씨 크기를 22px로 증가
                item.style.fontWeight = 'bolder'; // 글씨 굵기를 'bolder'로 변경
            }
        });

        item.addEventListener('mouseleave', () => {
            if (!item.classList.contains('active')) { 
                // 'active' 클래스가 없는 경우에만 원래 상태로 복원
                item.style.fontSize = '20px'; // 글씨 크기를 20px로 복원
                item.style.fontWeight = 'bold'; // 글씨 굵기를 'bold'로 복원
            }
        });

        /**
         * 메뉴 클릭 시 active 클래스 적용
         * - 클릭한 메뉴 항목에만 'active' 클래스를 추가하고 다른 메뉴 항목에서는 제거
         */
        item.addEventListener('click', () => {
            // 모든 메뉴 항목에서 'active' 클래스를 제거
            menuItems.forEach(menu => menu.classList.remove('active'));
            // 클릭한 메뉴 항목에만 'active' 클래스 추가
            item.classList.add('active');
        });
    });

    /**
     * 로그아웃 버튼 클릭 시 확인 메시지 표시
     * - 사용자가 로그아웃 버튼을 클릭하면 확인 창을 띄우고, 취소 시 동작을 중단
     */
    const logoutLink = document.querySelector('.logout-btn'); // 로그아웃 버튼 선택
    if (logoutLink) { // 로그아웃 버튼이 존재하는 경우만 실행
        logoutLink.addEventListener('click', e => {
            // 확인 창을 표시하고 사용자가 '취소'를 선택하면 이벤트를 취소
            if (!confirm('로그아웃 하시겠습니까?')) { 
                e.preventDefault(); // 기본 동작(링크 이동) 중단
            }
        });
    }
