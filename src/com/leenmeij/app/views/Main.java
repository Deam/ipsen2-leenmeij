package com.leenmeij.app.views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Insets;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	private JPanel contentPane;
	public JMenuItem addVehicleItem;
	public JMenuItem quitItem, logoutItem;
	public JMenuItem addOptionItem;
	public JMenuItem addUserItem;
	public JMenuItem editUserItem;
	
	public JMenu reservationItem;
	public JMenuItem addReservationItem;
	public JMenuItem userOverviewItem;
	public JMenuItem vehicleOverviewItem;
	public JMenuItem overviewOptionItem;
	public JMenuItem viewReservationItem;
	private JLabel lblGebruikers;
	public JScrollPane usersPanel;
	private JLabel lblVoertuigen;
	public JScrollPane vehiclePanel;
	private JLabel lblReserveringen;
	public JScrollPane reservationsPanel;
	private JLabel lblFacturen;
	public JScrollPane invoicePanel;
	private JLabel lblHieronderStaanDe;
	private JLabel lblHieronderStaanDe_1;
	private JLabel lblHieronderStaanDe_2;
	private JLabel lblHieronderStaanDe_3;
	private JSeparator separator;
	public JMenuItem invoiceMenuItem;
	public JLabel loggedLabel;
	public JMenu vehicleItem;
	public JMenu usersItem;
	
	public Main(ActionListener actionListner) {
		setTitle("LeenMeij");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 588);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(66, 139, 202));
		menuBar.setOpaque(true);
		setJMenuBar(menuBar);
		
		JMenu fileItem = new JMenu("Bestand");
		fileItem.setForeground(new Color(255, 255, 255));
		menuBar.add(fileItem);
		
		logoutItem = new JMenuItem("Uitloggen");
		fileItem.add(logoutItem);
		
		JSeparator itemSeperator = new JSeparator();
		fileItem.add(itemSeperator);
		
		quitItem = new JMenuItem("Afsluiten");
		fileItem.add(quitItem);
		
		vehicleItem = new JMenu("Voertuigen");
		vehicleItem.setForeground(new Color(255, 255, 255));
		menuBar.add(vehicleItem);
		
		addVehicleItem = new JMenuItem("Toevoegen");
		addVehicleItem.addActionListener(actionListner);
		vehicleItem.add(addVehicleItem);
		
		vehicleOverviewItem = new JMenuItem("Overzicht");
		vehicleItem.add(vehicleOverviewItem);
		
		JSeparator vehicleseperator = new JSeparator();
		vehicleItem.add(vehicleseperator);
		
		addOptionItem = new JMenuItem("Optie toevoegen");
		addOptionItem.addActionListener(actionListner);
		vehicleItem.add(addOptionItem);
		
		overviewOptionItem = new JMenuItem("Optie overzicht");
		vehicleItem.add(overviewOptionItem);
		
		usersItem = new JMenu("Gebruikers");
		usersItem.setForeground(new Color(255, 255, 255));
		menuBar.add(usersItem);
		
		addUserItem = new JMenuItem("Toevoegen");
		usersItem.add(addUserItem);
		
		userOverviewItem = new JMenuItem("Overzicht");
		usersItem.add(userOverviewItem);
		
		reservationItem = new JMenu("Reserveringen");
		reservationItem.setForeground(Color.WHITE);
		menuBar.add(reservationItem);
		
		addReservationItem = new JMenuItem("Toevoegen");
		reservationItem.add(addReservationItem);
		
		viewReservationItem = new JMenuItem("Overzicht");
		reservationItem.add(viewReservationItem);
		
		separator = new JSeparator();
		reservationItem.add(separator);
		
		invoiceMenuItem = new JMenuItem("Facturen overzicht");
		reservationItem.add(invoiceMenuItem);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		loggedLabel = new JLabel("Ingelogd als: ");
		loggedLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_loggedLabel = new GridBagConstraints();
		gbc_loggedLabel.anchor = GridBagConstraints.EAST;
		gbc_loggedLabel.insets = new Insets(0, 0, 5, 0);
		gbc_loggedLabel.gridx = 2;
		gbc_loggedLabel.gridy = 0;
		contentPane.add(loggedLabel, gbc_loggedLabel);
		
		lblGebruikers = new JLabel("Gebruikers");
		lblGebruikers.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblGebruikers = new GridBagConstraints();
		gbc_lblGebruikers.anchor = GridBagConstraints.WEST;
		gbc_lblGebruikers.insets = new Insets(0, 0, 5, 5);
		gbc_lblGebruikers.gridx = 0;
		gbc_lblGebruikers.gridy = 1;
		contentPane.add(lblGebruikers, gbc_lblGebruikers);
		
		lblVoertuigen = new JLabel("Voertuigen");
		lblVoertuigen.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblVoertuigen = new GridBagConstraints();
		gbc_lblVoertuigen.anchor = GridBagConstraints.WEST;
		gbc_lblVoertuigen.insets = new Insets(0, 0, 5, 0);
		gbc_lblVoertuigen.gridx = 2;
		gbc_lblVoertuigen.gridy = 1;
		contentPane.add(lblVoertuigen, gbc_lblVoertuigen);
		
		lblHieronderStaanDe = new JLabel("Hieronder staan de 15 laatst aangemelde klanten");
		GridBagConstraints gbc_lblHieronderStaanDe = new GridBagConstraints();
		gbc_lblHieronderStaanDe.anchor = GridBagConstraints.WEST;
		gbc_lblHieronderStaanDe.insets = new Insets(0, 0, 5, 5);
		gbc_lblHieronderStaanDe.gridx = 0;
		gbc_lblHieronderStaanDe.gridy = 2;
		contentPane.add(lblHieronderStaanDe, gbc_lblHieronderStaanDe);
		
		lblHieronderStaanDe_1 = new JLabel("Hieronder staan de 15 laatst aangemaakte voertuigen");
		GridBagConstraints gbc_lblHieronderStaanDe_1 = new GridBagConstraints();
		gbc_lblHieronderStaanDe_1.anchor = GridBagConstraints.WEST;
		gbc_lblHieronderStaanDe_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblHieronderStaanDe_1.gridx = 2;
		gbc_lblHieronderStaanDe_1.gridy = 2;
		contentPane.add(lblHieronderStaanDe_1, gbc_lblHieronderStaanDe_1);
		
		usersPanel = new JScrollPane();
		GridBagConstraints gbc_usersPanel = new GridBagConstraints();
		gbc_usersPanel.insets = new Insets(0, 0, 5, 5);
		gbc_usersPanel.fill = GridBagConstraints.BOTH;
		gbc_usersPanel.gridx = 0;
		gbc_usersPanel.gridy = 3;
		contentPane.add(usersPanel, gbc_usersPanel);
		
		vehiclePanel = new JScrollPane();
		GridBagConstraints gbc_vehiclePanel = new GridBagConstraints();
		gbc_vehiclePanel.insets = new Insets(0, 0, 5, 0);
		gbc_vehiclePanel.fill = GridBagConstraints.BOTH;
		gbc_vehiclePanel.gridx = 2;
		gbc_vehiclePanel.gridy = 3;
		contentPane.add(vehiclePanel, gbc_vehiclePanel);
		
		lblReserveringen = new JLabel("Reserveringen");
		lblReserveringen.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblReserveringen = new GridBagConstraints();
		gbc_lblReserveringen.anchor = GridBagConstraints.WEST;
		gbc_lblReserveringen.insets = new Insets(0, 0, 5, 5);
		gbc_lblReserveringen.gridx = 0;
		gbc_lblReserveringen.gridy = 4;
		contentPane.add(lblReserveringen, gbc_lblReserveringen);
		
		lblFacturen = new JLabel("Facturen");
		lblFacturen.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblFacturen = new GridBagConstraints();
		gbc_lblFacturen.anchor = GridBagConstraints.WEST;
		gbc_lblFacturen.insets = new Insets(0, 0, 5, 0);
		gbc_lblFacturen.gridx = 2;
		gbc_lblFacturen.gridy = 4;
		contentPane.add(lblFacturen, gbc_lblFacturen);
		
		lblHieronderStaanDe_2 = new JLabel("Hieronder staan de reserveringen voor vandaag");
		GridBagConstraints gbc_lblHieronderStaanDe_2 = new GridBagConstraints();
		gbc_lblHieronderStaanDe_2.anchor = GridBagConstraints.WEST;
		gbc_lblHieronderStaanDe_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblHieronderStaanDe_2.gridx = 0;
		gbc_lblHieronderStaanDe_2.gridy = 5;
		contentPane.add(lblHieronderStaanDe_2, gbc_lblHieronderStaanDe_2);
		
		lblHieronderStaanDe_3 = new JLabel("Hieronder staan de 15 laatste facturen");
		GridBagConstraints gbc_lblHieronderStaanDe_3 = new GridBagConstraints();
		gbc_lblHieronderStaanDe_3.anchor = GridBagConstraints.WEST;
		gbc_lblHieronderStaanDe_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblHieronderStaanDe_3.gridx = 2;
		gbc_lblHieronderStaanDe_3.gridy = 5;
		contentPane.add(lblHieronderStaanDe_3, gbc_lblHieronderStaanDe_3);
		
		reservationsPanel = new JScrollPane();
		GridBagConstraints gbc_reservationsPanel = new GridBagConstraints();
		gbc_reservationsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_reservationsPanel.fill = GridBagConstraints.BOTH;
		gbc_reservationsPanel.gridx = 0;
		gbc_reservationsPanel.gridy = 6;
		contentPane.add(reservationsPanel, gbc_reservationsPanel);
		
		invoicePanel = new JScrollPane();
		GridBagConstraints gbc_invoicePanel = new GridBagConstraints();
		gbc_invoicePanel.fill = GridBagConstraints.BOTH;
		gbc_invoicePanel.gridx = 2;
		gbc_invoicePanel.gridy = 6;
		contentPane.add(invoicePanel, gbc_invoicePanel);
		
		setVisible(true);
	}

}
