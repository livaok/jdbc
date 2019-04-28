package ru.liva.pac;

/**
 * @author liva
 */

import java.io.*;

public class ClientServiceManager {

	private final ClientService clientService;
	private static volatile ClientServiceManager clientServiceManager;


	private ClientServiceManager(){
		this.clientService = new ClientService();
	}

	public ClientService getClientService(){
		return clientService;
	}

	public static ClientServiceManager getInstance() throws Exception{
		if (clientServiceManager == null) {
			synchronized (ClientServiceManager.class) {
				if (clientServiceManager == null) {
//					File database = new File(System.getProperty("java.io.tmpdir"), "Database.db");
//					database.createNewFile();
					clientServiceManager = new ClientServiceManager();
				}
			}
		}
		return clientServiceManager;
	}
}
