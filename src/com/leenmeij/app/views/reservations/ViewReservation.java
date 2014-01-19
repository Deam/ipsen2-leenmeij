package com.leenmeij.app.views.reservations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * The overview for all the reservations
 * @author Deam Kop (s1075228)
 *
 */
@SuppressWarnings("serial")
public class ViewReservation extends JFrame {
	
	private JPanel contentPane;
	public JScrollPane tablePanel;
	public JButton editButton;

	public ViewReservation() {
		setTitle("Reservering overzicht");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tablePanel = new JScrollPane();
		contentPane.add(tablePanel, BorderLayout.CENTER);
		
		editButton = new JButton("Bewerken");
		editButton.setToolTipText("Selecteer de reservering");
		editButton.setOpaque(true);
		editButton.setForeground(Color.WHITE);
		editButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		editButton.setFocusPainted(false);
		editButton.setContentAreaFilled(true);
		editButton.setBorderPainted(false);
		editButton.setBackground(new Color(66, 139, 202));
		contentPane.add(editButton, BorderLayout.SOUTH);
	}

}
