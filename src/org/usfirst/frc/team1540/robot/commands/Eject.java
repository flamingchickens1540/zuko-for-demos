package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Eject extends Command {
	
	public Eject() {
		requires(Robot.shooter);
		requires(Robot.intakeRollers);
	}
	
	@Override
	protected void initialize() {
		Robot.shooter.set(-Robot.tuning.getFlywheelBackwardsValue());
		Robot.intakeRollers.set(-Robot.tuning.getIntakeRollersValue());
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.intakeRollers.stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
