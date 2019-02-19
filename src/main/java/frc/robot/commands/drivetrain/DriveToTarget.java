package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
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

	public DriveToTarget(int pipeline, int camMode) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		requires(Robot.vision);
		// requires(Robot.arm);

		this.pipeline = pipeline;
		this.camMode = camMode;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.vision.setPipeline(pipeline);
		Robot.vision.setCamMode(camMode);
		Robot.drivetrain.getTurnController().setSetpoint(Robot.navX.getYaw());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Right Encoder Positions", Robot.drivetrain.rightMotor1.getSelectedSensorPosition());
		Robot.drivetrain.arcadeDrive(OI.driverController.getY(Hand.kLeft), -(Robot.drivetrain.getTurnController().getError() / 45));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// return Robot.vision.getDistance() < 10;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.resetSpeed();
		Robot.vision.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.resetSpeed();
		Robot.vision.stop();
	}
}