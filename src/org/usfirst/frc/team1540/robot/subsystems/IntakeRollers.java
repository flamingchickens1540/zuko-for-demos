package org.usfirst.frc.team1540.robot.subsystems;

import org.team1540.base.wrappers.ChickenTalon;
import org.team1540.base.wrappers.ChickenTalon.TalonControlMode;
import org.usfirst.frc.team1540.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRollers extends Subsystem {
	
	private final ChickenTalon armRollerTalon = new ChickenTalon(RobotMap.intakeArmRollers);
	private final ChickenTalon rollersTalon = new ChickenTalon(RobotMap.intakeRollers);
	
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

}
