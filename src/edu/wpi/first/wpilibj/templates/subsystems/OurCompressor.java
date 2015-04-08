package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.templates.commands.COMP_On;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author CJ
 */
public class OurCompressor extends Subsystem 
{
    //private Compressor compressor;
    private boolean compressorState;
    private Relay compressor;
    private DigitalInput presSwitch;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public OurCompressor() {        
        compressor = new Relay(3);
        presSwitch = new DigitalInput(13);
        compressorState = true;
        //relay = new Relay(3);
    }

    public void initDefaultCommand() 
    {
        setDefaultCommand(new COMP_On());    
        // Set the default command for a subsystem here.
        //
    }
    
    public boolean isSwitchOn(){
        return presSwitch.get();
    }
    
    public void compressorOn()
    {
        compressor.set(Relay.Value.kForward);
        //relay.set(Relay.Value.kOn);
        //System.out.println("Compressor Turned On.");
        //compressorState = true;
    }
    
    public void compressorOff()
    {
        compressor.set(Relay.Value.kOff);
        //System.out.println("Compressor Turned Off.");
        compressorState = false;
    }
    
    public boolean isCompressorRunning()
    {
        return compressor.get() == Relay.Value.kForward;
    }
}