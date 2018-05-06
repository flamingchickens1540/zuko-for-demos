package org.usfirst.frc.team1540.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.usfirst.frc.team1540.robot.Robot;

public class IntakeArmDown extends TimedCommand {

    public IntakeArmDown(String name) {
        super(1);
        requires(Robot.intakeArm);
    }

    @Override
    protected void initialize() {
        Robot.intakeArm.set(Robot.tuning.getIntakeArmValue());
    }

    @Override
    protected void end() {
        Robot.intakeArm.set(0);
    }

}
