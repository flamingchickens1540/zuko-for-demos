package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command {
	
	private double startTime;
	
	public Intake() {
		requires(Robot.intakeRollers);
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		Robot.shooter.set(-Robot.tuning.getFlywheelBackwardsValue());
		Robot.intakeRollers.set(Robot.tuning.getIntakeRollersValue());
		startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void end() {
		Robot.shooter.stop();
		Robot.intakeRollers.stop();
	}
	
	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - startTime > 1000) && 
				Robot.shooter.getCurrent() > Robot.tuning.getFlywheelBackwardsCurrentThreshold();
	}

}
