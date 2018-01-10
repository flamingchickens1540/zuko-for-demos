package org.usfirst.frc.team1540.robot.subsystems;

import org.team1540.base.power.PowerManageable;
import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.commands.JoystickPortcullisArms;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PortcullisArms extends Subsystem implements PowerManageable {

	private double priority = 8;
	private final Object motorLock = new Object();

	CANTalon leftArmTalon = new CANTalon(RobotMap.portcullisL);
	CANTalon rightArmTalon = new CANTalon(RobotMap.portcullisR);
	
	public PortcullisArms() {
		leftArmTalon.changeControlMode(TalonControlMode.PercentVbus);
		rightArmTalon.changeControlMode(TalonControlMode.PercentVbus);
		leftArmTalon.reverseOutput(false);
		rightArmTalon.reverseOutput(true);
	}
	
	public void set(double value) {
		leftArmTalon.set(-value);
		rightArmTalon.set(value);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickPortcullisArms());
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
			return leftArmTalon.getOutputCurrent() + rightArmTalon.getOutputCurrent();
	}

	@Override
	public void limitPower(double limit) {
		synchronized (motorLock) {
			leftArmTalon.EnableCurrentLimit(true);
			rightArmTalon.EnableCurrentLimit(true);

			leftArmTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 2)));
			rightArmTalon.setCurrentLimit(Math.toIntExact(Math.round(limit / 2)));
		}
	}

	@Override
	public void stopLimitingPower() {
		synchronized (motorLock) {
			leftArmTalon.EnableCurrentLimit(false);
			rightArmTalon.EnableCurrentLimit(false);
		}
	}

}
