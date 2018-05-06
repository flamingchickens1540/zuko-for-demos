package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class SpinupFlywheel extends CommandGroup {
	
	public SpinupFlywheel() {
		requires(Robot.shooter);
		requires(Robot.intakeRollers);
		
		addSequential(new TimedCommand(0.3) {
			
			@Override
			protected void initialize() {
				Robot.intakeRollers.setOnlySecondRoller(-Robot.tuning.getIntakeRollersValue());
			}
			
			@Override
			protected void end() {
				Robot.intakeRollers.stop();
			}
			
		});
		
		addSequential(new Command() {
			
			@Override
			protected void initialize() {
//				Robot.shooter.setSpeed(Robot.tuning.getFlywheelTargetSpeed());
				Robot.shooter.set(0.65);
			}
			
			@Override
			protected boolean isFinished() {
//				return Robot.shooter.upToSpeed(Robot.tuning.getFlywheelTargetSpeed());
				return true;
			}
			
		});
	}
	
}
