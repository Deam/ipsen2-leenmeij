package com.leenmeij.app.views.vehicle;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AddDamage extends JFrame {

	private JPanel contentPane;
	public JTextField vehicleTextField;
	public JTextField reservationTextField;
	public JTextArea textArea;
	public JButton addButton;
	public int vehicleID;

	public AddDamage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblReserveringsnummer = new JLabel("Reserveringsnummer:");
		GridBagConstraints gbc_lblReserveringsnummer = new GridBagConstraints();
		gbc_lblReserveringsnummer.anchor = GridBagConstraints.EAST;
		gbc_lblReserveringsnummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblReserveringsnummer.gridx = 0;
		gbc_lblReserveringsnummer.gridy = 0;
		contentPane.add(lblReserveringsnummer, gbc_lblReserveringsnummer);
		
		reservationTextField = new JTextField();
		reservationTextField.setEditable(false);
		GridBagConstraints gbc_reservationTextField = new GridBagConstraints();
		gbc_reservationTextField.insets = new Insets(0, 0, 5, 0);
		gbc_reservationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_reservationTextField.gridx = 1;
		gbc_reservationTextField.gridy = 0;
		contentPane.add(reservationTextField, gbc_reservationTextField);
		reservationTextField.setColumns(10);
		
		JLabel lblVoertuig = new JLabel("Voertuig:");
		GridBagConstraints gbc_lblVoertuig = new GridBagConstraints();
		gbc_lblVoertuig.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoertuig.anchor = GridBagConstraints.WEST;
		gbc_lblVoertuig.gridx = 0;
		gbc_lblVoertuig.gridy = 1;
		contentPane.add(lblVoertuig, gbc_lblVoertuig);
		
		vehicleTextField = new JTextField();
		vehicleTextField.setEditable(false);
		GridBagConstraints gbc_vehicleTextField = new GridBagConstraints();
		gbc_vehicleTextField.insets = new Insets(0, 0, 5, 0);
		gbc_vehicleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_vehicleTextField.gridx = 1;
		gbc_vehicleTextField.gridy = 1;
		contentPane.add(vehicleTextField, gbc_vehicleTextField);
		vehicleTextField.setColumns(10);
		
		JLabel lblSchademelding = new JLabel("Schademelding:");
		GridBagConstraints gbc_lblSchademelding = new GridBagConstraints();
		gbc_lblSchademelding.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblSchademelding.insets = new Insets(0, 0, 5, 5);
		gbc_lblSchademelding.gridx = 0;
		gbc_lblSchademelding.gridy = 2;
		contentPane.add(lblSchademelding, gbc_lblSchademelding);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		contentPane.add(textArea, gbc_textArea);
		
		addButton = new JButton("Toepassen");
		addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addButton.setForeground(new Color(255, 255, 255));
		addButton.setToolTipText("Voeg een voertuig toe aan de database");
		addButton.setBackground(new Color(92, 184, 92));
		addButton.setBorderPainted(false);
		addButton.setOpaque(true);
		addButton.setFocusPainted(false);
		addButton.setContentAreaFilled(true);
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.gridwidth = 2;
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 3;
		contentPane.add(addButton, gbc_addButton);
	}

}
