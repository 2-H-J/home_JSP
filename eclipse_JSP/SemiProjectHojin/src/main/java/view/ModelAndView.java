package view;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	// redirect 여부
	private boolean redirect;
	// 이동할 페이지 경로
	private String path;
	// request 영역에 저장할 데이터
	private Map<String, Object> model = new HashMap<>();

	// FindLoginIdController 함 
	public ModelAndView(String string) {
		// TODO Auto-generated constructor stub
	}

	// 모델 데이터 추가 메서드
	public void addObject(String key, Object value) {
		model.put(key, value);

	}

	// redirect 여부 반환
	public boolean isRedirect() {
		return redirect;
	}

	// redirect 여부 설정 메서드
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	// 이동할 페이지 경로 반환
	public String getPath() {
		return path;
	}

	// 이동할 페이지 경로 설정 메서드
	public void setPath(String path) {
		this.path = path;
	}

	// 모델 데이터 반환
	public Map<String, Object> getModel() {
		return model;
	}

	// 모델 데이터 설정 메서드
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
