package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author John
 */
public class CLIMB_ClearBarWeightBack extends CommandBase {
    private double motorSpeed;
    private double encoderStopValue;
    private double tolerance;
    
    private boolean wtDone = false;
    private boolean climbDone = false;
    
    public CLIMB_ClearBarWeightBack(double speed, double tol) {
        requires(climb);
        requires(weightTransferPID);
        motorSpeed = speed;
        tolerance = tol;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("ClearBarWeightBack; Encoder: " + climb.getArmEncoderValue() + ": back limit:" + weightTransferPID.getWeightBackwardLimit());
        if(!weightTransferPID.getWeightBackwardLimit())
        {
            weightTransferPID.moveBackward(-1);
            wtDone = false;
        }
        else
        {
            weightTransferPID.stopMove();
            wtDone = true;
        }
        
        if(/*encoderStopValue > climb.getArmEncoderValue() && */climb.getTopArmSwitch())
        {
            climbDone = true;
            climb.setArmMotorSpeed(0);
            climb.setArmLockOn();

        }
        /*else if(encoderStopValue < climb.getArmEncoderValue() && climb.getBottomArmSwitch())
        {
            climbDone = true;
            climb.setArmMotorSpeed(0);
            climb.setArmLockOn();            
        }
        else if(climb.getArmEncoderValue() > encoderStopValue)
        {
            climbDone = true;
            climb.setArmMotorSpeed(0);
            climb.setArmLockOn();            
        }*/
        else
        {
            climb.setArmLockOff();
            climb.setArmMotorSpeed(motorSpeed);
            climbDone = false;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (climbDone && wtDone);
    }

    // Called once after isFinished returns true
    protected void end() {
        climb.setArmMotorSpeed(0);
        climb.setArmLockOn();
        drive.arcadeDrive(0.0, 0.0, true);
        weightTransferPID.stopMove();
        System.out.println("ClearBarWeightBack DONE!!");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        climb.setArmMotorSpeed(0);
        climb.setArmLockOn();
        drive.arcadeDrive(0.0, 0.0, true);
        weightTransferPID.stopMove();
    }
}