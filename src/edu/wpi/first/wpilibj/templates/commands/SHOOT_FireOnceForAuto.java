package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *Makes sure the solenoid waits .5 seconds until firing again
 * @author CJ Atene
 */
public class SHOOT_FireOnceForAuto extends CommandBase 
{
    private Timer timer;
    private boolean shot = false;
    private boolean firstDiscLoaded = false;
    /**
     *
     */
    public SHOOT_FireOnceForAuto() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(score);
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    /**
     *Makes sure that the motor is up to speed before launching the disk
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected void initialize() 
    {
        System.out.println("init SHOOT_FireOnce");
        if(!score.isSpeedControl())
        {     
            System.out.println("\t\t\tset mtr");
            //score.setSpeed(oi.getCoLftJoyY() * 10);
        }
        //this.setTimeout(1);
        timer.reset();
        timer.start();
        score.setSpeed(score.highGoalSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *
     */
    public int state =1;
    protected void execute() 
    {
        double time = timer.get();
        shot = false;
        
//        if(score.getShooterMotorSpeed() == 0 && !score.isSpeedControl())
//        {     
//            score.setSpeed(8);
//
//        }
        
        switch(state){
            case 1:
                score.fireIn();
                if(!score.isThereADisk())
                {
                      score.setDiskBlocker(1.0);
                }
              
                

                System.out.print("\tStage 1 :" +score.isThereADisk() + ": Time:" +time +":" );
                if(time>= score.BRUSH_DISC_IN || score.isThereADisk()){
                    score.setDiskBlocker(0);

                    state++;
                    timer.reset();
                    timer.start();
                }
                break;
                
            case 2:
                System.out.print("\tStage 2 :"+score.isThereADisk() + ": Time:"+time +":" );

                if(time > score.DISC_FALL_DELAY){
                    score.fireOut();
                    timer.reset();
                    timer.start();
                    state ++;
                }
                break;
                
            case 3:
                System.out.print("\tStage 3 :"+score.isThereADisk() + ": Time:"+time +":" );
                if(time > score.PISTON_MOVE_DELAY){//TODO: change to look at limit switch
                    score.fireIn();
                    timer.reset();
                    timer.start();
                    state  = 99;
                    shot = true;
                }
                break;
            default:
                break;
        }
//        if(!firstDiscLoaded && !score.isThereADisk() && time < score.DISC_FALL_DELAY){
//            
//        }else if ((!firstDiscLoaded && score.isThereADisk()) || (!firstDiscLoaded && time >= score.DISC_FALL_DELAY)){
//            System.out.println("Stage 2 :" +score.isThereADisk() + ": Time:" +time +":" );
//            score.setDiskBlocker(0);
//            firstDiscLoaded = true;
//            timer.reset();
//            timer.start();
//        }
//        else if (time < score.PISTON_MOVE_DELAY) {
//            System.out.println("Stage 3");
//            score.setDiskBlocker(0);
//            score.fireOut();
//        } else if (time >= score.PISTON_MOVE_DELAY && time < score.PISTON_TIME_RESET) {
//            System.out.println("Stage 4");
//            score.fireIn();
//        } else {
//            System.out.println("done");
//            score.setDiskBlocker(0);
//            timer.reset();
//            shot = true;
//        }

        System.out.println(" state :" + state + ":");
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *Kills program when done but Timing out
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected boolean isFinished() 
    {
        System.out.println("DONE SHOOTING");
        return shot || oi.buttonBackPressed();
    }

    // Called once after isFinished returns true
    /**
     *When it ends it makes sure the FirePin is in
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected void end() 
    {
        state = 1;
        //score.setSpeed(0);
        System.out.println("end of SHOOT_FireOnce");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     *When the method is interrupted it reverts it to END
     * @param none
     * @return none
     * 
     * @author CJ Atene
     */
    protected void interrupted() 
    {
        //score.setSpeed(0);
        //end();
    }
}
