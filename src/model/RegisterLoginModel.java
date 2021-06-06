
package model;

import controller.MainController;
import controller.RegisterLoginController;

public class RegisterLoginModel {

	// instance variables
	private String registerUsernameGiven;
	private String registerPasswordGiven;
	private String loginUsernameGiven;
	private String loginPasswordGiven;
	private String warningText;
	private String warningColor;
	private MainController mainController;
	private RegisterLoginController registerLoginController;

	public RegisterLoginModel(MainController mainController, RegisterLoginController registerLoginController) {
		warningText = "Geen fouten opgetreden";

		this.mainController = mainController;
		this.registerLoginController = registerLoginController;
	}

	public void registerAccount() {
		if (nameAvailableCheck(registerUsernameGiven) == false) {
			warningText = "Naam niet beschikbaar";
			warningColor = "red";
		} else {
			if (containsMinThreeChars(registerUsernameGiven) == false
					|| isOnlyCharAndNumbers(registerUsernameGiven) == false) {

				System.out.println("Username is too short or contains other characters than letters/numbers!");
				warningText = "Naam heeft de verkeerde lengte (3-25 karakters) of bevat andere tekens dan cijfers/nummers";
				warningColor = "red";
			} else {
				if (containsMinThreeChars(registerPasswordGiven) == false
						|| isOnlyCharAndNumbers(registerPasswordGiven) == false) {
					warningColor = "red";
					warningText = "Wachtwoord heeft de verkeerde lengte (3-25 karakters) of bevat vreemde tekens";
				} else {
					registerLoginController.addAccountToDatabase(registerUsernameGiven, registerPasswordGiven);
					warningText = "Registratie voltooid! Log nu in met jouw gegevens";
					warningColor = "green";
				}
			}
		}
	}

	public void tryLogin() {
		if (containsMinThreeChars(loginUsernameGiven) == false || isOnlyCharAndNumbers(loginUsernameGiven) == false) {
			warningText = "Naam is te kort of bevat vreemde tekens";
			warningColor = "red";
		} else {
			if (containsMinThreeChars(loginPasswordGiven) == false
					|| isOnlyCharAndNumbers(loginPasswordGiven) == false) {
				warningText = "wachtwoord is te kort of bevat vreemde tekens";
				warningColor = "red";
			} else {
				if (nameAvailableCheck(loginUsernameGiven) == true) {
					warningText = "Deze gebruiker is niet bekend";
					warningColor = "red";
				} else {
					if (isValidPassword(loginUsernameGiven, loginPasswordGiven) == true) {
						warningText = "Ingelogd!";
						warningColor = "green";

						mainController.login(loginUsernameGiven);
						mainController.showFirstMainMenu();

					} else {
						warningText = "wachtwoord is incorrect!";
						warningColor = "red";
					}
				}
			}
		}
	}

	// Check methods
	private boolean containsMinThreeChars(String input) { // checks for at least 3 characters
		if (input.length() < 3) {
			return false;
		} else if(input.length() > 25){
			System.out.println("Max size of input is 25 characters");
			return false;
		}else {
			return true;
		}
	}

	private boolean isOnlyCharAndNumbers(String input) { // checks for letters and numbers only
		boolean charChecker = true;
		for (int i = 0; i < input.length(); i++) {
			if (Character.isLetterOrDigit(input.charAt(i)) == false) {
				return false;
			}
		}
		return charChecker;
	}

	private boolean nameAvailableCheck(String input) { // check availability of username

		int oneOrZeroOrElse = registerLoginController.nameAvailableCheck(input);

		if (oneOrZeroOrElse == 0) {
			return true;
		} else if (oneOrZeroOrElse == 1) {
			return false;
		} else {
			return false;
		}

	}

	private boolean isValidPassword(String userName, String pw) {

		int oneOrZeroOrElse = registerLoginController.isValidPassword(userName, pw);

		if (oneOrZeroOrElse == 0) {
			return false;
		} else if (oneOrZeroOrElse == 1) {
			return true;
		} else {
			return false;
		}

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

	public String getWarningText() {
		return warningText;
	}

	public void setWarningText(String warningText) {
		this.warningText = warningText;
	}

	public String getWarningColor() {
		return warningColor;
	}

	public void setWarningColor(String warningColor) {
		this.warningColor = warningColor;
	}

}
