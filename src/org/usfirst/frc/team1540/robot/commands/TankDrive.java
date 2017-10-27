package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.OI;
import org.usfirst.frc.team1540.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {
	
	public TankDrive() {
        requires(Robot.driveTrain);
    }
	
	@Override
	protected void execute() {
		Robot.driveTrain.tankDrive(OI.getDriveLeftAxis() + OI.getDriveLeftTrigger() - OI.getDriveRightTrigger(),
				OI.getDriveRightAxis() + OI.getDriveLeftTrigger() - OI.getDriveRightTrigger());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
