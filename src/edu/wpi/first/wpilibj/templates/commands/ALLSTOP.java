package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author meyersbs
 */
public class ALLSTOP extends CommandGroup {
    
    public ALLSTOP() {
        addSequential(new CLIMB_Stop());
    }
}
