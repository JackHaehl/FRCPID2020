package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoAdjustedDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private  double distance;
    private  double driveGain = 0.05; //The multiplier to the drive error to determine drive power
    private  double turnGain = 0.25; //The multiplier to the turn error to determine turn power
    private double drive;
    private double turn;
    private double driveErrorPercent;
    private  double minDrivePower = 0.2; //The minimum power to make the robot move, TODO: adjust this to the actual value.
    private boolean goingForward;

    public AutoAdjustedDrive(Drivetrain drivetrain, double distance,boolean goingForward){
        this.drivetrain = drivetrain;
        this.distance = distance;
    }
    public void initialize(){
        drivetrain.resetGyro();
    }
    public void execute(){
        driveErrorPercent = ((drivetrain.getTrueDistance() - distance) / distance) * 100;
        drive = (driveGain * driveErrorPercent) + minDrivePower; 
        turn = drivetrain.getDriveAngle() * turnGain;
        if(goingForward){
            drivetrain.arcadeDrive(drive, turn);
        }else{
            drivetrain.arcadeDrive(-drive, -turn);
        }
    }
    public void end(){
        
    }
    public boolean isFinished(){
        return drivetrain.getTrueDistance() >= distance;
    }
}