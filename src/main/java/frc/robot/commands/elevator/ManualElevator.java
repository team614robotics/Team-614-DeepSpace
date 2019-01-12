package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class ManualElevator extends Command {
	public ManualElevator() {
		requires(Robot.elevator);
	}

	public void initialize() {
	}

	public void execute() {
		if (Robot.elevator.isManual()) {
			Robot.elevator.set(OI.operatorController.getY(Hand.kLeft));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}