package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This class is designed to handle the case where there is a {@link Subsystem}
 * which uses a single {@link PIDController} almost constantly (for instance, an
 * elevator which attempts to stay at a constant height).
 *
 * <p>It provides some convenience methods to run an internal {@link PIDController}.
 * It also allows access to the internal {@link PIDController} in order to give
 * total control to the programmer.</p>
 *
 * @author Joe Grinstead, Matt Stafford.
 */
public abstract class SuperPIDSubsystem extends Subsystem implements Sendable {

    /**
     * The internal {@link PIDController}
     */
    private PIDController controller1;//encoder
    private PIDController controller2;//encoder
    /**
     * An output which calls {@link PIDCommand#usePIDOutput(double)}
     */
    private PIDOutput output1 = new PIDOutput() {

        public void pidWrite(double output) {
            usePIDOutput1(output);
        }
    };
    private PIDOutput output2 = new PIDOutput() {

        public void pidWrite(double output) {
            usePIDOutput2(output);
        }
    };
    /**
     * A source which calls {@link PIDCommand#returnPIDInput()}
     */
    private PIDSource source1 = new PIDSource() {

        /*
         * This returns the pid input#
         */
        public double pidGet() {
            return returnPIDInput1();
        }
    };
    private PIDSource source2 = new PIDSource() {

        /*
         * This returns the pid input#
         */
        public double pidGet() {
            return returnPIDInput2();
        }
    };

    /**
     * Instantiates a {@link PIDSubsystem} that will use the given p, i and d
     * values.
     *
     * @param name the name
     * @param p the proportional value
     * @param i the integral value
     * @param d the derivative value
     */
    public SuperPIDSubsystem(String name, double p, double i, double d) {
        controller1 = new PIDController(p, i, d, source1, output1);
        controller2 = new PIDController(p, i, d, source2, output2);

    }

    /**
     * Instantiates a {@link PIDSubsystem} that will use the given p, i and d
     * values.
     *
     * @param name the name
     * @param p the proportional value
     * @param i the integral value
     * @param d the derivative value
     * @param f the feed forward value
     */
    public SuperPIDSubsystem(String name, double p, double i, double d, double f) {

        controller1 = new PIDController(p, i, d, f, source1, output1);
        controller2 = new PIDController(p, i, d, f, source2, output2);
    }

    /**
     * Instantiates a {@link PIDSubsystem} that will use the given p, i and d
     * values. It will also space the time between PID loop calculations to be
     * equal to the given period.
     *
     * @param name the name
     * @param p the proportional value
     * @param i the integral value
     * @param d the derivative value
     * @param period the time (in seconds) between calculations
     */
    public SuperPIDSubsystem(String name, double p, double i, double d, double f, double period) {

        controller1 = new PIDController(p, i, d, f, source1, output1, period);
        controller2 = new PIDController(p, i, d, f, source2, output2, period);
    }

    /**
     * Instantiates a {@link PIDSubsystem} that will use the given p, i and d
     * values. It will use the class name as its name.
     *
     * @param p the proportional value
     * @param i the integral value
     * @param d the derivative value
     */
    public SuperPIDSubsystem(double p, double i, double d) {
        controller1 = new PIDController(p, i, d, source1, output1);
        controller2 = new PIDController(p, i, d, source2, output2);

    }

    /**
     * Instantiates a {@link PIDSubsystem} that will use the given p, i and d
     * values. It will use the class name as its name. It will also space the
     * time between PID loop calculations to be equal to the given period.
     *
     * @param p the proportional value
     * @param i the integral value
     * @param d the derivative value
     * @param f the feed forward coefficient
     * @param period the time (in seconds) between calculations
     */
    public SuperPIDSubsystem(double p, double i, double d, double period, double f) {
        controller1 = new PIDController(p, i, d, f, source1, output1, period);
        controller2 = new PIDController(p, i, d, f, source2, output2, period);

    }

    /**
     * Instantiates a {@link PIDSubsystem} that will use the given p, i and d
     * values. It will use the class name as its name. It will also space the
     * time between PID loop calculations to be equal to the given period.
     *
     * @param p the proportional value
     * @param i the integral value
     * @param d the derivative value
     * @param period the time (in seconds) between calculations
     */
    public SuperPIDSubsystem(double p, double i, double d, double period) {
        controller1 = new PIDController(p, i, d, source1, output1, period);
        controller2 = new PIDController(p, i, d, source2, output2, period);
    }

    /**
     * Returns the {@link PIDController} used by this {@link PIDSubsystem}. Use
     * this if you would like to fine tune the pid loop.
     *
     * <p>Notice that calling {@link PIDController#setSetpoint(double) setSetpoint(...)}
     * on the controller will not result in the setpoint being trimmed to be in
     * the range defined by {@link PIDSubsystem#setSetpointRange(double, double) setSetpointRange(...)}.</p>
     *
     * @return the {@link PIDController} used by this {@link PIDSubsystem}
     */
    public PIDController getPIDController1() {
        return controller1;
    }

