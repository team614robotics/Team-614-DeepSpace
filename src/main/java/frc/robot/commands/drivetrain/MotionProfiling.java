// package frc.robot.commands.drivetrain;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.Timer;
// import frc.lib.TrapezoidalMotionProfile;
// import frc.lib.ProfilePoint;
// import frc.robot.Robot;

// public class MotionProfiling extends Command {
//     Timer time = new Timer();
//     TrapezoidalMotionProfile profile;
//     double heading;

//     double target;
//     double error = 0;
//     double vf;
//     double maxVel;
//     double output;

//     public MotionProfiling(double target, double heading, double maxVel, double maxAcc) {
//         this(target, heading, maxVel, maxAcc, 0, 0);
//     }

//     public MotionProfiling(double target, double heading, double maxVel, double maxAcc, double vi, double vf) {
//         requires(Robot.drivetrain);
//         this.heading = heading;
//         this.profile = new TrapezoidalMotionProfile(target, maxVel, maxAcc, vi, vf);
//         this.vf = vf;
//         this.target = target;
//         this.maxVel = maxVel;
//         setInterruptible(true);
//         setTimeout(profile.getDuration() + 1.5);
//     }

//     public void initialize() {
//         time.reset();
//         time.start();        
//     }

//     public void execute() {
//         ProfilePoint point = profile.getAtTime(time.get());
//         if(point.pos != 0) {
//            error = point.pos + Robot.intake.sparkMaxE.getEncoder().getPosition();
//         }
//         output = point.vel * 0.1 + error * 0.05;

//         // Output Stabilizer ((Robot.intake.sparkMaxE.getEncoder().getPosition() / target) > -0.4 ?  (Robot.intake.sparkMaxE.getEncoder().getPosition() / target) : -0.4)
//         // Don't add unless needed 

//         SmartDashboard.putNumber("Output: ", output);
//         SmartDashboard.putNumber("Error: ", error);
//         SmartDashboard.putNumber("Time?: ", time.get());
//         SmartDashboard.putNumber("Profile duration?: ", profile.getDuration());
//         SmartDashboard.putNumber("Encoder Position: ",  Robot.intake.sparkMaxE.getEncoder().getPosition());
//         SmartDashboard.putNumber("How close to target: ",  Math.abs(target + Robot.intake.sparkMaxE.getEncoder().getPosition()));
//         SmartDashboard.putBoolean("Is it timed out: ", !isTimedOut());
//         Robot.intake.sparkMaxE.set((-output * 0.1216 * Math.cbrt(30/target))); 

//         // 0.1216 kP
//         // 30 kF
//         // 0.1 kPh
//         // 0.05 kPi

//         // PID Values
//         // Rest in TMP

        
//     }

//     public boolean isFinished() {
//         return Robot.intake.sparkMaxE.getEncoder().getPosition() < -target + .055 || isTimedOut();
//     }

//     public void end() {
//         Robot.intake.sparkMaxE.set(0);
//         Robot.intake.resetSparkMax();
//     }
// }