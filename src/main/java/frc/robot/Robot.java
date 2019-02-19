/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.SPI;
import frc.lib.CreateNewPath;
import frc.robot.subsystems.chassis.Drivetrain;
import frc.robot.subsystems.chassis.SRXPID;
import frc.robot.subsystems.Pneumatics;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Vision;
/**
 * 
 * The VM is configured to automatically run this class, and to call the
 * 
 * functions corresponding to each mode, as described in the TimedRobot
 * 
 * documentation. If you change the name of this class or the package after
 * 
 * creating this project, you must also update the build.gradle file in the
 * 
 * project.
 * 
 */

public class Robot extends TimedRobot {

	public static AHRS navX;

	// public static Drivetrain drivetrain;

	// public static DrivetrainCompanion drivetrainCompanion;

	public static Pneumatics pneumatics;

	public static OI oi;

	public static Arm arm = new Arm();
	public static Intake intake = new Intake();
	
	public static Climber climber = new Climber();
	public static Vision vision = new Vision();
	// public static DrivetrainCompanion drivetrainCompanion = new DrivetrainCompanion();

	public static Drivetrain drivetrain;

	Command autonomousCommand;

	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * 
	 * This function is run when the robot is first started up and should be used
	 * 
	 * for any initialization code.
	 * 
	 */

	@Override

	public void robotInit() {

		try {

			navX = new AHRS(SPI.Port.kMXP, (byte) 200);

		} catch (RuntimeException e) {

			DriverStation.reportError("NAVX ERROR: " + e.getMessage(), true);

		}
		
		drivetrain = new Drivetrain();
		// drivetrainCompanion = new DrivetrainCompanion();
		pneumatics = new Pneumatics();
		oi = new OI();
		

		// SmartDashboard.putBoolean("SetSpeedBoth", false);
		// SmartDashboard.putNumber("SpeedArm", 0.5);
	
		

		// SmartDashboard.putNumber("Unspool", 0);
		// SmartDashboard.putNumber("Feed Forward", 0.1);

		SmartDashboard.putNumber("Arm P", 0.5);
		SmartDashboard.putNumber("Arm I", 0);
		SmartDashboard.putNumber("Arm D", 0.8);
		SmartDashboard.putNumber("Arm F", 0);

		SmartDashboard.putNumber("Angle of Arm", 45);
		SmartDashboard.putNumber("Feed Forward", 0.1);
		SmartDashboard.putNumber("Spool Speed", 0.5);
		SmartDashboard.putNumber("Intake Speed", 0.7);
		// Robot.pneumatics.setBikebrakeState(RobotMap.PistonIn);
		
		// chooser.setDefaultOption("Default Auto", new Command());

		// chooser.addOption("My Auto", new MyAutoCommand());

		// SmartDashboard.putData("Autonomous", chooser);

		// SmartDashboard.putNumber("Drivetrain Left Encoder Distance", drivetrain.leftEncoder.getDistance());

		// SmartDashboard.putNumber("Drivetrain Left Encoder Rate", drivetrain.leftEncoder.getRate());

		// SmartDashboard.putNumber("Drivetrain Left Encoder Get", drivetrain.leftEncoder.get());
	}

	/**
	 * 
	 * 
	 * This function is called every robot packet, no matter the mode. Use this for
	 * 
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * 
	 * teleoperated and test.
	 *
	 * 
	 * 
	 * <p>
	 * 
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * 
	 * and SmartDashboard integrated updating.
	 * 
	 */

	@Override

	public void robotPeriodic() {
		SmartDashboard.putNumber("Limelight X", vision.getX());
		SmartDashboard.putNumber("Limelight Y", vision.getY());
		SmartDashboard.putNumber("Limelight Distance", vision.getDistance());
		SmartDashboard.putNumber("Limelight Area", vision.getArea());
		SmartDashboard.putNumber("Joystick Value Left", OI.driverController.getY(Hand.kLeft));
		SmartDashboard.putNumber("Joystick Value Right", OI.driverController.getX(Hand.kRight));
	}

