/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * 
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * 
 * to a variable name. This provides flexibility changing wiring, makes checking
 * 
 * the wiring easier and significantly reduces the number of magic numbers
 * 
 * floating around.
 * 
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

	// MOTOR CONTROLLERS

	public static final int leftMotorA = 0; // LDrive
	public static final int leftMotorB = 1;

	public static final int rightMotorA = 2; // RDrive
	public static final int rightMotorB = 3;

	public static final int sparkMaxA = 5; // Arm
	public static final int sparkMaxB = 6;

	public static final int sparkMaxC = 7; // Intake
	public static final int sparkMaxD = 8;

	public static final int sparkMaxE = 9; // Climber
	public static final int sparkMaxF = 10;

	// DRIVETRAIN CONSTANTS

	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6; // Fix
	public static final double DRIVETRAIN_ENCODER_PULSES_PER_REV = 245; // Fix

	public static final double inchesToTicksEquation = (1400) / (2 * Math.PI * (DRIVETRAIN_WHEEL_DIAMETER / 2)); // Fix
	public static final double degreesToTicksConstant = 42 / 180;

	public static final double DRIVETRAIN_DISTANCE_PER_PULSE = 1.258
			* ((RobotMap.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) / RobotMap.DRIVETRAIN_ENCODER_PULSES_PER_REV);

	public static final double maxVelocity = 0;

	public static final double maxAcceleration = 0;

	public static final double kCollisionThreshold_DeltaG = 0.6f;

	// ENCODERS

	// public static final int leftMotorEncoderA = 0;
	// public static final int leftMotorEncoderB = 1;
	// NOT USED

	// MOTION MAGIC VALUES

	public static final double drivetrainRotationP = 4;
	public static final double drivetrainRotationI = 0;
	public static final double drivetrainRotationD = 6;
	public static final double drivetrainRotationF = 0;

	public static final double drivetrainDistanceP = 0.014;
	public static final double drivetrainDistanceI = 0.001;
	public static final double drivetrainDistanceD = 6;
	public static final double drivetrainDistanceF = 0.08;

	public static final double p = 1;
	public static final double f = 0.01;

	// PNEUMATICS
    public static final int solenoidPort1A = 0;
	public static final int solenoidPort2A = 1;
	public static final int solenoidPort1B = 2;
	public static final int solenoidPort2B = 3;
	public static final int solenoidPort3 = 4;
	public static final int solenoidPort4 = 5;
	public static final int solenoidPort5 = 6;
	public static final int solenoidPort6 = 7;
	public static final int compressor = 0;

	public static final double nativeUpdates = 100;

	public static final DoubleSolenoid.Value PistonOut = DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value PistonIn = DoubleSolenoid.Value.kReverse;

	public static final int PIDLoopIdx = 0;
	public static final int timeoutMs = 0;
	public static final int slotIdx = 0;

	public static final int encTicksPerDeg = 1920 / 90;
	public static final int degPerEncTicks = 90 / 1920;

}