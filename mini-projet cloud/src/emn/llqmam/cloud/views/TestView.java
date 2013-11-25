package emn.llqmam.cloud.views;

import emn.llqmam.cloud.views.components.ApplicationFrame;


public class TestView {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ConnectDialog connectDialog = new ConnectDialog(null, "Application Name", ApplicationFactory.getNewApplication());
//		connectDialog.showDialog();
		
		ApplicationFrame frame = new ApplicationFrame();
		frame.setVisible(true);
	}

}
