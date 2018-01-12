package tests;

import javax.swing.JFrame;

import org.usfirst.frc.team95.robot.components.DrivePod;

import mocks.MockMotorController;
import mocks.MockMotorController;
import mocks.MockSolenoid;

public class DrivePodTester extends JFrame implements MockSolenoid.Listener, MockMotorController.Listener {
	private static final long serialVersionUID = 6749988287158526368L;
	
	private DrivePod uut;
	private MockMotorController leader, follower1, follower2;
	private MockSolenoid shifter;
	
	private double lastLeaderThrottle;
	private boolean lastShifterGear;
	
	public DrivePodTester(String title) {
		super(title);
		leader = new MockMotorController(this);
		follower1 = new MockMotorController(this);
		follower2 = new MockMotorController(this);
		shifter = new MockSolenoid(this);
		
		uut = new DrivePod("UUT", leader, follower1, follower2, shifter);
	}
	
	public void runDrivePodTest() {
		
	}
	
	@Override
	public void takeSet(MockSolenoid source, boolean on) {
		 lastShifterGear = on;
	}

	@Override
	public void takeSet(MockMotorController source, double set) {
		if(source == leader) {
			lastLeaderThrottle = set;
		}
	}

	@Override
	public void takeSpeed(MockMotorController source, double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takePosition(MockMotorController source, double pos) {
		// TODO Auto-generated method stub
		
	}

}
