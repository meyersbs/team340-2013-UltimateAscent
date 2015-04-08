package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author meyersbs
 */
public class SHOOT_OptionalShoot extends CommandBase {
    
    boolean done = false;
    double optionalSpeed = 0;
    public SHOOT_OptionalShoot(double speed) {
        requires(score);
        optionalSpeed = speed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(oi.buttonAPressed())
        {
            done = false;
            score.setSpeed(optionalSpeed);
//            new ScoreFireOnce().execute();
            if(score.isSpeed())
            {
                score.fireOut();
            }
            this.setTimeout(.5);
            if(this.isTimedOut())
            {
                score.fireIn();
            }
        }
        else if(!oi.buttonBackPressed())
        {
            //done = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
        score.setSpeed(0);
        score.fireIn();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        score.setSpeed(0);
        score.fireIn();
    }
}