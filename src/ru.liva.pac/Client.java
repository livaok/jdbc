package ru.liva.pac;

/**
 * @author liva
 */
public class Client {

	private Long   id;
	private String login;
	private String password;

	public Client(String login, String password) {

		this.id = null;
		this.login = login;
		this.password = password;
	}

	public Client(Long id, String login, String password) {

		this.id = id;
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	void setId(Long id) {
		this.id = id;
	}
}
