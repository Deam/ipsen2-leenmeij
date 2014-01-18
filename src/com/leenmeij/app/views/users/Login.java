package com.leenmeij.app.views.users;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

@SuppressWarnings("serial")
public class Login extends JFrame {
	public JPasswordField passwordField;
	public JTextField emailTextField;
	public JButton loginButton;
	public JButton cancelButton;

	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 136);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{127, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblLeenmeijInlogscherm = new JLabel("LeenMeij Inlogscherm");
		lblLeenmeijInlogscherm.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblLeenmeijInlogscherm = new GridBagConstraints();
		gbc_lblLeenmeijInlogscherm.gridwidth = 2;
		gbc_lblLeenmeijInlogscherm.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeenmeijInlogscherm.gridx = 0;
		gbc_lblLeenmeijInlogscherm.gridy = 0;
		getContentPane().add(lblLeenmeijInlogscherm, gbc_lblLeenmeijInlogscherm);
		
		JLabel lblEmailadresominteloggen = new JLabel("E-mail:");
		lblEmailadresominteloggen.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblEmailadresominteloggen = new GridBagConstraints();
		gbc_lblEmailadresominteloggen.anchor = GridBagConstraints.WEST;
		gbc_lblEmailadresominteloggen.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailadresominteloggen.gridx = 0;
		gbc_lblEmailadresominteloggen.gridy = 1;
		getContentPane().add(lblEmailadresominteloggen, gbc_lblEmailadresominteloggen);
		
		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 1;
		getContentPane().add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lblWachtwoord = new JLabel("Wachtwoord:");
		lblWachtwoord.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblWachtwoord = new GridBagConstraints();
		gbc_lblWachtwoord.insets = new Insets(0, 0, 5, 5);
		gbc_lblWachtwoord.anchor = GridBagConstraints.WEST;
		gbc_lblWachtwoord.gridx = 0;
		gbc_lblWachtwoord.gridy = 2;
		getContentPane().add(lblWachtwoord, gbc_lblWachtwoord);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		getContentPane().add(passwordField, gbc_passwordField);
		
		cancelButton = new JButton("Annuleren");
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setToolTipText("Verwijder de geselecteerde klant");
		cancelButton.setBackground(new Color(195, 96, 90));	
		cancelButton.setBorderPainted(false);
		cancelButton.setOpaque(true);
		cancelButton.setFocusPainted(false);
		cancelButton.setContentAreaFilled(true);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 0;
		gbc_cancelButton.gridy = 3;
		getContentPane().add(cancelButton, gbc_cancelButton);
		
		loginButton = new JButton("Inloggen");
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setToolTipText("Voeg een voertuig toe aan de database");
		loginButton.setBackground(new Color(92, 184, 92));
		loginButton.setBorderPainted(false);
		loginButton.setOpaque(true);
		loginButton.setFocusPainted(false);
		loginButton.setContentAreaFilled(true);
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 3;
		getContentPane().add(loginButton, gbc_loginButton);
	}
}
