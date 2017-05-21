package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class CancelShooter extends InstantCommand {
	
	public CancelShooter() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		Robot.shooter.stop();
	}
	
}
