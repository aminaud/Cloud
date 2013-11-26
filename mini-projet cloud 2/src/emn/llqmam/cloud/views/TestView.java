package emn.llqmam.cloud.views;

import java.util.ArrayList;
import java.util.List;

import emn.llqmam.cloud.application.ApplicationFactory;
import emn.llqmam.cloud.data.Vm;
import emn.llqmam.cloud.views.components.ApplicationFrame;


public class TestView {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ConnectDialog connectDialog = new ConnectDialog(null, "Application Name", ApplicationFactory.getNewApplication());
//		connectDialog.showDialog();
		
		List<Vm> listvm = new ArrayList<>();
		
		
		
		ApplicationFrame frame = new ApplicationFrame(ApplicationFactory.getNewApplication(), "1.2.3", listvm, null);
		frame.setVisible(true);
	}

}
