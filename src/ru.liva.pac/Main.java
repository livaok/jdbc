package ru.liva.pac;

/**
 * @author liva
 */
public class Main {

	public static void main(String[] args) throws Exception {
		Client client = new Client("test22", "test");
		ClientServiceManager.getInstance().getService().saveClient(client);
		Client test = ClientServiceManager.getInstance().getService().getClient("test1");
		System.out.println();
	}
}
