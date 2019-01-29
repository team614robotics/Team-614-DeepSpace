/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Align extends Command {
	
	private double SteeringAdjust; 
	
	public Align() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drivetrain.TankDrive(0.0, 0.0);
		SteeringAdjust = Robot.vision.calcSteeringAdjust();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivetrain.TankDrive(SteeringAdjust, -SteeringAdjust);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.TankDrive(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.drivetrain.TankDrive(0.0, 0.0);
	}
}
