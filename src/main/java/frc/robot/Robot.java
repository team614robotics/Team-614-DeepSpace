/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.hal.sim.mockdata.PDPDataJNI;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Climber;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static PowerDistributionPanel pdp;
	public static AHRS navX;
	public static Drivetrain drivetrain;
	public static Pneumatics pneumatics;
	public static Arm arm;
	public static Vision vision;
	public static Climber climber;
	public static Intake intake;
	public static OI oi;

	private DigitalInput limit;
	private boolean rumbling = false;
	private int ticks = 10;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		try {
			navX = new AHRS(SPI.Port.kMXP, (byte) 200);
		} catch (RuntimeException e) {
			DriverStation.reportError("NAVX ERROR: " + e.getMessage(), true);
		}

		pdp = new PowerDistributionPanel();
		drivetrain = new Drivetrain();
		pneumatics = new Pneumatics();
		arm = new Arm();
		vision = new Vision("limelight");
		climber = new Climber();
		intake = new Intake();
		oi = new OI();

		limit = new DigitalInput(4);

		// chooser.setDefaultOption("Default Auto", new Command());
		// chooser.addOption("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Autonomous", chooser);
		
		SmartDashboard.putNumber("Intake Speed", 0);
		SmartDashboard.putNumber("Outake Speed", 0);		
	}

	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Drivetrain Left Encoder Distance", drivetrain.leftEncoder.getDistance());
		SmartDashboard.putNumber("Drivetrain Left Encoder Rate", drivetrain.leftEncoder.getRate());
		SmartDashboard.putNumber("Drivetrain Left Encoder Get", drivetrain.leftEncoder.get());

		SmartDashboard.putNumber("Arm Encoder Distance", arm.encoder.getDistance());
		SmartDashboard.putNumber("Arm Encoder Rate", arm.encoder.getRate());
		SmartDashboard.putNumber("Arm Encoder Get", arm.encoder.get());

		SmartDashboard.putNumber("navX Yaw", Robot.navX.getYaw());

		SmartDashboard.putNumber("Joystick Value", OI.driverController.getX(Hand.kRight));
		SmartDashboard.putBoolean("Arm Up", Robot.arm.isUp());

		SmartDashboard.putNumber("Limelight X", Robot.vision.getX());
		SmartDashboard.putNumber("Limelight Y", Robot.vision.getY());
		SmartDashboard.putNumber("Limelight Area", Robot.vision.getArea());
		SmartDashboard.putNumber("Limelight Distance", Robot.vision.getDistance());
		SmartDashboard.putNumber("Operator Controller", OI.operatorController.getX(Hand.kLeft));

		// SmartDashboard.putNumber("Voltage 1", PDPDataJNI.getVoltage(1));
		// SmartDashboard.putNumber("Voltage 2", PDPDataJNI.getVoltage(2));
		// SmartDashboard.putNumber("Voltage 3", PDPDataJNI.getVoltage(3));
		
		SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage());

		SmartDashboard.putNumber("PDP Current 0", pdp.getCurrent(0));
		SmartDashboard.putNumber("PDP Current 1", pdp.getCurrent(1));
		SmartDashboard.putNumber("PDP Current 2", pdp.getCurrent(2));
		SmartDashboard.putNumber("PDP Current 3", pdp.getCurrent(3));
		SmartDashboard.putNumber("PDP Current 4", pdp.getCurrent(4));
		SmartDashboard.putNumber("PDP Current 5", pdp.getCurrent(5));
		SmartDashboard.putNumber("PDP Current 6", pdp.getCurrent(6));
		SmartDashboard.putNumber("PDP Current 7", pdp.getCurrent(7));
		SmartDashboard.putNumber("PDP Current 8", pdp.getCurrent(8));
		SmartDashboard.putNumber("PDP Current 9", pdp.getCurrent(9));
		SmartDashboard.putNumber("PDP Current 10", pdp.getCurrent(10));
		SmartDashboard.putNumber("PDP Current 11", pdp.getCurrent(11));
		SmartDashboard.putNumber("PDP Current 12", pdp.getCurrent(12));
		SmartDashboard.putNumber("PDP Current 13", pdp.getCurrent(13));
		SmartDashboard.putNumber("PDP Current 14", pdp.getCurrent(14));
		SmartDashboard.putNumber("PDP Current 15", pdp.getCurrent(15));

		SmartDashboard.putBoolean("Beak Is Open", limit.get());

		if (pdp.getCurrent(0) > 30 || rumbling) {
			rumbling = true;

			vision.setLED(2);

			OI.driverController.setRumble(RumbleType.kLeftRumble, 1);
			OI.driverController.setRumble(RumbleType.kRightRumble, 1);
			--ticks;

			if (ticks <= 0) {
				rumbling = false;
			}
		} else {
			vision.setLED(0);

			ticks = 15;

			OI.driverController.setRumble(RumbleType.kLeftRumble, 0);
			OI.driverController.setRumble(RumbleType.kRightRumble, 0);
		}

		SmartDashboard.putNumber("PDP Current 0", pdp.getCurrent(0));
		// SmartDashboard.putNumber("PDP Current 1", pdp.getCurrent(1));
		// SmartDashboard.putNumber("PDP Current 2", pdp.getCurrent(2));
		// SmartDashboard.putNumber("PDP Current 3", pdp.getCurrent(3));
		// SmartDashboard.putNumber("PDP Current 4", pdp.getCurrent(4));
		// SmartDashboard.putNumber("PDP Current 5", pdp.getCurrent(5));
		// SmartDashboard.putNumber("PDP Current 6", pdp.getCurrent(6));
		// SmartDashboard.putNumber("PDP Current 7", pdp.getCurrent(7));
		// SmartDashboard.putNumber("PDP Current 8", pdp.getCurrent(8));
		// SmartDashboard.putNumber("PDP Current 9", pdp.getCurrent(9));
		// SmartDashboard.putNumber("PDP Current 10", pdp.getCurrent(10));
		// SmartDashboard.putNumber("PDP Current 11", pdp.getCurrent(11));
		// SmartDashboard.putNumber("PDP Current 12", pdp.getCurrent(12));
		// SmartDashboard.putNumber("PDP Current 13", pdp.getCurrent(13));
		SmartDashboard.putNumber("PDP Current 14", pdp.getCurrent(14));
		SmartDashboard.putNumber("PDP Current 15", pdp.getCurrent(15));

		// SmartDashboard.putNumber("Current 1", PDPDataJNI.getCurrent(0, 1));
		// SmartDashboard.putNumber("Current 2", PDPDataJNI.getCurrent(0, 2));
		// SmartDashboard.putNumber("Current 3", PDPDataJNI.getCurrent(0, 3));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.navX.reset();
		drivetrain.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
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
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
