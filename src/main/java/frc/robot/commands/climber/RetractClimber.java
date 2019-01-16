package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

/**
 * Makes the drivetrain drive straight for a distance in inches
 */
public class RetractClimber extends Command {

	public RetractClimber() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.climber);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.climber.set(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.climber.set(-1);
	}

	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.climber.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.climber.stop();
	}
}