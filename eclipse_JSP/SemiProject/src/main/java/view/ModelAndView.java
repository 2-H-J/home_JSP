package view;

import java.util.HashMap;
import java.util.Map;

/**
 * ModelAndView 클래스는 컨트롤러의 실행 결과를 표현하는 객체로,
 * 이동할 페이지 경로와 전달할 데이터를 캡슐화합니다.
 */
public class ModelAndView {
    // redirect 여부를 저장하는 필드
    private boolean redirect;
    // 이동할 페이지 경로를 저장하는 필드
    private String path;
    // request 영역에 저장할 데이터를 담는 Map
    private Map<String, Object> model = new HashMap<>();

    /**
     * 모델 데이터 추가 메서드
     * @param key 데이터의 키
     * @param value 데이터 값
     */
    public void addObject(String key, Object value) {
        System.out.println("[ModelAndView] addObject() 호출 - key: " + key + ", value: " + value);
        model.put(key, value);
    }

    /**
     * redirect 여부 반환
     * @return true이면 redirect, false이면 forward
     */
    public boolean isRedirect() {
        System.out.println("[ModelAndView] isRedirect() 호출 - redirect: " + redirect);
        return redirect;
    }

    /**
     * redirect 여부 설정 메서드
     * @param redirect true이면 redirect로 설정
     */
    public void setRedirect(boolean redirect) {
        System.out.println("[ModelAndView] setRedirect() 호출 - redirect: " + redirect);
        this.redirect = redirect;
    }

    /**
     * 이동할 페이지 경로 반환
     * @return 이동할 경로
     */
    public String getPath() {
        System.out.println("[ModelAndView] getPath() 호출 - path: " + path);
        return path;
    }

    /**
     * 이동할 페이지 경로 설정 메서드
     * @param path 이동할 경로
     */
    public void setPath(String path) {
        System.out.println("[ModelAndView] setPath() 호출 - path: " + path);
        this.path = path;
    }

    /**
     * 모델 데이터 반환
     * @return 모델 데이터가 담긴 Map 객체
     */
    public Map<String, Object> getModel() {
        System.out.println("[ModelAndView] getModel() 호출 - model: " + model);
        return model;
    }

    /**
     * 모델 데이터 설정 메서드
     * @param model 모델 데이터가 담긴 Map 객체
     */
    public void setModel(Map<String, Object> model) {
        System.out.println("[ModelAndView] setModel() 호출 - model: " + model);
        this.model = model;
    }
}
