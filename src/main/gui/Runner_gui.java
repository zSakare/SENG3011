package main.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import main.utils.Strategy;


public class Runner_gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Runner_gui window = new Runner_gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Runner_gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JTextPane txtpnOutput = new JTextPane();
		txtpnOutput.setEditable(false);
		GridBagConstraints gbc_txtpnOutput = new GridBagConstraints();
		gbc_txtpnOutput.gridwidth = 3;
		gbc_txtpnOutput.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnOutput.fill = GridBagConstraints.BOTH;
		gbc_txtpnOutput.gridx = 0;
		gbc_txtpnOutput.gridy = 0;
		frame.getContentPane().add(txtpnOutput, gbc_txtpnOutput);
		
		JLabel lblChooseStrategy = new JLabel("Choose Strategy:");
		GridBagConstraints gbc_lblChooseStrategy = new GridBagConstraints();
		gbc_lblChooseStrategy.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseStrategy.gridx = 1;
		gbc_lblChooseStrategy.gridy = 1;
		frame.getContentPane().add(lblChooseStrategy, gbc_lblChooseStrategy);
		
		JButton btnLoadInputFile = new JButton("Load Input File");
		btnLoadInputFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO: input file locator
			}
		});
		GridBagConstraints gbc_btnLoadInputFile = new GridBagConstraints();
		gbc_btnLoadInputFile.anchor = GridBagConstraints.SOUTH;
		gbc_btnLoadInputFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoadInputFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadInputFile.gridx = 0;
		gbc_btnLoadInputFile.gridy = 2;
		frame.getContentPane().add(btnLoadInputFile, gbc_btnLoadInputFile);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(3);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Random", "Momentum", "Mean-Revision"}));
		comboBox.setToolTipText("Choose Strategy");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		JButton btnPerformanceReport = new JButton("Performance Report");
		btnPerformanceReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: Link to performance report generator
			}
		});
		GridBagConstraints gbc_btnPerformanceReport = new GridBagConstraints();
		gbc_btnPerformanceReport.anchor = GridBagConstraints.SOUTH;
		gbc_btnPerformanceReport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPerformanceReport.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerformanceReport.gridx = 2;
		gbc_btnPerformanceReport.gridy = 2;
		frame.getContentPane().add(btnPerformanceReport, gbc_btnPerformanceReport);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: Exit handler
			    System.exit(0);

			}
		});
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.insets = new Insets(0, 0, 0, 5);
		gbc_btnQuit.anchor = GridBagConstraints.SOUTH;
		gbc_btnQuit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuit.gridx = 0;
		gbc_btnQuit.gridy = 3;
		frame.getContentPane().add(btnQuit, gbc_btnQuit);
		
		JButton btnExecuteStrategy = new JButton("Execute Strategy");
		btnExecuteStrategy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: Link to strategy executor
				
			}
		});
		GridBagConstraints gbc_btnExecuteStrategy = new GridBagConstraints();
		gbc_btnExecuteStrategy.anchor = GridBagConstraints.SOUTH;
		gbc_btnExecuteStrategy.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExecuteStrategy.insets = new Insets(0, 0, 0, 5);
		gbc_btnExecuteStrategy.gridx = 1;
		gbc_btnExecuteStrategy.gridy = 3;
		frame.getContentPane().add(btnExecuteStrategy, gbc_btnExecuteStrategy);
		
		JButton btnSaveLog = new JButton("Save Log");
		btnSaveLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: Save output log to file
			}
		});
		GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
		gbc_btnSaveLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveLog.gridx = 2;
		gbc_btnSaveLog.gridy = 3;
		frame.getContentPane().add(btnSaveLog, gbc_btnSaveLog);
	}
}

