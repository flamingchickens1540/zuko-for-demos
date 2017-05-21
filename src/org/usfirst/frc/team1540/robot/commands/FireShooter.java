package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class FireShooter extends TimedCommand {
	
	public FireShooter() {
		super(1);
		requires(Robot.intakeRollers);
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		Robot.intakeRollers.setOnlySecondRoller(Robot.tuning.getIntakeRollersValue());
	}
	
	@Override
	protected void end() {
		Robot.intakeRollers.stop();
		Robot.shooter.stop();
	}

}
