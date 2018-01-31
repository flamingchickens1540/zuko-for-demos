package org.usfirst.frc.team1540.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.base.wrappers.ChickenTalon;
import org.team1540.base.wrappers.ChickenTalon.TalonControlMode;
import org.usfirst.frc.team1540.robot.Robot;
import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.RobotUtil;

public class DriveTrain extends Subsystem {
	
	private final ChickenTalon driveLeftTalon = new ChickenTalon(RobotMap.driveLeftA);
    private final ChickenTalon driveLeftBTalon = new ChickenTalon(RobotMap.driveLeftB);
    private final ChickenTalon driveLeftCTalon = new ChickenTalon(RobotMap.driveLeftC);
	
	private final ChickenTalon driveRightTalon = new ChickenTalon(RobotMap.driveRightA);
    private final ChickenTalon driveRightBTalon = new ChickenTalon(RobotMap.driveRightB);
    private final ChickenTalon driveRightCTalon = new ChickenTalon(RobotMap.driveRightC);
	
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

	public ChickenTalon getDriveLeftTalon() {
		return driveLeftTalon;
	}

	public ChickenTalon getDriveRightTalon() {
		return driveRightTalon;
	}
}
