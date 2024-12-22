package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import service.UsersService;

/*
 * ProfileImageServlet
 * - 프로필 이미지 업로드, 삭제 및 가져오기를 처리하는 서블릿
 */
@WebServlet("/profileImage") // 서블릿 매핑 경로 설정
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 5,       // 단일 파일 최대 크기: 5MB
    maxRequestSize = 1024 * 1024 * 50    // 전체 요청 최대 크기: 50MB
)
public class ProfileImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 이미지 파일 저장 경로
    private static final String IMAGE_PATH = "C:/ProfileUserIMG/";
    // 기본 이미지 파일명
    private static final String DEFAULT_IMAGE = "Default_IMG.png";

    // 사용자 서비스 객체: 사용자 관련 DB 작업 수행
    private UsersService usersService = UsersService.getInstance();

    /*
     * POST 요청 처리: 이미지 업로드 및 삭제
     * @param request  HTTP 요청 객체
     * @param response HTTP 응답 객체
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 경로 확인 및 생성
            ensureDirectoryExists(IMAGE_PATH);

            // 요청에서 동작(action)과 사용자 번호(userNumber)를 가져옴
            String action = request.getParameter("action"); // upload or delete
            int userNumber = Integer.parseInt(request.getParameter("userNumber"));

            // 이미지 삭제 처리
            if ("delete".equals(action)) {
                handleImageDelete(userNumber); // 이미지 삭제 메서드 호출
                response.sendRedirect("mypageView.do"); // 삭제 후 마이페이지로 리다이렉트
                return;
            }

            // 파일 업로드 처리
            Part filePart = request.getPart("profileImage"); // 파일 데이터 가져오기
            if (filePart != null && filePart.getSize() > 0) {
                // 1. 파일 확장자 검증
                String originalFileName = filePart.getSubmittedFileName(); // 원본 파일명
                String extension = getFileExtension(originalFileName); // 파일 확장자 추출
                if (!isValidImageExtension(extension)) {
                    System.out.println("[ProfileImageServlet] 유효하지 않은 파일 업로드 시도: " + extension);
                    response.sendRedirect("mypageView.do?error=invalidFile");
                    return;
                }

                // 2. 기존 이미지 삭제
                String oldImage = usersService.getProfileImage(userNumber); // 기존 이미지명 가져오기
                if (oldImage != null && !oldImage.equals(DEFAULT_IMAGE)) {
                    File oldFile = new File(IMAGE_PATH + oldImage);
                    if (oldFile.exists()) {
                        oldFile.delete(); // 기존 이미지 파일 삭제
                        System.out.println("[ProfileImageServlet] 기존 이미지 삭제: " + oldImage);
                    }
                }

                // 3. 새 이미지 저장
                String newFileName = UUID.randomUUID().toString() + "-" + userNumber + "." + extension;
                File uploadFile = new File(IMAGE_PATH + newFileName);
                filePart.write(uploadFile.getAbsolutePath()); // 파일 저장
                System.out.println("[ProfileImageServlet] 새 이미지 업로드 성공: " + newFileName);

                // 4. DB 업데이트
                usersService.updateProfileImage(userNumber, newFileName); // DB에 새 이미지 파일명 저장
                response.sendRedirect("mypageView.do"); // 성공 시 리다이렉트
            } else {
                // 파일이 선택되지 않은 경우
                System.out.println("[ProfileImageServlet] 파일이 선택되지 않았습니다.");
                response.sendRedirect("mypageView.do?error=noFile");
            }
        } catch (Exception e) {
            // 예외 발생 시 오류 처리
            e.printStackTrace();
            System.out.println("[ProfileImageServlet] 오류 발생: " + e.getMessage());
            response.sendRedirect("mypageView.do?error=serverError");
        }
    }

    /*
     * GET 요청 처리: 이미지 가져오기
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 요청 파라미터에서 이미지 파일명을 가져옴
        String imageName = request.getParameter("image");
        File imageFile;

        // 이미지 파일이 없거나 비어있는 경우 기본 이미지로 설정
        if (imageName == null || imageName.isEmpty()) {
            imageFile = new File(getServletContext().getRealPath("/img/defaultProfile/") + DEFAULT_IMAGE);
        } else {
            imageFile = new File(IMAGE_PATH + imageName); // 요청된 이미지 파일 경로
            if (!imageFile.exists()) {
                // 요청된 이미지가 없으면 기본 이미지로 대체
                imageFile = new File(getServletContext().getRealPath("/img/defaultProfile/") + DEFAULT_IMAGE);
            }
        }

        // 이미지 파일을 클라이언트에 전송
        response.setContentType("image/jpeg");
        try (FileInputStream in = new FileInputStream(imageFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        }
    }

    /*
     * 경로 확인 및 디렉토리 생성 메서드
     * @param path 생성하려는 경로
     * @throws IOException 생성 실패 시 예외
     */
    private void ensureDirectoryExists(String path) throws IOException {
        File directory = new File(path);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("폴더 생성 실패: " + path);
        }
    }

    /*
     * 이미지 확장자 검증 메서드
     * @param extension 파일 확장자
     * @return 유효한 확장자인 경우 true 반환
     */
    private boolean isValidImageExtension(String extension) {
        return extension != null && extension.matches("(?i)jpg|jpeg|png|bmp|webp"); // 대소문자 구분 없이 검증
    }

    /*
     * 파일 확장자 추출 메서드
     * @param fileName 파일명
     * @return 파일 확장자
     */
    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        }
        return null;
    }

    /*
     * 이미지 삭제 처리 메서드
     * @param userNumber 사용자 번호
     */
    private void handleImageDelete(int userNumber) {
        // DB에서 기존 이미지 파일명 가져오기
        String oldImage = usersService.getProfileImage(userNumber);
        if (oldImage != null && !oldImage.equals(DEFAULT_IMAGE)) {
            File oldFile = new File(IMAGE_PATH + oldImage);
            if (oldFile.exists()) {
                oldFile.delete(); // 기존 이미지 파일 삭제
                System.out.println("[ProfileImageServlet] 기존 이미지 삭제: " + oldImage);
            }
        }

        // DB에 기본 이미지로 설정
        usersService.deleteProfileImage(userNumber);
        System.out.println("[ProfileImageServlet] 이미지 삭제 완료: 기본 이미지 설정");
    }
}
