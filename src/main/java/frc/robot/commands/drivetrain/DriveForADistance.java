// package frc.robot.commands.drivetrain;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.Timer;
// import frc.robot.Robot;

// /**
//  * Makes the drivetrain drive straight for a distance in inches
//  */
// public class DriveForADistance extends Command {
// 	private double distance, speed;

// 	Timer timer; 
// 	public DriveForADistance() {
// 		// Use requires() here to declare subsystem dependencies
// 		requires(Robot.drivetrain);
// 		Robot.drivetrain.leftEncoder.reset();
// 	}

// 	// Called just before this Command runs the first time
// 	protected void initialize() {
// 		Robot.drivetrain.leftEncoder.reset();
// 		// PID Controller Setting the setpoint which
// 		// just returns the setpoint
// 		timer.start();
// 	}

// 	// Called repeatedly when this Command is scheduled to run
// 	protected void execute() {
// 		Robot.drivetrain.setTalons(0.9, 0.9);
// 		// Sets the setpoint at which how far you want the robot to go, it will
// 		// manipulate the output whenever it needs to
// 		SmartDashboard.putNumber("Time", timer.get());
// 	}

// 	protected boolean isFinished() {
// 		return false;
	    
// 	}

// 	// Called once after isFinished returns true
// 	protected void end() {
// 		Robot.drivetrain.setUsingDistancePID(false);
// 		Robot.drivetrain.setUsingTurnPID(false);
// 		Robot.drivetrain.stop();
// 		timer.stop();
// 		SmartDashboard.putNumber("Time", timer.get());
// 	}

// 	// Called when another command which requires one or more of the same
// 	// subsystems is scheduled to run
// 	protected void interrupted() {
// 		Robot.drivetrain.setUsingDistancePID(false);
// 		Robot.drivetrain.setUsingTurnPID(false);
// 		Robot.drivetrain.stop();
// 		timer.stop();
// 		SmartDashboard.putNumber("Time", timer.get());
// 	}
// }