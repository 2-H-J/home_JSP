package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.UsersDTO;
import service.UsersService;
import view.ModelAndView;

/**
 * UpdateUserController는 사용자 정보 수정을 처리하는 컨트롤러입니다.
 */
public class UpdateUserController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 세션 확인
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // 세션이 없으면 로그인 페이지로 리다이렉트
            response.sendRedirect("signin.jsp");
            return null;
        }

        // 사용자 정보 수정 요청 처리 (GET 요청 시 JSP로 이동)
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // 현재 사용자 정보를 JSP에서 사용할 수 있도록 설정
            UsersDTO currentUser = (UsersDTO) session.getAttribute("user");
            request.setAttribute("user", currentUser);

            // updateUser.jsp로 포워드
            ModelAndView view = new ModelAndView();
            view.setPath("updateUser.jsp"); // JSP 경로 설정
            view.setRedirect(false);       // 포워드 설정
            return view;
        }

        // 사용자 정보 수정 요청 처리 (POST 요청 시 데이터 업데이트)
        // 클라이언트로부터 전달받은 수정 데이터
        String nickName = request.getParameter("nickName");
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // 현재 사용자 정보 가져오기
        UsersDTO currentUser = (UsersDTO) session.getAttribute("user");

        // 사용자 정보 수정 로직
        UsersDTO updatedUser = new UsersDTO();
        updatedUser.setUserNumber(currentUser.getUserNumber());
        updatedUser.setNickName(nickName != null && !nickName.isEmpty() ? nickName : currentUser.getNickName());
        updatedUser.setUserEmail(email != null && !email.isEmpty() ? email : currentUser.getUserEmail());
        updatedUser.setUpdateTime(Timestamp.from(Instant.now()));

        // 비밀번호 변경이 요청된 경우
        if (currentPassword != null && !currentPassword.isEmpty() && newPassword != null && confirmPassword != null) {
            if (!newPassword.equals(confirmPassword)) {
                // 새 비밀번호와 확인 비밀번호가 일치하지 않을 경우 에러 메시지 출력
                response.getWriter().println("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
                return null;
            }

            // 현재 비밀번호가 올바른지 확인
            boolean passwordCheck = UsersService.getInstance().login(currentUser.getLoginId(), currentPassword) != null;
            if (!passwordCheck) {
                // 현재 비밀번호가 올바르지 않은 경우 에러 메시지 출력
                response.getWriter().println("현재 비밀번호가 일치하지 않습니다.");
                return null;
            }

            // 새 비밀번호로 업데이트
            updatedUser.setPassword(newPassword);
            updatedUser.setPwUpdateTime(Timestamp.from(Instant.now()));
        }

        // 데이터베이스 업데이트
        int result = UsersService.getInstance().updateUser(updatedUser);
        if (result > 0) {
            // 업데이트 성공 시 세션 갱신
            System.out.println("사용자 정보 수정 성공");
            session.setAttribute("user", updatedUser);
        } else {
            // 업데이트 실패 시 에러 메시지 출력
            response.getWriter().println("사용자 정보 수정 실패.");
        }

        // 성공 시 메인 페이지로 리다이렉트
        ModelAndView view = new ModelAndView();
        view.setPath("index.jsp");
        view.setRedirect(true);
        return view;
    }
}
