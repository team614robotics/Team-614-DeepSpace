/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot.*;
import frc.robot.commands.drivetrain.RotateToAngle;
import frc.robot.commands.vision.VisionProcessing;

/**
 *
 */
public class Vision extends Subsystem {
	/**
	 *
	 */
	private NetworkTable table;
	private NetworkTableEntry tx;
	private NetworkTableEntry ty;
	private NetworkTableEntry ta;
	private NetworkTableEntry pipeline;
	private NetworkTableEntry led;
	private NetworkTableEntry cam;

	// private final double Kp = -0.04f;
	// private double steeringAdjust = 0.0f;
	// public final double minCommand = 0.0f;
	// private double headingError;

	private double heightOfTarget = 0.8001;
	private double heightOfCamrea = 0.46736;
	// public double xOffset;
	// public double yOffset;
	// public double area;
	// public double LEDMode;
	// public double targetNumber;
	// public boolean target;
	public double distance;

	public Vision() {
		// table = NetworkTableInstance.getDefault().getTable("limelight");
		tx = table.getEntry("tx");
		ty = table.getEntry("ty");
		ta = table.getEntry("ta");
		pipeline = table.getEntry("pipeline");
		led = table.getEntry("ledMode");
		cam = table.getEntry("camMode");
		setPipeline(2);
		setCamMode(0);
		
	}
	public void initDefaultCommand() {
		
	}
	// public boolean HasTarget() {
	// 	targetNumber = tv.getDouble(0);
	// 	if (targetNumber == 0) {
	// 		target = false;
	// 	} else if (targetNumber == 1) {
	// 		target = true;
	// 	}
	// 	return target;
	// }

	// Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
	public double getX() {
		return tx.getDouble(0);
	}

	// Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
	public double getY() {
		return ty.getDouble(0);
	}

	// Limelight LED state
	public double getLEDMode() {
		return led.getDouble(1);
	}

	public void setLED(double mode) {
		if (getLEDMode() != mode) {
			led.setDouble(mode);
		}
	}

	public double getPipeline(){
		return pipeline.getDouble(0);
	}
	
	public void setPipeline(double mode){
		pipeline.setDouble(mode);
	}
	
	public double getCamMode(){
		return cam.getDouble(0);
	}
	
	public void setCamMode(double mode){
		cam.setDouble(mode);
	}

	public double getArea(){
		return ta.getDouble(0.0);
	}
	// public double calcSteeringAdjust(){
	// 	headingError = -getX();
	// 	if(getX() > 1.0){
	// 		steeringAdjust = Kp*headingError - minCommand;
	// 	}else if(getX() < 1.0){
	// 		steeringAdjust = Kp*headingError + minCommand;
	// 	}
	// 	return steeringAdjust;
	// }

	public double calcDistance(){
		// return (3.709)/Math.sqrt(getArea());
		return (heightOfTarget-heightOfCamrea)/(Math.tan(Math.toRadians(getY())));
		//*(Math.tan(Math.toRadians(getY())));
	}

	public double getDistance(){
		distance = 4.73 * Math.pow(getArea(), -.340);
		return distance;
	}

	public void stop() {
		setPipeline(2);
		setCamMode(0);
	}

	public void reset() {
		setPipeline(2);
		setCamMode(0);
	}
}
