package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

/**
 * Makes the drivetrain drive straight for a distance in inches
 */
public class MoveElevator extends Command {
	private double distance;

	public MoveElevator(double distance, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.elevator);
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.companion.setUsingPID(true);
		Robot.elevator.companion.getController().setSetpoint(distance);
		Robot.elevator.setManual(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Elevator PID Speed", Robot.elevator.companion.getPIDSpeed());
		Robot.elevator.set(Robot.elevator.companion.getPIDSpeed());
	}

	protected boolean isFinished() {
		return timeSinceInitialized() > 0.2 && Robot.elevator.encoder.getRate() < 0.5
				&& Robot.elevator.encoder.getRate() > -0.5;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.companion.setUsingPID(false);
		Robot.elevator.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.companion.setUsingPID(false);
		Robot.elevator.stop();
	}
}