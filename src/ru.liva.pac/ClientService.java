package ru.liva.pac;

import java.nio.file.Path;
import java.sql.*;

/**
 * @author liva
 */
public class ClientService {

	private final String url;

	public ClientService(Path path) {
		this.url = String.format("jdbc:sqlite:%s", path.toString());
	}

	public Client getClient(String name) throws SQLException {

		try (Connection connection = DriverManager.getConnection(url, null, null)) {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dataTable WHERE login=?")) {

				statement.setString(1, name);

				ResultSet result = statement.executeQuery();
				if (result.next()) {
					return new Client(result.getLong("id"), result.getString("login"), result.getString("password"));
				}
			}
		}

		return null;
	}

	public void saveClient(Client client) throws SQLException {

		String sql;
		if (client.getId() == null) {
			sql = "INSERT INTO dataTable (login, password) VALUES (?, ?)";
		}
		else {
			sql = "UPDATE dataTable SET login = ?, password = ? WHERE ID = ?";
		}

		try (Connection connection = DriverManager.getConnection(url, null, null)) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, client.getLogin());
				statement.setString(2, client.getPassword());

				if (client.getId() != null) {
					statement.setLong(3, client.getId());
				}

				statement.executeUpdate();
			}
		}

		if (client.getId() == null) {
			client.setId(getClient(client.getLogin()).getId());
		}
	}
}
