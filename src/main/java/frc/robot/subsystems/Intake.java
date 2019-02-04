package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Spark motor = new Spark(RobotMap.intakeMotor);
	public DigitalInput sensor;
	// = new DigitalInput(RobotMap.photoElectric);
	public Counter counter;

	public Intake() {
		// counter = new Counter(sensor);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		motor.set(0);
	}

	public void set(double speed) {
		motor.set(speed);
	}
}