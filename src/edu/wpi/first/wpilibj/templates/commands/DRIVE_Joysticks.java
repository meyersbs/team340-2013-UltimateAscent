package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author 
 */
public class DRIVE_Joysticks extends CommandBase {
    
    private final double deadzone = .05;
    public final double SLOW_MOVE_FACTOR = 0.5;

    /**
     *
     */
    public DRIVE_Joysticks() {
        requires(drive);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    /**
     *
     */
    protected void initialize() {
        //System.out.println("\tDRIVE_Joysticks init");
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *
     */
    protected void execute() {
        
        
        if(oi.getDriveSlowMove() > 0.2 || oi.getDriveSlowMove() < -0.2 || oi.getDriveSlowRotate() > 0.2 || oi.getDriveSlowRotate() < -0.2)
        {
            drive.arcadeDrive(oi.getDriveSlowMove() * SLOW_MOVE_FACTOR, oi.getDriveSlowRotate() * SLOW_MOVE_FACTOR, false);
            System.out.println("slow move:" + (oi.getDriveSlowMove() * SLOW_MOVE_FACTOR) + ": slow rotate :" + (oi.getDriveSlowRotate() * SLOW_MOVE_FACTOR));
        }
        else
        {
            drive.arcadeDrive(oi.getDriveMove(), oi.getDriveRotate(), true);
        }
        //System.out.println("Ultra :" + drive.getUltrasonicVals());
        //System.out.println("\tDRIVE_Joysticks execute");
        //drive.arcadeDrive(oi.getDriveSlowMove() * SLOW_MOVE_FACTOR, oi.getDriveSlowRotate() * SLOW_ROTATE_VALUE, true);   
        //double magnitude =0;
        //magnitude = Math.sqrt(oi.getDriveMove() * oi.getDriveMove() + oi.getDriveRotate() * oi.getDriveRotate());
                //magnitude = Math.sqrt((dsIn.getArcadeJoyXRightDriver() * dsIn.getArcadeJoyXRightDriver()
                //+ (dsIn.getArcadeJoyYRightDriver() * dsIn.getArcadeJoyYRightDriver())));
        
//        if(dsIn.getAutoBalance() == true)
//        {
//            //this.arcadeDrive(balance.getTiltCmdVal(), 0.0, sensitive);
//            balance.prnVals();
//        }
//        else
//        {    
        //if (magnitude > deadzone) {
        //    drive.arcadeDrive((oi.getDriveMove() * 0.5), (oi.getDriveRotate() * 0.7), true);
        //} else {
        //}
        
        //drive.arcadeDrive(oi.getDriveMove(), oi.getDriveRotate(), true);
        //drive.testVictor.set(oi.getDriveMove());
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *
     * @return
     */
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    /**
     *
     */
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     *
     */
    protected void interrupted() {
    }
}
