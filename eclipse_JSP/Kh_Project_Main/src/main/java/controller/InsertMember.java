package controller;

import java.io.IOException;
import java.sql.Timestamp;

import org.json.JSONObject;
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
        System.out.println("[InsertMember] execute() 시작");

        // 응답 UTF-8 설정 추가
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // 클라이언트로부터 전달받은 회원 정보 파라미터
        String userName = request.getParameter("userName");
        String loginId = request.getParameter("loginId");
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userEmail = request.getParameter("userEmail");

        System.out.println("[InsertMember] 회원 정보 파라미터 수신: userName=" + userName + ", loginId=" + loginId + ", nickName=" + nickName);

        // JSON 응답 객체
        JSONObject jsonResponse = new JSONObject();

        // 비밀번호 확인 로직 추가
        if (!password.equals(confirmPassword)) {
            System.out.println("[InsertMember] 비밀번호가 일치하지 않음");
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "비밀번호가 일치하지 않습니다.");
            response.getWriter().write(jsonResponse.toString());
            return null;
        }

        // 입력값 검증
        if (!isValidLoginId(loginId)) {
            System.out.println("[InsertMember] 아이디 형식이 올바르지 않음");
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "아이디는 3~20자의 영문 대소문자와 숫자로만 구성되어야 합니다.");
            response.getWriter().write(jsonResponse.toString());
            return null;
        }
        if (!isValidPassword(password)) {
            System.out.println("[InsertMember] 비밀번호 형식이 올바르지 않음");
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "비밀번호는 8~20자이며, 대소문자, 숫자, 특수문자(@$!%*?&)를 포함해야 합니다.");
            response.getWriter().write(jsonResponse.toString());
            return null;
        }
        if (!isValidNickName(nickName)) {
            System.out.println("[InsertMember] 닉네임 형식이 올바르지 않음");
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "닉네임은 2~10자여야 합니다.");
            response.getWriter().write(jsonResponse.toString());
            return null;
        }
        if (!isValidUserName(userName)) {
            System.out.println("[InsertMember] 이름 형식이 올바르지 않음");
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "이름은 한글 또는 영문으로 2~20자여야 합니다.");
            response.getWriter().write(jsonResponse.toString());
            return null;
        }
        if (!isValidEmail(userEmail)) {
            System.out.println("[InsertMember] 이메일 형식이 올바르지 않음");
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "유효한 이메일 형식이 아닙니다.");
            response.getWriter().write(jsonResponse.toString());
            return null;
        }

        // 비밀번호 해싱
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("[InsertMember] 비밀번호 해싱 완료: " + hashedPassword);

        // 현재 시간을 회원 생성 시간과 비밀번호 갱신 시간으로 설정
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        System.out.println("[InsertMember] 현재 시간 설정 완료: " + currentTime);

        // UsersDTO 객체 생성 및 데이터 설정
        UsersDTO dto = new UsersDTO();
        dto.setUserName(userName);
        dto.setLoginId(loginId);
        dto.setNickName(nickName);
        dto.setPassword(hashedPassword);
        dto.setCreateTime(currentTime);
        dto.setPwUpdateTime(currentTime);
        dto.setUserEmail(userEmail);

        System.out.println("[InsertMember] UsersDTO 객체 생성 및 데이터 설정 완료");

        // UsersService를 사용하여 회원 등록
        int result = UsersService.getInstance().insertMember(dto);

        if (result > 0) { // 회원가입 성공
            System.out.println("[InsertMember] 회원가입 성공");
            response.sendRedirect("./IdInsertSuccessPage.jsp"); // 성공 페이지로 리다이렉트
        } else { // 회원가입 실패
            System.out.println("[InsertMember] 회원가입 실패");
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "회원가입에 실패했습니다. 다시 시도해주세요.");
            response.getWriter().write(jsonResponse.toString());
        }

        System.out.println("[InsertMember] execute() 종료");
        return null; // JSON 응답만 반환
    }

    // 로그인 ID 검증
    private boolean isValidLoginId(String loginId) {
        return loginId != null && loginId.matches("^[a-zA-Z0-9]{3,20}$");
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
