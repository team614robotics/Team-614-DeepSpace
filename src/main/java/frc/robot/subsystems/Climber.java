package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

public class Climber extends Subsystem {
	// private CANSparkMax motorA;
	// private CANSparkMax motorB;
	
	private VictorSP motorA;
	private VictorSP motorB;

	public Climber() {
		// motorA = new CANSparkMax(RobotMap.climberMotorA, MotorType.kBrushless);
		// motorB = new CANSparkMax(RobotMap.climberMotorB, MotorType.kBrushless);

		motorA = new VictorSP(RobotMap.climberMotorA);
		motorB = new VictorSP(RobotMap.climberMotorB);
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