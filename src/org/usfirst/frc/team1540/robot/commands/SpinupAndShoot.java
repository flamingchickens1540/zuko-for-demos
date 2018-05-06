package org.usfirst.frc.team1540.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SpinupAndShoot extends CommandGroup {

    public SpinupAndShoot() {
        addSequential(new SpinupFlywheel());
        addSequential(new WaitCommand(1.5));
        addSequential(new FireShooter());
    }

}
