package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.DriveToTarget;

public class DeliverCargo extends CommandGroup {
    public DeliverCargo() {
		// requires(Robot.vision);
		// addSequential(new RotateToAngle(Robot.vision.getX(), false));
		addSequential(new DriveToTarget(2,0));
	}
}