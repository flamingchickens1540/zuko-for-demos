package org.usfirst.frc.team1540.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.base.power.PowerManageable;
import org.team1540.base.power.PowerManager;
import org.team1540.base.wrappers.ChickenTalon;
import org.team1540.base.wrappers.ChickenTalon.TalonControlMode;
import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.RobotUtil;

public class DriveTrain extends Subsystem implements PowerManageable {

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
    PowerManager.getInstance().registerPowerManageable(this);
  }

	@Override
	protected void initDefaultCommand() {
//		actuallyInitDefaultCommand();
	}

  public void actuallyInitDefaultCommand() {
//		setDefaultCommand(Robot.driveModeChooser.getSelected());
	}

  public void tankDrive(double leftValue, double rightValue) {
		double deadzone = 0.1;
    driveLeftTalon.set(ControlMode.PercentOutput, -RobotUtil.deadzone(leftValue, deadzone));
    driveRightTalon.set(ControlMode.PercentOutput, RobotUtil.deadzone(rightValue, deadzone));
	}

  public void setLeft(double value) {
		driveLeftTalon.set(-value);
	}

  public void setRight(double value) {
		driveRightTalon.set(value);
	}

  public double getDriveLeftTalonVelocity() {
    return driveLeftTalon.getQuadratureVelocity();
  }

  public double getDriveRightTalonVelocity() {
    return driveRightTalon.getQuadratureVelocity();
  }

  public double getDriveLeftTalonPosition() {
    return driveLeftTalon.getQuadraturePosition();
  }

  public double getDriveRightTalonPosition() {
    return driveRightTalon.getQuadraturePosition();
  }

  public void setLeftVelocity(double velocity) {
    driveLeftTalon.set(ControlMode.Velocity, velocity);
  }

  public void setRightVelocity(double velocity) {
    driveRightTalon.set(ControlMode.Velocity, velocity);
  }

  public void prepareForMotionProfiling() {
    driveRightTalon.setControlMode(ControlMode.Velocity);
    driveRightBTalon.setControlMode(ControlMode.Follower);
    driveRightCTalon.setControlMode(ControlMode.Follower);
    driveLeftTalon.setControlMode(ControlMode.Velocity);
    driveLeftBTalon.setControlMode(ControlMode.Follower);
    driveLeftCTalon.setControlMode(ControlMode.Follower);
    driveRightBTalon.set(driveRightTalon.getDeviceID());
    driveRightCTalon.set(driveRightTalon.getDeviceID());
    driveLeftBTalon.set(driveLeftTalon.getDeviceID());
    driveLeftCTalon.set(driveLeftTalon.getDeviceID());

    driveRightTalon.setInverted(true);
    driveRightBTalon.setInverted(true);
    driveRightCTalon.setInverted(true);
    driveLeftTalon.setInverted(false);
    driveLeftBTalon.setInverted(false);
    driveLeftCTalon.setInverted(false);
    driveRightTalon.setSensorPhase(false);
    driveLeftTalon.setSensorPhase(true);

    // This needs to be here, as PIDFiZone values are stored in memory
    driveLeftTalon.config_IntegralZone(driveLeftTalon.getDefaultPidIdx(), 1000);
    driveRightTalon.config_IntegralZone(driveRightTalon.getDefaultPidIdx(), 1000);
    driveLeftTalon.config_kI(driveLeftTalon.getDefaultPidIdx(), 0.01);
    driveRightTalon.config_kI(driveRightTalon.getDefaultPidIdx(), 0.01);
    driveLeftTalon.config_kF(driveLeftTalon.getDefaultPidIdx(), 0.1);
    driveRightTalon.config_kF(driveRightTalon.getDefaultPidIdx(), 0.1);
    driveLeftTalon.configClosedloopRamp(0);
    driveLeftBTalon.configClosedloopRamp(0);
    driveLeftCTalon.configClosedloopRamp(0);
    driveRightTalon.configClosedloopRamp(0);
    driveRightBTalon.configClosedloopRamp(0);
    driveRightCTalon.configClosedloopRamp(0);

    driveLeftTalon.setSelectedSensorPosition(0);
    driveRightTalon.setSelectedSensorPosition(0);
  }

  public void displayAutoInfo() {
    SmartDashboard
        .putNumber("lSetpoint", driveLeftTalon.getClosedLoopTarget(0));
    SmartDashboard
        .putNumber("rSetpoint", driveRightTalon.getClosedLoopTarget(0));
    SmartDashboard
        .putNumber("lOutput", driveLeftTalon.getMotorOutputPercent());
    SmartDashboard
        .putNumber("rOutput", driveRightTalon.getMotorOutputPercent());
    SmartDashboard.putNumber("lVelocity", driveLeftTalon.getQuadratureVelocity());
    SmartDashboard.putNumber("rVelocity", driveRightTalon.getQuadratureVelocity());
//    SmartDashboard.putBoolean("lMotorPhase", driveLeftTalon.getInverted());
//    SmartDashboard.putBoolean("rMotorPhase", driveRightTalon.getInverted());
  }

  @Override
  public double getPriority() {
    return 10;
  }

  @Override
  public void setPriority(double priority) {

  }

  @Override
  public double getCurrent() {
    return driveLeftTalon.getOutputCurrent() + driveLeftBTalon.getOutputCurrent() +
        driveLeftCTalon.getOutputCurrent() + driveRightTalon.getOutputCurrent() +
        driveRightBTalon.getOutputCurrent() + driveRightCTalon.getOutputCurrent();
  }

  @Override
  public void limitPower(double limit) {
    double realLimit = limit / 6;
    driveLeftTalon.configContinuousCurrentLimit(Math.toIntExact(Math.round(realLimit)));
    driveLeftBTalon.configContinuousCurrentLimit(Math.toIntExact(Math.round(realLimit)));
    driveLeftCTalon.configContinuousCurrentLimit(Math.toIntExact(Math.round(realLimit)));
    driveRightTalon.configContinuousCurrentLimit(Math.toIntExact(Math.round(realLimit)));
    driveRightBTalon.configContinuousCurrentLimit(Math.toIntExact(Math.round(realLimit)));
    driveRightCTalon.configContinuousCurrentLimit(Math.toIntExact(Math.round(realLimit)));
    driveLeftTalon.enableCurrentLimit(true);
    driveLeftBTalon.enableCurrentLimit(true);
    driveLeftCTalon.enableCurrentLimit(true);
    driveRightTalon.enableCurrentLimit(true);
    driveRightBTalon.enableCurrentLimit(true);
    driveRightCTalon.enableCurrentLimit(true);
  }

  @Override
  public void stopLimitingPower() {
    driveLeftTalon.enableCurrentLimit(false);
    driveLeftBTalon.enableCurrentLimit(false);
    driveLeftCTalon.enableCurrentLimit(false);
    driveRightTalon.enableCurrentLimit(false);
    driveRightBTalon.enableCurrentLimit(false);
    driveRightCTalon.enableCurrentLimit(false);
  }
}
