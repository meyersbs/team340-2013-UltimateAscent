package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Ben Meyers
 * This command simply waits for the user to press a button to tell the robot to climb to the next level.
 */
public class CLIMB_WaitPermission extends CommandBase {
    
    /**
     *
     */
    public CLIMB_WaitPermission() {
        requires(climb);
    }

    /**
     *
     */
    protected void initialize() {
    }

    /**
     *
     */
    protected void execute() {
        System.out.println("Waiting for Permission... " + oi.buttonBackPressed());
    }

    /**
     *
     * @return boolean: if button is pressed
     */
    protected boolean isFinished() {
        return oi.buttonBackPressed();
    }

    /**
     *
     */
    protected void end() {
        //DO NOTHING
        System.out.println("Permission granted!");

    }

    /**
     *
     */
    protected void interrupted() {
        //DO NOTHING
    }
}
