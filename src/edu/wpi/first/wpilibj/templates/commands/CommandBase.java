package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.*;
import edu.wpi.first.wpilibj.templates.commands.WT_ShiftWeightForward;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    /**
     *
     */
    public static OI oi;
    //RobotMap.DRIVE_GYRO =
    public double autoState = SmartDashboard.getNumber("AutoState", 1);
            
    // Create a single static instance of all of your subsystems
    
    /**
     *
     */
    public static Drive drive = new Drive();
    
    /**
     *
     */
    public static Climb climb = new Climb();
    
    /**
     *
     */
    public static Score score = new Score();
    
    /**
     * 
     */
    public static Shoot shoot = new Shoot();
    
    /**
     *
     */
    public static OurCompressor comp = new OurCompressor();
    
    /**
     * 
     */
    public static WeightTransferPID weightTransferPID = new WeightTransferPID();
    
    
    /**
     *
     */
    public static NoSub noSub = new NoSub();
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(weightTransferPID);
        SmartDashboard.putData(score);
        SmartDashboard.putData(comp);
        SmartDashboard.putData(drive);
        SmartDashboard.putData(climb);
        SmartDashboard.putData(shoot);
    }

    /**
     *
     * @param name
     */
    public CommandBase(String name) {
        super(name);
    }

    /**
     *
     */
    public CommandBase() {
        super();
    }
}