package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Ben Meyers
 * Creates the final variables for the PID loop, as well as the swinger sensor, gyro, and the weight motor victor.
 */
public class WeightTransferPID extends PIDSubsystem {

    private static final double Kp = 0.0001; //.1
    private static final double Ki = 0.000000001;//0.005;
    private static final double Kd = 0.0;//0.0125; //.125
    private static final double SWINGING_SENSOR_VOLTAGE_MIN = 2.4;
    private static final double SWINGING_SENSOR_VOLTAGE_MAX = 2.75;
    private static final double FORWARD = 1; //Safe: 0.50
    private static final double BACKWARD = -1;

    private AnalogChannel swingingSensor;
    private Gyro gyro;
    private CANJaguar weightMover;
    private DigitalInput weightForwardLimit;
    private DigitalInput weightBackwardLimit; 
    private DigitalInput weightMiddleLimit;
    
    /**
     * 
     *Creates the swinger sensor, the gyro, and the weight motor victor.
     */
    public WeightTransferPID() {
        super("WeightTransferPID", Kp, Ki, Kd);
        swingingSensor = new AnalogChannel(1);
        gyro = new Gyro(swingingSensor);
        try{
            weightMover = new CANJaguar(6);
            System.out.println("\n\nWe made the weightTransfer jaguar\n\n");
            SmartDashboard.putBoolean("CANJag6", true);
        }catch(Exception e)
        {
            e.printStackTrace();
            SmartDashboard.putBoolean("CANJag6", false);
        }
        weightForwardLimit = new DigitalInput(6);
        weightBackwardLimit = new DigitalInput(5);
        weightMiddleLimit = new DigitalInput(14);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    /**
     *
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     *
     * @return double PID input
     */
    protected double returnPIDInput() 
    {
        return gyro.pidGet();
    }
    
    /**
     *
     * @param output
     * uses the PID output
     */
    protected void usePIDOutput(double output) 
    {
        if(output > .75)//foreward?
        {
            output = .75;
        }
        else if(output < -.75)//backward?
        {
            output = -.75;
        }

        if (output <.7 && output >0){
            output = .7;
        }else if(output >-.7 && output <0){
            output = -.7;
        }
        
        if(output <0 && this.getWeightForwardLimit()){
            System.out.println("\t\tSTOP !!! fwd limit hit mtr:" + output);
            output = 0;
            
        }else if(output >0 && this.getWeightBackwardLimit()){
            System.out.println("\t\tSTOP !!! bwd limit hit mtr:" + output);
            output =0;
        }
        System.out.println("\t\tWT motor value:" + output);
        try{
            weightMover.setX(-output); 
            SmartDashboard.putBoolean("CANJag6", true);
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
        }
    }
    
    /**
     *
     * @return double gyro angle.
     */
    public double getGyroAngle()
    {
        SmartDashboard.putNumber("WT_Gyro", Math.floor(gyro.getAngle()));
        return gyro.getAngle();
    }
    
    /**
     * @return boolean when front limit switch is pressed.
     */
    public boolean getWeightForwardLimit()
    {
        return !weightForwardLimit.get(); //Limit wired normally true.
    }
    
    /**
     * @return boolean when back limit switch is pressed.
     */
    public boolean getWeightBackwardLimit()
    {
        return !weightBackwardLimit.get(); //Limit wired normally true.
    }
    
    public boolean getWeightMiddleLimit()
    {
        return weightMiddleLimit.get();
    }
    
    /**
     *
     * @return boolean when the robot is not swinging.
     */
    public boolean isNotSwinging(){
        return (swingingSensor.getAverageVoltage() > SWINGING_SENSOR_VOLTAGE_MIN && swingingSensor.getAverageVoltage()<SWINGING_SENSOR_VOLTAGE_MAX) ;
    }
    /**
     * 
     * @return boolean true when weightmover isn't 0.
     */
    
    public boolean isMotorOn()
    {
        try{
            if(weightMover.getX() != 0)
            {
                SmartDashboard.putBoolean("CANJag6", true);
                return true;
            }
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
        }
        return false;
    }
    
    /**
     * @return void
     * @param speed 
     */
    public void setWeightTransferPIDMotor(double speed)
    {
        try{
            weightMover.setX(speed);
            SmartDashboard.putBoolean("CANJag6", true);
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
            e.printStackTrace();;
        }
    }
    
    public double getWeightTransferPIDMotor(){
        try {
            return weightMover.getX();
        } catch (CANTimeoutException ex) {
            SmartDashboard.putBoolean("CANJag6", false);
            ex.printStackTrace();
        }
        return -99999;
    }
    
    /**
     * @return void
     *Moves forward
     */
    public void moveForward()
    {
        try{   
            weightMover.setX(FORWARD);
            SmartDashboard.putBoolean("CANJag6", true);
            //System.out.println("Weight moving Forward: " + weightMover.getX());
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
            e.printStackTrace();
        }
    }
    
    /**
     * @return void
     *Moves backward
     */
    public void moveBackward()
    {
        try{   
            weightMover.setX(BACKWARD);
            SmartDashboard.putBoolean("CANJag6", true);
            //System.out.println("Weight moving Backward: " + weightMover.getX());
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
            e.printStackTrace();
        }
    }
    
    /**
     * @return void
     *Moves forward
     */
    public void moveForward(double mve)
    {
        try{   
            weightMover.setX(mve);
            SmartDashboard.putBoolean("CANJag6", true);
            //System.out.println("Weight moving Forward: " + weightMover.getX());
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
            e.printStackTrace();
        }
    }    
    
    public void moveBackward(double mve)
    {
        if (mve >0){
            mve = -mve;
        }
        try{   
            weightMover.setX(mve);
            SmartDashboard.putBoolean("CANJag6", true);
            //System.out.println("Weight moving Backward: " + weightMover.getX());
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
            e.printStackTrace();
        }
    }    

    /**
     * @return void
     *Stops motor
     */
    public void stopMove()
    {
        try{   
            weightMover.setX(0);
            SmartDashboard.putBoolean("CANJag6", true);
        }catch(CANTimeoutException e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
            e.printStackTrace();
        }
    } 
    
    /**
     * @return void
     * Resets Gyro
     */
    public void resetGyro()
    {
        gyro.reset();
    }
    
    public double getVoltage()
    {
        return swingingSensor.getVoltage();
    }
    
    public boolean isCurrentBad()
    {
        try{
            return Math.abs(weightMover.getOutputCurrent()) > 40;
        }catch(Exception e)
        {
            SmartDashboard.putBoolean("CANJag6", false);
            return false;
        }
    }
}