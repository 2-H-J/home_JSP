import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class KakaoSearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("검색어 입력 : ");
		System.out.println(kakaoSearch(sc.nextLine()));

	}

	public static String kakaoSearch(String text) {

		StringBuilder sb = new StringBuilder();

		try {
			// 0. 보낼 데이터를 인코딩 - UTF-8
			text = URLEncoder.encode(text, "UTF-8");
			// 1. URL 셋팅 - 쿼리 스트링도 적용
			String apiURL = "https://dapi.kakao.com/v2/search/image?query=" + text; // JSON 결과
			// 2. URL 생성 및 Connection 생성
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 3. Connection 설정
			// 헤더 설정 -> 인증 정보 (클라이언트 키캆, 시크릿 값, API 키값)
			// Method 설정
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Authorization", "KakaoAK REST_API_KEY");//REST_API_KEY에 키값 넣기

			// 4. 응답 결과 - 200 정상, 404 경로 X, 401 인증 X, 403 접속 권한 X
			int responseCode = conn.getResponseCode();

			if (responseCode != 200) {
				throw new Exception("호출 오류");
			}

			// 5. 데이터 읽기 --> 문자열로 받음(json, xml)
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String str = "";

				while ((str = br.readLine()) != null)
					sb.append(str);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}