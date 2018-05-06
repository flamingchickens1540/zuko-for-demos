package org.usfirst.frc.team1540.robot.subsystems;

import org.team1540.base.wrappers.ChickenTalon;
import org.team1540.base.wrappers.ChickenTalon.TalonControlMode;
import org.usfirst.frc.team1540.robot.RobotMap;
import org.usfirst.frc.team1540.robot.commands.JoystickPortcullisArms;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PortcullisArms extends Subsystem {
	
	ChickenTalon leftArmTalon = new ChickenTalon(RobotMap.portcullisL);
	ChickenTalon rightArmTalon = new ChickenTalon(RobotMap.portcullisR);
	
	public PortcullisArms() {
		leftArmTalon.changeControlMode(TalonControlMode.PercentVbus);
		rightArmTalon.changeControlMode(TalonControlMode.PercentVbus);
		leftArmTalon.reverseOutput(true);
		rightArmTalon.reverseOutput(false);
	}
	
	public void set(double value) {
		leftArmTalon.set(value);
		rightArmTalon.set(value);
	}

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new JoystickPortcullisArms());
	}

}
