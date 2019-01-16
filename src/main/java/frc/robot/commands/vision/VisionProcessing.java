/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

/**
 *
 */
public class VisionProcessing extends Command {
	public VisionProcessing() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.vision);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Limelight X", Robot.vision.getX());
		SmartDashboard.putNumber("Limelight Y", Robot.vision.getY());
		SmartDashboard.putNumber("Limelight Area", Robot.vision.getArea());
		SmartDashboard.putNumber("Limelight Distance", Robot.vision.getDistance());

		// if (Robot.vision.getDistance() > 50) {
		// 	Robot.vision.setLED(2.0);
		// } else {
		// 	Robot.vision.setLED(0.0);
		// }
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}