package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

/**
 * Makes the drivetrain drive straight for a distance in inches
 */
public class MoveArm extends Command {
	private double distance;

	public MoveArm(double distance, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.arm);
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.companion.setUsingPID(true);
		Robot.arm.companion.getController().setSetpoint(distance);
		Robot.arm.setManual(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Elevator PID Speed", Robot.arm.companion.getPIDSpeed());
		Robot.arm.set(Robot.arm.companion.getPIDSpeed());
	}

	protected boolean isFinished() {
		return timeSinceInitialized() > 0.2 && Robot.arm.encoder.getRate() < 0.5
				&& Robot.arm.encoder.getRate() > -0.5;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.companion.setUsingPID(false);
		Robot.arm.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.arm.companion.setUsingPID(false);
		Robot.arm.stop();
	}
}