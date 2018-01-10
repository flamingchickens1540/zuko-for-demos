package org.usfirst.frc.team1540.robot.subsystems;

import org.team1540.base.power.PowerManageable;
import org.usfirst.frc.team1540.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRollers extends Subsystem implements PowerManageable {

	private double priority = 6;
	private final Object motorLock = new Object();

	private final CANTalon armRollerTalon = new CANTalon(RobotMap.intakeArmRollers);
	private final CANTalon rollersTalon = new CANTalon(RobotMap.intakeRollers);
	
	public IntakeRollers() {
//		armRollerTalon.reverseSensor(false);
//		armRollerTalon.reverseOutput(false);
//		rollersTalon.reverseSensor(false);
//		rollersTalon.reverseOutput(true);
		armRollerTalon.changeControlMode(TalonControlMode.PercentVbus);
		rollersTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void set(double value) {
		armRollerTalon.set(-value);
		rollersTalon.set(-value);
	}
	
	public void setOnlySecondRoller(double value) {
		rollersTalon.set(-value);
	}
	
	public void stop() {
		set(0);
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
			return armRollerTalon.getOutputCurrent() + rollersTalon.getOutputCurrent();
	}

	@Override
	public void limitPower(double limit) {
		synchronized (motorLock) {
			armRollerTalon.EnableCurrentLimit(true);
			rollersTalon.EnableCurrentLimit(true);

			armRollerTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 2)));
			rollersTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 2)));
		}
	}

	@Override
	public void stopLimitingPower() {
		synchronized (motorLock) {
			armRollerTalon.EnableCurrentLimit(false);
			rollersTalon.EnableCurrentLimit(false);
		}
	}

}
