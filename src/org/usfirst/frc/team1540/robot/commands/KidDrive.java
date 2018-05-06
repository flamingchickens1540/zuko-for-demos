package org.usfirst.frc.team1540.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1540.robot.OI;
import org.usfirst.frc.team1540.robot.Robot;

public class KidDrive extends Command {

    public KidDrive() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void execute() {
        double normalLeft = OI.getDriveLeftAxis() + OI.getDriveLeftTrigger() - OI.getDriveRightTrigger();
        double normalRight = OI.getDriveRightAxis() + OI.getDriveLeftTrigger() - OI.getDriveRightTrigger();
        double kidDriveSlowness = 3;
        double kidTurnSlowness = 2;
        double kidLeft = OI.getKidAxisY() / kidDriveSlowness - OI.getKidAxisX() / kidTurnSlowness;
        double kidRight = OI.getKidAxisY() / kidDriveSlowness + OI.getKidAxisX() / kidTurnSlowness;
        Robot.driveTrain.tankDrive(normalLeft + kidLeft, normalRight + kidRight);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
