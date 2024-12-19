package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dto.UsersDTO;
import service.UsersService;
import view.ModelAndView;

/**
 * MyPageViewController 클래스는 마이페이지 이동 및 프로필 이미지 업로드/삭제 요청을 처리한다.
 */
public class MyPageViewController implements Controller {

    private UsersService usersService = UsersService.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[MyPageViewController] execute() 호출 -> 시작");

        HttpSession session = request.getSession();
        System.out.println("[MyPageViewController] 세션 가져오기 완료 -> 세션 ID: " + session.getId());

        UsersDTO sessionUser = (UsersDTO) session.getAttribute("user");
        if (sessionUser == null) {
            System.out.println("[MyPageViewController] 세션에 사용자 정보가 없습니다. -> 로그인 페이지로 이동");
            session.invalidate(); // 세션 초기화
            return redirectToLogin();
        }
        System.out.println("[MyPageViewController] 세션 사용자 정보 확인 -> userNumber: " + sessionUser.getUserNumber());

        int userNumber = sessionUser.getUserNumber();

        String action = request.getParameter("action");
        System.out.println("[MyPageViewController] 요청 파라미터 확인 -> action: " + action);

        if (action != null) {
            if ("uploadProfile".equals(action)) {
                System.out.println("[MyPageViewController] 프로필 업로드 요청 처리 시작");
                handleProfileUpload(request, userNumber);
                System.out.println("[MyPageViewController] 프로필 업로드 처리 완료");
            } else if ("deleteProfile".equals(action)) {
                System.out.println("[MyPageViewController] 프로필 삭제 요청 처리 시작");
                handleProfileDelete(userNumber);
                System.out.println("[MyPageViewController] 프로필 삭제 처리 완료");
            } else {
                System.out.println("[MyPageViewController] 지원하지 않는 action 값: " + action);
            }
        } else {
            System.out.println("[MyPageViewController] action 파라미터가 없습니다.");
        }

        System.out.println("[MyPageViewController] 사용자 정보 재조회 시작 -> userNumber: " + userNumber);
        UsersDTO user = usersService.findUserByUserNumber(userNumber);
        if (user == null) {
            System.out.println("[MyPageViewController] 세션에 사용자 정보가 없습니다. -> 로그인 페이지로 이동");
            return redirectToLogin();
        } else {
            System.out.println("[MyPageViewController] 사용자 정보 확인 -> 이름: " + user.getUserName());
        }

        System.out.println("[MyPageViewController] 사용자 정보를 request 및 session에 저장");
        request.setAttribute("user", user);
        session.setAttribute("user", user); // 세션에 최신 사용자 정보 저장

        // 프로필 이미지를 Base64 형식으로 변환
        String profileImageBase64 = getProfileImageBase64(user.getProfileImageUrl());
        request.setAttribute("profileImageBase64", profileImageBase64);

        System.out.println("[MyPageViewController] 사용자 프로필 이미지 -> " + user.getProfileImageUrl());

        ModelAndView view = new ModelAndView();
        view.setPath("./mypageView.jsp");
        view.setRedirect(false);

        System.out.println("[MyPageViewController] 마이페이지로 포워드 설정 완료 -> Path: " + view.getPath());
        System.out.println("[MyPageViewController] execute() 완료 -> ModelAndView 반환");

        return view;
    }

    private void handleProfileUpload(HttpServletRequest request, int userNumber) throws IOException, ServletException {
        System.out.println("[MyPageViewController] 프로필 업로드 처리 시작 -> 사용자 번호: " + userNumber);
        try {
            usersService.uploadProfileImage(request, userNumber);
            System.out.println("[MyPageViewController] 프로필 업로드 완료 -> 사용자 번호: " + userNumber);
        } catch (Exception e) {
            System.out.println("[MyPageViewController] 프로필 업로드 중 오류 발생: " + e.getMessage());
            throw new ServletException(e); // 혹은 사용자에게 알맞은 에러 메시지 전달
        }
    }

    private void handleProfileDelete(int userNumber) throws IOException {
        System.out.println("[MyPageViewController] 프로필 삭제 처리 시작 -> 사용자 번호: " + userNumber);
        usersService.deleteProfileImage(userNumber);
        System.out.println("[MyPageViewController] 프로필 삭제 완료 -> 사용자 번호: " + userNumber);
    }

    private ModelAndView redirectToLogin() {
        System.out.println("[MyPageViewController] 로그인 페이지로 리다이렉트 설정");
        ModelAndView view = new ModelAndView();
        view.setPath("./loginView.jsp");
        view.setRedirect(true);
        System.out.println("[MyPageViewController] 리다이렉트 설정 완료 -> Path: " + view.getPath());
        return view;
    }

    private String getProfileImageBase64(String imagePath) {
        try {
            // 저장된 이미지 경로를 가져와 파일을 읽음
            File imageFile = new File("C:/ProfileUserIMG", imagePath.replace("/img/profiles/", ""));
            if (!imageFile.exists()) {
                System.out.println("[MyPageViewController] 프로필 이미지 파일이 존재하지 않습니다.");
                return null;
            }
            byte[] fileContent = Files.readAllBytes(imageFile.toPath());
            System.out.println("[MyPageViewController] 프로필 이미지 Base64 변환 완료");
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.out.println("[MyPageViewController] 프로필 이미지 Base64 변환 중 오류 발생: " + e.getMessage());
            return null;
        }
    }
}
