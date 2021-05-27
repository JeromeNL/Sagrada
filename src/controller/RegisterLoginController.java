package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterLoginController {

	private DatabaseController dbController;

	public RegisterLoginController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	public void addAccountToDatabase(String registerUsernameGiven, String registerPasswordGiven) {
		dbController.doUpdateQuery(
				"INSERT INTO account VALUES('" + registerUsernameGiven + "', '" + registerPasswordGiven + "')");
	}

	public int nameAvailableCheck(String input) {

		input.toLowerCase();
		ResultSet nameAvailable = dbController
				.doQuery("SELECT COUNT(*) as counter FROM account where username = '" + input + "'");

		try {
			while (nameAvailable.next()) {
				int oneOrZero = Integer.parseInt(nameAvailable.getString("counter"));
				System.out.println(oneOrZero);

				if (oneOrZero == 0) {
					return 0;
				} else if (oneOrZero == 1) {
					return 1;
				} else {
					return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 1;
	}

	public int isValidPassword(String userName, String pw) {

		userName.toLowerCase();
		ResultSet askedPassword = dbController.doQuery("SELECT COUNT(*) as counter FROM account WHERE username = '"
				+ userName + "' && password = '" + pw + "'");

		try {
			while (askedPassword.next()) {
				int oneOrZero = Integer.parseInt(askedPassword.getString("counter"));
				System.out.println(oneOrZero);

				if (oneOrZero == 0) {
					return 0;
				} else if (oneOrZero == 1) {
					return 1;
				} else {
					return 0;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;

	}
}
