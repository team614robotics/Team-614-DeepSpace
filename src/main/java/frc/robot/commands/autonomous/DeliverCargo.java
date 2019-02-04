package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.drivetrain.DriveToTarget;
import frc.robot.commands.drivetrain.RotateToAngle;

public class DeliverCargo extends CommandGroup {
    public DeliverCargo() {
		addSequential(new DriveToTarget(1, 0));
	}
}