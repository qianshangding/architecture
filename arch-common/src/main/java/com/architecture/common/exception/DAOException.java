package com.architecture.common.exception;

/**
 * DAO异常
 * 
 * @author Fish Exp
 */
public class DAOException extends RuntimeException {
	private static final long serialVersionUID = -2920541562595283945L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}
