package controller;

import java.io.IOException;
import java.sql.Timestamp;

import org.mindrot.jbcrypt.BCrypt;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

/**
 * InsertMember 클래스는 클라이언트로부터 전달받은 회원 정보를 사용하여 데이터베이스에 새로운 회원을 추가하는 컨트롤러입니다.
 */
public class InsertMember implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 클라이언트로부터 전달받은 회원 정보 파라미터
        String userName = request.getParameter("userName");
        String loginId = request.getParameter("loginId");
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userEmail = request.getParameter("userEmail");

        // 반환할 ModelAndView 객체 생성
        ModelAndView view = new ModelAndView();

        // 비밀번호 확인 로직 추가
        if (!password.equals(confirmPassword)) {
            response.getWriter().println("회원가입 실패: 비밀번호가 일치하지 않습니다.");
            view.setPath("./IdInsertErrorPage.jsp"); // 실패 페이지 경로 설정
            return view;
        }

        // 입력값 검증
        if (!isValidLoginId(loginId)) {
            response.getWriter().println("회원가입 실패: 아이디 형식이 올바르지 않습니다.");
            view.setPath("./IdInsertErrorPage.jsp");
            return view;
        }
        if (!isValidPassword(password)) {
            response.getWriter().println("회원가입 실패: 비밀번호 형식이 올바르지 않습니다.");
            view.setPath("./IdInsertErrorPage.jsp");
            return view;
        }
        if (!isValidNickName(nickName)) {
            response.getWriter().println("회원가입 실패: 닉네임 형식이 올바르지 않습니다.");
            view.setPath("./IdInsertErrorPage.jsp");
            return view;
        }
        if (!isValidUserName(userName)) {
            response.getWriter().println("회원가입 실패: 이름 형식이 올바르지 않습니다.");
            view.setPath("./IdInsertErrorPage.jsp");
            return view;
        }
        if (!isValidEmail(userEmail)) {
            response.getWriter().println("회원가입 실패: 이메일 형식이 올바르지 않습니다.");
            view.setPath("./IdInsertErrorPage.jsp");
            return view;
        }

        // 비밀번호 해싱
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Hashed Password: " + hashedPassword); // 디버깅용 출력

        // 현재 시간을 회원 생성 시간과 비밀번호 갱신 시간으로 설정
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        // UsersDTO 객체 생성 및 데이터 설정
        UsersDTO dto = new UsersDTO();
        dto.setUserName(userName);
        dto.setLoginId(loginId);
        dto.setNickName(nickName);
        dto.setPassword(hashedPassword);
        dto.setCreateTime(currentTime);
        dto.setPwUpdateTime(currentTime);
        dto.setUserEmail(userEmail);

        // UsersService를 사용하여 회원 등록
        int result = UsersService.getInstance().insertMember(dto);

        if (result > 0) { // 회원가입 성공
            view.setPath("./IdInsertSuccessPage.jsp"); // 성공 페이지 경로 설정
            view.setRedirect(true); // 리다이렉트 설정
        } else { // 회원가입 실패
            response.getWriter().println("회원가입에 실패했습니다. 다시 시도해주세요.");
            view.setPath("./IdInsertErrorPage.jsp"); // 실패 페이지 경로 설정
        }

        return view;
    }

    // 로그인 ID 검증: 5~20자의 영문, 숫자 조합
    private boolean isValidLoginId(String loginId) {
        return loginId != null && loginId.matches("^[a-zA-Z0-9]{5,20}$");
    }

    // 비밀번호 검증: 8~20자, 대소문자, 숫자, 특수문자 포함
    private boolean isValidPassword(String password) {
        return password != null
                && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$");
    }

    // 닉네임 검증: 2~10자
    private boolean isValidNickName(String nickName) {
        return nickName != null && nickName.matches("^.{2,10}$");
    }

    // 사용자 이름 검증: 한글 또는 영문, 2~20자
    private boolean isValidUserName(String userName) {
        return userName != null && userName.matches("^[가-힣a-zA-Z\\s]{2,20}$");
    }

    // 이메일 검증: 유효한 이메일 형식
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }
}
