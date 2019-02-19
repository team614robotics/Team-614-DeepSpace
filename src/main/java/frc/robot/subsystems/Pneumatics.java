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

	public DoubleSolenoid bikebrakePiston;

	public DoubleSolenoid climberPistonA;
	public DoubleSolenoid climberPistonB;

	public DoubleSolenoid umbrellaPiston;

	public Pneumatics() {
		compressor = new Compressor(RobotMap.compressor);
		climberPistonA = new DoubleSolenoid(RobotMap.solenoidPort1A, RobotMap.solenoidPort2A);
		climberPistonB = new DoubleSolenoid(RobotMap.solenoidPort1B, RobotMap.solenoidPort2B);
		bikebrakePiston = new DoubleSolenoid(4, 5);
		umbrellaPiston = new DoubleSolenoid(RobotMap.solenoidPort5, RobotMap.solenoidPort6);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new CompressorControl());
	}

	public DoubleSolenoid.Value getClimberState() {
		return climberPistonA.get();
	}
	public DoubleSolenoid.Value getBikebrakeState() {
		return bikebrakePiston.get();
	}
	public DoubleSolenoid.Value getUmbrellaState() {
		return umbrellaPiston.get();
	}

	public void setClimberState(DoubleSolenoid.Value state) {
		climberPistonA.set(state);
		climberPistonB.set(state);
	}
	public void setBikebrakeState(DoubleSolenoid.Value state) {
		bikebrakePiston.set(state);
	}
	public void setUmbrellaState(DoubleSolenoid.Value state) {
		umbrellaPiston.set(state);
	}
}