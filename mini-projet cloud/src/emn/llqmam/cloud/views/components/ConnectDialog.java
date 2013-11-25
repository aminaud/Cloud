package emn.llqmam.cloud.views.components;


import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.views.listeners.ConnectListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * <p>This class allows to build a dialog where an user can enter his connection information.</p>
 * 
 * @author AM
 * @version 1.0 beta
 */

public class ConnectDialog extends JDialog {

	/**
	 * <p>The label to ask the user number.</p>
	 */
	private JLabel lbUsername;
	/**
	 * <p>The label to ask the user alias.</p>
	 */
	private JLabel lbPassword;
	/**
	 * <p>The field to put the user number.</p>
	 */
	private JTextField tfUsername;
	/**
	 * <p>The field to put the user alias.</p>
	 */
	private JTextField tfPassword;
	/**
	 * <p>The field to know if the user wants we keep his information.</p>
	 */
	private JCheckBox cbKeeping;
	/**
	 * <p>The button to log on.</p>
	 */
	private JButton btnLogin;
	/**
	 * <p>The button to come back.</p>
	 */
	private JButton btnCancel;
	/**
	 * <p>The main controller of the application.</p>
	 */
	private IApplication application;

	/**
	 * <p>This constructor allows to build this dialog.</p>
	 * @param parent the parent frame.
	 * @param name the name for this dialog view.
	 * @param clientApp the main controller of this application.
	 */
	public ConnectDialog(Frame parent, String name, IApplication clientApp) {
		super(parent, name, false);
		this.application = clientApp;
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}
	
	/**
	 * <p>This method allows to initialize the dialog view.</p>
	 */
	private void initComponent() {
		
		// panel for the image
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Colors.BACKGROUND);
		panIcon.setLayout(new BorderLayout());
		panIcon.setPreferredSize(new Dimension(96, 62)); // width, height
		panIcon.add(new JLabel(new ImageIcon("resources/logo.png")));
		
		// the form part for the username.
		lbUsername = new JLabel("Username");
		tfUsername = new JTextField(20);

		// the form part for the password.
		lbPassword = new JLabel("Password");
		tfPassword = new JTextField(20);
		
		// panel for the form
		JPanel panForm = new JPanel(new GridBagLayout());
		panForm.setBackground(Colors.BACKGROUND);
		panForm.setPreferredSize(new Dimension(320, 130));
		panForm.add(lbUsername, getGridBagConstraints(0, 0, 1));
		panForm.add(tfUsername, getGridBagConstraints(1, 0, 2));
		panForm.add(lbPassword, getGridBagConstraints(0, 1, 1));
		panForm.add(tfPassword, getGridBagConstraints(1, 1, 2));
		
		// checkbox
		cbKeeping = new JCheckBox();
		cbKeeping.setText("Remember my information");
		cbKeeping.setBackground(Colors.BACKGROUND);
		boolean keeping = false;
		cbKeeping.setSelected(keeping);
		cbKeeping.setEnabled(false);
		panForm.add(cbKeeping, getGridBagConstraints(0, 2, 3));
		
		// top panel inside
		JPanel panTopInside = new JPanel();
		panTopInside.setBackground(Colors.BACKGROUND);
		panTopInside.setLayout(new BorderLayout());
		panTopInside.setBorder(BorderFactory.createTitledBorder("Connection on client"));
		// we add icon & form
		panTopInside.add(panIcon, BorderLayout.WEST);
		panTopInside.add(panForm, BorderLayout.CENTER);
		
		// top panel outside
		JPanel panTopOutside = new JPanel();
		panTopOutside.setBackground(Colors.BACKGROUND);
		panTopOutside.setLayout(new BorderLayout());
		panTopOutside.setBorder(new EmptyBorder(10, 20, 10, 20)); // top, left, bottom, right
		// we add top panel inside
		panTopOutside.add(panTopInside, BorderLayout.CENTER);
		
		
		// buttons
		btnLogin = new JButton("Connect");
		btnLogin.setBackground(Colors.FOCUS);
		btnLogin.setForeground(Colors.FOCUS_TEXT);
		btnLogin.addActionListener(new ConnectListener(this, application));

		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Colors.NO_FOCUS);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.finish();
			}
		});
		
		// panel for buttons
		JPanel panButtons = new JPanel();
		panButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
		panButtons.add(btnCancel);
		panButtons.add(btnLogin);

		// we add panels to the view
		getContentPane().add(panTopOutside, BorderLayout.CENTER);
		getContentPane().add(panButtons, BorderLayout.PAGE_END);

		// we ask to the dialog to take size and some other parameters since its content.
		pack();
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
	
	/**
	 * <p>To know the user entry for the password.</p>
	 * @return the user entry for the password.
	 */
	public String getPassword() {
		return tfPassword.getText();
	}
	
	/**
	 * <p>To know the user entry for the username.</p>
	 * @return the user entry for the username.
	 */
	public String getUsername() {
		return tfUsername.getText();
	}

	public void showDialog(boolean show) {
		this.setVisible(show);
	}
}