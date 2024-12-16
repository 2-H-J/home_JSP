package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmailService;
import view.ModelAndView;

public class SendVerificationCodeController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String verificationCode = generateVerificationCode(); // 인증 코드 생성 로직 추가

        EmailService emailService = new EmailService(); // EmailService 인스턴스 생성
        boolean emailSent = emailService.sendVerificationCode(email, verificationCode);

        ModelAndView view = new ModelAndView();
        if (emailSent) {
            request.getSession().setAttribute("verificationCode", verificationCode);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null; // Ajax 요청은 반환할 view가 없음
    }

    private String generateVerificationCode() {
        // 간단한 6자리 숫자 코드 생성
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
