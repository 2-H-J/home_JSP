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

	// 모델 데이터 추가 메서드
	public void addObject(String key, Object value) {
		System.out.println("[ModelAndView] addObject 호출됨: key = " + key + ", value = " + value);
		model.put(key, value);
	}

	// redirect 여부 반환
	public boolean isRedirect() {
		System.out.println("[ModelAndView] isRedirect 호출됨 -> 반환값: " + redirect);
		return redirect;
	}

	// redirect 여부 설정 메서드
	public void setRedirect(boolean redirect) {
		System.out.println("[ModelAndView] setRedirect 호출됨: redirect = " + redirect);
		this.redirect = redirect;
	}

	// 이동할 페이지 경로 반환
	public String getPath() {
		System.out.println("[ModelAndView] getPath 호출됨 -> 반환값: " + path);
		return path;
	}

	// 이동할 페이지 경로 설정 메서드
	public void setPath(String path) {
		System.out.println("[ModelAndView] setPath 호출됨: path = " + path);
		this.path = path;
	}

	// 모델 데이터 반환
	public Map<String, Object> getModel() {
		System.out.println("[ModelAndView] getModel 호출됨 -> 반환값: model.size = " + model.size());
		return model;
	}

	// 모델 데이터 설정 메서드
	public void setModel(Map<String, Object> model) {
		System.out.println("[ModelAndView] setModel 호출됨: model.size");
		this.model = model;
	}

}
