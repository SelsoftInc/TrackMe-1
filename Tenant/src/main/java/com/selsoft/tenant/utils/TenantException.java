package com.selsoft.tenant.utils;
import org.apache.commons.lang.StringUtils;

import com.selsoft.tenant.constants.TenantConstants;

	public class TenantException extends Throwable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8564819668730634762L;
		
		private String errorType;
		private String error;
		
		public String getErrorType() {
			return errorType;
		}

		public void setErrorType(String errorType) {
			this.errorType = errorType;
		}
		

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public TenantException(){}
		
		public TenantException(String errorType, String error) {
			this.errorType = errorType;
			this.error = error;
		}
		
		public TenantException(String errorType, Throwable t) {
			this.errorType = errorType;
			this.error = (t != null ? (StringUtils.isNotBlank(t.getMessage()) ? t.getMessage() : 
										(StringUtils.isNotBlank(t.getLocalizedMessage()) ? t.getLocalizedMessage() : TenantConstants.IRRECOVERABLE_ERROR))
									: TenantConstants.IRRECOVERABLE_ERROR);
		}

		@Override
		public String toString() {
			return ("Error Type : " + this.getErrorType() + ", Error : " + this.getError());
		}
		
	}


