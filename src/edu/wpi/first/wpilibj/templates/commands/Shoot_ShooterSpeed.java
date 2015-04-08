package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 *
 * @author Adam Audycki
 */
public class Shoot_ShooterSpeed extends CommandBase {
    
    public double mySpeed;
    public Shoot_ShooterSpeed(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shoot);
        mySpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	shoot.disable();
	shoot.enable();
        shoot.setSpeed(mySpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shoot.setSpeed(mySpeed);
	shoot.upkeepSpeedControl();
        shoot.shooterRamp();
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
