package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import service.UsersService;
import view.ModelAndView;

/*
 * ProfileImageController
 * - 프로필 이미지 업로드 및 삭제를 처리하는 컨트롤러 클래스
 */
public class ProfileImageController implements Controller {
    // 이미지가 저장될 서버 경로 (로컬 저장소)
    private static final String UPLOAD_DIR = "C:/ProfileUserIMG"; // 이미지 저장 경로
    private static final String DEFAULT_IMAGE = "Default_IMG.png"; // 기본 이미지 파일명

    // UsersService 객체: 사용자 관련 DB 작업을 수행
    private UsersService usersService = UsersService.getInstance();

    /*
     * execute 메서드
     * - 업로드 또는 삭제 요청을 처리하는 메인 메서드
     * @param request  HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @return ModelAndView 페이지 이동 설정
     */
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        // 페이지 이동 및 리다이렉트 여부를 설정하는 객체
        ModelAndView view = new ModelAndView();
        
        // 요청 파라미터에서 action (업로드 or 삭제) 및 사용자 번호를 가져옴
        String action = request.getParameter("action");
        int userNumber = Integer.parseInt(request.getParameter("userNumber"));

        try {
            // action 값에 따라 업로드 또는 삭제 메서드 실행
            if ("upload".equals(action)) {
                handleImageUpload(request, userNumber);
                System.out.println("이미지 업로드 성공: 사용자 번호 " + userNumber);
            } else if ("delete".equals(action)) {
                handleImageDelete(userNumber);
                System.out.println("이미지 삭제 성공: 사용자 번호 " + userNumber);
            }

            // 작업 완료 후 마이페이지로 리다이렉트 설정
            view.setRedirect(true);
            view.setPath("mypageView.do");
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지를 출력 및 설정
            e.printStackTrace();
            System.out.println("오류 발생: " + e.getMessage());
            request.setAttribute("errorMessage", "이미지 처리 중 오류가 발생했습니다: " + e.getMessage());

            // 오류 발생 시 JSP 페이지로 포워딩 설정
            view.setRedirect(false);
            view.setPath("/views/mypageView.jsp");
        }

        return view;
    }

    /*
     * handleImageUpload
     * - 프로필 이미지 업로드를 처리하는 메서드
     * @param request    HTTP 요청 객체
     * @param userNumber 사용자 번호
     * @throws IOException
     * @throws ServletException
     */
    private void handleImageUpload(HttpServletRequest request, int userNumber) throws IOException, ServletException {
        // 요청에서 파일 Part 객체를 가져옵니다.
        Part filePart = request.getPart("profileImage");

        // 파일이 선택되었는지 확인
        if (filePart != null && filePart.getSize() > 0) {
            // 기존 프로필 이미지 삭제
            String oldImage = usersService.getProfileImage(userNumber);
            if (oldImage != null && !oldImage.equals(DEFAULT_IMAGE)) {
                File oldFile = new File(UPLOAD_DIR, oldImage); // 기존 파일 경로 설정
                if (oldFile.exists()) {
                    oldFile.delete(); // 기존 이미지 삭제
                    System.out.println("기존 이미지 삭제: " + oldImage);
                }
            }

            // 새 이미지 저장
            String originalFileName = filePart.getSubmittedFileName(); // 원본 파일명
            String extension = getFileExtension(originalFileName); // 파일 확장자 추출

            // 확장자 검증
            if (!isValidImageExtension(extension)) {
                throw new IOException("유효하지 않은 이미지 파일입니다.");
            }

            // 새 파일명 생성: UUID + 사용자 번호 + 확장자
            String newFileName = UUID.randomUUID().toString() + "-" + userNumber + "." + extension;
            
            // 파일 저장 (UPLOAD_DIR에 새 파일 저장)
            filePart.write(UPLOAD_DIR + File.separator + newFileName);
            System.out.println("새 이미지 저장: " + newFileName);

            // DB에 새 이미지 파일명 업데이트
            usersService.updateProfileImage(userNumber, newFileName);
        } else {
            // 파일이 선택되지 않은 경우 예외 처리
            throw new IOException("파일이 선택되지 않았습니다.");
        }
    }

    /*
     * handleImageDelete
     * - 프로필 이미지를 기본 이미지로 변경하는 메서드
     * @param userNumber 사용자 번호
     */
    private void handleImageDelete(int userNumber) {
        // 사용자 이미지 정보를 기본 이미지로 설정
        usersService.deleteProfileImage(userNumber);
        System.out.println("이미지가 기본 이미지로 변경됨: 사용자 번호 " + userNumber);
    }

    /*
     * getFileExtension
     * - 파일명에서 확장자를 추출하는 메서드
     * @param fileName 파일명
     * @return 확장자 (소문자)
     */
    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /*
     * isValidImageExtension
     * - 이미지 파일 확장자 검증
     * @param extension 파일 확장자
     * @return 유효한 확장자인 경우 true 반환
     */
    private boolean isValidImageExtension(String extension) {
        return extension.matches("png|jpg|jpeg");
    }
}
