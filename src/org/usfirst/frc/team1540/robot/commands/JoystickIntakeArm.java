package org.usfirst.frc.team1540.robot.commands;

import org.usfirst.frc.team1540.robot.OI;
import org.usfirst.frc.team1540.robot.Robot;
import org.usfirst.frc.team1540.robot.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickIntakeArm extends Command {
	
	public JoystickIntakeArm() {
		requires(Robot.intakeArm);
	}
	
	@Override
	protected void execute() {
		Robot.intakeArm.set(RobotUtil.deadzone(OI.getIntakeArmAxis(), 0.1));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
