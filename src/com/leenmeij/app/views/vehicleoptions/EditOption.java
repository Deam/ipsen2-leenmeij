package com.leenmeij.app.views.vehicleoptions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * A view for editing vehicle options
 * @author Deam Kop (s1075228)
 *
 */
@SuppressWarnings("serial")
public class EditOption extends JFrame {

	public JTextField nameField, priceField;
	public JButton editButton;

	public EditOption() {
		setTitle("Voertuigoptie bewerken");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 156);
		getContentPane().setLayout(new BorderLayout(0, 0));

		editButton = new JButton("Bewerken");
		editButton.setToolTipText("Sla de aangepaste gegevens op");
		editButton.setOpaque(true);
		editButton.setForeground(Color.WHITE);
		editButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		editButton.setFocusPainted(false);
		editButton.setContentAreaFilled(true);
		editButton.setBorderPainted(false);
		editButton.setBackground(new Color(66, 139, 202));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.gridx = 1;
		gbc_button.gridy = 3;
		getContentPane().add(editButton, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
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
		nameField.setToolTipText("Pas hier de naam van de voertuigoptie aan.");
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
		priceField
				.setToolTipText("Pas hier de prijs van de voertuigoptie aan.");
		priceField.setColumns(10);
		GridBagConstraints gbc_priceField = new GridBagConstraints();
		gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceField.insets = new Insets(0, 0, 5, 0);
		gbc_priceField.gridx = 1;
		gbc_priceField.gridy = 2;
		panel.add(priceField, gbc_priceField);
	}
}
