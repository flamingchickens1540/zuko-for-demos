package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.OI;
import org.usfirst.frc.team1540.robot.Robot;
import org.usfirst.frc.team1540.robot.RobotUtil;
import org.usfirst.frc.team1540.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	
	public ArcadeDrive() {
        requires(Robot.driveTrain);
    }
	
	@Override
	protected void execute() {
//		Robot.driveTrain.setLeft(OI.getDriveAccAxis() - OI.getDriveTurnAxis());
//		Robot.driveTrain.setRight(OI.getDriveAccAxis() + OI.getDriveTurnAxis());
		Robot.driveTrain.setLeft(RobotUtil.exponentDeadzone(OI.getDriveAccAxis() - OI.getDriveTurnAxis(), 
				DriveTrain.deadzone, DriveTrain.exponent));
		Robot.driveTrain.setRight(RobotUtil.exponentDeadzone(OI.getDriveAccAxis() + OI.getDriveTurnAxis(), 
				DriveTrain.deadzone, DriveTrain.exponent));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
