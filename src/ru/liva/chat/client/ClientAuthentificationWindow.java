package ru.liva.chat.client;

import ru.liva.pac.Client;
import ru.liva.pac.ClientServiceManager;

import javax.swing.*;
import java.awt.*;

/**
 * @author liva
 */
public class ClientAuthentificationWindow extends JFrame {

	public ClientAuthentificationWindow() {

		setBounds(400, 400, 300, 150);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Authentification");

		JPanel jPanelLogin = new JPanel(new BorderLayout());
		add(jPanelLogin, BorderLayout.NORTH);
		JLabel inputLogin = new JLabel("Input login");
		jPanelLogin.add(inputLogin, BorderLayout.NORTH);
		TextField login = new TextField();
		jPanelLogin.add(login, BorderLayout.CENTER);

		JPanel jPanelPass = new JPanel(new BorderLayout());
		add(jPanelPass, BorderLayout.SOUTH);
		JLabel inputPass = new JLabel("Input pass");
		jPanelPass.add(inputPass, BorderLayout.NORTH);
		JTextField pass = new JTextField();
		jPanelPass.add(pass, BorderLayout.CENTER);

		JButton send = new JButton("Авторизация");
		add(send, BorderLayout.CENTER);

		send.addActionListener(e -> {
			try {
				ClientServiceManager.getInstance().getService().saveClient(new Client(login.getText(), pass.getText()));
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
			setVisible(false);
			new ClientWindow();
		});

		setVisible(true);
	}
}
