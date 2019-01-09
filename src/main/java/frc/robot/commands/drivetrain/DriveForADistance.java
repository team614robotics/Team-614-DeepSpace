package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

/**
 * Makes the drivetrain drive straight for a distance in inches
 */
public class DriveForADistance extends Command {
	private double distance, speed;

	public DriveForADistance(double distance, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		this.distance = distance;
		this.speed = speed;

		Robot.drivetrain.leftEncoder.reset();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.setUsingTurnPID(true);
		Robot.drivetrain.pid.setUsingDistancePID(true);
		Robot.drivetrain.getTurnController().setSetpoint(Robot.navX.getYaw());
		Robot.drivetrain.pid.getDistanceController().setSetpoint(distance);
		// PID Controller Setting the setpoint which
		// just returns the setpoint
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("PID Rotate Rate", Robot.drivetrain.getPIDRotateRate());
		SmartDashboard.putNumber("PID Speed", Robot.drivetrain.pid.getPIDSpeed());
		SmartDashboard.putNumber("navX Yaw", Robot.navX.getYaw());
		Robot.drivetrain.arcadeDrive(speed * Robot.drivetrain.pid.getPIDSpeed(), Robot.drivetrain.getPIDRotateRate());
		// Sets the setpoint at which how far you want the robot to go, it will
		// manipulate the output whenever it needs to
	}

	protected boolean isFinished() {
		return timeSinceInitialized() > 0.2 && Robot.drivetrain.leftEncoder.getRate() < 1
				&& Robot.drivetrain.leftEncoder.getRate() > -1;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.pid.setUsingDistancePID(false);
		Robot.drivetrain.setUsingTurnPID(false);
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.pid.setUsingDistancePID(false);
		Robot.drivetrain.setUsingTurnPID(false);
		Robot.drivetrain.stop();
	}
}