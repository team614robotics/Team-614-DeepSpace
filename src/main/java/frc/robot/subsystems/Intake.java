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

import frc.robot.subsystems.chassis.HawkTalons;
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


public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // One Intake Motor, simple speed 
 public CANSparkMax sparkMaxE, sparkMaxF;
 public CANPIDController pidController1, pidController2;
  // public CANSparkMax sparkMaxF;
  // private CANEncoder encoderA;
  // private CANEncoder encoderB;
  

 public Intake() {
  sparkMaxE = new CANSparkMax(0, MotorType.kBrushless);
  sparkMaxF = new CANSparkMax(RobotMap.sparkMaxF, MotorType.kBrushless);
  pidController1 = new CANPIDController(sparkMaxE);
  pidController2 = new CANPIDController(sparkMaxF);
 }
  
  @Override
 public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new SetSpeed());
 }

 public void resetSparkMax() {
    sparkMaxE.restoreFactoryDefaults();
    sparkMaxE = new CANSparkMax(RobotMap.sparkMaxE, MotorType.kBrushless);   
 }

 public void runIntake(double speed) {
    sparkMaxE.set(speed);
 }

 public void runOutake(double speed) {
    sparkMaxE.set(-speed);
 }

 public void runPositionalPIDLeft(double target) {
   pidController1.setP(0.4);
   pidController1.setI(0.0012);
   pidController1.setD(0.001);
   pidController1.setOutputRange(-1, 1);
   pidController1.setReference(target, ControlType.kPosition);
 }

 public void runPositionalPIDRight(double target) {
   pidController2.setP(0.4);
   pidController2.setI(0.0012);
   pidController2.setD(0.001);
   pidController2.setOutputRange(-1, 1);
   pidController2.setReference(target, ControlType.kPosition);
 }
}

