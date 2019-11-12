package db.mysql;

/**
 * Defines database parameters.
 */
public class MySQLDBUtil {
	private static final String HOSTNAME = "localhost";
	private static final String PORT_NUM = "****"; // change it to your MySQL port number
	public static final String DB_NAME = "****"; // change it to your database name
	private static final String USERNAME = "****"; // change it to your user name
	private static final String PASSWORD = "****"; // change it to your password
	public static final String URL = "jdbc:mysql://"
			+ HOSTNAME + ":" + PORT_NUM + "/" + DB_NAME
			+ "?user=" + USERNAME + "&password=" + PASSWORD
			+ "&autoReconnect=true&serverTimezone=UTC";
}
