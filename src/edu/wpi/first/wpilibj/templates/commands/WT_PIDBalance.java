//TODO:CLean up old code.
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.WeightTransferPID;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Ben Meyers
 * Uses the gyro to balance to specified angle within tolerance.
 */
public class WT_PIDBalance extends CommandBase {
    private double setPoint;
    private Timer timer;
    
    /**
     *
     * @param setPoint
     * Instantiates the timer, and the PID point.
     */
    public WT_PIDBalance(double setPoint) {
        requires(weightTransferPID);
        this.setPoint = setPoint;
        timer = new Timer();
    }

    /**
     * 
     *Sets tolerance, setPoint, starts timer, and enables PID.
     */
    protected void initialize() {
        weightTransferPID.setAbsoluteTolerance(3); //Was 1
        weightTransferPID.setSetpoint(this.setPoint);
        timer.start();
        weightTransferPID.enable();
    }

    /**
     * @return void
     *Prints out gyro value.
     */
    protected void execute() {
        System.out.println("Gyro Angle: " + weightTransferPID.getGyroAngle() + " motor:" + weightTransferPID.getWeightTransferPIDMotor());        
        /*System.out.println("gyro angle: " + weightTransferPID.getGyroAngle()
                + " motor value: " + weightTransferPID.getPosition()
                + " on target: " + weightTransferPID.onTarget()
                + " is swinging: " + weightTransferPID.isNotSwinging());
           */
    }

    /**
     *
     * @return boolean when we are within the tolerance of the specified angle and we are not swinging.
     */
    protected boolean isFinished() {
        return weightTransferPID.isNotSwinging() && weightTransferPID.onTarget();
    }

    /**
     *Prints the time it took to stabilize, stops timer, disables PID.
     */
    protected void end() {
       // System.out.println("Time to execute : " + timer.get());
        timer.stop();
        weightTransferPID.disable();
    }

    /**
     *Disables PID.
     */
    protected void interrupted() {
        weightTransferPID.disable();
    }
}