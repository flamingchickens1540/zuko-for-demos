package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.OI;
import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LittleKidMode extends Command {
	
	private boolean kidDisabled = false;
	
	public LittleKidMode() {
        requires(Robot.driveTrain);
    }
	
	@Override
	protected void initialize() {
		OI.buttonKidSpinup.whenPressed(new SpinupFlywheel());
		OI.buttonKidFire.whenPressed(new FireShooter());
		OI.buttonDisableKid.whenPressed(new InstantCommand() {
			@Override
			protected void initialize() {
				kidDisabled = true;
			}
		});
		OI.buttonEnableKid.whenPressed(new InstantCommand() {
			@Override
			protected void initialize() {
				kidDisabled = false;
			}
		});
	}
	
	@Override
	protected void execute() {
		if (!kidDisabled) {
			double multiplier = 0.5;
//			Robot.driveTrain.tankDrive((OI.getKidYAxis() - OI.getKidXAxis()) * multiplier, 
//				(OI.getKidYAxis() + OI.getKidXAxis()) * multiplier);
			Robot.driveTrain.setLeft((OI.getKidYAxis() - OI.getKidXAxis()) * multiplier);
			Robot.driveTrain.setRight((OI.getKidYAxis() + OI.getKidXAxis()) * multiplier);
		}
		SmartDashboard.putBoolean("Kid Disabled", kidDisabled);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
