package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Makes sure the solenoid waits .5 seconds until firing again
 *
 * @author CJ Atene
 */
public class SHOOT_RapidFire extends CommandBase {

    private Timer timer;
    private boolean shot = false;
    private boolean doneShooting = false;
    private final int count = 0;

    /**
     *
     */
    public SHOOT_RapidFire() {
        // Use requires() here to declare subsystem dependencies
        requires(score);
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    /**
     * Makes sure that the motor is up to speed before launching the disk
     *
     * @param none
     * @return none
     *
     * @author CJ Atene
     */
    protected void initialize() {
        System.out.println("init SHOOT_RapidFire");
        if(!score.isSpeedControl())
        {     
            System.out.println("\t\t\tset mtr");
        }
        //this.setTimeout(1);
        timer.reset();
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *
     */
    public int state =1;

    protected void execute() {
        double time = timer.get();
        doneShooting = false;
        System.out.println("US:" + drive.getUltrasonicVals());
        
        if(!score.isSpeedControl())
        {     
            //System.out.println("\t\t\tset mtr");
        }
        
        switch(state){
            case 1:
                score.setDiskBlocker(1.0);
                //System.out.print("\tStage 1 :" +score.isThereADisk() + ": Time:" +time +":" );
                if(time>= score.BRUSH_DISC_IN || score.isThereADisk()){
                    score.setDiskBlocker(0);

                    state++;
                    timer.reset();
                    timer.start();
                }
                break;
                
            case 2:
                //System.out.print("\tStage 2 :"+score.isThereADisk() + ": Time:"+time +":" );

                if(time > score.DISC_FALL_DELAY){
                    score.fireOut();
                    timer.reset();
                    timer.start();
                    state ++;
                }
                break;
                
            case 3:
                //System.out.print("\tStage 3 :"+score.isThereADisk() + ": Time:"+time +":" );
                if(time > score.PISTON_MOVE_DELAY){
                    score.fireIn();
                    timer.reset();
                    timer.start();
                    state ++;
                }
                break;
            case 4:
                score.setDiskBlocker(1.0);
                //System.out.print("\tStage 4 :" +score.isThereADisk() + ": Time:" +time +":" );
                if(time>= score.BRUSH_DISC_IN || score.isThereADisk()){
                    score.setDiskBlocker(0);

                    state++;
                    timer.reset();
                    timer.start();
                }
                break;
            case 5:
                //System.out.print("\tStage 5 :" +score.isThereADisk() + ": Time:" +time +":" );

                if(time > score.DISC_FALL_DELAY){
                    if(score.isThereADisk()){
                        state = 2;
                    }else{
                        state++;
                    }
                    timer.reset();
                    timer.start();
                }
                break;
            case 6:
                //System.out.print("\tStage 6 :" +score.isThereADisk() + ": Time:" +time +":" );

                doneShooting = true;
                state = 1;
                break;
                
            default:
                break;
        }
        System.out.println(" stage:" + state);
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Kills program when done but Timing out
     *
     * @param none
     * @return none
     *
     * @author CJ Atene
     */
    protected boolean isFinished() {
        return doneShooting;
    }

    // Called once after isFinished returns true
    /**
     * When it ends it makes sure the FirePin is in
     *
     * @param none
     * @return none
     *
     * @author CJ Atene
     */
    protected void end() {
        score.setSpeed(0);
        score.setDiskBlocker(0);
        score.fireIn();
        System.out.println("end of SHOOT_FireOnce");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     * When the method is interrupted it reverts it to END
     *
     * @param none
     * @return none
     *
     * @author CJ Atene
     */
    protected void interrupted() {
        end();
    }
}