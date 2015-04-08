package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.WeightTransferPID;

/**
 *
 * @author Pat Godard
 * Manual override for shifting the weight forwards, ignores all sensors
 * ALL MANUAL OVERRIDE METHODS CAN BREAK THE ROBOT USE WITH CAUTION
 */
public class MAN_WeightShiftFront extends CommandBase {
    
    public MAN_WeightShiftFront() {
        requires(weightTransferPID);
    }

    protected void initialize() {
        System.out.println("Weight Front Button Pressed");
    }
    /**
     * Turns the weight transfer motor on
     */
    protected void execute() {
        System.out.println("Shifting Weight Forward");
        weightTransferPID.moveForward(.5);
    }
    /**
     * Finishes when the manual override button is released
     */
    protected boolean isFinished() {
        return !oi.getWeightFront().get();
    }
    /**
     * Stops the motor
     */
    protected void end() {
        System.out.println("Stopping Forward Weight Shift");
        weightTransferPID.stopMove();
    }
    /**
     * Stops the motor
     */
    protected void interrupted() {
        weightTransferPID.stopMove();
    }
}