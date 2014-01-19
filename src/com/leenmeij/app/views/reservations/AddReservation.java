package com.leenmeij.app.views.reservations;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JSeparator;
import javax.swing.JPanel;

/**
 * The view for adding a reservation
 * @author Deam Kop (s1075228)
 *
 */
@SuppressWarnings("serial")
public class AddReservation extends JFrame {
	public JTextField firstnameTextField;
	public JTextField lastnameTextField;
	public JTextField customerNumberTextField;
	public JTextField brandTextField;
	public JTextField modelTextField;
	public JTextField licenseTextField;
	public JXDatePicker startDatePicker;
	public JXDatePicker endDatePicker;
	public JButton addButton;
	public JButton selectCustomerButton;
	public JButton selectVehicleButton;
	public JPanel vehicleOptionsPanel;
	public JTextField priceTextField;
	public JTextField vatTextField;
	public JTextField totalTextField;


	public AddReservation() {
		setTitle("Reservering aanmaken");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 554, 695);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 356, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblDatumSelecteren = new JLabel("Datum Selecteren");
		lblDatumSelecteren.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblDatumSelecteren = new GridBagConstraints();
		gbc_lblDatumSelecteren.gridwidth = 2;
		gbc_lblDatumSelecteren.anchor = GridBagConstraints.WEST;
		gbc_lblDatumSelecteren.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatumSelecteren.gridx = 0;
		gbc_lblDatumSelecteren.gridy = 0;
		getContentPane().add(lblDatumSelecteren, gbc_lblDatumSelecteren);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 4;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 1;
		getContentPane().add(separator_2, gbc_separator_2);
		
		JLabel lblBegindatum = new JLabel("Begindatum:");
		GridBagConstraints gbc_lblBegindatum = new GridBagConstraints();
		gbc_lblBegindatum.anchor = GridBagConstraints.WEST;
		gbc_lblBegindatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblBegindatum.gridx = 0;
		gbc_lblBegindatum.gridy = 2;
		getContentPane().add(lblBegindatum, gbc_lblBegindatum);
		
		startDatePicker = new JXDatePicker();
		GridBagConstraints gbc_startDatePicker = new GridBagConstraints();
		gbc_startDatePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_startDatePicker.insets = new Insets(0, 0, 5, 5);
		gbc_startDatePicker.gridx = 1;
		gbc_startDatePicker.gridy = 2;
		getContentPane().add(startDatePicker, gbc_startDatePicker);
		
		JLabel lblEinddatum = new JLabel("Einddatum:");
		GridBagConstraints gbc_lblEinddatum = new GridBagConstraints();
		gbc_lblEinddatum.anchor = GridBagConstraints.WEST;
		gbc_lblEinddatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblEinddatum.gridx = 0;
		gbc_lblEinddatum.gridy = 3;
		getContentPane().add(lblEinddatum, gbc_lblEinddatum);
		
		endDatePicker = new JXDatePicker();
		GridBagConstraints gbc_endDatePicker = new GridBagConstraints();
		gbc_endDatePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_endDatePicker.insets = new Insets(0, 0, 5, 5);
		gbc_endDatePicker.gridx = 1;
		gbc_endDatePicker.gridy = 3;
		getContentPane().add(endDatePicker, gbc_endDatePicker);
		
		JLabel lblKlantSelecteren = new JLabel("Klant selecteren");
		lblKlantSelecteren.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblKlantSelecteren = new GridBagConstraints();
		gbc_lblKlantSelecteren.gridwidth = 2;
		gbc_lblKlantSelecteren.anchor = GridBagConstraints.WEST;
		gbc_lblKlantSelecteren.insets = new Insets(0, 0, 5, 5);
		gbc_lblKlantSelecteren.gridx = 0;
		gbc_lblKlantSelecteren.gridy = 5;
		getContentPane().add(lblKlantSelecteren, gbc_lblKlantSelecteren);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 6;
		getContentPane().add(separator, gbc_separator);
		
