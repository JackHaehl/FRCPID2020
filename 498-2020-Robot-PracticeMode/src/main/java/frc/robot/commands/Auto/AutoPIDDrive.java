package frc.robot.commands.Auto;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoPIDDrive extends CommandBase{
    private final Drivetrain drivetrain;
    private double distance;
    public AutoPIDDrive(double distance){
        this.distance = distance;
    }

    public void initialize(){
        drivetrain.resetGyro();
        drivetrain.setSetpoint(0);
        drivetrain.enable();
    }
    public void execute(){

    }
    public void end(){
        drivetrain.disable();
    }
    public boolean isFinished(){
        return drivetrain.getTrueDistance() > distance;
    }
}