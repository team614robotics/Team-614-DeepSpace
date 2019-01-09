package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 *
 */
public class Pneumatics extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Compressor compressor;
	public DoubleSolenoid loaderPiston;
	public DoubleSolenoid intakePiston;

	public Pneumatics() {
		compressor = new Compressor(RobotMap.compressor);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CompressorControl());
	}
	
	public DoubleSolenoid.Value getLoaderState() {
		return loaderPiston.get();
	}
	
	public void setLoaderState(DoubleSolenoid.Value state) {
		loaderPiston.set(state);
	}

	public DoubleSolenoid.Value getIntakeState() {
		return intakePiston.get();
	}

	public void setIntakeState(DoubleSolenoid.Value state) {
		intakePiston.set(state);
	}
}