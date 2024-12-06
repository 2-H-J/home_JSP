package view;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    // redirect 여부 (페이지 이동 방식 결정)
    private boolean redirect;
    // 이동할 페이지 경로
    private String path;
    // request 영역에 저장할 데이터 (모델 데이터)
    private Map<String, Object> model = new HashMap<>();

    // 모델 데이터 추가 메서드
    public void addObject(String key, Object value) {
        model.put(key, value);
        System.out.println("[ModelAndView] addObject() - 파일: ModelAndView.java -> key: " + key + ", value: " + value + " -> 데이터 추가됨, 다음 단계로 이동 (Controller에서 ModelAndView로 데이터 추가됨)");
    }

    // redirect 여부 반환
    public boolean isRedirect() {
        System.out.println("[ModelAndView] isRedirect() - 파일: ModelAndView.java -> redirect 상태 반환 -> 현재 상태: " + redirect + " (현재 ModelAndView의 redirect 상태 확인)");
        return redirect;
    }

    // redirect 여부 설정 메서드
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
        System.out.println("[ModelAndView] setRedirect() - 파일: ModelAndView.java -> redirect 설정: " + redirect + " -> DispatcherServlet.java에서 설정됨 (DispatcherServlet에서 ModelAndView의 redirect 설정)");
    }

    // 이동할 페이지 경로 반환
    public String getPath() {
        System.out.println("[ModelAndView] getPath() - 파일: ModelAndView.java -> 이동할 경로 반환 -> 현재 경로: " + path + " (현재 ModelAndView의 경로 반환)");
        return path;
    }

    // 이동할 페이지 경로 설정 메서드
    public void setPath(String path) {
        this.path = path;
        System.out.println("[ModelAndView] setPath() - 파일: ModelAndView.java -> 경로 설정: " + path + " -> DispatcherServlet.java에서 설정됨 (DispatcherServlet에서 ModelAndView의 경로 설정)");
    }

    // 모델 데이터 반환
    public Map<String, Object> getModel() {
        System.out.println("[ModelAndView] getModel() - 파일: ModelAndView.java -> 모델 데이터 반환 -> 현재 모델 데이터: " + model + " (현재 ModelAndView의 모델 데이터 확인)");
        return model;
    }

    // 모델 데이터 설정 메서드
    public void setModel(Map<String, Object> model) {
        this.model = model;
        System.out.println("[ModelAndView] setModel() - 파일: ModelAndView.java -> 모델 데이터 설정: " + model + " -> Controller에서 설정됨 (Controller에서 ModelAndView의 모델 데이터 설정)");
    }
}

// 이 클래스는 컨트롤러에서 처리한 결과 데이터를 저장하고 페이지 이동 정보를 제공하기 위한 ModelAndView 클래스입니다.
// redirect 여부에 따라 페이지 이동 방식을 결정하며, 이동할 페이지 경로와 함께 request 영역에 저장할 데이터를 포함합니다.
// System.out.println()을 사용하여 각 메서드 호출 시 데이터를 추적하며, 파일 간의 호출 관계와 흐름을 명확히 알 수 있도록 파일 이름과 흐름에 대한 정보를 로그로 남깁니다.
