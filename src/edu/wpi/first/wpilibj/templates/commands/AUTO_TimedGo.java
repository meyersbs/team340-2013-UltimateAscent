package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Adam Audycki
 */
public class AUTO_TimedGo extends CommandBase 
{
    Timer timer;
    private double goTimerValue;
    private double speed;
    private double direction;
    
    public AUTO_TimedGo(double speed, double dir, double goTime) 
    {
        requires(drive);
        timer = new Timer();
        goTimerValue = goTime;
        this.speed = speed;
        this.direction = dir;
        
    }

    protected void initialize() 
    {
        timer.reset();
        timer.start();
        System.out.println("INIT Auto Timed Go time:" + timer.get() + ":");
    }
    
    private boolean done = false;

    protected void execute() 
    {
        done = drive.TimedGo(speed, direction, goTimerValue, timer);
    }

    protected boolean isFinished() 
    {
        return done;
    }

    protected void end() 
    {
        System.out.println("DONE Auto Timed Go time:" + timer.get() + ":");
        drive.setLeftDrive(0);
        drive.setRightDrive(0);
    }

    protected void interrupted() 
    {
        drive.setLeftDrive(0);
        drive.setRightDrive(0);
    }
}
