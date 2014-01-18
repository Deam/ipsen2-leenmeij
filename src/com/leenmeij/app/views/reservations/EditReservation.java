package com.leenmeij.app.views.reservations;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class EditReservation extends JFrame {

	private JPanel contentPane;
	public JTextField reservationField;
	public JTextField userField;
	public JTextField vehicleField;
	public JButton damageButton;
	public JButton editButton;
	public JButton deleteButton;
	public JPanel optionsPanel;

	public EditReservation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblReservering = new JLabel("Reservering");
		lblReservering.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblReservering = new GridBagConstraints();
		gbc_lblReservering.anchor = GridBagConstraints.WEST;
		gbc_lblReservering.gridwidth = 2;
		gbc_lblReservering.insets = new Insets(0, 0, 5, 0);
		gbc_lblReservering.gridx = 0;
		gbc_lblReservering.gridy = 0;
		contentPane.add(lblReservering, gbc_lblReservering);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JLabel lblReserveringsnummer = new JLabel("Reserveringsnummer:");
		GridBagConstraints gbc_lblReserveringsnummer = new GridBagConstraints();
		gbc_lblReserveringsnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblReserveringsnummer.anchor = GridBagConstraints.WEST;
		gbc_lblReserveringsnummer.gridx = 0;
		gbc_lblReserveringsnummer.gridy = 2;
		contentPane.add(lblReserveringsnummer, gbc_lblReserveringsnummer);
		
		reservationField = new JTextField();
		reservationField.setEnabled(false);
		GridBagConstraints gbc_reservationField = new GridBagConstraints();
		gbc_reservationField.insets = new Insets(0, 0, 5, 0);
		gbc_reservationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_reservationField.gridx = 1;
		gbc_reservationField.gridy = 2;
		contentPane.add(reservationField, gbc_reservationField);
		reservationField.setColumns(10);
		
		JLabel lblUser = new JLabel("Klant:");
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.WEST;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 3;
		contentPane.add(lblUser, gbc_lblUser);
		
		userField = new JTextField();
		userField.setEnabled(false);
		GridBagConstraints gbc_userField = new GridBagConstraints();
		gbc_userField.insets = new Insets(0, 0, 5, 0);
		gbc_userField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userField.gridx = 1;
		gbc_userField.gridy = 3;
		contentPane.add(userField, gbc_userField);
		userField.setColumns(10);
		
		JLabel lblVoertuig = new JLabel("Voertuig:");
		GridBagConstraints gbc_lblVoertuig = new GridBagConstraints();
		gbc_lblVoertuig.anchor = GridBagConstraints.WEST;
		gbc_lblVoertuig.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoertuig.gridx = 0;
		gbc_lblVoertuig.gridy = 4;
		contentPane.add(lblVoertuig, gbc_lblVoertuig);
		
		vehicleField = new JTextField();
		vehicleField.setEnabled(false);
		GridBagConstraints gbc_vehicleField = new GridBagConstraints();
		gbc_vehicleField.insets = new Insets(0, 0, 5, 0);
		gbc_vehicleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_vehicleField.gridx = 1;
		gbc_vehicleField.gridy = 4;
		contentPane.add(vehicleField, gbc_vehicleField);
		vehicleField.setColumns(10);
		
		damageButton = new JButton("Schade melden");
		damageButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		damageButton.setForeground(new Color(255, 255, 255));
		damageButton.setToolTipText("Sla de bewerkte gegevens op");
		damageButton.setBackground(new Color(66, 139, 202));
		damageButton.setBorderPainted(false);
		damageButton.setOpaque(true);
		damageButton.setFocusPainted(false);
		damageButton.setContentAreaFilled(true);
		GridBagConstraints gbc_damageButton = new GridBagConstraints();
		gbc_damageButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_damageButton.insets = new Insets(0, 0, 5, 0);
		gbc_damageButton.gridx = 1;
		gbc_damageButton.gridy = 5;
		contentPane.add(damageButton, gbc_damageButton);
		
		JLabel lblVoertuigopties = new JLabel("Voertuigopties:");
		GridBagConstraints gbc_lblVoertuigopties = new GridBagConstraints();
		gbc_lblVoertuigopties.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblVoertuigopties.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoertuigopties.gridx = 0;
		gbc_lblVoertuigopties.gridy = 6;
		contentPane.add(lblVoertuigopties, gbc_lblVoertuigopties);
		
		optionsPanel = new JPanel();
		GridBagConstraints gbc_optionsPanel = new GridBagConstraints();
		gbc_optionsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_optionsPanel.fill = GridBagConstraints.BOTH;
		gbc_optionsPanel.gridx = 1;
		gbc_optionsPanel.gridy = 6;
		contentPane.add(optionsPanel, gbc_optionsPanel);
		
		deleteButton = new JButton("Annuleren");
		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		deleteButton.setForeground(new Color(255, 255, 255));
		deleteButton.setToolTipText("Verwijder de geselecteerde klant");
		deleteButton.setBackground(new Color(195, 96, 90));	
		deleteButton.setBorderPainted(false);
		deleteButton.setOpaque(true);
		deleteButton.setFocusPainted(false);
		deleteButton.setContentAreaFilled(true);
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteButton.gridwidth = 2;
		gbc_deleteButton.insets = new Insets(0, 0, 5, 0);
		gbc_deleteButton.gridx = 0;
		gbc_deleteButton.gridy = 9;
		contentPane.add(deleteButton, gbc_deleteButton);
		
		editButton = new JButton("Bewerken");
		editButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		editButton.setForeground(new Color(255, 255, 255));
		editButton.setToolTipText("Sla de bewerkte gegevens op");
		editButton.setBackground(new Color(66, 139, 202));
		editButton.setBorderPainted(false);
		editButton.setOpaque(true);
		editButton.setFocusPainted(false);
		editButton.setContentAreaFilled(true);
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.gridwidth = 2;
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.gridx = 0;
		gbc_editButton.gridy = 10;
		contentPane.add(editButton, gbc_editButton);
	}

}
