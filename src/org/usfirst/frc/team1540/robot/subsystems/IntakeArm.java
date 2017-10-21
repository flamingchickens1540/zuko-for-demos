package org.usfirst.frc.team1540.robot.subsystems;

import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.commands.JoystickIntakeArm;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeArm extends Subsystem {
	
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
	
	public double getCurrent() {
		return armTalon.getOutputCurrent();
	}

}
