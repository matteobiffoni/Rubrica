import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	private JPanel formPanel;
	private JPanel usernamePanel;
	private JPanel usernameLabelPanel;
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JPanel passwordPanel;
	private JPanel passwordLabelPanel;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JPanel showPasswordPanel;
	private JButton showPasswordButton;
	private JPanel buttonsPanel;
	private JButton loginButton;
	private JPanel errorPanel;
	private JLabel errorLabel;
	public Login() {
		this.setLayout(new BorderLayout());
		formPanel = new JPanel(new BorderLayout());
		usernamePanel = new JPanel(new BorderLayout());
		usernameLabel = new JLabel("Username");
		usernameLabelPanel = new JPanel(new FlowLayout());
		usernameLabelPanel.add(usernameLabel);
		usernameField = new JTextField(15);
		usernameField.setHorizontalAlignment(JTextField.CENTER);
		usernamePanel.add(usernameLabelPanel, BorderLayout.NORTH);
		usernamePanel.add(usernameField, BorderLayout.CENTER);
		passwordPanel = new JPanel(new BorderLayout());
		passwordLabel = new JLabel("Password");
		passwordLabelPanel = new JPanel(new FlowLayout());
		passwordLabelPanel.add(passwordLabel);
		passwordField = new JPasswordField(15);
		passwordField.setHorizontalAlignment(JPasswordField.CENTER);
		showPasswordPanel = new JPanel(new FlowLayout());
		showPasswordButton = new JButton("Show");
		showPasswordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (passwordField.getEchoChar() == (char) 0) {
					passwordField.setEchoChar('*');
					showPasswordButton.setText("Show");
				}
				else {
					passwordField.setEchoChar((char) 0);
					showPasswordButton.setText("Hide");
				}
			}
		});
		showPasswordPanel.add(showPasswordButton);
		passwordPanel.add(passwordLabelPanel, BorderLayout.NORTH);
		passwordPanel.add(passwordField, BorderLayout.CENTER);
		passwordPanel.add(showPasswordPanel, BorderLayout.SOUTH);
		formPanel.add(usernamePanel, BorderLayout.NORTH);
		formPanel.add(passwordPanel, BorderLayout.SOUTH);
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        errorPanel = new JPanel(new FlowLayout());
        errorLabel = new JLabel("Credenziali errate");
        errorPanel.add(errorLabel);
        errorLabel.setVisible(false);
        buttonsPanel = new JPanel(new FlowLayout());
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String username = usernameField.getText().trim();
        		String password = String.valueOf(passwordField.getPassword()).trim();
				int loginEffettuato = RubricaDB.login(username, password);
				if (loginEffettuato < 0) {
					errorLabel.setText("Errore imprevisto, riprovare");
					errorLabel.setVisible(true);
					pack();
				}
				else if (loginEffettuato == 0) {
					errorLabel.setText("Credenziali errate");
        			errorLabel.setVisible(true);
        			pack();
        		}
        		else {
					Utente.currentUser = new Utente(loginEffettuato, username, password);
        			//ArrayList<Persona> personeInformazioni = RubricaIO.retrieveInformazioni();
					ArrayList<Persona> personeInformazioni = RubricaDB.retrieveInformazioni();
        	        RubricaTableModel rubricaTableModel = new RubricaTableModel(personeInformazioni);
        	        new FinestraPrincipale(rubricaTableModel);
					dispose();
        		}
        	}
        });
        buttonsPanel.add(loginButton);
		this.add(formPanel, BorderLayout.NORTH);
		this.add(errorPanel, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.setTitle("Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getRootPane().setDefaultButton(loginButton);
        this.pack();
		this.setMinimumSize(new Dimension(300, 200));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
}
