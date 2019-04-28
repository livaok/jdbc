package ru.liva.pac;

/**
 * @author liva
 */

import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientServiceManager {

	private static volatile ClientServiceManager instance;

	private final ClientService service;

	private ClientServiceManager(Path path){
		this.service = new ClientService(path);
	}

	public static ClientServiceManager getInstance() throws Exception{
		if (instance == null) {
			synchronized (ClientServiceManager.class) {
				if (instance == null) {

					Path path = Paths.get(System.getProperty("java.io.tmpdir"), "database.db");
					path.toFile().createNewFile();

					instance = new ClientServiceManager(path);
				}
			}
		}
		return instance;
	}

	public ClientService getService(){
		return service;
	}
}
