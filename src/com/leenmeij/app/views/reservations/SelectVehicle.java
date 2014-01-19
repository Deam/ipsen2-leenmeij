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
 * The view for selecting a vehicle
 * So we can set the information in the
 * AddReservation view.
 * @author Deam Kop (s1075228)
 *
 */
@SuppressWarnings("serial")
public class SelectVehicle extends JFrame {

	private JPanel contentPane;
	public JScrollPane tablePanel;
	public JButton selectButton;

	public SelectVehicle() {
		setTitle("Voertuig selecteren");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tablePanel = new JScrollPane();
		contentPane.add(tablePanel, BorderLayout.CENTER);
		
		selectButton = new JButton("Selecteren");
		selectButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectButton.setForeground(new Color(255, 255, 255));
		selectButton.setToolTipText("Selecteer het voertuig");
		selectButton.setBackground(new Color(92, 184, 92));
		selectButton.setBorderPainted(false);
		selectButton.setOpaque(true);
		selectButton.setFocusPainted(false);
		selectButton.setContentAreaFilled(true);	
		contentPane.add(selectButton, BorderLayout.SOUTH);
	}

}
