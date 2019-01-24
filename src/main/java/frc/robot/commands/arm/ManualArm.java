package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class ManualArm extends Command {
	public ManualArm() {
		requires(Robot.arm);
	}

	public void initialize() {
	}

	public void execute() {
		if (Robot.arm.isManual()) {
			Robot.arm.set(OI.operatorController.getY(Hand.kLeft));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}