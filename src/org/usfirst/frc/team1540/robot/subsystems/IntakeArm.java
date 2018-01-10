package org.usfirst.frc.team1540.robot.subsystems;

import org.team1540.base.power.PowerManageable;
import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.commands.JoystickIntakeArm;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeArm extends Subsystem implements PowerManageable {

	private double priority = 5;
	private final Object motorLock = new Object();

	private final CANTalon armTalon = new CANTalon(RobotMap.intakeArm);
	
	public IntakeArm() {
		armTalon.changeControlMode(TalonControlMode.PercentVbus);
		armTalon.reverseOutput(false);
		armTalon.reverseSensor(false);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickIntakeArm());
	}
	
	public void set(double value) {
		armTalon.set(-value);
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
			return armTalon.getOutputCurrent();
	}

	@Override
	public void limitPower(double limit) {
		synchronized (motorLock) {
			armTalon.EnableCurrentLimit(true);
			armTalon.setCurrentLimit(Math.toIntExact(Math.round(limit)));
		}
	}

	@Override
	public void stopLimitingPower() {
		synchronized (motorLock) {
			armTalon.EnableCurrentLimit(false);
		}
	}

}
