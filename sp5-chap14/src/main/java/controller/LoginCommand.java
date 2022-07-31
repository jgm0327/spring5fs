package controller;

import lombok.Data;

@Data
public class LoginCommand {

	private String email;
	private String password;
	private boolean rememberEmail;
}
