package com.leenmeij.app.views.users;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

import com.leenmeij.app.models.User;

@SuppressWarnings("serial")
public class EditUser extends JFrame {

	private JPanel contentPane;

	public JTextField idTextField;
	public JTextField firstNameTextField;
	public JTextField lastNameTextField;
	public JTextField zipCodeTextField;
	public JTextField addressLineOneTextField;
	public JTextField cityTextField;
	public JTextField countryTextField;
	public JTextField addressLineTwoTextField;
	public JTextField emailTextField;
	public JTextField phoneNumberTextField;
	public JTextField passportNumberTextField;
	public JTextField kvkNumberTextField;
	public JTextField licenseNumberTextField;
	public JTextField vatNumberTextField;
	
	public JButton editButton;

	public EditUser() {
		setTitle("Gebruiker bewerken");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 543);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblVoertuigBewerken = new JLabel("Gebruiker bewerken");
		lblVoertuigBewerken.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblVoertuigBewerken, BorderLayout.NORTH);
		
		idTextField = new JTextField();

		editButton = new JButton("Toepassen");
		editButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		editButton.setForeground(new Color(255, 255, 255));
		editButton.setToolTipText("Sla de bewerkte gegevens op");
		editButton.setBackground(new Color(66, 139, 202));
		editButton.setBorderPainted(false);
		editButton.setOpaque(true);
		editButton.setFocusPainted(false);
		editButton.setContentAreaFilled(true);
		contentPane.add(editButton, BorderLayout.SOUTH);

		JPanel contentPanel = new JPanel();
		contentPane.add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 1;
		contentPanel.add(lblEmail, gbc_lblEmail);

		emailTextField = new JTextField();
		emailTextField.setToolTipText("Vul hier de e-mail van de gebruiker in");
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 1;
		contentPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);

		JLabel lblVoornaam = new JLabel("Voornaam:");
		lblVoornaam.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblVoornaam = new GridBagConstraints();
		gbc_lblVoornaam.anchor = GridBagConstraints.WEST;
		gbc_lblVoornaam.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoornaam.gridx = 0;
		gbc_lblVoornaam.gridy = 2;
		contentPanel.add(lblVoornaam, gbc_lblVoornaam);

		firstNameTextField = new JTextField();
		firstNameTextField.setToolTipText("Vul hier de voornaam van de gebruiker in");
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTextField.gridx = 1;
		gbc_firstNameTextField.gridy = 2;
		contentPanel.add(firstNameTextField, gbc_firstNameTextField);
		firstNameTextField.setColumns(10);

		JLabel lblAchternaam = new JLabel("Achternaam:");
		lblAchternaam.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAchternaam = new GridBagConstraints();
		gbc_lblAchternaam.anchor = GridBagConstraints.WEST;
		gbc_lblAchternaam.insets = new Insets(0, 0, 5, 5);
		gbc_lblAchternaam.gridx = 0;
		gbc_lblAchternaam.gridy = 3;
		contentPanel.add(lblAchternaam, gbc_lblAchternaam);

		lastNameTextField = new JTextField();
		lastNameTextField
				.setToolTipText("Vul hier ");
		GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
		gbc_lastNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameTextField.gridx = 1;
		gbc_lastNameTextField.gridy = 3;
		contentPanel.add(lastNameTextField, gbc_lastNameTextField);
		lastNameTextField.setColumns(10);

		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPostcode = new GridBagConstraints();
		gbc_lblPostcode.anchor = GridBagConstraints.WEST;
		gbc_lblPostcode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostcode.gridx = 0;
		gbc_lblPostcode.gridy = 4;
		contentPanel.add(lblPostcode, gbc_lblPostcode);

		zipCodeTextField = new JTextField();
		zipCodeTextField
				.setToolTipText("Vul hier de postcode van de gebruiker in");
		GridBagConstraints gbc_zipCodeTextField = new GridBagConstraints();
		gbc_zipCodeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_zipCodeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zipCodeTextField.gridx = 1;
		gbc_zipCodeTextField.gridy = 4;
		contentPanel.add(zipCodeTextField, gbc_zipCodeTextField);
		zipCodeTextField.setColumns(10);

		JLabel lblStraat = new JLabel("Straat:");
		lblStraat.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblStraat = new GridBagConstraints();
		gbc_lblStraat.anchor = GridBagConstraints.WEST;
		gbc_lblStraat.insets = new Insets(0, 0, 5, 5);
		gbc_lblStraat.gridx = 0;
		gbc_lblStraat.gridy = 5;
		contentPanel.add(lblStraat, gbc_lblStraat);

		addressLineOneTextField = new JTextField();
		addressLineOneTextField
				.setToolTipText("Vul hier de straat in van de gebruiker.");
		GridBagConstraints gbc_addressLineOneTextField = new GridBagConstraints();
		gbc_addressLineOneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_addressLineOneTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressLineOneTextField.gridx = 1;
		gbc_addressLineOneTextField.gridy = 5;
		contentPanel.add(addressLineOneTextField, gbc_addressLineOneTextField);
		addressLineOneTextField.setColumns(10);

		JLabel lblHuisnummer = new JLabel("Huisnummer:");
		lblHuisnummer.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblHuisnummer = new GridBagConstraints();
		gbc_lblHuisnummer.anchor = GridBagConstraints.WEST;
		gbc_lblHuisnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblHuisnummer.gridx = 0;
		gbc_lblHuisnummer.gridy = 6;
		contentPanel.add(lblHuisnummer, gbc_lblHuisnummer);

		addressLineTwoTextField = new JTextField();
		addressLineTwoTextField.setToolTipText("Vul hier het huisnummer van de gebruiker");
		GridBagConstraints gbc_addressLineTwoTextField = new GridBagConstraints();
		gbc_addressLineTwoTextField.insets = new Insets(0, 0, 5, 0);
		gbc_addressLineTwoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressLineTwoTextField.gridx = 1;
		gbc_addressLineTwoTextField.gridy = 6;
		contentPanel.add(addressLineTwoTextField, gbc_addressLineTwoTextField);
		addressLineTwoTextField.setColumns(10);

		JLabel lblWoonplaats = new JLabel("Woonplaats:");
		lblWoonplaats.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblWoonplaats = new GridBagConstraints();
		gbc_lblWoonplaats.anchor = GridBagConstraints.WEST;
		gbc_lblWoonplaats.insets = new Insets(0, 0, 5, 5);
		gbc_lblWoonplaats.gridx = 0;
		gbc_lblWoonplaats.gridy = 7;
		contentPanel.add(lblWoonplaats, gbc_lblWoonplaats);

		cityTextField = new JTextField();
		cityTextField.setToolTipText("Vul hier de woonplaats in van de gebruiker");
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.insets = new Insets(0, 0, 5, 0);
		gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityTextField.gridx = 1;
		gbc_cityTextField.gridy = 7;
		contentPanel.add(cityTextField, gbc_cityTextField);
		cityTextField.setColumns(10);

		JLabel lblLand = new JLabel("Land:");
		lblLand.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblLand = new GridBagConstraints();
		gbc_lblLand.anchor = GridBagConstraints.WEST;
		gbc_lblLand.insets = new Insets(0, 0, 5, 5);
		gbc_lblLand.gridx = 0;
		gbc_lblLand.gridy = 8;
		contentPanel.add(lblLand, gbc_lblLand);

		countryTextField = new JTextField();
		countryTextField
				.setToolTipText("Vul hier het land in van de gebruiker");
		GridBagConstraints gbc_countryTextField = new GridBagConstraints();
		gbc_countryTextField.insets = new Insets(0, 0, 5, 0);
		gbc_countryTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryTextField.gridx = 1;
		gbc_countryTextField.gridy = 8;
		contentPanel.add(countryTextField, gbc_countryTextField);
		countryTextField.setColumns(10);

		JLabel lblTelefoonnummer = new JLabel("Telefoonnummer:");
		lblTelefoonnummer.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblTelefoonnummer = new GridBagConstraints();
		gbc_lblTelefoonnummer.anchor = GridBagConstraints.WEST;
		gbc_lblTelefoonnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefoonnummer.gridx = 0;
		gbc_lblTelefoonnummer.gridy = 9;
		contentPanel.add(lblTelefoonnummer, gbc_lblTelefoonnummer);
		
		phoneNumberTextField = new JTextField();
		GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
		gbc_phoneNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberTextField.gridx = 1;
		gbc_phoneNumberTextField.gridy = 9;
		contentPanel.add(phoneNumberTextField, gbc_phoneNumberTextField);
		phoneNumberTextField.setColumns(10);
		
		JLabel lblRijbewijsnummer = new JLabel("Rijbewijsnummer:");
		lblRijbewijsnummer.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblRijbewijsnummer = new GridBagConstraints();
		gbc_lblRijbewijsnummer.anchor = GridBagConstraints.WEST;
		gbc_lblRijbewijsnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblRijbewijsnummer.gridx = 0;
		gbc_lblRijbewijsnummer.gridy = 10;
		contentPanel.add(lblRijbewijsnummer, gbc_lblRijbewijsnummer);
		
		licenseNumberTextField = new JTextField();
		GridBagConstraints gbc_licenseNumberTextField = new GridBagConstraints();
		gbc_licenseNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_licenseNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_licenseNumberTextField.gridx = 1;
		gbc_licenseNumberTextField.gridy = 10;
		contentPanel.add(licenseNumberTextField, gbc_licenseNumberTextField);
		licenseNumberTextField.setColumns(10);
		
		JLabel lblPaspoortnummer = new JLabel("Paspoortnummer:");
		lblPaspoortnummer.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPaspoortnummer = new GridBagConstraints();
		gbc_lblPaspoortnummer.anchor = GridBagConstraints.WEST;
		gbc_lblPaspoortnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaspoortnummer.gridx = 0;
		gbc_lblPaspoortnummer.gridy = 11;
		contentPanel.add(lblPaspoortnummer, gbc_lblPaspoortnummer);
		
		passportNumberTextField = new JTextField();
		GridBagConstraints gbc_passportNumberTextField = new GridBagConstraints();
		gbc_passportNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_passportNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passportNumberTextField.gridx = 1;
		gbc_passportNumberTextField.gridy = 11;
		contentPanel.add(passportNumberTextField, gbc_passportNumberTextField);
		passportNumberTextField.setColumns(10);
		
		JLabel lblKvkNummer = new JLabel("KVK Nummer:");
		lblKvkNummer.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblKvkNummer = new GridBagConstraints();
		gbc_lblKvkNummer.anchor = GridBagConstraints.WEST;
		gbc_lblKvkNummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblKvkNummer.gridx = 0;
		gbc_lblKvkNummer.gridy = 12;
		contentPanel.add(lblKvkNummer, gbc_lblKvkNummer);
		
		kvkNumberTextField = new JTextField();
		GridBagConstraints gbc_kvkNumberTextField = new GridBagConstraints();
		gbc_kvkNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_kvkNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_kvkNumberTextField.gridx = 1;
		gbc_kvkNumberTextField.gridy = 12;
		contentPanel.add(kvkNumberTextField, gbc_kvkNumberTextField);
		kvkNumberTextField.setColumns(10);
		
		JLabel lblBtwNummer = new JLabel("BTW Nummer:");
		lblBtwNummer.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblBtwNummer = new GridBagConstraints();
		gbc_lblBtwNummer.anchor = GridBagConstraints.WEST;
		gbc_lblBtwNummer.insets = new Insets(0, 0, 0, 5);
		gbc_lblBtwNummer.gridx = 0;
		gbc_lblBtwNummer.gridy = 13;
		contentPanel.add(lblBtwNummer, gbc_lblBtwNummer);
		
		vatNumberTextField = new JTextField();
		GridBagConstraints gbc_vatNumberTextField = new GridBagConstraints();
		gbc_vatNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_vatNumberTextField.gridx = 1;
		gbc_vatNumberTextField.gridy = 13;
		contentPanel.add(vatNumberTextField, gbc_vatNumberTextField);
		vatNumberTextField.setColumns(10);
	}

	public User getModel() {
		User user = new User();
		
		return user;
	}
}
