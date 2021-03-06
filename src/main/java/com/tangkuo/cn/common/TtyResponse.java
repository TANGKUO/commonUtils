package com.tangkuo.cn.common;

import java.io.Serializable;
import java.util.Map;

import com.tangkuo.cn.utils.StringUtils;

/**
 * 基础响应信息
 * 
 */
public class TtyResponse implements Serializable {

	private static final long serialVersionUID = 5014379068811962022L;

	private String code;

	private String message;

	private Object data;

	private Map<String, String> params;

	public TtyResponse() {

	}

	public TtyResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public TtyResponse setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public TtyResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public TtyResponse setData(Object data) {
		this.data = data;
		return this;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public boolean isSuccess() {
		return StringUtils.isEmpty(code) || "0000".equals(code) || "00".equals(code);
	}

	@Override
	public String toString() {
		return "response [code=" + code + ", message=" + message + ", data=" + data + ", params=" + params + "]";
	}

}