		JLabel lblVoornaam = new JLabel("Voornaam:");
		GridBagConstraints gbc_lblVoornaam = new GridBagConstraints();
		gbc_lblVoornaam.anchor = GridBagConstraints.WEST;
		gbc_lblVoornaam.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoornaam.gridx = 0;
		gbc_lblVoornaam.gridy = 7;
		getContentPane().add(lblVoornaam, gbc_lblVoornaam);
		
		firstnameTextField = new JTextField();
		firstnameTextField.setEditable(false);
		GridBagConstraints gbc_firstnameTextField = new GridBagConstraints();
		gbc_firstnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstnameTextField.gridx = 1;
		gbc_firstnameTextField.gridy = 7;
		getContentPane().add(firstnameTextField, gbc_firstnameTextField);
		firstnameTextField.setColumns(10);
		
		selectCustomerButton = new JButton("Klant selecteren");
		selectCustomerButton.setToolTipText("Voeg een klant toe aan de reservering");
		selectCustomerButton.setOpaque(true);
		selectCustomerButton.setForeground(Color.WHITE);
		selectCustomerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectCustomerButton.setFocusPainted(false);
		selectCustomerButton.setContentAreaFilled(true);
		selectCustomerButton.setBorderPainted(false);
		selectCustomerButton.setBackground(new Color(66, 139, 202));		
		
		GridBagConstraints gbc_selectCustomerButton = new GridBagConstraints();
		gbc_selectCustomerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectCustomerButton.insets = new Insets(0, 0, 5, 0);
		gbc_selectCustomerButton.gridx = 3;
		gbc_selectCustomerButton.gridy = 7;
		getContentPane().add(selectCustomerButton, gbc_selectCustomerButton);
		
		JLabel lblAchternaam = new JLabel("Achternaam:");
		GridBagConstraints gbc_lblAchternaam = new GridBagConstraints();
		gbc_lblAchternaam.anchor = GridBagConstraints.WEST;
		gbc_lblAchternaam.insets = new Insets(0, 0, 5, 5);
		gbc_lblAchternaam.gridx = 0;
		gbc_lblAchternaam.gridy = 8;
		getContentPane().add(lblAchternaam, gbc_lblAchternaam);
		
		lastnameTextField = new JTextField();
		lastnameTextField.setEditable(false);
		GridBagConstraints gbc_lastnameTextField = new GridBagConstraints();
		gbc_lastnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastnameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastnameTextField.gridx = 1;
		gbc_lastnameTextField.gridy = 8;
		getContentPane().add(lastnameTextField, gbc_lastnameTextField);
		lastnameTextField.setColumns(10);
		
		JLabel lblKlantnummer = new JLabel("Klantnummer:");
		GridBagConstraints gbc_lblKlantnummer = new GridBagConstraints();
		gbc_lblKlantnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblKlantnummer.anchor = GridBagConstraints.WEST;
		gbc_lblKlantnummer.gridx = 0;
		gbc_lblKlantnummer.gridy = 9;
		getContentPane().add(lblKlantnummer, gbc_lblKlantnummer);
		
		customerNumberTextField = new JTextField();
		customerNumberTextField.setEditable(false);
		GridBagConstraints gbc_customerNumberTextField = new GridBagConstraints();
		gbc_customerNumberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_customerNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerNumberTextField.gridx = 1;
		gbc_customerNumberTextField.gridy = 9;
		getContentPane().add(customerNumberTextField, gbc_customerNumberTextField);
		customerNumberTextField.setColumns(10);
		
		JLabel lblVoertuigSelecteren = new JLabel("Voertuig selecteren");
		lblVoertuigSelecteren.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblVoertuigSelecteren = new GridBagConstraints();
		gbc_lblVoertuigSelecteren.gridwidth = 2;
		gbc_lblVoertuigSelecteren.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoertuigSelecteren.anchor = GridBagConstraints.WEST;
		gbc_lblVoertuigSelecteren.gridx = 0;
		gbc_lblVoertuigSelecteren.gridy = 11;
		getContentPane().add(lblVoertuigSelecteren, gbc_lblVoertuigSelecteren);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 12;
		getContentPane().add(separator_1, gbc_separator_1);
		
