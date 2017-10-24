package org.usfirst.frc.team1540.robot.subsystems;

import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.commands.JoystickPortcullisArms;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PortcullisArms extends Subsystem {
	
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

}
