package com.tangkuo.cn.common;

import java.io.Serializable;

public class TtyObject implements Serializable {

	private static final long serialVersionUID = -1133660048887177718L;

	/**
	 * 主健ID
	 */
	private long id;

	/**
	 * Getter method for property <tt>id</tt>.
	 * 
	 * @return property value of id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id
	 *            value to be assigned to property id
	 */
	public void setId(long id) {
		this.id = id;
	}

}
