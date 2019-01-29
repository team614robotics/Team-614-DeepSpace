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

	private final double Kp = -0.01f;
	private double steeringAdjust = 0.0f;
	public final double minCommand = 0.05f;
	private double headingError;

	private double heightOfTarget = 0.8001;
	private double heightOfCamrea = 0.46736;
	public double xOffset;
	public double yOffset;
	public double area;
	public double LEDMode;
	public double targetNumber;
	public boolean target;
	public double distance;

	public Vision() {
		// table = NetworkTableInstance.getDefault().getTable("limelight");
	}
	public void setTable(NetworkTable table) {
		this.table = table;
	}
	public NetworkTable getTable() {
		return NetworkTableInstance.getDefault().getTable("limelight");
	}

	public void initDefaultCommand() {
		
	}
	public boolean HasTarget() {
		targetNumber = getTable().getEntry("tv").getDouble(0);
		if (targetNumber == 0) {
			target = false;
		} else if (targetNumber == 1) {
			target = true;
		}
		return target;
	}

	// Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
	public double getX() {
		xOffset = getTable().getEntry("tx").getDouble(0);
		return xOffset;
	}

	// Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
	public double getY() {
		yOffset = getTable().getEntry("ty").getDouble(0);
		return yOffset;
	}

	// Limelight LED state
	public double getLEDMode() {
		LEDMode = getTable().getEntry("ledMode").getDouble(1);
		return LEDMode;
	}

	public void setLED(double mode) {
		if (getLEDMode() != mode) {
			getTable().getEntry("ledMode").setDouble(mode);
		}
	}

	public void setHeadingError(double headingError){
		this.headingError = headingError;
	}
	public double calcSteeringAdjust(){
		headingError = -getX();
		if(getX() > 1.0){
			steeringAdjust = Kp*headingError - minCommand;
		}else if(getX() < 1.0){
			steeringAdjust = Kp*headingError + minCommand;
		}
		return steeringAdjust;
	}
	public double calcDistance(){
		// return (3.709)/Math.sqrt(getArea());
		return (heightOfTarget-heightOfCamrea)/(Math.tan(Math.toRadians(getY())));
		//*(Math.tan(Math.toRadians(getY())));
	}
	public void stop() {
	}

	public void reset() {
	}
}