		JLabel lblMerkl = new JLabel("Merk:");
		GridBagConstraints gbc_lblMerkl = new GridBagConstraints();
		gbc_lblMerkl.anchor = GridBagConstraints.WEST;
		gbc_lblMerkl.insets = new Insets(0, 0, 5, 5);
		gbc_lblMerkl.gridx = 0;
		gbc_lblMerkl.gridy = 13;
		getContentPane().add(lblMerkl, gbc_lblMerkl);
		
		brandTextField = new JTextField();
		brandTextField.setEditable(false);
		GridBagConstraints gbc_brandTextField = new GridBagConstraints();
		gbc_brandTextField.insets = new Insets(0, 0, 5, 5);
		gbc_brandTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brandTextField.gridx = 1;
		gbc_brandTextField.gridy = 13;
		getContentPane().add(brandTextField, gbc_brandTextField);
		brandTextField.setColumns(10);
		
		selectVehicleButton = new JButton("Voertuig selecteren");
		selectVehicleButton.setToolTipText("Voeg een voertuig toe aan de reservering");
		selectVehicleButton.setOpaque(true);
		selectVehicleButton.setForeground(Color.WHITE);
		selectVehicleButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectVehicleButton.setFocusPainted(false);
		selectVehicleButton.setContentAreaFilled(true);
		selectVehicleButton.setBorderPainted(false);
		selectVehicleButton.setBackground(new Color(66, 139, 202));		
		GridBagConstraints gbc_selectVehicleButton = new GridBagConstraints();
		gbc_selectVehicleButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectVehicleButton.insets = new Insets(0, 0, 5, 0);
		gbc_selectVehicleButton.gridx = 3;
		gbc_selectVehicleButton.gridy = 13;
		getContentPane().add(selectVehicleButton, gbc_selectVehicleButton);
		
		JLabel lblModel = new JLabel("Model:");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.WEST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 14;
		getContentPane().add(lblModel, gbc_lblModel);
		
		modelTextField = new JTextField();
		modelTextField.setEditable(false);
		GridBagConstraints gbc_modelTextField = new GridBagConstraints();
		gbc_modelTextField.insets = new Insets(0, 0, 5, 5);
		gbc_modelTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_modelTextField.gridx = 1;
		gbc_modelTextField.gridy = 14;
		getContentPane().add(modelTextField, gbc_modelTextField);
		modelTextField.setColumns(10);
		
		JLabel lblKenteken = new JLabel("Kenteken:");
		GridBagConstraints gbc_lblKenteken = new GridBagConstraints();
		gbc_lblKenteken.insets = new Insets(0, 0, 5, 5);
		gbc_lblKenteken.anchor = GridBagConstraints.WEST;
		gbc_lblKenteken.gridx = 0;
		gbc_lblKenteken.gridy = 15;
		getContentPane().add(lblKenteken, gbc_lblKenteken);
		
		licenseTextField = new JTextField();
		licenseTextField.setEditable(false);
		GridBagConstraints gbc_licenseTextField = new GridBagConstraints();
		gbc_licenseTextField.insets = new Insets(0, 0, 5, 5);
		gbc_licenseTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_licenseTextField.gridx = 1;
		gbc_licenseTextField.gridy = 15;
		getContentPane().add(licenseTextField, gbc_licenseTextField);
		licenseTextField.setColumns(10);
		
		JLabel lblVoertuigoptieSelecteren = new JLabel("Voertuigoptie selecteren");
		lblVoertuigoptieSelecteren.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblVoertuigoptieSelecteren = new GridBagConstraints();
		gbc_lblVoertuigoptieSelecteren.anchor = GridBagConstraints.WEST;
		gbc_lblVoertuigoptieSelecteren.gridwidth = 2;
		gbc_lblVoertuigoptieSelecteren.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoertuigoptieSelecteren.gridx = 0;
		gbc_lblVoertuigoptieSelecteren.gridy = 17;
		getContentPane().add(lblVoertuigoptieSelecteren, gbc_lblVoertuigoptieSelecteren);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.gridwidth = 4;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 18;
		getContentPane().add(separator_3, gbc_separator_3);
		
