package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PIDCompanion extends Subsystem implements PIDOutput {
	private PIDController distanceController;
	private double PIDdistanceSpeed;
	private boolean usingDistancePID;

	/* The following PID Controller coefficients will need to be tuned */
	/* to match the dynamics of your drive system. Note that the */
	/* SmartDashboard in Test mode has support for helping you tune */
	/* controllers by displaying a form where you can enter new P, I, */
	/* and D constants and test the mechanism. */

	private final double distanceTolerance = 0.1f;

	public PIDCompanion() {
		usingDistancePID = false;

		distanceController = new PIDController(RobotMap.drivetrainDistanceP, RobotMap.drivetrainDistanceI, RobotMap.drivetrainDistanceD, RobotMap.drivetrainDistanceF, Robot.drivetrain.leftEncoder, this);
		distanceController.setOutputRange(-1.0, 1.0);
		distanceController.setAbsoluteTolerance(distanceTolerance);

		/* Add the PID Controller to the Test-mode dashboard, allowing manual */
		/* tuning of the Turn Controller's P, I and D coefficients. */
		/* Typically, only the P value needs to be modified. */
		LiveWindow.addActuator("Drivetrain", "DistanceController", distanceController);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setUsingDistancePID(boolean set) {
		usingDistancePID = true;
		if (usingDistancePID) {
			distanceController.enable();
		} else {
			distanceController.disable();
		}
	}

	public boolean getUsingDistancePID() {
		return usingDistancePID;
	}

	public double getPIDSpeed() {
		return PIDdistanceSpeed;
	}

	public PIDController getDistanceController() {
		return distanceController;
	}

	public void pidWrite(double output) {
		if (usingDistancePID) {
			PIDdistanceSpeed = output;
		}
	}
}