package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.WeightTransferPID;
import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author Ben Meyers
 * Uses the gyro to balance to specified angle within tolerance continously.
 */
public class WT_PIDBalanceForever extends CommandBase {
    private double setPoint;
    
    /**
     *
     * @param setPoint
     * Instantiate setPoint.
     */
    public WT_PIDBalanceForever(double setPoint) {
        requires(weightTransferPID);
        this.setPoint = setPoint;        
    }

    /**
     *Sets tolerance, setPoint, and enables PID.
     */
    protected void initialize() {
        weightTransferPID.setAbsoluteTolerance(1);
        weightTransferPID.setSetpoint(this.setPoint);
        weightTransferPID.enable();
        
    }

    /**
     *Prints out gyro Angle.
     */
    protected void execute() {
        System.out.println("gyro angle: " + weightTransferPID.getGyroAngle()
                + " motor value: " + weightTransferPID.getPosition()
                + " on target: " + weightTransferPID.onTarget()
                + " is swinging: " + weightTransferPID.isNotSwinging());
    }

    /**
     *
     * @return
     * Never finishes
     */
    protected boolean isFinished() {
        
        return false;
    }

    /**
     *Disables PID.
     */
    protected void end() {
        weightTransferPID.disable();
    }

    /**
     *Disables PID.
     */
    protected void interrupted() {
        weightTransferPID.disable();
    }
}