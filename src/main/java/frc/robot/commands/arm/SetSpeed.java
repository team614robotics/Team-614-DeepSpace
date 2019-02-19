/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetSpeed extends Command {
	public SetSpeed() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if(OI.operatorController.getY(Hand.kRight) == 0) {
		// 	Robot.pneumatics.bikebrakePiston.set(RobotMap.PistonIn);
		// }
		// else {
		//     Robot.pneumatics.bikebrakePiston.set(RobotMap.PistonOut);
		// }
		if(-OI.driverController.getTriggerAxis(Hand.kRight) + OI.driverController.getTriggerAxis(Hand.kLeft) > 0) {
			Robot.pneumatics.bikebrakePiston.set(RobotMap.PistonIn);
		}
		Robot.arm.set(-OI.driverController.getTriggerAxis(Hand.kRight) + OI.driverController.getTriggerAxis(Hand.kLeft), 0);
		
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