import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class NaverSearchMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("검색어 입력 : ");
		System.out.println(naverNewsSearch(sc.nextLine()));
	}

	public static String naverNewsSearch(String text) {
		// 0. API 클라이언트 Id, Secret 키값들 선언
		String clientId = "sxXgzmDv4_r2We_I7LqF"; // 애플리케이션 클라이언트 아이디
		String clientSecret = "GcSOpaAChN"; // 애플리케이션 클라이언트 시크릿
		
		StringBuilder sb = new StringBuilder();
		
		// 0-1. 보낼 데이터를 인코딩 - UTF-8 후 try/catch
		try {
			text = URLEncoder.encode(text, "UTF-8");

			// 1. URL setting후 쿼리 스트링(+ text)도 적용
			String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text; // JSON 결과

			// 2. URL 생성 및 Connection 생성
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 3. Connection 설정
			// 헤더 설정 -> 인증 정보 (클라이언트 키값, 시크릿 값, API 키값) - 꼭 헤더로 키값 설정을 안할 수도 잇다.
			// Method 설정
			conn.setRequestMethod("GET");
			conn.addRequestProperty("X-Naver-Client-Id", clientId);
			conn.addRequestProperty("X-Naver-Client-Secret", clientSecret);

			// 4. 응답 결과 받기 *에러코드 종류 - ( 200:정상 / 404:경로 X / 401:인증 X / 403:접속 권한X )
			int responseCode = conn.getResponseCode();

			// 4-1. 응답 결과 코드종류 200(정상)이 아니면 예외처리
			if (responseCode != 200) {
				throw new Exception("호출 오류");
			}

			// 5. 데이터 읽기 -> 문자열로 받음 (json, xml)
			try (BufferedReader br 
					= new BufferedReader(
							new InputStreamReader(conn.getInputStream()))) {
				String str = "";
				
				
				while((str = br.readLine()) != null) {
					sb.append(str);
				}
				sb.toString();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		} catch (MalformedURLException e) { // apiURL catch
			e.printStackTrace();

		} catch (IOException e) { // url.openConnection catch
			e.printStackTrace();

		} catch (Exception e) { // throw new Exception("호출 오류"); catch
			e.printStackTrace();
		}

		return sb.toString();
	}
}
