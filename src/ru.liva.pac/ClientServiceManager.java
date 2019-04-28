package ru.liva.pac;

/**
 * @author liva
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ClientServiceManager {

	private static volatile ClientServiceManager instance;

	private final ClientService service;

	private ClientServiceManager(Path path) {
		this.service = new ClientService(path);
	}

	public static ClientServiceManager getInstance() throws Exception {
		if (instance == null) {
			synchronized (ClientServiceManager.class) {
				if (instance == null) {

					Path path = Paths.get(System.getProperty("java.io.tmpdir"), "database.db");

//					TODO remove
					if (path.toFile().exists()) {
						path.toFile().delete();
					}

					if (path.toFile().createNewFile()) {
						String url = String.format("jdbc:sqlite:%s", path.toString());

						try (Connection connection = DriverManager.getConnection(url, null, null)) {
							try (Statement statement = connection.createStatement()) {
								statement.execute("CREATE TABLE dataTable (ID INTEGER PRIMARY KEY AUTOINCREMENT, login VARCHAR NOT NULL UNIQUE, password VARCHAR NOT NULL)");
							}
						}
					}

					instance = new ClientServiceManager(path);
				}
			}
		}
		return instance;
	}

	public ClientService getService() {
		return service;
	}
}
