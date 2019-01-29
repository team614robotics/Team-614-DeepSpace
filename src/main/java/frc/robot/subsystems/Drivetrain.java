/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.TankDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public PIDCompanion distanceCompanion;
	public PIDCompanion turnCompanion;

	public DifferentialDrive drivetrain;

	public Encoder leftEncoder;

	public VictorSP leftMotorA = new VictorSP(RobotMap.leftMotorA);
	public VictorSP leftMotorB = new VictorSP(RobotMap.leftMotorB);
	public VictorSP rightMotorA = new VictorSP(RobotMap.rightMotorA);
	public VictorSP rightMotorB = new VictorSP(RobotMap.rightMotorB);

	public SpeedController leftMotors = new SpeedControllerGroup(leftMotorA, leftMotorB);
	public SpeedController rightMotors = new SpeedControllerGroup(rightMotorA, rightMotorB);
	

	public Drivetrain() {
		drivetrain = new DifferentialDrive(leftMotors, rightMotors);
		leftEncoder = new Encoder(RobotMap.drivetrainEncoderA, RobotMap.drivetrainEncoderB, false,
				Encoder.EncodingType.k4X);

		leftEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_DISTANCE_PER_PULSE);

		distanceCompanion = new PIDCompanion(RobotMap.drivetrainRotationP, RobotMap.drivetrainRotationI,
				RobotMap.drivetrainRotationD, RobotMap.drivetrainRotationF, leftEncoder, "Drivetrain", "Distance");

		turnCompanion = new PIDCompanion(RobotMap.drivetrainRotationP, RobotMap.drivetrainRotationI,
				RobotMap.drivetrainRotationD, RobotMap.drivetrainRotationF, Robot.navX, "Drivetrain", "Turn");

		turnCompanion.getController().setInputRange(-180.0f, 180.0f);
		turnCompanion.getController().setContinuous(true);
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TankDrive());
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		drivetrain.arcadeDrive(moveValue, -rotateValue);
	}
	public void TankDrive(double left,double right){
		drivetrain.tankDrive(left, right);
	}
	public void stop() {
		drivetrain.arcadeDrive(0, 0);
	}

	public void reset() {
		leftEncoder.reset();
	}
}
