package view;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	//redirect 여부
	private boolean redirect;
	//이동할 페이지 경로
	private String path;
	//request 영역에 저장할 데이터
	private Map<String, Object> model = new HashMap<>();
	
	public void addObject(String key, Object value) {
		model.put(key, value);
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	
	
	
}