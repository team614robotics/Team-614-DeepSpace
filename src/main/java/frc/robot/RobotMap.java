/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public static final int leftMotorA = 0;
	public static final int leftMotorB = 1;
	public static final int rightMotorA = 2;
	public static final int rightMotorB = 3;

	// ENCODERS
	
	public static final int leftMotorEncoderA = 0;
	public static final int leftMotorEncoderB = 1;
	
	// DRIVETRAIN CONSTANTS
	
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
	public static final double DRIVETRAIN_ENCODER_PULSES_PER_REV = 245;
	public static final double DRIVETRAIN_DISTANCE_PER_PULSE = (RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) / RobotMap.DRIVETRAIN_ENCODER_PULSES_PER_REV;
	
	public static final double kCollisionThreshold_DeltaG = 0.6f;
	
	public static final double drivetrainRotationP = 0.1;
	public static final double drivetrainRotationI = 0;
	public static final double drivetrainRotationD = 0.12;
	public static final double drivetrainRotationF = 0;
	
	public static final double drivetrainDistanceP = 0.1;
	public static final double drivetrainDistanceI = 0;
	public static final double drivetrainDistanceD = 0.0;
	public static final double drivetrainDistanceF = 0;
}
