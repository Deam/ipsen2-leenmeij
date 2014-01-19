package com.leenmeij.app.views.vehicleoptions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.BorderLayout;

/**
 * A view for adding vehicle options
 * @author Deam Kop (s1075228)
 *
 */
@SuppressWarnings("serial")
public class AddOption extends JFrame {
	public JTextField nameField, priceField;
	public JButton addButton;
	/**
	 * Create the frame.
	 */
	public AddOption() {
		setTitle("Voertuig optie toevoegen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 156);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		addButton = new JButton("Toevoegen");
		getContentPane().add(addButton, BorderLayout.SOUTH);
		addButton.setToolTipText("Voeg de voertuigoptie toe aan de database");
		addButton.setOpaque(true);
		addButton.setForeground(Color.WHITE);
		addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addButton.setFocusPainted(false);
		addButton.setContentAreaFilled(true);
		addButton.setBorderPainted(false);
		addButton.setBackground(new Color(92, 184, 92));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.gridx = 1;
		gbc_button.gridy = 3;
		getContentPane().add(addButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel labelNaam = new JLabel("Naam:");
		labelNaam.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_labelNaam = new GridBagConstraints();
		gbc_labelNaam.anchor = GridBagConstraints.WEST;
		gbc_labelNaam.insets = new Insets(0, 0, 5, 5);
		gbc_labelNaam.gridx = 0;
		gbc_labelNaam.gridy = 1;
		panel.add(labelNaam, gbc_labelNaam);
		
		nameField = new JTextField();
		nameField.setToolTipText("Vul hier de naam van de voertuigoptie in");
		nameField.setColumns(10);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 1;
		panel.add(nameField, gbc_nameField);
		
		JLabel labelPrijsperuur = new JLabel("Prijs per uur:");
		labelPrijsperuur.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_labelPrijsperuur = new GridBagConstraints();
		gbc_labelPrijsperuur.anchor = GridBagConstraints.WEST;
		gbc_labelPrijsperuur.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrijsperuur.gridx = 0;
		gbc_labelPrijsperuur.gridy = 2;
		panel.add(labelPrijsperuur, gbc_labelPrijsperuur);
		
		priceField = new JTextField();
		priceField.setToolTipText("Vul hier de prijs van de voertuig optie in. [Prijs per dag gedeeld door 24]");
		priceField.setColumns(10);
		GridBagConstraints gbc_priceField = new GridBagConstraints();
		gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceField.insets = new Insets(0, 0, 5, 0);
		gbc_priceField.gridx = 1;
		gbc_priceField.gridy = 2;
		panel.add(priceField, gbc_priceField);
	}
}
