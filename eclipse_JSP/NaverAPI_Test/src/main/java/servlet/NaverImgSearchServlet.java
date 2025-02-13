package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/naverImgSearch.do")
public class NaverImgSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String search = request.getParameter("search");

	    // 검색어 확인 로그 추가
	    System.out.println("수신된 검색어: " + search);

	    // 검색어 유효성 검사
	    if (search == null || search.trim().isEmpty()) {
	        response.setContentType("application/json;charset=utf-8");
	        response.getWriter().println("{\"errorCode\":\"SE01\",\"errorMessage\":\"검색어를 입력해주세요.\"}");
	        return;
	    }

	    System.out.println("검색어: " + search);
	    String result = naverImgSearch(search);
	    System.out.println("API 응답 데이터: " + result);

	    response.setContentType("application/json;charset=utf-8");
	    response.getWriter().println(result);
	}

	public String naverImgSearch(String search) {
		String clientId = "6UBhCfjedOpSOuhskcCF";
		String clientSecret = "GU3p5IZHHp"; // 시크릿 패스워드 

		try {
			search = URLEncoder.encode(search, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

//		String apiURL = "https://openapi.naver.com/v1/image?query=" + search; 
		String apiURL = "https://openapi.naver.com/v1/search/image?query=" + search;
		
		System.out.println("API 호출 URL: " + apiURL); // API 호출 URL 로그 추가

		HttpURLConnection con = null;
		String result = null;
		try {
			URL url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			int responseCode = con.getResponseCode();
			
			InputStreamReader ir = null;
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				ir = new InputStreamReader(con.getInputStream());
			} else { // 오류 발생
				ir = new InputStreamReader(con.getErrorStream());
			}
			try (BufferedReader lineReader = new BufferedReader(ir)) {
				StringBuilder responseBody = new StringBuilder();

				String line;
				
				while ((line = lineReader.readLine()) != null) {
					responseBody.append(line);
				}
				// BufferedReader를 사용하여 응답 데이터를 읽고 StringBuilder에 저장
				
				result = responseBody.toString();
				
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
			}
			
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
		return result;

	}

}
