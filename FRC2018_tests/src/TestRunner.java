
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import tests.AdjustedTalonTester;
import tests.DrivePodTester;

public class TestRunner {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
//			AdjustedTalonTester test1 = new AdjustedTalonTester("Adjusted Talon test");
//			test1.runThrottleTest();
//			test1.setSize(1000, 1000);
//			test1.setLocationRelativeTo(null);
//			test1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//			test1.setVisible(true);
//
//			AdjustedTalonTester test2 = new AdjustedTalonTester("Adjusted Talon test");
//			test2.runVoltageTest();
//			test2.setSize(1000, 1000);
//			test2.setLocationRelativeTo(null);
//			test2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//			test2.setVisible(true);
			
			DrivePodTester test3 = new DrivePodTester("DrivePod test");
			test3.runDrivePodTest();
			test3.setSize(1000, 1000);
			test3.setLocationRelativeTo(null);
			test3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			test3.setVisible(true);
		});
	}
}
