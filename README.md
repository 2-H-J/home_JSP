# home_JSP

1. [MainPage] - 세션이 없거나 사용자 정보가 없음을 감지하고 쿠키를 확인함.
2. [MainPage] - 사용자 정보가 없어서 로그인 페이지 링크 제공.
3. [DispatcherServlet] - DispatcherServlet 생성자 호출로 인스턴스 생성.
4. [DispatcherServlet] - 클라이언트의 POST 요청을 수신하고 doGet() 메서드를 호출하여 처리 시작.
5. [DispatcherServlet] - 클라이언트의 GET 요청 수신 후 요청된 command인 "login" 추출.
6. [HandlerMapping] - HandlerMapping 인스턴스 생성 및 반환.
7. [HandlerMapping] - "login" command를 기반으로 LoginController 생성.
8. [DispatcherServlet] - 생성된 LoginController 인스턴스를 사용하여 요청 로직 처리 시작.
9. [LoginController] - 로그인 시도, 사용자 ID(user80)로 인증을 시작.
10. [UsersService] - UsersService 인스턴스 생성 및 UsersMapper 초기화.
11. [UsersService] - login() 메서드를 호출하여 사용자 인증 시도.
12. [LoginController] - 로그인 성공, 세션 설정 및 세션 만료 시간 설정 (1분 후).
13. [LoginController] - 로그인 성공 후 index.jsp로 리다이렉트 설정.
14. [ModelAndView] - 경로 설정 및 리다이렉트 설정 (index.jsp, redirect=true).
15. [DispatcherServlet] - 생성된 ModelAndView를 받아 요청 결과 반환.
16. [DispatcherServlet] - 모델 데이터를 저장하고, 리다이렉트 처리 여부 확인.
17. [DispatcherServlet] - 경로를 확인한 후 클라이언트를 index.jsp로 리다이렉트.
