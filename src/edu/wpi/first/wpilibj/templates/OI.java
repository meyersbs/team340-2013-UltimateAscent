package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.templates.commands.ALLSTOP;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    /**
     * Declaration of main driver buttons on the xbox controller
     */
    Joystick xBoxDriverController = new Joystick(1);
    Button driverButtonA = new JoystickButton(xBoxDriverController, 1),
        driverButtonB = new JoystickButton(xBoxDriverController, 2),
        driverButtonX = new JoystickButton(xBoxDriverController, 3),
        driverButtonY = new JoystickButton(xBoxDriverController, 4),
        driverButtonLB = new JoystickButton(xBoxDriverController, 5),
        driverButtonRB = new JoystickButton(xBoxDriverController, 6),
        driverButtonBack = new JoystickButton(xBoxDriverController, 7),
        driverButtonStart = new JoystickButton(xBoxDriverController, 8);
    
    /**
     * Declaration of co-driver buttons on the xbox controller
     */
    Joystick xBoxCoDriverController = new Joystick(2);
    Button coButtonA = new JoystickButton(xBoxCoDriverController, 1),
        coButtonB = new JoystickButton(xBoxCoDriverController, 2),
        coButtonX = new JoystickButton(xBoxCoDriverController, 3),
        coButtonY = new JoystickButton(xBoxCoDriverController, 4),
        coButtonLB = new JoystickButton(xBoxCoDriverController, 5),
        coButtonRB = new JoystickButton(xBoxCoDriverController, 6),
        coButtonBack = new JoystickButton(xBoxCoDriverController, 7),
        coButtonStart = new JoystickButton(xBoxCoDriverController, 8),
        coButtonRightJoy = new JoystickButton(xBoxCoDriverController, 10);
    
    /**
     * Returns the trigger as a button.
     */
    public class CoRightTrig extends Button{
        public boolean get(){
            return (xBoxCoDriverController.getRawAxis(3) < -.5);
        }
    }
    
    public class CoLeftTrig extends Button{
        public boolean get(){
            return (xBoxCoDriverController.getRawAxis(3) > .5);
        }
    }    
    CoRightTrig coRightTrig = new CoRightTrig();
    CoLeftTrig coLeftTrig = new CoLeftTrig();
    /**
     * Declaration of dseio manual override buttons
     */
    private DriverStationEnhancedIO dseio;
    Button shooterHigh = new DigitalIOButton(1); 
    Button shooterPym = new DigitalIOButton(2); 
    Button weightFront = new DigitalIOButton(4); 
    Button weightBack = new DigitalIOButton(3);
    Button clawUp = new DigitalIOButton(5);
    Button clawDown = new DigitalIOButton(6);
    Button shooterShoot = new DigitalIOButton(7);     
    Button allStopButton = new DigitalIOButton(8);

    
    /**
     * Sets all buttons to do what they are intended to
     * Creates dseio driverstation
     */
    public OI()
    {       
        dseio = DriverStation.getInstance().getEnhancedIO();
        
        //Driver Buttons
        driverButtonA.whenPressed(new CG_FinishClimbing_OldShooter());
        //driverButtonB.whenPressed();
        driverButtonX.whenPressed(new COMP_On());
        driverButtonY.whenPressed(new COMP_Off());
        driverButtonStart.whenPressed(new CG_ClimbAllTheWay());
        //Back Button Reserved for Climb Logic
        driverButtonRB.whenPressed(new DRIVE_ShiftHigh());
        driverButtonLB.whenPressed(new DRIVE_ShiftLow());
        
        //CoDriver Buttons
//        coButtonA.whenPressed(new SHOOT_SetMedGoal());
//        coButtonB.whenPressed(new WT_ShiftWeightBackward());
//        coButtonX.whenPressed(new WT_GoToDrivePos(2));
//        coButtonY.whenPressed(new SHOOT_SetHighGoal());
//        coButtonRB.whenPressed(new SHOOT_FireOnce());    
//        //coButtonLB.whenPressed(new SHOOT_RapidFire());
//        coButtonStart.whenPressed(new SHOOT_SetPymGoal());
//        coButtonBack.whenPressed(new SHOOT_SetPymGoalShootOnPym());
//        coButtonRightJoy.whenPressed(new SHOOT_SetPymGoalShootOnPym());
//        coRightTrig.whileHeld(new SHOOT_FeedForward());
        coButtonA.whenPressed(new Shoot_PropellerUnLock());
        coButtonA.whenReleased(new Shoot_PropellerLock());
        coButtonX.whenPressed(new Shoot_Off());     
        coButtonY.whenPressed(new Shoot_ShooterSpeed(RobotMap.highShooterVoltage));
	coButtonB.whenPressed(new Shoot_PropellerOn(-.5));
	coButtonB.whenReleased(new Shoot_PropellerOn(0.0));
        coButtonRB.whenPressed(new Shoot_PropellerOn(.75));
        coButtonRB.whenReleased(new Shoot_PropellerHome());
        //coButtonBack.whenPressed(new MAN_PropellerBackward());
        //coButtonStart.whenPressed(new MAN_PropellerForward());
        
        //Manual Override Buttons
        clawUp.whenPressed(new MAN_ClawUp());
        clawDown.whenPressed(new MAN_ClawDown());
        weightFront.whenPressed(new MAN_WeightShiftFront());//Print out message whenPressed.
        weightBack.whenPressed(new MAN_WeightShiftBack());
        shooterHigh.whenPressed(new MAN_ShootSetSpeedHigh());
        shooterPym.whenPressed(new MAN_ShootSetSpeedPym());
        //hooterShoot.whenPressed(new MAN_ShooterShoot());
    }
    
    /**
     * Shows if button A is pressed on the driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonAPressed()
    {
        return driverButtonA.get();
    }
    /**
     * Shows if button B is pressed on the driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonBPressed()
    {
        return driverButtonB.get();
    }
    /**
     * Shows if button X is pressed on the driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonXPressed()
    {
        return driverButtonX.get();
    }
    /**
     * Shows if button Y is pressed on the driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonYPressed()
    {
        return driverButtonY.get();
    }
    /**
     * Shows if button LB is pressed on the driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonLBPressed()
    {
        return driverButtonLB.get();
    }
    
    /**
     * 
     * @return double the co-drivers left joystick y value.
     */
    public double getCoLftJoyY()
    {
        return this.xBoxCoDriverController.getRawAxis(2);
    }
    
    /**
     * Shows if button RB is pressed on the co-driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonRBPressed()
    {
        return driverButtonRB.get();
    }
    /**
     * Shows if button START is pressed on the co-driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonStartPressed()
    {
        return driverButtonStart.get();
    }
    /**
     * Shows if button BACK is pressed on the co-driver controller.
     * @return boolean the position of the button
     */
    public boolean buttonBackPressed()
    {
        return driverButtonBack.get();
    }
   
