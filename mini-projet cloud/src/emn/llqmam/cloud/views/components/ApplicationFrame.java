package emn.llqmam.cloud.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sun.awt.VerticalBagLayout;
import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.data.Node;
import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.views.listeners.ConnectListener;
import emn.llqmam.cloud.views.listeners.DisconnectListener;
import emn.llqmam.cloud.views.listeners.SuspendListener;


public class ApplicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IApplication application;


	public ApplicationFrame (IApplication application, String versionOpenNebula) {
		this.setTitle("Our Application");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents(versionOpenNebula);
		this.setBackground(Colors.BACKGROUND);
		this.application = application;
	}


	private void initComponents(String versionON) {

		
		
		JLabel opennebula = new JLabel("You are connected on Opennebula version " + versionON);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBackground(Colors.NO_FOCUS);
		btnDisconnect.addActionListener(new DisconnectListener(this, application));
		
		JPanel panTop = new JPanel(new BorderLayout());
		panTop.setBorder(new EmptyBorder(10, 20, 10, 20));
		panTop.add(opennebula, BorderLayout.CENTER);
		panTop.add(btnDisconnect, BorderLayout.EAST);

		// http://docs.oracle.com/javase/tutorial/uiswing/components/tree.html

		JTabbedPane tabbedPane = new JTabbedPane();
		JList<Node> listNodes = new JList<>();
		JList<Vm> listVM = new JList<>();
//		listVM.add
		tabbedPane.addTab("Nodes", listNodes);
		tabbedPane.addTab("VM", listVM);

		JPanel panTab = new JPanel();
		panTab.add(tabbedPane);

		// buttons
		JButton btnSuspend = new JButton("Suspend");
		btnSuspend.setBackground(Colors.NO_FOCUS);
		btnSuspend.addActionListener(new SuspendListener(this, application));

		JButton btnResume = new JButton("Resume");
		btnResume.setBackground(Colors.NO_FOCUS);
		btnResume.addActionListener(new SuspendListener(this, application));

		JButton btnMigrate = new JButton("Migrate");
		btnMigrate.setBackground(Colors.NO_FOCUS);
		btnMigrate.addActionListener(new SuspendListener(this, application));

		JButton btnRemove = new JButton("Remove");
		btnRemove.setBackground(Colors.NO_FOCUS);
		btnRemove.addActionListener(new SuspendListener(this, application));
		JPanel buttons = new JPanel(new GridBagLayout());

		int padding = 5;
		buttons.add(btnSuspend, getGridBagConstraints(0, 0, 1, padding));
		buttons.add(btnResume, getGridBagConstraints(0, 1, 1, padding));
		buttons.add(btnMigrate, getGridBagConstraints(0, 2, 1, padding));
		buttons.add(btnRemove, getGridBagConstraints(0, 3, 1, padding));
		
		JPanel panBut = new JPanel();
		panBut.add(buttons);

		// adding of all panels of the frame
		this.getContentPane().add(panTop, BorderLayout.NORTH);
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
	private GridBagConstraints getGridBagConstraints (int x, int y, int width, int padding) {
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		cs.gridx = x;
		cs.gridy = y;
		cs.ipadx = 0;
		cs.ipady = padding;
		cs.gridwidth = width;
		cs.insets = new Insets(padding, padding, padding, padding);
		return cs;
	}
	
	public void showFrame(boolean show) {
		this.setVisible(show);
	}

}