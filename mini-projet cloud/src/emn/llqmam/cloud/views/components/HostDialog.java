package emn.llqmam.cloud.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opennebula.client.host.Host;

import emn.llqmam.cloud.data.Vm;

/**
 * <p>
 * This class allows to build a dialog where an user can enter his connection
 * information.
 * </p>
 * 
 * @author AM
 * @version 1.0 beta
 */

public class HostDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Host host;

	/**
	 * <p>
	 * This constructor allows to build this dialog.
	 * </p>
	 * 
	 * @param parent
	 *            the parent frame.
	 * @param name
	 *            the name for this dialog view.
	 * @param application
	 *            the main controller of this application.
	 */
	public HostDialog(Frame parent, String name, Host host) {
		super(parent, name, false);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.host = host;
		this.initComponent();
	}

	/**
	 * <p>
	 * This method allows to initialize the dialog view.
	 * </p>
	 */
	private void initComponent() {

		JLabel lbName = new JLabel("Name");
		JLabel tfName = new JLabel(host.getName());
		
		JLabel lbState = new JLabel("State");
		JLabel tfState = new JLabel(host.stateStr());
		
		JLabel lbHyper = new JLabel("Hypervisor");
		JLabel tfHyper = new JLabel(host.xpath("TEMPLATE/HYPERVISOR"));
		
		JLabel lbTotMem = new JLabel("Total memory");
		JLabel tfTotMem = new JLabel(host.xpath("TEMPLATE/TOTALMEMORY"));
		
		JLabel lbUsedMem = new JLabel("Used memory");
		JLabel tfUsedMem = new JLabel(host.xpath("TEMPLATE/USEDMEMORY"));
		
		JLabel lbFreeMem = new JLabel("Free memory");
		JLabel tfFreeMem = new JLabel(host.xpath("TEMPLATE/FREEMEMORY"));
		
		JLabel lbTotUC = new JLabel("Total UC");
		JLabel tfTotUC = new JLabel(host.xpath("TEMPLATE/TOTALCPU"));
		
		JLabel lbUsedUC = new JLabel("Used UC");
		JLabel tfUsedUC = new JLabel(host.xpath("TEMPLATE/USEDCPU"));
		
		JLabel lbFreeUC = new JLabel("Free UC");
		JLabel tfFreeUC = new JLabel(host.xpath("TEMPLATE/FREECPU"));
		
		// panel for the form
		JPanel panForm = new JPanel(new GridBagLayout());
		panForm.setBackground(Colors.BACKGROUND);
		panForm.setPreferredSize(new Dimension(320, 300));
		panForm.add(lbName, getGridBagConstraints(0, 0, 1));
		panForm.add(tfName, getGridBagConstraints(1, 0, 2));
		panForm.add(lbState, getGridBagConstraints(0, 1, 1));
		panForm.add(tfState, getGridBagConstraints(1, 1, 2));
		panForm.add(lbHyper, getGridBagConstraints(0, 2, 1));
		panForm.add(tfHyper, getGridBagConstraints(1, 2, 2));
		panForm.add(lbTotMem, getGridBagConstraints(0, 3, 1));
		panForm.add(tfTotMem, getGridBagConstraints(1, 3, 2));
		panForm.add(lbUsedMem, getGridBagConstraints(0, 4, 1));
		panForm.add(tfUsedMem, getGridBagConstraints(1, 4, 2));
		panForm.add(lbFreeMem, getGridBagConstraints(0, 5, 1));
		panForm.add(tfFreeMem, getGridBagConstraints(1, 5, 2));
		panForm.add(lbTotUC, getGridBagConstraints(0, 6, 1));
		panForm.add(tfTotUC, getGridBagConstraints(1, 6, 2));
		panForm.add(lbUsedUC, getGridBagConstraints(0, 7, 1));
		panForm.add(tfUsedUC, getGridBagConstraints(1, 7, 2));
		panForm.add(lbFreeUC, getGridBagConstraints(0, 8, 1));
		panForm.add(tfFreeUC, getGridBagConstraints(1, 8, 2));
		
		
		// top panel inside
		JPanel panTopInside = new JPanel();
		panTopInside.setBackground(Colors.BACKGROUND);
		panTopInside.setLayout(new BorderLayout());
		panTopInside.setBorder(BorderFactory.createTitledBorder("Host: " + host.getName()));
		// we add icon & form
		panTopInside.add(panForm, BorderLayout.CENTER);

		// top panel outside
		JPanel panTopOutside = new JPanel();
		panTopOutside.setBackground(Colors.BACKGROUND);
		panTopOutside.setLayout(new BorderLayout());
		panTopOutside.setBorder(new EmptyBorder(10, 20, 10, 20)); // top, left, bottom, right
		
		// we add top panel inside
		panTopOutside.add(panTopInside, BorderLayout.CENTER);

		JButton btnOk = new JButton("Ok");
		btnOk.setBackground(Colors.FOCUS);
		btnOk.setForeground(Colors.FOCUS_TEXT);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDialog(false);
			}
		});

		// panel for buttons
		JPanel panButtons = new JPanel();
		panButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
		panButtons.add(btnOk);

		// we add panels to the view
		getContentPane().add(panTopOutside, BorderLayout.CENTER);
		getContentPane().add(panButtons, BorderLayout.PAGE_END);

		// we ask to the dialog to take size and some other parameters since its
		// content.
		pack();
	}

	/**
	 * <p>
	 * This method allows to define a placement on a grid for a component.
	 * </p>
	 * 
	 * @param x
	 *            column for the component
	 * @param y
	 *            line for the component
	 * @param width
	 *            width for the cell, can be assimilated to a weight.
	 * @return the placement.
	 */
	private GridBagConstraints getGridBagConstraints(int x, int y, int width) {
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

	public void showDialog(boolean show) {
		this.setVisible(show);
	}
}