//    //get methods for all of those "buttons" from the dseio
//    public boolean getClawUpButton(){
//        return clawUp.get();
//    }
//    public boolean getClawDownButton(){
//        return clawDown.get();
//    }
//    public boolean getWeightFrontButton(){
//        return weightFront.get();
//    }
//    public boolean getWeightBackButton(){
//        return weightBack.get();
//    }
    /**
     * Getter method for the DSEIO
     * @return Returns the DSEIO
     */
    public DriverStationEnhancedIO getDseio() {
        return dseio;
    }
    /**
     * Getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public Button getClawUp() {
        return clawUp;
    }
    /**
     * Getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public Button getClawDown() {
        return clawDown;
    }
    /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public Button getWeightFront() {
        return weightFront;
    }
    /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public Button getWeightBack() {
        return weightBack;
    }
    /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public Button getShooterHigh() {
        return shooterHigh;
    }
    /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public Button getShooterLow() {
        return shooterPym;
    }
    /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public Button getShooterShoot() {
        return shooterShoot;
    }
    /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public boolean getShooterHighButton(){
        return shooterHigh.get();
    }
     /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public boolean getShooterPymButton(){
        return shooterPym.get();
    }
     /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public boolean getShooterShootButton(){
        return shooterShoot.get();
    }
    /**
     * getter method for the DSEIO button
     * @return returns if the button is pressed or not
     */
    public boolean getAllStopButton(){
        return allStopButton.get();
    }
    /**
     * Getter method for Xbox Button 
     * @return returns if the is pressed or not
     */
    public Joystick getxBoxDriverController() {
        return xBoxDriverController;
    }
    /**
     * Getter method for X-Axis
     * @return returns the position of the X-axis
     */
    public double getDriveMove(){
        return xBoxDriverController.getRawAxis(2); //x-axis
    }
    /**
     * Getter Method for Y-Axis
     * @return returns the position of the Y-axis
     */
    public double getDriveRotate(){
        return xBoxDriverController.getRawAxis(1); //y-axis
    }
    
    /**
     * Accessor Method for the X-Axis.
     * @return double for the X-Axis
     */
    public double getDriveSlowMove(){
        return xBoxDriverController.getRawAxis(3);
    }
    
    /**
     * Accessor Method for the Y-Axis
     * @return double for Y-Axis.
     */
    public double getDriveSlowRotate(){
        return xBoxDriverController.getRawAxis(4);
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getDriverButtonA() {
        return driverButtonA;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getDriverButtonB() {
        return driverButtonB;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getButtonX() {
        return driverButtonX;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getDriverButtonY() {
        return driverButtonY;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getDriverButtonLB() {
        return driverButtonLB;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getDriverButtonRB() {
        return driverButtonRB;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getDriverButtonBack() {
        return driverButtonBack;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getButtonStart() {
        return driverButtonStart;
    }
     /**
     * Getter method for driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Joystick getxBoxCoDriverController() {
        return xBoxCoDriverController;
    }
     /**
     * Getter method for Co-driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public boolean getCoButtonA() {
        return coButtonA.get();
    }
     /**
     * Getter method for Co-driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getCoButtonB() {
        return coButtonB;
    }
     /**
     * Getter method for Co-driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public Button getCoButtonX() {
        return coButtonX;
    }
     /**
     * Getter method for Co-driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public boolean getCoButtonY() {
        return coButtonY.get();
    }
     /**
     * Getter method for Co-driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public boolean getCoButtonLB() {
        return coButtonLB.get();
    }
     /**
     * Getter method for Co-driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public boolean getCoButtonRB() {
        return coButtonRB.get();
    }
     /**
     * Getter method for Co-driver Xbox Button 
     * @return returns if the is pressed or not
     */
    public boolean getCoButtonBack() {
        return coButtonBack.get();
    }
     /**
     * Getter method for Co-Xbox Button 
     * @return returns if the is pressed or not
     */
    public boolean getCoButtonStart() {
        return coButtonStart.get();
    }
}
