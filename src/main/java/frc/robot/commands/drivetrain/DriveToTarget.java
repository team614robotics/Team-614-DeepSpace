package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Vision;

// 	NAVX DEGREE ORIENTATION:
// 			 0
// 	    -45 \|/ +45
// 	  -90  --X--  +90
//	   -135 /|\ +135
//		  +/-180

public class DriveToTarget extends Command {
	private int pipeline;
	private int camMode;
	private Vision vision;

	public DriveToTarget(int pipeline, int camMode) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		// requires(Robot.arm);

		if (Robot.arm.isUp()) {
			requires(Robot.lowerLimelight);
			vision = Robot.lowerLimelight;
		} else {
			requires(Robot.upperLimelight);
			vision = Robot.upperLimelight;
		}

		this.pipeline = pipeline;
		this.camMode = camMode;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		vision.setPipeline(pipeline);
		vision.setCamMode(camMode);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double c = vision.getX() < 0 ? -0.35 : 0.35;
		// Robot.vision.getDistance() * -0.0035 - 0.35
		Robot.drivetrain.arcadeDrive(0.45, (vision.getX() * 0.02) + c);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return vision.getDistance() < 40;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop();
		vision.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.stop();
		vision.stop();
	}
}