package view;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    // redirect 여부를 나타내는 변수
    // true이면 리다이렉트 방식으로 페이지를 이동하고, false이면 포워드 방식으로 페이지를 이동
    private boolean redirect;

    // 이동할 페이지의 경로를 저장하는 변수
    private String path;

    // request 영역에 저장할 데이터들을 담을 Map 객체
    // key-value 형식으로 데이터를 저장하여 여러 데이터를 페이지에 전달할 수 있음
    private Map<String, Object> model = new HashMap<>();

    // 데이터를 추가하는 메소드
    // key와 value를 받아서 모델(Map 객체)에 저장
    public void addObject(String key, Object value) {
        model.put(key, value);
    }

    // redirect 여부를 반환하는 메소드
    public boolean isRedirect() {
        return redirect;
    }

    // redirect 여부를 설정하는 메소드
    // 이 메소드를 통해 리다이렉트 또는 포워드를 설정할 수 있음
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    // 이동할 페이지 경로를 반환하는 메소드
    public String getPath() {
        return path;
    }

    // 이동할 페이지 경로를 설정하는 메소드
    // 이 메소드를 통해 페이지 경로를 설정할 수 있음
    public void setPath(String path) {
        this.path = path;
    }

    // 모델 데이터를 반환하는 메소드
    // 모델(Map 객체)을 반환하여 컨트롤러에서 추가된 데이터를 뷰로 전달할 수 있게 함
    public Map<String, Object> getModel() {
        return model;
    }

    // 모델 데이터를 설정하는 메소드
    // 모델 전체를 교체하고 싶을 때 사용할 수 있음
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