    public PIDController getPIDController2() {
        return controller2;
    }

    /**
     * Adds the given value to the setpoint. If {@link PIDCommand#setRange(double, double) setRange(...)}
     * was used, then the bounds will still be honored by this method.
     *
     * @param deltaSetpoint the change in the setpoint
     */
    /*
     * TODO: This SetpointRelative method
     */
    public void setSetpointRelativeController1(double deltaSetpoint) {
        setSetpointController1(getPosition1() + deltaSetpoint);
    }

    public void setSetpointRelativeController2(double deltaSetpoint) {
        setSetpointController2(getPosition2() + deltaSetpoint);
    }

    /**
     * Sets the setpoint to the given value. If {@link PIDCommand#setRange(double, double) setRange(...)}
     * was called, then the given setpoint will be trimmed to fit within the
     * range.
     *
     * @param setpoint the new setpoint
     */
    public void setSetpointController1(double setpoint) {
        controller1.setSetpoint(setpoint);
    }

    public void setSetpointController2(double setpoint) {
        controller2.setSetpoint(setpoint);
    }

    /**
     * Returns the setpoint.
     *
     * @return the setpoint
     */
    public double getSetpoint1() {
        return controller1.getSetpoint();
    }

    public double getSetpoint2() {
        return controller2.getSetpoint();
    }

    /**
     * Returns the current position
     *
     * @return the current position
     */
    public double getPosition1() {
        return returnPIDInput1();
    }

    public double getPosition2() {
        return returnPIDInput2();
    }

    /**
     * Sets the maximum and minimum values expected from the input.
     *
     * @param minimumInput the minimum value expected from the input
     * @param maximumInput the maximum value expected from the output
     */
    public void setInputRangeController1(double minimumInput, double maximumInput) {
        controller1.setInputRange(minimumInput, maximumInput);
    }

    public void setInputRangeController2(double minimumInput, double maximumInput) {
        controller2.setInputRange(minimumInput, maximumInput);
    }

    /**
     * Set the absolute error which is considered tolerable for use with
     * OnTarget. The value is in the same range as the PIDInput values.
     *
     * @param t A PIDController.Tolerance object instance that is for example
     * AbsoluteTolerance or PercentageTolerance. E.g. setTolerance(new
     * PIDController.AbsoluteTolerance(0.1))
     */
    public void setAbsoluteToleranceController1(double t) {
        controller1.setAbsoluteTolerance(t);
    }

    public void setAbsoluteToleranceController2(double t) {
        controller2.setAbsoluteTolerance(t);
    }

    /**
     * Set the percentage error which is considered tolerable for use with
     * OnTarget. (Value of 15.0 == 15 percent)
     *
     * @param t A PIDController.Tolerance object instance that is for example
     * AbsoluteTolerance or PercentageTolerance. E.g. setTolerance(new
     * PIDController.AbsoluteTolerance(0.1))
     */
    public void setPercentToleranceController1(double p) {
        controller1.setPercentTolerance(p);
    }

    public void setPercentToleranceController2(double p) {
        controller2.setPercentTolerance(p);
    }

    /**
     * Return true if the error is within the percentage of the total input
     * range, determined by setTolerance. This assumes that the maximum and
     * minimum input were set using setInput.
     *
     * @return true if the error is less than the tolerance
     */
    public boolean onTargetController1() {
        return controller1.onTarget();
    }

    public boolean onTargetController2() {
        return controller2.onTarget();
    }

    /**
     * Returns the input for the pid loop.
     *
     * <p>It returns the input for the pid loop, so if this Subsystem was based
     * off of a gyro, then it should return the angle of the gyro</p>
     *
     * <p>All subclasses of {@link PIDSubsystem} must override this method.</p>
     *
     * @return the value the PID loop should use as input
     */
    protected abstract double returnPIDInput1();

    protected abstract double returnPIDInput2();

    /**
     * Uses the value that the pid loop calculated. The calculated value is the
     * "output" parameter. This method is a good time to set motor values, maybe
     * something along the lines of
     * <code>driveline.tankDrive(output, -output)</code>
     *
     * <p>All subclasses of {@link PIDSubsystem} must override this method.</p>
     *
     * @param output the value the pid loop calculated
     */
    protected abstract void usePIDOutput1(double output);

    protected abstract void usePIDOutput2(double output);


    /**
     * Enables the internal {@link PIDController}
     */
    public void enableContoller1() {
        controller1.enable();
    }

    public void enableContoller2() {
        controller2.enable();
    }

    /**
     * Disables the internal {@link PIDController}
     */
    public void disableController1() {
        controller1.disable();
    }

    public void disableController2() {
        controller2.disable();
    }

    public String getSmartDashboardType() {
        return "PIDSubsystem";
    }

    public void initTableController1(ITable table) {
        controller1.initTable(table);
        this.initTable(table);
    }

    public void initTableController2(ITable table) {
        controller2.initTable(table);
        this.initTable(table);
    }
}