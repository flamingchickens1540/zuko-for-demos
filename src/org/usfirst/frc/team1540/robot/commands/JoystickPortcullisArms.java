package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.OI;
import org.usfirst.frc.team1540.robot.Robot;
import org.usfirst.frc.team1540.robot.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickPortcullisArms extends Command {
	
	public JoystickPortcullisArms() {
		requires(Robot.portcullisArms);
	}
	
	@Override
	protected void execute() {
		Robot.portcullisArms.set(RobotUtil.deadzone(OI.getPortcullisArmsAxis(), 0.1));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
