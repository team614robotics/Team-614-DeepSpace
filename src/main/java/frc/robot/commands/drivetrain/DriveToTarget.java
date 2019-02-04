package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

// 	NAVX DEGREE ORIENTATION:
// 			 0
// 	    -45 \|/ +45
// 	  -90  --X--  +90
//	   -135 /|\ +135
//		  +/-180

public class DriveToTarget extends Command {
	public DriveToTarget() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		requires(Robot.vision);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.vision.setPipeline(0);
		Robot.vision.setCamMode(0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double c = Robot.vision.getX() < 0 ? -0.35 : 0.35;
		//Robot.vision.getDistance() * -0.0035
		Robot.drivetrain.arcadeDrive( - 0.35, (Robot.vision.getX() * 0.02) + c);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.vision.getDistance() < 40;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.stop();
	}
}