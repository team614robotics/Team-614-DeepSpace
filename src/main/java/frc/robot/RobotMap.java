/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

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

	public static final int elevatorMotor = 4;

	public static final int climberMotorA = 5;
	public static final int climberMotorB = 6;

	// ENCODERS
	
	public static final int drivetrainEncoderA = 0;
	public static final int drivetrainEncoderB = 1;

	public static final int elevatorEncoderA = 2;
	public static final int elevatorEncoderB = 3;
	
	// DRIVETRAIN CONSTANTS
	
	public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
	public static final double DRIVETRAIN_ENCODER_PULSES_PER_REV = 245;
	public static final double DRIVETRAIN_DISTANCE_PER_PULSE = (DRIVETRAIN_WHEEL_DIAMETER * Math.PI) / DRIVETRAIN_ENCODER_PULSES_PER_REV;
	
	public static final double drivetrainRotationP = 0.1;
	public static final double drivetrainRotationI = 0;
	public static final double drivetrainRotationD = 0.12;
	public static final double drivetrainRotationF = 0;
	
	public static final double drivetrainDistanceP = 0.1;
	public static final double drivetrainDistanceI = 0;
	public static final double drivetrainDistanceD = 0.0;
	public static final double drivetrainDistanceF = 0;

	public static double Drive_kP = 0.1;
    public static double Drive_kF = 0.01;
    public static double Drive_TurnHold_kP = 0.005;
    public static double Drive_Turn_kP = 0;
    public static double Drive_Turn_kD = 0;
    public static double Drive_OkayError = 2;
    public static double Drive_Turn_OkayError = 2;

	public static final double ELEVATOR_TRACK_PERIMETER = 6;
	public static final double ELEVATOR_ENCODER_PULSES_PER_REV = 245;
	public static final double ELEVATOR_DISTANCE_PER_PULSE = ELEVATOR_TRACK_PERIMETER / ELEVATOR_ENCODER_PULSES_PER_REV;
	
	public static final double elevatorDistanceP = 0.1;
	public static final double elevatorDistanceI = 0;
	public static final double elevatorDistanceD = 0.0;
	public static final double elevatorDistanceF = 0;

	// PNEUMATICS
	
	public static final int compressor = 0;

	public static final int grabberPistonA = 0;
	public static final int grabberPistonB = 1;
	
	public static final DoubleSolenoid.Value PistonOut = DoubleSolenoid.Value.kForward;
	public static final DoubleSolenoid.Value PistonIn = DoubleSolenoid.Value.kReverse;
}
