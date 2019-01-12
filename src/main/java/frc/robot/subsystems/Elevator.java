package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.elevator.ManualElevator;

public class Elevator extends Subsystem {
	private Spark motor;
	public Encoder encoder;
	public PIDCompanion companion;
	private boolean manual = true;

	public Elevator() {
		motor = new Spark(RobotMap.elevatorMotor);
		encoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB, false, Encoder.EncodingType.k4X);

		encoder.setDistancePerPulse(RobotMap.DRIVETRAIN_DISTANCE_PER_PULSE);
		// Change to elevator pulses per rev eventually.

		companion = new PIDCompanion(RobotMap.elevatorDistanceP, RobotMap.elevatorDistanceI, RobotMap.elevatorDistanceD,
				RobotMap.elevatorDistanceF, encoder, "Elevator", "Distance");
	}

	public boolean isManual() {
		return manual;
	}

	public void setManual(boolean manual) {
		this.manual = manual;

		if (manual) {
			companion.setUsingPID(false);
			stop();
		}
	}

	public void set(double speed) {
		motor.set(-speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualElevator());
	}

	public void stop() {
		set(0);
	}
}