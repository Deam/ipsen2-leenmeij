package com.leenmeij.app.views.reservations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SelectCustomer extends JFrame {

	private JPanel contentPane;
	public JTextField searchTextField;
	public JButton searchButton;
	public JButton selectButton;
	public JScrollPane tablePanel;
	
	public SelectCustomer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tablePanel = new JScrollPane();
		contentPane.add(tablePanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		searchTextField = new JTextField();
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		selectButton = new JButton("Selecteren");
		selectButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectButton.setForeground(new Color(255, 255, 255));
		selectButton.setToolTipText("Selecteer de klant");
		selectButton.setBackground(new Color(92, 184, 92));
		selectButton.setBorderPainted(false);
		selectButton.setOpaque(true);
		selectButton.setFocusPainted(false);
		selectButton.setContentAreaFilled(true);	
		contentPane.add(selectButton, BorderLayout.SOUTH);
		
		searchButton = new JButton("Zoeken");
		searchButton.setToolTipText("Zoeken naar een klant");
		searchButton.setOpaque(true);
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		searchButton.setFocusPainted(false);
		searchButton.setContentAreaFilled(true);
		searchButton.setBorderPainted(false);
		searchButton.setBackground(new Color(66, 139, 202));		
		panel.add(searchButton);
	}

}
