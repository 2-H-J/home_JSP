package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/fileUpload.do")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5, // 파일 사이즈 - maxFileSize = 1024 * 1024 * 5 : 1MB
		maxRequestSize = 1024 * 1024 * 50 // 전송 사이즈 - maxRequestSize = 1024 * 1024 * 50 : 50MB
) 

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 업로드할 파일1 정보를 읽는 부분
//		Part part = request.getPart("file1");
//		
//		System.out.println(part.getName()); 
//		// jsp파일의 input태그의 첫번째 name 속성값
		
//		System.out.println(part.getSize()); 
//		// 파일 사이즈
		
//		System.out.println(part.getHeader("content-disposition"));
//		// 파일데이터 전송방법 + jsp파일의 input태그의 첫번째 name 속성값 + 파일명
		
//		System.out.println(part.getSubmittedFileName());
//		// 파일의 파일명
		
		// Part part = request.getPart("file1"); 이거로는 name 속성값(file1)을 지정해서 뽑기 때문에
		// 불편하니 파일 업로드가 여러개면 반복문을 통해서 파일업로드 값을 뽑는다
		
//		------------------------------------------------------------------------------------------------
		
		// 저장할 경로에 파일저장할 폴더 사전에 미리 만들어 주기 (1)
		File root = new File("c:\\FileUpload");
		
		// 해당 경로의 저장할 폴더가 있는지 체크, 없으면 해당 경로에 폴더 생성 (2)
		if(!root.exists()) {
			System.out.println("파일 업로드할 경로에 폴더 생성 완료 : "+root);
			root.mkdirs();
		}
		
		// 업로드할 파일 정보를 읽는 부분 (3)
		Iterator<Part> it = request.getParts().iterator();
		
		// 콜렉션으로 올린 파일의 모든 정보를 받아옴 (4)
		// 콜렉션의 문제 list로 바꿔서 반복문으로 쓰기 힘드니 
		// 처음부터 끝까지 탐색을 할때 콜렉션 계열을 처음부터 끝까지 탐색을 할땐 iterator()로 해서
		// 아래와 같이 반복문으로 hasNext(), next() 해서 처음부터 끝까지 탐색 가능
		while(it.hasNext()) {
			
			Part part = it.next();
			
			// 파일을 만약 하나만 올렸을경우 나머지 안올린 파일은 걸러주어야하는 작업을 해야한다. (5)
			// if (!part.getSubmittedFileName().isEmpty() : 파일명이 비어있지 않을때 실행
			if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
//			part.getSubmittedFileName() != null : 만약 파일이 아니라 다른 타입이 들어오면 
//													에러 발생하지 않고 파일만 들어 올 수있게 적용
//			!part.getSubmittedFileName().isEmpty() : 파일명이 없으면 들어오지 못하게
				System.out.println("input태그의 name 속성값 : "+part.getName());
				System.out.println("파일의 파일명 : "+part.getSubmittedFileName());
				System.out.println("파일의 사이즈 : "+part.getSize());
			
				// 파일쓰기 ( 파일을 저장할 경로)(6)
//				part.write(part.getSubmittedFileName()); // root랑 경로연결
				/*
				만약 (1)~(2)번 코드없이 위 코드대로 실행하면
				java.io.FileNotFoundException: c:\fileupload\파일명 (지정된 경로를 찾을 수 없습니다) 에러 발생
				fileupload의 폴더가 없으니 에러발생 그러니 회원이 새로 들어올때마다
				폴더를 새로 만들어야 하기 때문에 경로를 사전에 체크를 하고 없으면 만드는 코드를 작성 해야한다.  
				File file = new File("c:\\fileupload"); 위에 생성
				*/
				
				// 파일쓰기 ( 파일을 저장할 경로)(6)
				part.write(root.getAbsolutePath()+"\\"+part.getSubmittedFileName());
				
			}else {
				// 받은 내용이 파일이 아닐때 네임속성값과 파라미터값을 꺼내게끔 처리
				System.out.println("못받는 네임 속성 : "+part.getName());
				System.out.println("못받는 파리미터값 : "+request.getParameter(part.getName()));
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
