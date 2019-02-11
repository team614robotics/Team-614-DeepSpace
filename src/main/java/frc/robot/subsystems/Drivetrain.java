/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.ArcadeDrive;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public PIDCompanion distanceCompanion;
	public PIDCompanion turnCompanion;

	public DifferentialDrive drivetrain;

	public Encoder leftEncoder;

	// public WPI_TalonSRX leftMotorA = new WPI_TalonSRX(RobotMap.leftMotorA);
	// public WPI_VictorSPX leftMotorB = new WPI_VictorSPX(RobotMap.leftMotorB);
	// public WPI_TalonSRX rightMotorA = new WPI_TalonSRX(RobotMap.rightMotorA);
	// public WPI_VictorSPX rightMotorB = new WPI_VictorSPX(RobotMap.rightMotorB);

	
	public VictorSP leftMotorA = new VictorSP(RobotMap.leftMotorA);
	public VictorSP leftMotorB = new VictorSP(RobotMap.leftMotorB);
	public VictorSP rightMotorA = new VictorSP(RobotMap.rightMotorA);
	public VictorSP rightMotorB = new VictorSP(RobotMap.rightMotorB);

	public SpeedController leftMotors = new SpeedControllerGroup(leftMotorA, leftMotorB);
	public SpeedController rightMotors = new SpeedControllerGroup(rightMotorA, rightMotorB);

	public Drivetrain() {
		// drivetrain = new DifferentialDrive(leftMotorA, rightMotorA);
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

		// leftMotorA.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
		// leftMotorA.setSensorPhase(false);

		// leftMotorA.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		// leftMotorA.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		// leftMotorA.configPeakOutputForward(1, RobotMap.kTimeoutMs);
		// leftMotorA.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);

		// leftMotorA.set(ControlMode.PercentOutput, 0);
		// leftMotorB.follow(leftMotorA);

		// rightMotorA.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
		// rightMotorA.setSensorPhase(false);

		// rightMotorA.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		// rightMotorA.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		// rightMotorA.configPeakOutputForward(1, RobotMap.kTimeoutMs);
		// rightMotorA.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);

		// rightMotorA.set(ControlMode.PercentOutput, 0);
		// rightMotorB.follow(rightMotorA);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ArcadeDrive());
	}

	public void arcadeDrive(double moveValue, double rotateValue) {
		drivetrain.arcadeDrive(moveValue, rotateValue);
	}

	public double getDistance() {
		return leftEncoder.getDistance();
	}

	public double getAngle() {
		return Robot.navX.getAngle();
	}

	public void stop() {
		drivetrain.arcadeDrive(0, 0);
	}

	public void reset() {
		leftEncoder.reset();
	}
}
