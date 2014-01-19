package com.leenmeij.app.views.users;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

/**
 * A view with a table for all the users
 * @author Deam Kop (s1075228)
 *
 */
@SuppressWarnings("serial")
public class Overview extends JFrame {

	private JPanel contentPane;
	public JScrollPane tablePanel;
	public JButton editButton;
	public JButton deleteButton;

	public Overview() {
		setTitle("Overzicht van de klanten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tablePanel = new JScrollPane();
		contentPane.add(tablePanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		editButton = new JButton("Bewerken");
		editButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		editButton.setForeground(new Color(255, 255, 255));
		editButton.setToolTipText("Bewerk de geselecteerde klant");
		editButton.setBackground(new Color(66, 139, 202));	
		editButton.setBorderPainted(false);
		editButton.setOpaque(true);
		editButton.setFocusPainted(false);
		editButton.setContentAreaFilled(true);
		
		deleteButton = new JButton("Verwijderen");
		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		deleteButton.setForeground(new Color(255, 255, 255));
		deleteButton.setToolTipText("Verwijder de gebruiker");
		deleteButton.setBackground(new Color(195, 96, 90));	
		deleteButton.setBorderPainted(false);
		deleteButton.setOpaque(true);
		deleteButton.setFocusPainted(false);
		deleteButton.setContentAreaFilled(true);
		
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

}
