/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
// import frc.robot.commands.SetSpeedClimber;
import frc.robot.subsystems.chassis.HawkTalons;
import frc.robot.commands.arm.SetSpeed;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.subsystems.chassis.SRXPID;


public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public HawkTalons hawkTalonA;
  public CANSparkMax sparkMaxB;
  // public CANSparkMax sparkMaxE;
  // public CANSparkMax sparkMaxF;
  // private CANEncoder encoderA;
  // private CANEncoder encoderB;

  private CANPIDController pidController;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, allowedErr;

  public double FEEDFORWARD = 0.1; // Between 0.5 to .2 try 0.1 for now

  public int timeout = 10;

  public Arm() {
    hawkTalonA = new HawkTalons(RobotMap.sparkMaxA);
    sparkMaxB = new CANSparkMax(RobotMap.sparkMaxB, MotorType.kBrushed);
    sparkMaxB.setInverted(true);
    // sparkMaxE = new CANSparkMax(RobotMap.sparkMaxE, MotorType.kBrushed);
    // sparkMaxF = new CANSparkMax(RobotMap.sparkMaxF, MotorType.kBrushed);  

    kP = 5e-5; 
    kI = 1e-6;
    kD = 0; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;

    // pidController.setP(kP);
    // pidController.setI(kI);
    // pidController.setD(kD);
    // pidController.setIZone(kIz);
    // pidController.setFF(kFF);
    // pidController.setOutputRange(kMinOutput, kMaxOutput); 

    //TODO: Configure pidController to frf
    configTalons();
  }

  public void configTalons() {
		// TALON CONFIG
		hawkTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		hawkTalonA.setSensorPhase(false); /* keep sensor and motor in phase */
		hawkTalonA.configNeutralDeadband(0.001, timeout);
		hawkTalonA.configNominalOutputForward(0, timeout);
		hawkTalonA.configNominalOutputReverse(0, timeout);
		hawkTalonA.configPeakOutputForward(1, timeout);
		hawkTalonA.configPeakOutputReverse(-1, timeout);
    // --
		hawkTalonA.setConfig(new SRXPID(0, 1, 0, 0), 0);
		hawkTalonA.configMotionProfileTrajectoryPeriod(10, timeout); 
	  hawkTalonA.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, timeout);
	}
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new SetSpeed());
  }

  public void set(double percent, double feedForward) {
    double output = percent + feedForward * Math.cos(Math.toRadians(getAngle()));
    // output = output > 1.0 ? 1.0 : output;
    // output = output < -1.0 ? -1.0 : output;
    if(getAngle() < 6) {
      output = percent;
    }
    else if(getAngle() > 90) {
      output = percent;
    }
    hawkTalonA.set(ControlMode.PercentOutput, 0.2 * percent);
    sparkMaxB.follow(CANSparkMax.ExternalFollower.kFollowerPhoenix, hawkTalonA.getDeviceID());
    // sparkMaxB.setInverted(true);
  }

  public void setSpeedA(double output) {
    hawkTalonA.set(output);
  }

  public void setSpeedBoth(double output) {
    hawkTalonA.set(ControlMode.PercentOutput, output);
    sparkMaxB.follow(CANSparkMax.ExternalFollower.kFollowerPhoenix, hawkTalonA.getDeviceID());
  }

  private double sensorUnitsToDegrees(double units) {
    return units / RobotMap.degreesToTicksConstant;
  }
  
  public double getAngle() { //returns angle of wrist in degrees
    return sensorUnitsToDegrees(hawkTalonA.getSelectedSensorPosition());
  }

  public double getSensor() {
    return hawkTalonA.getSelectedSensorPosition();
  }

  public void zeroSensor() {
    hawkTalonA.setSelectedSensorPosition(0);
  }

  // public void unspool(double speed) {
  //   sparkMaxF.set(speed);
  // }
}

