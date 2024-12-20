package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dto.UsersDTO;
import service.UsersService;
import view.ModelAndView;

public class UpdateUserController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[UpdateUserController] 실행 시작");

        // 1. 세션에서 사용자 정보 가져오기
        HttpSession session = request.getSession();
        UsersDTO sessionUser = (UsersDTO) session.getAttribute("user");

        if (sessionUser == null) {
            System.out.println("[UpdateUserController] 세션 정보 없음 -> 로그인 페이지로 리다이렉트");
            return redirectTo("./loginView.jsp");
        }

        // 2. 입력값 가져오기
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        String newNickName = request.getParameter("nickName");
        String emailLocal = request.getParameter("emailLocal");
        String emailDomain = request.getParameter("emailDomain");
        String customDomain = request.getParameter("customDomain");

        // 이메일 조합
        String newEmail = null;
        if ("custom".equals(emailDomain) && isValid(customDomain)) {
            newEmail = emailLocal + "@" + customDomain;
        } else if (isValid(emailLocal) && isValid(emailDomain)) {
            newEmail = emailLocal + "@" + emailDomain;
        }

        System.out.println("[UpdateUserController] 입력값 -> 닉네임: " + newNickName + ", 이메일: " + newEmail);

        UsersService usersService = UsersService.getInstance();

        // 3. 비밀번호 변경 처리
        if (isValid(currentPassword) && isValid(newPassword) && isValid(confirmNewPassword)) {
            // 새 비밀번호 형식 검증
        	if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")) {
        	    System.out.println("[UpdateUserController] 새 비밀번호 형식 불일치");
        	    request.setAttribute("error", "비밀번호는 8~20자의 대소문자, 숫자, 특수문자를 포함해야 합니다.");
        	    return forwardTo("./updateUserView.do", null);
        	}

            // 새 비밀번호와 확인 비밀번호 일치 여부 확인
            if (!newPassword.equals(confirmNewPassword)) {
                System.out.println("[UpdateUserController] 새 비밀번호 불일치");
                request.setAttribute("error", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
                return forwardTo("./updateUserView.do", null);
            }

            // 현재 비밀번호 검증 및 업데이트
            boolean isPasswordUpdated = usersService.updatePassword(sessionUser.getUserNumber(), currentPassword,
                    newPassword);

            if (!isPasswordUpdated) {
                System.out.println("[UpdateUserController] 현재 비밀번호 불일치");
                request.setAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
                return forwardTo("./updateUserView.do", null);
            }

            System.out.println("[UpdateUserController] 비밀번호 수정 완료");
        }

        // 4. 사용자 정보 업데이트 처리
        UsersDTO updateUser = new UsersDTO();
        updateUser.setUserNumber(sessionUser.getUserNumber());
        updateUser.setNickName(isValid(newNickName) ? newNickName : sessionUser.getNickName());
        updateUser.setUserEmail(isValid(newEmail) ? newEmail : sessionUser.getUserEmail());

        boolean updateResult = usersService.updateUser(updateUser);

        if (updateResult) {
            System.out.println("[UpdateUserController] 사용자 정보 수정 완료");
            UsersDTO updatedUser = usersService.findUserByUserNumber(sessionUser.getUserNumber());
            session.setAttribute("user", updatedUser);

            // 세션 상태 초기화
            session.removeAttribute("emailChecked");
            session.removeAttribute("nicknameChecked");

            response.sendRedirect("mypageView.do?success=update");
            return null;
        } else {
            System.out.println("[UpdateUserController] 사용자 정보 수정 실패");
            return forwardTo("./updateUserView.do", "정보 수정에 실패했습니다.");
        }
    }

    // 유효성 검사 메서드
    private boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // 리다이렉트 설정
    private ModelAndView redirectTo(String path) {
        ModelAndView view = new ModelAndView();
        view.setPath(path);
        view.setRedirect(true);
        return view;
    }

    // 포워드 설정
    private ModelAndView forwardTo(String path, String errorMessage) {
        ModelAndView view = new ModelAndView();
        view.setPath(path);
        view.setRedirect(false);
        view.addObject("error", errorMessage);
        return view;
    }
}
