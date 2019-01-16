package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

public class Climber extends Subsystem {
	private Spark motorA;
	private Spark motorB;

	public Climber() {
		motorA = new Spark(RobotMap.climberMotorA);
		motorB = new Spark(RobotMap.climberMotorB);
	}

	public void set(double speed) {
		motorA.set(speed);
		motorB.set(speed);
	}

	public void initDefaultCommand() {
	}

	public void stop() {
		set(0);
	}
}