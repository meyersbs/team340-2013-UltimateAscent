package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author CJ
 */
public class DS_PrintToLCD extends CommandBase {

    public DS_PrintToLCD() {
        // Use requires() here to declare subsystem dependencies
        requires(noSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        /**
         * *******************************Driver Station LCD*****************************************************************************
         */
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, climb.getCurrentCommand().getName());
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, drive.getCurrentCommand().getName());
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, comp.getCurrentCommand().getName());
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, score.getCurrentCommand().getName());
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, weightTransferPID.getCurrentCommand().getName());
        DriverStationLCD.getInstance().updateLCD();
        /**
         * ************************************SMART DASHBOARD******************************************************************************
         */
        SmartDashboard.putBoolean("WT-Gyro", weightTransferPID.getWeightBackwardLimit());
        SmartDashboard.putBoolean("WT-RLim", weightTransferPID.getWeightBackwardLimit());
        SmartDashboard.putBoolean("WT-FLim", weightTransferPID.getWeightForwardLimit());
        SmartDashboard.putNumber("Lift-Enc", score.getShooterSpeed());
        SmartDashboard.putNumber("Score-Speed", climb.getArmEncoderValue());
        SmartDashboard.putBoolean("IsLBPressed?:O!", oi.buttonLBPressed());
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}