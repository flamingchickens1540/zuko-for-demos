package org.usfirst.frc.team1540.robot.subsystems;

import org.usfirst.frc.team1540.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeArm extends Subsystem {
	
	private final CANTalon armTalon = new CANTalon(RobotMap.intakeArm);
	
	public IntakeArm() {
		armTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void set(double value) {
		armTalon.set(value);
	}

}
