package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.motion.ProfilePoint;
import frc.robot.motion.TrapezoidalMotionProfile;
import edu.wpi.first.wpilibj.Timer;

public class DriveProfiledDistance extends Command {
	Timer time = new Timer();
	TrapezoidalMotionProfile profile;

	double heading;
	double target;
	double error = Double.MAX_VALUE;
	double vf;

	public DriveProfiledDistance(double target, double heading, double maxVel, double maxAcc) {
		this(target, heading, maxVel, maxAcc, 0, 0);
	}

	public DriveProfiledDistance(double target, double heading, double maxVel, double maxAcc, double vi, double vf) {
		requires(Robot.drivetrain);
		this.heading = heading;
		this.profile = new TrapezoidalMotionProfile(target, maxVel, maxAcc, vi, vf);
		this.vf = vf;
		this.target = target;
		setInterruptible(true);
		setTimeout(profile.getDuration() + 1.5);
	}

	public void initialize() {
		time.reset();
		time.start();
		error = Double.MAX_VALUE;
		Robot.drivetrain.leftEncoder.reset();
	}

	public void execute() {
		ProfilePoint point = profile.getAtTime(time.get());
		error = point.pos - Robot.drivetrain.getDistance();
		double output = point.vel * RobotMap.Drive_kF + error * RobotMap.Drive_kP;
		System.out.println("drive out " + output + " vel " + point.vel);
		double turn = (heading - Robot.drivetrain.getAngle()) * RobotMap.Drive_TurnHold_kP;
		Robot.drivetrain.arcadeDrive(output + turn, output - turn);
	}

	public boolean isFinished() {
		return profile.getDuration() < time.get()
				&& Math.abs(target - Robot.drivetrain.getDistance()) < RobotMap.Drive_OkayError && !isTimedOut();
	}

	public void end() {
		Robot.drivetrain.stop();
	}
}