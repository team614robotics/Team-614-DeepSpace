package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.elevator.ManualElevator;

public class Elevator extends Subsystem {
	private Spark motor;
	public Encoder encoder;
	public PIDCompanion pid;
	private boolean manual = true;

	public Elevator() {
		motor = new Spark(RobotMap.elevatorMotor);
		encoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
		pid = new PIDCompanion();
		set(0);
	}

	public boolean isManual() {
		return manual;
	}

	public void setManual(boolean manual) {
		this.manual = manual;

		if (manual) {
			pid.setUsingDistancePID(false);
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