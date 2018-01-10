package org.usfirst.frc.team1540.robot.subsystems;

import org.team1540.base.power.PowerManageable;
import org.usfirst.frc.team1540.robot.Robot;
import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.RobotUtil;
import org.usfirst.frc.team1540.robot.commands.TankDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem implements PowerManageable {

	private double priority = 2;
	private final Object motorLock = new Object();

	private final CANTalon driveLeftTalon = new CANTalon(RobotMap.driveLeftA);
    private final CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveLeftB);
    private final CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveLeftC);
	
	private final CANTalon driveRightTalon = new CANTalon(RobotMap.driveRightA);
    private final CANTalon driveRightBTalon = new CANTalon(RobotMap.driveRightB);
    private final CANTalon driveRightCTalon = new CANTalon(RobotMap.driveRightC);
	
    public DriveTrain() {
    	driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightTalon.reverseOutput(false);
        driveLeftTalon.reverseOutput(false);
        driveRightTalon.reverseSensor(true);
        driveLeftTalon.reverseSensor(true);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
    }
    
	@Override
	protected void initDefaultCommand() {
		actuallyInitDefaultCommand();
	}
	
	public void actuallyInitDefaultCommand() {
		setDefaultCommand(Robot.driveModeChooser.getSelected());
	}
	
	public void tankDrive(double leftValue, double rightValue) {
		double deadzone = 0.1;
		driveLeftTalon.set(-RobotUtil.deadzone(leftValue, deadzone));
		driveRightTalon.set(RobotUtil.deadzone(rightValue, deadzone));
	}
	
	public void setLeft(double value) {
		driveLeftTalon.set(-value);
	}
	
	public void setRight(double value) {
		driveRightTalon.set(value);
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
			return driveLeftTalon.getOutputCurrent() + driveLeftBTalon.getOutputCurrent() + driveLeftCTalon.getOutputCurrent() +
					driveRightTalon.getOutputCurrent() + driveRightBTalon.getOutputCurrent() + driveRightCTalon.getOutputCurrent();
	}

	@Override
	public void limitPower(double limit) {
		synchronized (motorLock) {
			driveLeftTalon.EnableCurrentLimit(true);
			driveLeftBTalon.EnableCurrentLimit(true);
			driveLeftCTalon.EnableCurrentLimit(true);
			driveRightTalon.EnableCurrentLimit(true);
			driveRightBTalon.EnableCurrentLimit(true);
			driveRightCTalon.EnableCurrentLimit(true);

			driveLeftTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 6)));
			driveLeftBTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 6)));
			driveLeftCTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 6)));
			driveRightTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 6)));
			driveRightBTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 6)));
			driveRightCTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 6)));
		}
	}

	@Override
	public void stopLimitingPower() {
		synchronized (motorLock) {
			driveLeftTalon.EnableCurrentLimit(false);
			driveLeftBTalon.EnableCurrentLimit(false);
			driveLeftCTalon.EnableCurrentLimit(false);
			driveRightTalon.EnableCurrentLimit(false);
			driveRightBTalon.EnableCurrentLimit(false);
			driveRightCTalon.EnableCurrentLimit(false);
		}
	}
}
