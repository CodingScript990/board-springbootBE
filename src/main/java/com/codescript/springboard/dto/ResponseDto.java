package com.codescript.springboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
		// Member Field
		private boolean result;
		private String message;
		private D data;

		// ResponseDto <D> type setSuccess function
		public static <D> ResponseDto<D> setSuccess(String message, D data) { // parameter => message, data
				// return ResponseDto.set(boolean type, message, data)
				return ResponseDto.set(true, message, data);
		}

		// ResponseDto <D> type setFailed function
		public static <D> ResponseDto<D> setFailed(String message) { // parameter => message
				// return ResponseDto.set(boolean type, message, null);
				return ResponseDto.set(false, message, null);
		}
}
