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

    // 기본 생성자
    public ModelAndView() {
        System.out.println("[ModelAndView] 기본 생성자 호출 -> ModelAndView 인스턴스 생성");
    }

    // 파라미터를 받는 생성자
    public ModelAndView(String path, boolean redirect) {
        this.path = path;
        this.redirect = redirect;
        System.out.println("[ModelAndView] 생성자 호출 -> path: " + path + ", redirect: " + redirect);
    }

    // 모델 데이터 추가 메서드
    public void addObject(String key, Object value) {
        model.put(key, value);
        System.out.println("[ModelAndView] addObject() 호출 -> key: " + key + ", value: " + value + " -> 데이터 추가 완료");
    }

    // redirect 여부 반환
    public boolean isRedirect() {
        System.out.println("[ModelAndView] isRedirect() 호출 -> redirect 상태 반환: " + redirect);
        return redirect;
    }

    // redirect 여부 설정 메서드
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
        System.out.println("[ModelAndView] setRedirect() 호출 -> redirect 설정: " + redirect);
    }

    // 이동할 페이지 경로 반환
    public String getPath() {
        System.out.println("[ModelAndView] getPath() 호출 -> 현재 경로 반환: " + path);
        return path;
    }

    // 이동할 페이지 경로 설정 메서드
    public void setPath(String path) {
        this.path = path;
        System.out.println("[ModelAndView] setPath() 호출 -> 경로 설정: " + path);
    }

    // 모델 데이터 반환
    public Map<String, Object> getModel() {
        System.out.println("[ModelAndView] getModel() 호출 -> 현재 모델 데이터 반환: " + model);
        return model;
    }

    // 모델 데이터 설정 메서드
    public void setModel(Map<String, Object> model) {
        this.model = model;
        System.out.println("[ModelAndView] setModel() 호출 -> 모델 데이터 설정: " + model);
    }
}
