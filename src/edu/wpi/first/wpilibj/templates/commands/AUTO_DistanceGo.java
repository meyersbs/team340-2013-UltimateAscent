package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Adam Audycki
 */
public class AUTO_DistanceGo extends CommandBase 
{
    private double speed;
    private double dir;
    private double goTime;
    private double lDist;
    
    /**
     * 
     * @param speed
     * @param dir
     * @param goTime
     * @param lDist 
     */
    public AUTO_DistanceGo(double speed,double dir,double goTime, double lDist) 
    {
        requires(drive);
        
        this.speed = speed;
        this.dir = dir;
        this.goTime = goTime;
        this.lDist = lDist;
    }

    protected void initialize() 
    {
        
    }

    private boolean done = false;
    protected void execute() 
    {
        System.out.println(drive.getUltrasonicVals());
        done = drive.distanceGoGreaterThan(speed, dir,lDist);
    }

    protected boolean isFinished() 
    {
        return done;
    }

    protected void end() 
    {
        drive.setLeftDrive(0);
        drive.setRightDrive(0);
    }

    protected void interrupted() 
    {
        drive.setLeftDrive(0);
        drive.setRightDrive(0);
    }
}