	/**
	 * 
	 * This function is called once each time the robot enters Disabled mode. You
	 * 
	 * can use it to reset any subsystem information you want to clear when the
	 * 
	 * robot is disabled.
	 * 
	 */

	@Override

	public void disabledInit() {

		Robot.navX.reset();

		drivetrain.zeroSensors();
		drivetrain.resetSpeed();

	}

	@Override

	public void disabledPeriodic() {

		Scheduler.getInstance().run();

	}

	/**
	 * 
	 * This autonomous (along with the chooser code above) shows how to select
	 * 
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * 
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * 
	 * remove all of the chooser code and uncomment the getString code to get the
	 * 
	 * auto name from the text box below the Gyro
	 *
	 * 
	 * 
	 * <p>
	 * 
	 * You can add additional auto modes by adding additional commands to the
	 * 
	 * chooser code above (like the commented example) or additional comparisons to
	 * 
	 * the switch structure below with additional strings & commands.
	 * 
	 */

	@Override

	public void autonomousInit() {

		autonomousCommand = chooser.getSelected();

		/*
		 * 
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * 
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * 
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * 
		 * ExampleCommand(); break; }
		 * 
		 */

		// schedule the autonomous command (example)

		if (autonomousCommand != null) {

			autonomousCommand.start();

		}

	}

	/**
	 * 
	 * This function is called periodically during autonomous.
	 * 
	 */

	@Override

	public void autonomousPeriodic() {

		Scheduler.getInstance().run();

	}

	@Override

	public void teleopInit() {

		// This makes sure that the autonomous stops running when

		// teleop starts running. If you want the autonomous to

		// continue until interrupted by another command, remove

		// this line or comment it out.

		if (autonomousCommand != null) {

			autonomousCommand.cancel();

		}
		// Robot.drivetrain.leftEncoder.reset();
		Robot.arm.zeroSensor();

	}

	/**
	 * 
	 * This function is called periodically during operator control.
	 * 
	 */

	@Override

	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		// SmartDashboard.putNumber("Drivetrain Left Encoder Distance", drivetrain.leftEncoder.getDistance());
		// SmartDashboard.putNumber("Drivetrain Left Encoder Rate", drivetrain.leftEncoder.getRate());
		// SmartDashboard.putNumber("Drivetrain Left Encoder Get", drivetrain.leftEncoder.get());
		SmartDashboard.putNumber("navX Yaw", Robot.navX.getYaw());
		// SmartDashboard.putNumber("Voltage", Robot.climber.sparkMaxF.getBusVoltage());
		// SmartDashboard.putNumber("Output", Robot.climber.sparkMaxF.getOutputCurrent());
		SmartDashboard.putNumber("Output Controller", OI.driverController.getY(Hand.kLeft));
		// SmartDashboard.putBoolean("Is Encoder On", Robot.drivetrain.isEncoderOn());
		Robot.arm.hawkTalonA.setConfig(new SRXPID(
				SmartDashboard.getNumber("Arm F", 0), SmartDashboard.getNumber("Arm P", 0), 
				SmartDashboard.getNumber("Arm I", 0), SmartDashboard.getNumber("Arm D", 0)), 0);
		SmartDashboard.putNumber("Encoder", Robot.arm.hawkTalonA.getSelectedSensorPosition());
		// SmartDashboard.putNumber("Applied Output", Robot.arm.sparkMaxA.getAppliedOutput());
		// SmartDashboard.putNumber("Bus Voltage", Robot.arm.sparkMaxA.getBusVoltage());
		// SmartDashboard.putNumber("Output Current", Robot.arm.sparkMaxA.getOutputCurrent());
		// SmartDashboard.putBoolean("Is Encoder Present", );
		// SmartDashboard.putNumber("Motor Temperature", Robot.arm.sparkMaxA.getMotorTemperature());
	}

	/**
	 * 
	 * This function is called periodically during test mode.
	 * 
	 */

	@Override

	public void testPeriodic() {

	}

}