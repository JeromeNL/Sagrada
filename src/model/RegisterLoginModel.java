// by JeromeNL :)

package model;

// imports
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DatabaseController;
import javafx.scene.paint.Color;
import view.DieView;

public class RegisterLoginModel {

	// instance variables
	private String registerUsernameGiven;
	private String registerPasswordGiven;
	private String loginUsernameGiven;
	private String loginPasswordGiven;

	// classes
	public void registerAccount() {
		System.out.println("username input: " + registerUsernameGiven + " password given: " + registerPasswordGiven);

		if (nameAvailableCheck(registerUsernameGiven) == false) {
			System.out.println("Name not available");
		} else {
			if (containsMinThreeChars(registerUsernameGiven) == false
					|| isOnlyCharAndNumbers(registerUsernameGiven) == false) {
				System.out.println("Username is too short or contains other characters than letters/numbers!");
			} else {
				if (containsMinThreeChars(registerPasswordGiven) == false
						|| isOnlyCharAndNumbers(registerPasswordGiven) == false) {
					System.out.println("Password is too short or contains other characters than letters/numbers!");
				} else {
					DatabaseController DBRegister = new DatabaseController();
					DBRegister.doUpdateQuery("INSERT INTO account VALUES('" + registerUsernameGiven + "', '"
							+ registerPasswordGiven + "')");
					System.out.println("done!");
				}
			}
		}
	}

	public void tryLogin() {
		System.out.println("username input: " + loginUsernameGiven + " password given: " + loginPasswordGiven);

		if (containsMinThreeChars(loginUsernameGiven) == false || isOnlyCharAndNumbers(loginUsernameGiven) == false) {
			System.out.println("Username is too short or contains other characters than letters/numbers!");
		} else {
			if (containsMinThreeChars(loginPasswordGiven) == false
					|| isOnlyCharAndNumbers(loginPasswordGiven) == false) {
				System.out.println("Password is too short or contains other characters than letters/numbers!");
			} else {
				if (nameAvailableCheck(loginUsernameGiven) == true) {
					System.out.println("This user doesnt exist in database!");
				} else {
					DatabaseController DBLogin = new DatabaseController();
					ResultSet passwordDB = DBLogin
							.doQuery("Select password FROM account WHERE username = '" + loginUsernameGiven + "'");

					if (passwordCheck(loginUsernameGiven, loginPasswordGiven) == true) {
						System.out.println("logged in!");
					} else {
						System.out.println("Error! Wrong password!");
					}
				}
			}
		}
	}

	// Check methods
	private boolean containsMinThreeChars(String input) { // checks for at least 3 characters
		if (input.length() < 3) {
			System.out.println("Doesnt contain at least 3 characters");
			return false;
		} else {
			return true;
		}
	}

	private boolean isOnlyCharAndNumbers(String input) { // checks for letters and numbers only
		boolean charChecker = true;
		for (int i = 0; i < input.length(); i++) {
			if (Character.isLetterOrDigit(input.charAt(i)) == false) {
				System.out.println(Character.isLetterOrDigit(input.charAt(i)));
				return false;
			}
		}
		return charChecker;
	}

	private boolean nameAvailableCheck(String input) { // check availability of username

		input.toLowerCase();
		DatabaseController checkName = new DatabaseController();
		ResultSet nameAvailable = checkName
				.doQuery("SELECT COUNT(*) as counter FROM account where username = '" + input + "'");

		try {
			while (nameAvailable.next()) {
				int oneOrZero = Integer.parseInt(nameAvailable.getString("counter"));
				System.out.println(oneOrZero);

				if (oneOrZero == 0) {
					return true;
				} else if (oneOrZero == 1) {
					return false;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	private boolean passwordCheck(String userName, String pw) {
		userName.toLowerCase();
		DatabaseController checkPassword = new DatabaseController();
		ResultSet askedPassword = checkPassword.doQuery("SELECT COUNT(*) as counter FROM account WHERE username = '"
				+ userName + "' && password = '" + pw + "'");

		try {
			while (askedPassword.next()) {
				int oneOrZero = Integer.parseInt(askedPassword.getString("counter"));
				System.out.println(oneOrZero);

				if (oneOrZero == 0) {
					return false;
				} else if (oneOrZero == 1) {
					return true;
				} else {
					return false;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	// Getters N Setters
	public String getRegisterUsernameGiven() {
		return registerUsernameGiven;
	}

	public void setRegisterUsernameGiven(String registerUsernameGiven) {
		this.registerUsernameGiven = registerUsernameGiven;
	}

	public String getRegisterPasswordGiven() {
		return registerPasswordGiven;
	}

	public void setRegisterPasswordGiven(String registerPasswordGiven) {
		this.registerPasswordGiven = registerPasswordGiven;
	}

	public String getLoginUsernameGiven() {
		return loginUsernameGiven;
	}

	public void setLoginUsernameGiven(String loginUsernameGiven) {
		this.loginUsernameGiven = loginUsernameGiven;
	}

	public String getLoginPasswordGiven() {
		return loginPasswordGiven;
	}

	public void setLoginPasswordGiven(String loginPasswordGiven) {
		this.loginPasswordGiven = loginPasswordGiven;
	}

}
