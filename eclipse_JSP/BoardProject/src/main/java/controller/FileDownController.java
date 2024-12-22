package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class FileDownController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. 파일 번호(fno) 추출
        // - 클라이언트 요청에서 파일 번호를 가져옵니다.
        // - 파일 번호는 다운로드할 파일을 식별하기 위한 고유값입니다.
        int fno = Integer.parseInt(request.getParameter("fno"));

        // 2. 파일 경로 조회
        // - BoardService의 selectFilePath 메서드를 호출하여 파일 경로를 가져옵니다.
        // - 데이터베이스에서 해당 파일 번호에 대응하는 파일 경로를 반환합니다.
        String path = BoardService.getInstance().selectFilePath(fno);

        // 3. 파일 객체 생성
        // - File 클래스는 파일이나 디렉터리를 나타냅니다.
        // - path에 해당하는 파일 객체를 생성합니다.
        File file = new File(path);

        // 4. HTTP 응답 헤더 설정
        // - 다운로드 파일을 브라우저가 처리할 수 있도록 응답 헤더를 설정합니다.
        // - "Content-Disposition": 파일 이름과 다운로드 형태를 지정합니다.
        // - "Content-Transfer-Encoding": 데이터 전송 방식 설정
        // - setContentLength(): 파일 크기를 설정합니다.
        response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setContentLength((int) file.length());

        // 5. 파일 읽기 및 전송
        // - FileInputStream: 파일 데이터를 읽어오는 클래스
        // - BufferedOutputStream: 클라이언트로 데이터를 전송하는 출력 스트림
        try (FileInputStream fis = new FileInputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            
            // 버퍼 크기 1MB 설정
            byte[] buffer = new byte[1024 * 1024];
            
            while (true) {
                // 파일에서 데이터 읽기
                int size = fis.read(buffer);
                if (size == -1) break; // 더 이상 읽을 데이터가 없으면 종료

                // 데이터를 클라이언트로 전송
                bos.write(buffer, 0, size);
                bos.flush(); // 출력 스트림에 남아 있는 데이터를 강제로 전송
            }
        }

        // 6. null 반환
        // - 이 컨트롤러는 파일 다운로드만 처리하며, 추가적인 뷰로 이동하지 않습니다.
        return null;
    }
}
