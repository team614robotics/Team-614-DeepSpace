package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.arm.ManualArm;

public class Arm extends Subsystem {
	// private CANSparkMax motorA;
	// private CANSparkMax motorB;
	
	private VictorSP motorA;
	private VictorSP motorB;

	public Encoder encoder;
	public PIDCompanion companion;

	private boolean manual = true;

	private boolean up = true;

	public Arm() {
		// motorA = new CANSparkMax(RobotMap.elevatorMotorA, MotorType.kBrushed);
		// motorB = new CANSparkMax(RobotMap.elevatorMotorB, MotorType.kBrushed);
		motorA = new VictorSP(RobotMap.armMotorA);
		motorB = new VictorSP(RobotMap.armMotorB);
		encoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB, false, Encoder.EncodingType.k4X);

		encoder.setDistancePerPulse(RobotMap.DRIVETRAIN_DISTANCE_PER_PULSE);
		// Change to elevator pulses per rev eventually.

		companion = new PIDCompanion(RobotMap.elevatorDistanceP, RobotMap.elevatorDistanceI, RobotMap.elevatorDistanceD,
				RobotMap.elevatorDistanceF, encoder, "Arm", "Distance");
	}

	public boolean isManual() {
		return manual;
	}

	public boolean isUp() {
		return up;
	}

	public void setManual(boolean manual) {
		this.manual = manual;

		if (manual) {
			companion.setUsingPID(false);
			stop();
		}
	}

	public void set(double speed) {
		motorA.set(speed);
		motorB.set(speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualArm());
	}

	public void stop() {
		set(0);
	}
}