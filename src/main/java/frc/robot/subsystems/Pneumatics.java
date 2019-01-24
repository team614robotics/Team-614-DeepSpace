package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

/**
 *
 */
public class Pneumatics extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Compressor compressor;
	public DoubleSolenoid grabberPiston;

	public Pneumatics() {
		compressor = new Compressor(RobotMap.compressor);
		grabberPiston = new DoubleSolenoid(RobotMap.grabberPistonA, RobotMap.grabberPistonB);
		grabberPiston.set(RobotMap.PistonIn);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CompressorControl());
	}

	public DoubleSolenoid.Value getGrabberState() {
		return grabberPiston.get();
	}

	public void setGrabberState(DoubleSolenoid.Value state) {
		grabberPiston.set(state);
	}
}