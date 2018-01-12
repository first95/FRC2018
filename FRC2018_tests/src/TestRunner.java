import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import tests.AdjustedTalonTester;

public class TestRunner {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AdjustedTalonTester example1 = new AdjustedTalonTester("Adjusted Talon test");
			example1.runThrottleTest();
			example1.setSize(1000, 1000);
			example1.setLocationRelativeTo(null);
			example1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example1.setVisible(true);

			AdjustedTalonTester example2 = new AdjustedTalonTester("Adjusted Talon test");
			example2.runVoltageTest();
			example2.setSize(1000, 1000);
			example2.setLocationRelativeTo(null);
			example2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example2.setVisible(true);
		});
	}
}
