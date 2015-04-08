/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class Shoot_PropellerHome extends CommandBase {
    
    private int state = 0;
    private final int step1 = 1;
    private final int step2 = 2;
    private final int step3 = 3;
    
    public Shoot_PropellerHome() {
        requires(shoot);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        state = step1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Shoot propeller home state:" + state + ":");
        switch(state)
        {
            case step1:
                if(shoot.propellerCheck())
                {
                    state = step2;
                }
                break;
            
            case step2:
                shoot.startTimer();
                System.out.println(shoot.getTimer());
                shoot.setDiskPropeller(-.3);                
                state = step3;
                break;
                
            case step3:
                if(shoot.getTimer() >= 0.25)
                {
                    shoot.setDiskPropeller(0);
                    state = 99;                    
                }                
                break;
            default:
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == 99;
    }

    // Called once after isFinished returns true
    protected void end() {
        shoot.setDiskPropeller(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shoot.setDiskPropeller(0);
    }
}
