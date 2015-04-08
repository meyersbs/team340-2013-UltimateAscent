 //TODO:Clean old code, Fix autoGo methods, Fix gyro name.
/*
 * @author CJ Atene
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.templates.commands.DRIVE_Joysticks;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author CJ Atene
 */
public class Drive extends SuperPIDSubsystem {
    /**********STOLEN FROM LAST YEAR FOR THIS YEAR'S AUTONOMOUS CODE***********/
    //Ultrasonic Variables
    private final int maxNumValues = 6;
    private int nextSampleLocation = 0;
    private int numSamples = 0;
    private double[] ultraAverage = new double[maxNumValues];
    private int itr;
    private double calcDist = 0;
    //Special Drive Variables
    private boolean isFirstTimedGoLoop = true;
    private final int LEFT_MULT = 1;
    private final int RIGHT_MULT = -1;
    private Timer myTimer;
    /**************************************************************************/
    
    
    CANJaguar leftDrive1;
    CANJaguar leftDrive2;
    CANJaguar rightDrive1;
    CANJaguar rightDrive2;
    Solenoid shifter;
    private static final double Kp = 0.1;
    private static final double Ki = 0.05;
    private static final double Kd = 0.125;
    
    AnalogChannel driveGyro = RobotMap.DRIVE_GYRO;
    private static Gyro gyroDrive;    
   
    /**
     *
     */
    public Victor testVictor;
    //RobotDrive drive;
    AnalogChannel ultra = RobotMap.ULTRA;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /**
     *
     */
    public  Drive()
    {
        super("drive" ,Kp,Ki,Kd);
        testVictor = new Victor(2);
        myTimer = new Timer(); //For Autonomous
        gyroDrive = new Gyro(driveGyro);
        shifter = new Solenoid(3);
        createJags();
        
        try
        {
            //drive = new RobotDrive(leftDrive1, leftDrive2, rightDrive1, rightDrive2);
        }catch (Exception e){
            System.out.println("our own stack trace2\nVV");
            e.printStackTrace();
            System.out.println("^^\nour own stack trace2");            
        }   
    }

