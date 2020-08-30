package cl.kranio.cc.model;

import java.io.Serializable;

public class GameResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1235121171773562671L;
	private boolean OK;
	private Object data;
	private boolean error;
	private String information;
	public GameResponse() {
		super();
	}

	public GameResponse(boolean oK, Object data, boolean error, String information) {
		super();
		OK = oK;
		this.data = data;
		this.error = error;
		this.information = information;
	}

	public boolean isOK() {
		return OK;
	}

	public void setOK(boolean oK) {
		OK = oK;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
