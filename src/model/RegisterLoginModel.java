package model;

import java.sql.ResultSet;

import controller.DatabaseController;

// by Jerome :)
public class RegisterLoginModel {

	// instance variables
	private String registerUsernameGiven;
	private String registerPasswordGiven;

	private String loginUsernameGiven;
	private String loginPasswordGiven;
	private boolean loginCorrect = false;

	// classes

	public void registerAccount() {
		// ingevoerde login en username krijgen uit textfield

		System.out.println("username input: " + registerUsernameGiven + " password given: " + registerPasswordGiven);

		DatabaseController DBRegister = new DatabaseController();

		DBRegister.doUpdateQuery(
				"INSERT INTO account VALUES('" + registerUsernameGiven + "', '" + registerPasswordGiven + "')");
		// ingevoerde gegevens in database zetten.

		// gebruiker moet nu inloggen.

	}

	public void tryLogin() {
		// ingevoerde login username krijgen uit textfield
		// ingevoerde login password krijgen uit textfield
		System.out.println("username input: " + loginUsernameGiven + " password given: " + loginPasswordGiven);

		DatabaseController DBLogin = new DatabaseController();
		// opvragen uit database welk wachtwoord er bij de username hoort.
		ResultSet passwordDB = DBLogin
				.doQuery("Select password FROM account WHERE username = '" + loginUsernameGiven + "'");

		// resultset naar een string

		// Als password bij username past -> ww correct.
		// Anders: inlog verkeerd.
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