    /**
     *
     */
    public void initDefaultCommand() {
        setDefaultCommand(new DRIVE_Joysticks());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     *
     * @return double PID input
     */
    protected double returnPIDInput1() 
    {
        return gyroDrive.pidGet();
    }

    /**
     * 
     * @return double PID input 2
     */
        protected double returnPIDInput2() 
    {
        return ultra.getValue();
    }
    /**
     *
     * @param output
     * uses the PID output
     */
    protected void usePIDOutput1(double output) 
    {
        this.arcadeDrive(0,output,true);
        //TODO: DO SOMETHING        
    }
    
    /**
     * 
     * @param output 
     */
    protected void usePIDOutput2(double output) 
    {
        this.arcadeDrive(output,0,true);
        
        //TODO: DO SOMETHING        
    }
    
    /**
     *
     * @return double gyro angle.
     */
    public double getGyroAngle()
    {
        SmartDashboard.putNumber("Drive_Gyro", gyroDrive.getAngle());
        return gyroDrive.getAngle();
    }    
    
    /**
     * @return void
     */
    public void resetGyro(){
        gyroDrive.reset();
    }
    
    
    /**
     * @param none
     * @return void
     * Shift high.
     */
    public void shiftHigh()
    {
        shifter.set(true);
    }
    
    /**
     * @param none
     * @return void
     * Shift low.
     */    
    public void shiftLow()
    {
        shifter.set(false);
    }
    
/*************************STOLEN FROM LAST YEAR'S CODE**************************/
/*******************************************************************************/    
    public double getUltrasonicValue()
    {
        return ultra.getValue();
    }
    
    /*
     * This code was stolen from last year's optonomousPrime class. It needs to
     * be called once at the beginning of the autonomous mode.
     */
    public double getUltrasonicVals()
    {
        ultraAverage[nextSampleLocation] = getUltrasonicValue() /2;
        //System.out.println("ultra value:: " + ultraAverage[nextSampleLocation]/2);
        if(nextSampleLocation < (maxNumValues-1))
        {
            nextSampleLocation++;
        }
        else
        {
            nextSampleLocation = 0;
        }
        if(numSamples < maxNumValues)
        {
            numSamples++;
        }
        calcDist = 0;
        //System.out.println("\t1 CalcDist: " + calcDist);

        for(itr = 0; itr < numSamples; itr++)
        {
            calcDist = calcDist + ultraAverage[itr];
        }
        //System.out.println("\t2 CalcDist: " + calcDist + "  samples: " + numSamples);

        calcDist = calcDist / numSamples;
        //System.out.println("\t3 CalcDist: " + calcDist);
        SmartDashboard.putNumber("Drive_Ultra",Math.floor(calcDist));
        return calcDist;
    }
    
    public void setLeftDrive(double speed) 
    {

        try {
            leftDrive1.setX(speed);
            leftDrive2.setX(speed);
            SmartDashboard.putBoolean("CANJag2", true);
            SmartDashboard.putBoolean("CANJag3", true);
        } catch (CANTimeoutException e) {
            SmartDashboard.putBoolean("CANJag2", false);
            SmartDashboard.putBoolean("CANJag3", false);
        }
    }

    public void setRightDrive(double speed) 
    {

        try {
            rightDrive1.setX(speed);
            rightDrive2.setX(speed);
            SmartDashboard.putBoolean("CANJag4", true);
            SmartDashboard.putBoolean("CANJag5", true);
        } catch (CANTimeoutException e) {
            SmartDashboard.putBoolean("CANJag4", false);
            SmartDashboard.putBoolean("CANJag5", false);
        }
    }    

    public void go(double speed, double dir) 
    {
        setLeftDrive((speed * LEFT_MULT) + dir);
        setRightDrive((speed * RIGHT_MULT) - dir);
    }    
    
    public boolean TimedGo(double speed, double dir, double goTime, Timer MyTimer) {
        System.out.println("\t\t\tTime <" + MyTimer.get() + ">");

        if (MyTimer.get() <= goTime) {
            this.arcadeDrive(speed, dir, false);
            return false;
        }
        return true;
    }

    public boolean distanceGo(double speed, double dir, double lDist)
    {
        getUltrasonicVals();
        System.out.println("\t\t\tDistance <" + calcDist + ">");
        while(lDist >= (calcDist + 2) && lDist >= (calcDist - 2))
        {
            //this.arcadeDrive(speed, dir,false);
            return false;
        }
        return true;
    }
    
    
    public boolean distanceGoGreaterThan(double speed, double dir, double lDist)
    {
        getUltrasonicVals();
        System.out.println("\t\t\tDistance <" + calcDist + ">");
        if(lDist <= (calcDist + 2)) //TODO: PLUS OR MINUS?
        {
            this.arcadeDrive(speed, dir,false);
            return false;
        }
        return true;
    }
    
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        if (squaredInputs) {
            // square the inputs (while preserving the sign) to increase fine control while permitting full power
            try
            {
//                rOut.leftJaguarBack.configNeutralMode(CANJaguar.NeutralMode.kBrake);
//                rOut.rightJaguarBack.configNeutralMode(CANJaguar.NeutralMode.kBrake);
//                rOut.rightJaguarFront.configNeutralMode(CANJaguar.NeutralMode.kBrake);
//                rOut.leftJaguarFront.configNeutralMode(CANJaguar.NeutralMode.kBrake); 
            }
            catch (Exception e)
            {
                //System.out.println("Exception on public void humanControl");           
            }            
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }
        else
        {
            try
            {
//                rOut.leftJaguarBack.configNeutralMode(CANJaguar.NeutralMode.kCoast);
//                rOut.rightJaguarBack.configNeutralMode(CANJaguar.NeutralMode.kCoast);
//                rOut.rightJaguarFront.configNeutralMode(CANJaguar.NeutralMode.kCoast);
//                rOut.leftJaguarFront.configNeutralMode(CANJaguar.NeutralMode.kCoast); 
            }
            catch (Exception e)
            {
                System.out.println("Error setting to coast in arcadeDrive()");
                e.printStackTrace();           
            }            
        }

        if (moveValue > 0.0) {
            
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        this.setLeftRightMotorOutputs(leftMotorSpeed, -rightMotorSpeed); //rightMotorSpeed negated.
    }

    private void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        this.setLeftDrive(leftOutput);
        this.setRightDrive(rightOutput);
                //System.out.println("2. leftOutput = " + leftOutput);
                //System.out.println("2. rightOutput = " + rightOutput);
 
    }  

    private void createJags() {
        //Construct Left Drives
        try{
            leftDrive1 = new CANJaguar(2);
            leftDrive2 = new CANJaguar(3);
            System.out.println("\nWe made left drive jaguars (2 of them)!");
            SmartDashboard.putBoolean("CANJag2", true);
            SmartDashboard.putBoolean("CANJag3", true);
        }catch(Exception e)
        {
            System.out.println("our own stack trace\nVV");
            e.printStackTrace();
            System.out.println("^^\nour own stack trace");
            SmartDashboard.putBoolean("CANJag2", false);
            SmartDashboard.putBoolean("CANJag3", false);
        }
        //Construct right drives
        try{
            rightDrive1 = new CANJaguar(4);
            rightDrive2 = new CANJaguar(5);
            System.out.println("\nWe made right drive jaguars (2 of them)!");
            SmartDashboard.putBoolean("CANJag4", true);
            SmartDashboard.putBoolean("CANJag5", true);
        }catch(Exception e)
        {
            System.out.println("our own stack trace\nVV");
            e.printStackTrace();
            System.out.println("^^\nour own stack trace");
            SmartDashboard.putBoolean("CANJag4", false);
            SmartDashboard.putBoolean("CANJag5", false);
        }
    }
}