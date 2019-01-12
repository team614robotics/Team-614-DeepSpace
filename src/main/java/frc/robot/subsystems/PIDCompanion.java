package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class PIDCompanion extends Subsystem implements PIDOutput {
	private PIDController controller;

	private double speed;
	private boolean usingPID;

	private final double tolerance = 0.1f;

	public PIDCompanion(double Kp, double Ki, double Kd, double Kf, PIDSource source, String subsystem, String name) {
		usingPID = false;

		controller = new PIDController(Kp, Ki, Kd, Kf, source, this);

		controller.setOutputRange(-1.0, 1.0);
		controller.setAbsoluteTolerance(tolerance);
		// controller.setContinuous(true);

		LiveWindow.addActuator(subsystem, name, controller);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setUsingPID(boolean set) {
		usingPID = set;
		if (usingPID) {
			controller.enable();
		} else {
			controller.disable();
		}
	}

	public boolean getUsingPID() {
		return usingPID;
	}

	public double getPIDSpeed() {
		return speed;
	}

	public PIDController getController() {
		return controller;
	}

	public void pidWrite(double output) {
		if (usingPID) {
			speed = output;
		}
	}
}