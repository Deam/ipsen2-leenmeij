package com.leenmeij.app.views.vehicle;

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

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

import com.leenmeij.app.controllers.VehicleController;

/**
 * The view for editing the information of a vehicle
 * @author Deam
 *
 */
@SuppressWarnings("serial")
public class EditVehicle extends JFrame {

	private JPanel contentPane;

	public JTextField brandTextField;
	public JTextField modelTextField;
	public JTextField milageTextField;
	public JTextField licenseTextBox;
	public JTextField colorTextField;
	public JTextField hourlyrateTextBox;
	public JLabel pictureLabel;
	public JButton selectImageButton, editButton;
	public JComboBox<String> categoryComboBox;
	public JTextField idTextField;
	public JTextField imageTextField;

	public String imgName = "";
	private JScrollPane scrollPane;
	public JTextArea commentArea;
	public JTextField usageText;
	private JLabel lblSchadegeschiedenis;
	public JScrollPane damagePanels;
	private JLabel lblBeschikbaarVoorVerhuur;
	public JCheckBox availableCheckBox;

	public EditVehicle() {
		setTitle("Voertuig bewerken");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

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
		
		idTextField = new JTextField();
		imageTextField = new JTextField();

		JPanel contentPanel = new JPanel();
		contentPane.add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblCategorie = new GridBagConstraints();
		gbc_lblCategorie.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategorie.anchor = GridBagConstraints.WEST;
		gbc_lblCategorie.gridx = 0;
		gbc_lblCategorie.gridy = 0;
		contentPanel.add(lblCategorie, gbc_lblCategorie);
	
		categoryComboBox = new JComboBox<String>(VehicleController.categoryItems());
		categoryComboBox
				.setToolTipText("Selecteer de categorie waarin het voertuig valt.");
		GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
		gbc_categoryComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoryComboBox.gridx = 1;
		gbc_categoryComboBox.gridy = 0;
		contentPanel.add(categoryComboBox, gbc_categoryComboBox);

		JLabel lblMerk = new JLabel("Merk:");
		lblMerk.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblMerk = new GridBagConstraints();
		gbc_lblMerk.anchor = GridBagConstraints.WEST;
		gbc_lblMerk.insets = new Insets(0, 0, 5, 5);
		gbc_lblMerk.gridx = 0;
		gbc_lblMerk.gridy = 1;
		contentPanel.add(lblMerk, gbc_lblMerk);

		brandTextField = new JTextField();
		brandTextField.setToolTipText("Pas hier het merk van het voertuig aan");
		GridBagConstraints gbc_brandTextField = new GridBagConstraints();
		gbc_brandTextField.insets = new Insets(0, 0, 5, 0);
		gbc_brandTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brandTextField.gridx = 1;
		gbc_brandTextField.gridy = 1;
		contentPanel.add(brandTextField, gbc_brandTextField);
		brandTextField.setColumns(10);

		JLabel lblModel = new JLabel("Model:");
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.anchor = GridBagConstraints.WEST;
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 2;
		contentPanel.add(lblModel, gbc_lblModel);

		modelTextField = new JTextField();
		modelTextField
				.setToolTipText("Pas hier het model van het voertuig aan");
		GridBagConstraints gbc_modelTextField = new GridBagConstraints();
		gbc_modelTextField.insets = new Insets(0, 0, 5, 0);
		gbc_modelTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_modelTextField.gridx = 1;
		gbc_modelTextField.gridy = 2;
		contentPanel.add(modelTextField, gbc_modelTextField);
		modelTextField.setColumns(10);

		JLabel lblKilometerstand = new JLabel("Kilometerstand:");
		lblKilometerstand.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblKilometerstand = new GridBagConstraints();
		gbc_lblKilometerstand.anchor = GridBagConstraints.WEST;
		gbc_lblKilometerstand.insets = new Insets(0, 0, 5, 5);
		gbc_lblKilometerstand.gridx = 0;
		gbc_lblKilometerstand.gridy = 3;
		contentPanel.add(lblKilometerstand, gbc_lblKilometerstand);

		milageTextField = new JTextField();
		milageTextField.setToolTipText("Pas hier de kilometerstand van het voertuig aan");
		GridBagConstraints gbc_milageTextField = new GridBagConstraints();
		gbc_milageTextField.insets = new Insets(0, 0, 5, 0);
		gbc_milageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_milageTextField.gridx = 1;
		gbc_milageTextField.gridy = 3;
		contentPanel.add(milageTextField, gbc_milageTextField);
		milageTextField.setColumns(10);

		JLabel licensePlateTextBox = new JLabel("Kenteken:");
		licensePlateTextBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_licensePlateTextBox = new GridBagConstraints();
		gbc_licensePlateTextBox.anchor = GridBagConstraints.WEST;
		gbc_licensePlateTextBox.insets = new Insets(0, 0, 5, 5);
		gbc_licensePlateTextBox.gridx = 0;
		gbc_licensePlateTextBox.gridy = 4;
		contentPanel.add(licensePlateTextBox, gbc_licensePlateTextBox);

		licenseTextBox = new JTextField();
		licenseTextBox.setToolTipText("Pas hier het kenteken van het voertuig aan");
		GridBagConstraints gbc_licenseTextBox = new GridBagConstraints();
		gbc_licenseTextBox.insets = new Insets(0, 0, 5, 0);
		gbc_licenseTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_licenseTextBox.gridx = 1;
		gbc_licenseTextBox.gridy = 4;
		contentPanel.add(licenseTextBox, gbc_licenseTextBox);
		licenseTextBox.setColumns(10);

		JLabel usageLabel = new JLabel("Verbruik:");
		usageLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_usageLabel = new GridBagConstraints();
		gbc_usageLabel.anchor = GridBagConstraints.WEST;
		gbc_usageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usageLabel.gridx = 0;
		gbc_usageLabel.gridy = 5;
		contentPanel.add(usageLabel, gbc_usageLabel);

		usageText = new JTextField();
		usageText.setToolTipText("Pas hier het verbruik van het voertuig aan");
		GridBagConstraints gbc_usageText = new GridBagConstraints();
		gbc_usageText.insets = new Insets(0, 0, 5, 0);
		gbc_usageText.fill = GridBagConstraints.HORIZONTAL;
		gbc_usageText.gridx = 1;
		gbc_usageText.gridy = 5;
		contentPanel.add(usageText, gbc_usageText);
		usageText.setColumns(10);

		JLabel lblKleur = new JLabel("Kleur:");
		lblKleur.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblKleur = new GridBagConstraints();
		gbc_lblKleur.anchor = GridBagConstraints.WEST;
		gbc_lblKleur.insets = new Insets(0, 0, 5, 5);
		gbc_lblKleur.gridx = 0;
		gbc_lblKleur.gridy = 6;
		contentPanel.add(lblKleur, gbc_lblKleur);

		colorTextField = new JTextField();
		colorTextField.setToolTipText("Pas hier de kleur van het voertuig aan");
		GridBagConstraints gbc_colorTextField = new GridBagConstraints();
		gbc_colorTextField.insets = new Insets(0, 0, 5, 0);
		gbc_colorTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_colorTextField.gridx = 1;
		gbc_colorTextField.gridy = 6;
		contentPanel.add(colorTextField, gbc_colorTextField);
		colorTextField.setColumns(10);

		JLabel lblPrijsPerUur = new JLabel("Prijs per uur:");
		lblPrijsPerUur.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPrijsPerUur = new GridBagConstraints();
		gbc_lblPrijsPerUur.anchor = GridBagConstraints.WEST;
		gbc_lblPrijsPerUur.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrijsPerUur.gridx = 0;
		gbc_lblPrijsPerUur.gridy = 7;
		contentPanel.add(lblPrijsPerUur, gbc_lblPrijsPerUur);

		hourlyrateTextBox = new JTextField();
		hourlyrateTextBox.setToolTipText("Pas hier de prijs per uur van het voertuig aan");
		GridBagConstraints gbc_hourlyrateTextBox = new GridBagConstraints();
		gbc_hourlyrateTextBox.insets = new Insets(0, 0, 5, 0);
		gbc_hourlyrateTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_hourlyrateTextBox.gridx = 1;
		gbc_hourlyrateTextBox.gridy = 7;
		contentPanel.add(hourlyrateTextBox, gbc_hourlyrateTextBox);
		hourlyrateTextBox.setColumns(10);

		JLabel lblAfbeelding = new JLabel("Afbeelding:");
		lblAfbeelding.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAfbeelding = new GridBagConstraints();
		gbc_lblAfbeelding.anchor = GridBagConstraints.WEST;
		gbc_lblAfbeelding.insets = new Insets(0, 0, 5, 5);
		gbc_lblAfbeelding.gridx = 0;
		gbc_lblAfbeelding.gridy = 8;
		contentPanel.add(lblAfbeelding, gbc_lblAfbeelding);

		selectImageButton = new JButton("Afbeelding selecteren");
		selectImageButton.setToolTipText("Klik hier om een bestandselecteer venster te openen.");
		GridBagConstraints gbc_selectImageButton = new GridBagConstraints();
		gbc_selectImageButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectImageButton.insets = new Insets(0, 0, 5, 0);
		gbc_selectImageButton.gridx = 1;
		gbc_selectImageButton.gridy = 8;
		contentPanel.add(selectImageButton, gbc_selectImageButton);
		
		pictureLabel = new JLabel("Gekozen afbeelding..");
		pictureLabel.setToolTipText("Het pad van de gekozen afbeelding");
		GridBagConstraints gbc_pictureLabel = new GridBagConstraints();
		gbc_pictureLabel.insets = new Insets(0, 0, 5, 0);
		gbc_pictureLabel.gridx = 1;
		gbc_pictureLabel.gridy = 9;
		contentPanel.add(pictureLabel, gbc_pictureLabel);

		JLabel lblOpmerkingen = new JLabel("Opmerkingen:");
		lblOpmerkingen.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblOpmerkingen = new GridBagConstraints();
		gbc_lblOpmerkingen.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblOpmerkingen.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpmerkingen.gridx = 0;
		gbc_lblOpmerkingen.gridy = 10;
		contentPanel.add(lblOpmerkingen, gbc_lblOpmerkingen);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 10;
		contentPanel.add(scrollPane, gbc_scrollPane);

		commentArea = new JTextArea();
		commentArea.setLineWrap(true);
		commentArea.setWrapStyleWord(true);
		commentArea.setToolTipText("Pas hier de omschrijving van het voertuig aan");
		scrollPane.setViewportView(commentArea);
		
		lblSchadegeschiedenis = new JLabel("Schadegeschiedenis:");
		lblSchadegeschiedenis.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblSchadegeschiedenis = new GridBagConstraints();
		gbc_lblSchadegeschiedenis.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblSchadegeschiedenis.insets = new Insets(0, 0, 5, 5);
		gbc_lblSchadegeschiedenis.gridx = 0;
		gbc_lblSchadegeschiedenis.gridy = 11;
		contentPanel.add(lblSchadegeschiedenis, gbc_lblSchadegeschiedenis);
		
		damagePanels = new JScrollPane();
		GridBagConstraints gbc_damagePanels = new GridBagConstraints();
		gbc_damagePanels.insets = new Insets(0, 0, 5, 0);
		gbc_damagePanels.fill = GridBagConstraints.BOTH;
		gbc_damagePanels.gridx = 1;
		gbc_damagePanels.gridy = 11;
		contentPanel.add(damagePanels, gbc_damagePanels);
		
		lblBeschikbaarVoorVerhuur = new JLabel("Beschikbaar voor verhuur:");
		lblBeschikbaarVoorVerhuur.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblBeschikbaarVoorVerhuur = new GridBagConstraints();
		gbc_lblBeschikbaarVoorVerhuur.insets = new Insets(0, 0, 0, 5);
		gbc_lblBeschikbaarVoorVerhuur.gridx = 0;
		gbc_lblBeschikbaarVoorVerhuur.gridy = 12;
		contentPanel.add(lblBeschikbaarVoorVerhuur, gbc_lblBeschikbaarVoorVerhuur);
		
		availableCheckBox = new JCheckBox("Niet beschikbaar");
		availableCheckBox.setToolTipText("Vink aan als het voertuig niet beschikbaar is");
		GridBagConstraints gbc_availableCheckBox = new GridBagConstraints();
		gbc_availableCheckBox.anchor = GridBagConstraints.WEST;
		gbc_availableCheckBox.gridx = 1;
		gbc_availableCheckBox.gridy = 12;
		contentPanel.add(availableCheckBox, gbc_availableCheckBox);
	}
}
