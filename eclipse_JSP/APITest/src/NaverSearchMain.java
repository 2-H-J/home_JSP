import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
// 12월3일 영상
public class NaverSearchMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 사용자 입력을 받기 위해 Scanner 객체 생성
        
        System.out.print("검색어 입력 : "); // 사용자에게 검색어 입력 요청
        // naverNewsSearch 메서드 호출 후 결과 출력
        System.out.println(naverNewsSearch(sc.nextLine())); 
    }

    // 네이버 뉴스 검색 API를 호출하는 메서드
    public static String naverNewsSearch(String text) {
        // 0. API 클라이언트 Id, Secret 키값들 선언
        String clientId = "애플리케이션 클라이언트 아이디"; // 애플리케이션 클라이언트 아이디 (발급받은 값)
        String clientSecret = "애플리케이션 클라이언트 시크릿"; // 애플리케이션 클라이언트 시크릿 키 (발급받은 값)

        StringBuilder sb = new StringBuilder(); // API 응답 결과를 저장할 StringBuilder 객체
        
        // 0-1. 보낼 데이터를 인코딩 - UTF-8 후 try/catch
        try {
            // 검색어(text)를 UTF-8 방식으로 인코딩하여 URL에 안전하게 포함
            text = URLEncoder.encode(text, "UTF-8");

            // 1. URL 설정 후 쿼리 스트링(+ text)도 적용
            // 네이버 검색 API의 뉴스 검색 URL에 인코딩된 검색어를 쿼리로 추가
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text; // JSON 결과 요청

            // 2. URL 생성 및 Connection 생성
            URL url = new URL(apiURL); // 위에서 만든 API URL 객체로 생성
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // HttpURLConnection 객체로 연결

            // 3. Connection 설정
            // 헤더 설정 -> 인증 정보 (클라이언트 키값, 시크릿 값, API 키값) - 네이버 API에서 제공된 인증 방식
            conn.setRequestMethod("GET"); // HTTP 요청 메서드를 GET으로 설정
            conn.addRequestProperty("X-Naver-Client-Id", clientId); // 네이버 API 클라이언트 ID
            conn.addRequestProperty("X-Naver-Client-Secret", clientSecret); // 네이버 API 클라이언트 시크릿

            // 4. 응답 결과 받기 *에러코드 종류 - ( 200:정상 / 404:경로 X / 401:인증 X / 403:접속 권한X )
            int responseCode = conn.getResponseCode(); // 서버로부터 받은 응답 코드를 저장

            // 4-1. 응답 결과 코드종류 200(정상)이 아니면 예외처리
            if (responseCode != 200) { // 응답 코드가 200이 아닌 경우 예외 발생
                throw new Exception("호출 오류"); // 사용자 정의 예외 메시지
            }

            // 5. 데이터 읽기 -> 문자열로 받음 (json, xml)
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) { // 연결에서 데이터 입력 스트림을 생성
                String str = ""; // 한 줄씩 데이터를 읽기 위한 변수
                
                // API 응답을 한 줄씩 읽어서 StringBuilder에 추가
                while ((str = br.readLine()) != null) {
                    sb.append(str); // StringBuilder에 읽은 줄 추가
                }
                sb.toString(); // StringBuilder를 String으로 변환 (필요 없음, 이미 StringBuilder 객체 활용 중)
            }

        // URL 인코딩 예외 처리 (UnsupportedEncodingException)
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // UTF-8 인코딩 중 예외 발생 시 스택 트레이스를 출력

        // URL 형식이 잘못된 경우 예외 처리 (MalformedURLException)
        } catch (MalformedURLException e) { // apiURL catch
            e.printStackTrace(); // URL 객체 생성 중 예외 발생 시 스택 트레이스 출력

        // 연결 관련 예외 처리 (IOException)
        } catch (IOException e) { // url.openConnection catch
            e.printStackTrace(); // 연결 과정 중 예외 발생 시 스택 트레이스 출력

        // 호출 오류 예외 처리
        } catch (Exception e) { // throw new Exception("호출 오류"); catch
            e.printStackTrace(); // 사용자 정의 예외 메시지 출력
        }

        // API 호출 결과 (JSON 데이터) 반환
        return sb.toString();
    }
}
