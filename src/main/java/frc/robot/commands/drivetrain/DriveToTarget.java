package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

// 	NAVX DEGREE ORIENTATION:
// 			 0
// 	    -45 \|/ +45
// 	  -90  --X--  +90
//	   -135 /|\ +135
//		  +/-180

public class DriveToTarget extends Command {
	int mode;
	int pipeline;
	public DriveToTarget(int pipeline, int mode) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);

		requires(Robot.vision);
		
		this.mode = mode;
		this.pipeline = pipeline;
		// requires(Robot.vision);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.vision.setCamMode(mode);
		Robot.vision.setPipeline(pipeline);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if (Robot.vision.getDistance() > 50) {
			double c = Robot.vision.getX() < 0 ? -0.35 : 0.35;
			Robot.drivetrain.arcadeDrive(0, (Robot.vision.getX() * 0.02) + c);
			// Robot.vision.getDistance() * -0.0035 - 0.35
		// }
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.vision.getDistance() < 40;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
		Robot.vision.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.stop();
		Robot.vision.stop();
	}
}