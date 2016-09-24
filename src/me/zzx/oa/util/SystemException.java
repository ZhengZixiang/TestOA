package me.zzx.oa.util;

public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -8643446294382520465L;

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
