package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Timer;
import frc.lib.TrapezoidalMotionProfile;
import frc.lib.ProfilePoint;
import frc.robot.OI;
import frc.robot.Robot;

public class KeepState extends Command {
    Timer time = new Timer();
    TrapezoidalMotionProfile profile;
    double heading;

    double target;
    double error = 0;
    double vf;
    double maxVel;
    double output;
    
    public KeepState(double target) {
        this.target = target;
    }

    public void initialize() {
        // time.reset();
        // time.start();     
        // target = Robot.intake.sparkMaxE.getEncoder().getPosition();  

    }

    public void execute() {
        // if(target != 0) {
        //    error = Math.abs(target) + Robot.intake.sparkMaxE.getEncoder().getPosition();
        // }

        // output = error * 0.2;

        // // Output Stabilizer ((Robot.intake.sparkMaxE.getEncoder().getPosition() / target) > -0.4 ? (Robot.intake.sparkMaxE.getEncoder().getPosition() / target) : -0.4)
        // // Don't add unless needed 

        // SmartDashboard.putNumber("Output: ", output);
        // SmartDashboard.putNumber("Error: ", error);
        // SmartDashboard.putNumber("Time?: ", time.get());
        // SmartDashboard.putNumber("Profile duration?: ", profile.getDuration());
        // SmartDashboard.putNumber("Encoder Position: ",  Robot.intake.sparkMaxE.getEncoder().getPosition());
        // SmartDashboard.putNumber("How close to target: ",  Math.abs(target + Robot.intake.sparkMaxE.getEncoder().getPosition()));
        // SmartDashboard.putBoolean("Is it timed out: ", !isTimedOut());
        // Robot.intake.sparkMaxE.set(-output * 0.1216 + Math.cos(target) * 0.12); 

        Robot.intake.runPositionalPIDRight(this.target);
        Robot.intake.runPositionalPIDLeft(-this.target);

        // PID VALUES -
        // 0.1216 kP
        // 30 kF
        // 0.1 kPh
        // 0.05 kPi
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
    }
}