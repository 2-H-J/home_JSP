package controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Base64;

import dto.BoardFileDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardsService;
import view.ModelAndView;

public class BoardFileDownController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int fileNumber = Integer.parseInt(request.getParameter("fileNumber"));
        
        // DB에서 Base64 인코딩된 파일 데이터를 가져옴
        BoardFileDTO fdto = BoardsService.getInstance().selectFilePath(fileNumber);
        String base64Encoded = fdto.getFilePath();
        String fileName = fdto.getFileName();
        
        // 세션에서 사용자 정보 가져오기
        HttpSession session = request.getSession();
        UsersDTO user = (UsersDTO) session.getAttribute("user");

        if (user == null) {
            // 세션에 사용자 정보가 없을 경우 로그인 페이지로 리다이렉트
            response.sendRedirect("login.do");
            return null;
        }

        // Base64로 인코딩된 파일을 디코딩하여 바이트 배열로 변환
        byte[] fileContent = Base64.getDecoder().decode(base64Encoded);

        // 파일 확장자 추출 (파일 이름에서 마지막 점을 기준으로 확장자 추출)
        String fileExtension = "";
        if (fileName.lastIndexOf('.') > 0) {
            fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        }

        // MIME 타입 설정
        String mimeType = "application/octet-stream"; // 기본값: 바이너리 파일

        switch (fileExtension) {
            case "jpg":
            case "jpeg":
                mimeType = "image/jpeg";
                break;
            case "png":
                mimeType = "image/png";
                break;
            case "gif":
                mimeType = "image/gif";
                break;
            case "pdf":
                mimeType = "application/pdf";
                break;
            case "txt":
                mimeType = "text/plain";
                break;
            case "zip":
                mimeType = "application/zip";
                break;
            default:
                mimeType = "application/octet-stream"; // 기본값
                break;
        }

        // 클라이언트에게 다운로드할 파일의 이름 설정 (확장자를 포함한 파일 이름)
        String fileNaming = fileName; // 이미 파일 이름에 확장자가 포함되어 있다고 가정

        // MIME 타입 설정
        response.setHeader("Content-Disposition", "attachment;filename=" + fileNaming);
        response.setContentType(mimeType);
        response.setContentLength(fileContent.length);

        // 바이트 배열을 클라이언트에게 전송
        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            bos.write(fileContent);
            bos.flush();
        }

        return null;
    }
}
