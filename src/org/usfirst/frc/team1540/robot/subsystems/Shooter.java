package org.usfirst.frc.team1540.robot.subsystems;

import org.team1540.base.power.PowerManageable;
import org.usfirst.frc.team1540.robot.Robot;
import org.usfirst.frc.team1540.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem implements PowerManageable {

	private double priority = 10;
	private final Object motorLock = new Object();

	private final CANTalon flywheelLeftTalon = new CANTalon(RobotMap.flywheelL);
	private final CANTalon flywheelRightTalon = new CANTalon(RobotMap.flywheelR);
	
	public Shooter() {
		flywheelLeftTalon.setFeedbackDevice(FeedbackDevice.EncRising);
        flywheelLeftTalon.reverseSensor(false);
        flywheelLeftTalon.reverseOutput(false);
        flywheelLeftTalon.configNominalOutputVoltage(+0f, -0f);
        flywheelLeftTalon.configPeakOutputVoltage(+12f, -12f);
        flywheelLeftTalon.configEncoderCodesPerRev(1024);
        flywheelLeftTalon.changeControlMode(TalonControlMode.Speed);
        flywheelRightTalon.reverseSensor(false);
        flywheelRightTalon.reverseOutput(false);
        flywheelRightTalon.changeControlMode(TalonControlMode.Follower);
        flywheelRightTalon.set(flywheelLeftTalon.getDeviceID());
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void set(double value) {
		flywheelLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
		flywheelRightTalon.changeControlMode(TalonControlMode.Follower);
        flywheelRightTalon.set(flywheelLeftTalon.getDeviceID());
        flywheelLeftTalon.set(value);
        
	}
	
	public void stop() {
		flywheelLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
		flywheelRightTalon.changeControlMode(TalonControlMode.PercentVbus);
		flywheelLeftTalon.set(0);
		flywheelRightTalon.set(0);
	}
	
	public void setSpeed(double rpm) {
		flywheelLeftTalon.changeControlMode(TalonControlMode.Speed);
		flywheelRightTalon.changeControlMode(TalonControlMode.Follower);
        flywheelRightTalon.set(flywheelLeftTalon.getDeviceID());
		flywheelLeftTalon.setSetpoint(rpm);
	}
	
	public double getSetpoint() {
		return flywheelLeftTalon.getSetpoint();
	}
	
	public double getSpeed() {
//		return flywheelLeftTalon.getSpeed();
		return flywheelLeftTalon.getEncVelocity();
	}
	
	public boolean upToSpeed(double rpm) {
		return Math.abs(rpm - getSpeed()) < Robot.tuning.getFlywheelSpeedMarginOfError();
	}

	@Override
	public double getPriority() {
		return priority;
	}

	@Override
	public void setPriority(double priority) {
		this.priority = priority;
	}

	@Override
	public double getCurrent() {
			return flywheelLeftTalon.getOutputCurrent() + flywheelRightTalon.getOutputCurrent();
	}

	@Override
	public void limitPower(double limit) {
		synchronized (motorLock) {
			flywheelLeftTalon.EnableCurrentLimit(true);
			flywheelRightTalon.EnableCurrentLimit(true);

			flywheelLeftTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 2)));
			flywheelRightTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 2)));
		}
	}

	@Override
	public void stopLimitingPower() {
		synchronized (motorLock) {
			flywheelLeftTalon.EnableCurrentLimit(false);
			flywheelRightTalon.EnableCurrentLimit(false);
		}
	}

}
