package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.DriveForADistance;

public class DeliverHatch extends CommandGroup {
    public DeliverHatch() {
		addSequential(new DriveForADistance(100, -0.5));
	}
}