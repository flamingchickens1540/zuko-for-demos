package org.usfirst.frc.team1540.robot.subsystems;

import org.usfirst.frc.team1540.robot.Robot;
import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.RobotUtil;
import org.usfirst.frc.team1540.robot.commands.TankDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	private final CANTalon driveLeftTalon = new CANTalon(RobotMap.driveLeftA);
    private final CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveLeftB);
    private final CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveLeftC);
	
	private final CANTalon driveRightTalon = new CANTalon(RobotMap.driveRightA);
    private final CANTalon driveRightBTalon = new CANTalon(RobotMap.driveRightB);
    private final CANTalon driveRightCTalon = new CANTalon(RobotMap.driveRightC);
    
    public static final double deadzone = 0.1;
    public static final double exponent = 2;
	
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
	
//	public void tankDrive(double leftValue, double rightValue) {
//		double deadzone = 0.1;
//		double exponent = 2;
//		driveLeftTalon.set(-RobotUtil.exponentDeadzone(leftValue, deadzone, exponent));
//		driveRightTalon.set(RobotUtil.exponentDeadzone(rightValue, deadzone, exponent));
//	}
	
	public void setLeft(double value) {
		driveLeftTalon.set(-value);
	}
	
	public void setRight(double value) {
		driveRightTalon.set(value);
	}
	
}
