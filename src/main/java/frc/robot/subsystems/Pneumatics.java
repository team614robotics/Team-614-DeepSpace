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
	public DoubleSolenoid umbrellaPiston;

	public Pneumatics() {
		compressor = new Compressor(RobotMap.compressor);
		umbrellaPiston = new DoubleSolenoid(RobotMap.umbrellaPistonA, RobotMap.umbrellaPistonB);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CompressorControl());
	}

	public DoubleSolenoid.Value getUmbrellaState() {
		return umbrellaPiston.get();
	}

	public void setUmbrellaState(DoubleSolenoid.Value state) {
		umbrellaPiston.set(state);
	}
}