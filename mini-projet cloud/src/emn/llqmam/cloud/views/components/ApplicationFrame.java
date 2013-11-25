package emn.llqmam.cloud.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class ApplicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ApplicationFrame () {
		this.setTitle("Our Application");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
	}


	private void initComponents() {
		
		JPanel panTop = new JPanel();
		
		// the form part for the username.
		JLabel lbUsername = new JLabel("Userame");
		JTextField tfUsername = new JTextField(20);

		// the form part for the password.
		JLabel lbPassword = new JLabel("Password");
		JTextField tfPassword = new JTextField(20);
		
		// http://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
		
		// panel for the form
		JPanel panForm = new JPanel(new GridBagLayout());
		panForm.setBackground(Colors.BACKGROUND);
		panForm.setPreferredSize(new Dimension(320, 130));
		panForm.add(lbUsername, getGridBagConstraints(0, 0, 1));
		panForm.add(tfUsername, getGridBagConstraints(1, 0, 2));
		panForm.add(lbPassword, getGridBagConstraints(0, 1, 1));
		panForm.add(tfPassword, getGridBagConstraints(1, 1, 2));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Nodes", panForm);
		tabbedPane.addTab("VM", new JPanel());
		
		JPanel panTab = new JPanel();
		panTab.add(tabbedPane);
		
		

		
		
		JPanel panBut = new JPanel();
		
		this.getContentPane().add(panTop, BorderLayout.PAGE_START);
		this.getContentPane().add(panTab, BorderLayout.CENTER);
		this.getContentPane().add(panBut, BorderLayout.EAST);
		
		
	}
	
	/**
	 * <p>This method allows to define a placement on a grid for a component.</p>
	 * @param x column for the component
	 * @param y line for the component
	 * @param width width for the cell, can be assimilated to a weight.
	 * @return the placement.
	 */
	private GridBagConstraints getGridBagConstraints (int x, int y, int width) {
		int padding = 5;
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = x;
		cs.gridy = y;
		cs.ipadx = padding;
		cs.ipady = padding;
		cs.gridwidth = width;
		cs.insets = new Insets(padding, padding, padding, padding);
		return cs;
	}

}
