package emn.llqmam.cloud.views.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.application.IApplication;
import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.views.listeners.DisconnectListener;
import emn.llqmam.cloud.views.listeners.SuspendListener;


public class ApplicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IApplication application;
	
	private JList<Vm> jlistVM;
	
	public JList<Vm> getJlistVM() {
		return jlistVM;
	}


	public JList<Host> getJlistHosts() {
		return jlistHosts;
	}

	private JList<Host> jlistHosts;


	public ApplicationFrame (IApplication application, String versionOpenNebula, List<Vm> listVM, List<Host> listNode) {
		this.setTitle("Our Application");
		this.setSize(700, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents(versionOpenNebula, listVM, listNode);
		this.setBackground(Colors.BACKGROUND);
		this.application = application;
	}


	private void initComponents(String versionON, List<Vm> listVM, List<Host> listHosts) {
		JLabel opennebula = new JLabel("You are connected on Opennebula version " + versionON + " as " + application.getName());
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBackground(Colors.NO_FOCUS);
		btnDisconnect.addActionListener(new DisconnectListener(this, application));
		
		JPanel panTop = new JPanel(new BorderLayout());
		panTop.setBorder(new EmptyBorder(10, 20, 10, 20));
		panTop.add(opennebula, BorderLayout.CENTER);
		panTop.add(btnDisconnect, BorderLayout.EAST);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(500, 400)); // width, height
		
		// Affichage des hosts
		int nbHosts;
		if (listHosts != null) {
			Host[] arrHost = new Host[0];
			jlistHosts = new JList<>(listHosts.toArray(arrHost));
			nbHosts = listHosts.size();
		}
		else {
			jlistHosts = new JList<>();
			nbHosts = 0;
		}
		jlistHosts.setCellRenderer(new HostCellRenderer());
		
		MouseListener HostMouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 2) {
		        	 // TODO
		             int index = jlistHosts.locationToIndex(e.getPoint());
		             System.out.println("Double clicked on Item " + index);
		          }
		     }
		 };
		 jlistHosts.addMouseListener(HostMouseListener);
		
		JScrollPane scrollListHosts = new JScrollPane(jlistHosts);
		tabbedPane.addTab("Hosts (" + nbHosts + ")", scrollListHosts);
		
		// affichage des VM
		
		int nbVM;
		if (listVM != null) {
			Vm[] arrVm = new Vm[0];
			jlistVM = new JList<>(listVM.toArray(arrVm));
			nbVM = listVM.size();
		}
		else {
			jlistVM = new JList<>();
			nbVM = 0;
		}
		jlistVM.setCellRenderer(new VMCellRenderer());
		
		MouseListener VMMouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 2) {
		             Vm vm = jlistVM.getSelectedValue();
		             VMDialog dialog = new VMDialog(null, "VM Information", vm);
		             dialog.showDialog(true);
		          }
		     }
		 };
		 jlistVM.addMouseListener(VMMouseListener);
		
		
		JScrollPane scrollListVM = new JScrollPane(jlistVM);
		tabbedPane.addTab("VM (" + nbVM + ")", scrollListVM);

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

	private class VMCellRenderer extends JLabel implements ListCellRenderer<Vm> {
	     public VMCellRenderer() {
	         setOpaque(true);
	     }
	     public Component getListCellRendererComponent(JList<? extends Vm> list,
	                                                   Vm value,  int index,
	                                                   boolean isSelected,  boolean cellHasFocus) {
	         setText(value.get_name());
	         
	         if (isSelected) {
	             setBackground(list.getSelectionBackground());
	             setForeground(list.getSelectionForeground());
	         } else {
	             setBackground(list.getBackground());
	             setForeground(list.getForeground());
	         }
	         setEnabled(list.isEnabled());
	         setFont(list.getFont());
	         setOpaque(true);
	         
	         return this;
	     }
	 }
	
	private class HostCellRenderer extends JLabel implements ListCellRenderer<Host> {
	     public HostCellRenderer() {
	         setOpaque(true);
	     }
	     public Component getListCellRendererComponent(JList<? extends Host> list,
	                                                   Host value,  int index,
	                                                   boolean isSelected,  boolean cellHasFocus) {
	         setText(value.getName());
	         return this;
	     }
	 }
}