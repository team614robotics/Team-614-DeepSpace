/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.drivetrain.DriveForADistance;
import frc.robot.commands.drivetrain.RotateToAngle;
import frc.robot.commands.elevator.MoveElevator;
import frc.robot.commands.elevator.ToggleManual;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public static final int AButton = 1;
	public static final int BButton = 2;
	public static final int XButton = 3;
	public static final int YButton = 4;
	public static final int LeftBumper = 5;
	public static final int RightBumper = 6;
	public static final int BackButton = 7;
	public static final int StartButton = 8;
	public static final int LeftStick = 9;
	public static final int RightStick = 10;

	public static final XboxController driverController = new XboxController(0);
	public static final XboxController operatorController = new XboxController(1);

	public static final Button driveForADistance = new JoystickButton(driverController, AButton);
	public static final Button rotateToAngle = new JoystickButton(driverController, BButton);
	public static final Button moveElevatorHigh = new JoystickButton(operatorController, YButton);
	public static final Button moveElevatorMedium = new JoystickButton(operatorController, BButton);
	public static final Button moveElevatorLow = new JoystickButton(operatorController, AButton);
	public static final Button toggleManual = new JoystickButton(operatorController, XButton);

	public OI() {
		driveForADistance.whenPressed(new DriveForADistance(100, -0.5));
		rotateToAngle.whenPressed(new RotateToAngle(90, false));
		moveElevatorHigh.whenPressed(new MoveElevator(150, 0.5));
		moveElevatorMedium.whenPressed(new MoveElevator(100, 0.5));
		moveElevatorLow.whenPressed(new MoveElevator(50, 0.5));
		toggleManual.whenPressed(new ToggleManual());
	}
}
