package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Vision;

/**
 *
 */
public class ToggleCamera extends Command {
	public ToggleCamera() {
		requires(Robot.lowerLimelight);
		requires(Robot.upperLimelight);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Vision.upper) {
			Robot.upperLimelight.closeStream();
			Robot.lowerLimelight.openStream();
			Vision.upper = false;
		} else {
			Robot.lowerLimelight.closeStream();
			Robot.upperLimelight.openStream();
			Vision.upper = true;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}