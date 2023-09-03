package com.example.demo.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.entity.User;

public class UserValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty", "姓名欄不能為空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty", "電子郵件不能為空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "密碼不能為空白");

		if (!errors.hasFieldErrors("username")) {
			if (user.getUsername() != null && user.getUsername().length() <= 3) {
				errors.rejectValue("username", "user.username.length", "使用者名稱至少三個字元");
			}
		}
		if (!errors.hasFieldErrors("password")) {
			if (user.getPassword() != null && user.getPassword().length() <= 6) {
				errors.rejectValue("password", "user.password.length", "密碼長度至少6個位元");
			}
		}
		if (!errors.hasFieldErrors("email")) {
			if (user.getEmail() != null && !Pattern.matches(EMAIL_PATTERN, user.getEmail())) {
				errors.rejectValue("email", "user.email.invalid", "無效的電子郵件格式");
			}

		}

	}
}