		vehicleOptionsPanel = new JPanel();
		GridBagConstraints gbc_vehicleOptionsPanel = new GridBagConstraints();
		gbc_vehicleOptionsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_vehicleOptionsPanel.fill = GridBagConstraints.BOTH;
		gbc_vehicleOptionsPanel.gridx = 1;
		gbc_vehicleOptionsPanel.gridy = 19;
		getContentPane().add(vehicleOptionsPanel, gbc_vehicleOptionsPanel);
		
		JLabel lblNewLabel = new JLabel("Prijs");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 21;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_4.gridwidth = 4;
		gbc_separator_4.insets = new Insets(0, 0, 5, 0);
		gbc_separator_4.gridx = 0;
		gbc_separator_4.gridy = 22;
		getContentPane().add(separator_4, gbc_separator_4);
		
		JLabel lblPrijs = new JLabel("Prijs:");
		GridBagConstraints gbc_lblPrijs = new GridBagConstraints();
		gbc_lblPrijs.anchor = GridBagConstraints.WEST;
		gbc_lblPrijs.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrijs.gridx = 0;
		gbc_lblPrijs.gridy = 23;
		getContentPane().add(lblPrijs, gbc_lblPrijs);
		
		priceTextField = new JTextField();
		priceTextField.setEditable(false);
		GridBagConstraints gbc_priceTextField = new GridBagConstraints();
		gbc_priceTextField.insets = new Insets(0, 0, 5, 5);
		gbc_priceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceTextField.gridx = 1;
		gbc_priceTextField.gridy = 23;
		getContentPane().add(priceTextField, gbc_priceTextField);
		priceTextField.setColumns(10);
		
		JLabel lblBtw = new JLabel("BTW:");
		GridBagConstraints gbc_lblBtw = new GridBagConstraints();
		gbc_lblBtw.anchor = GridBagConstraints.WEST;
		gbc_lblBtw.insets = new Insets(0, 0, 5, 5);
		gbc_lblBtw.gridx = 0;
		gbc_lblBtw.gridy = 24;
		getContentPane().add(lblBtw, gbc_lblBtw);
		
		vatTextField = new JTextField();
		vatTextField.setEditable(false);
		GridBagConstraints gbc_vatTextField = new GridBagConstraints();
		gbc_vatTextField.insets = new Insets(0, 0, 5, 5);
		gbc_vatTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_vatTextField.gridx = 1;
		gbc_vatTextField.gridy = 24;
		getContentPane().add(vatTextField, gbc_vatTextField);
		vatTextField.setColumns(10);
		
		JLabel lblTotaal = new JLabel("Totaal:");
		GridBagConstraints gbc_lblTotaal = new GridBagConstraints();
		gbc_lblTotaal.anchor = GridBagConstraints.WEST;
		gbc_lblTotaal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotaal.gridx = 0;
		gbc_lblTotaal.gridy = 25;
		getContentPane().add(lblTotaal, gbc_lblTotaal);
		
		totalTextField = new JTextField();
		totalTextField.setEditable(false);
		GridBagConstraints gbc_totalTextField = new GridBagConstraints();
		gbc_totalTextField.anchor = GridBagConstraints.NORTH;
		gbc_totalTextField.insets = new Insets(0, 0, 5, 5);
		gbc_totalTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalTextField.gridx = 1;
		gbc_totalTextField.gridy = 25;
		getContentPane().add(totalTextField, gbc_totalTextField);
		totalTextField.setColumns(10);
		
		addButton = new JButton("Reservering aanmaken");
		addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addButton.setForeground(new Color(255, 255, 255));
		addButton.setToolTipText("Voeg de reservering toe aan de database");
		addButton.setBackground(new Color(92, 184, 92));
		addButton.setBorderPainted(false);
		addButton.setOpaque(true);
		addButton.setFocusPainted(false);
		addButton.setContentAreaFilled(true);		
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.anchor = GridBagConstraints.NORTH;
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.gridwidth = 4;
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 26;
		getContentPane().add(addButton, gbc_addButton);
	}
}
