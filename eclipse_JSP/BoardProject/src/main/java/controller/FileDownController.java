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
			//다운받을 파일의 게시글 번호, 파일번호 
//			int bno = Integer.parseInt(request.getParameter("bno"));
			int fno = Integer.parseInt(request.getParameter("fno"));
			// ... 차후에 테이블에서 전송할 파일 경로를 받아옴
			String path = BoardService.getInstance().selectFilePath(fno);
			
			File file = new File(path);
			response.setHeader("Content-Disposition",
					"attachement;fileName="+file.getName());
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentLength((int)file.length());
			
			//스트림을 이용해서 파일을 읽어서, 클라이언트에게 전송
			try(FileInputStream fis = new FileInputStream(file);
				BufferedOutputStream bos 
							= new BufferedOutputStream(response.getOutputStream())){
				byte[] buffer = new byte[1024 * 1024];
				while(true) {
					int size = fis.read(buffer);
					if(size == -1) break;
					bos.write(buffer,0,size);
					bos.flush();
				}
			}
		return null;
	}

}