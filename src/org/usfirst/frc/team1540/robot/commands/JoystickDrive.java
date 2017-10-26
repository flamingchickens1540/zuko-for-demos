package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.OI;
import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive extends Command {
	
	public JoystickDrive() {
        requires(Robot.driveTrain);
    }
	
	@Override
	protected void execute() {
		Robot.driveTrain.setLeft(OI.getDriveAccAxis() - OI.getDriveTurnAxis());
		Robot.driveTrain.setRight(OI.getDriveAccAxis() + OI.getDriveTurnAxis());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
