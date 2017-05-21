
package org.usfirst.frc.team1540.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1540.robot.commands.CancelShooter;
import org.usfirst.frc.team1540.robot.commands.Eject;
import org.usfirst.frc.team1540.robot.commands.FireShooter;
import org.usfirst.frc.team1540.robot.commands.Intake;
import org.usfirst.frc.team1540.robot.commands.SpinupFlywheel;
import org.usfirst.frc.team1540.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1540.robot.subsystems.IntakeArm;
import org.usfirst.frc.team1540.robot.subsystems.IntakeRollers;
import org.usfirst.frc.team1540.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final IntakeRollers intakeRollers = new IntakeRollers();
	public static final IntakeArm intakeArm = new IntakeArm();
//	public static OI oi;
	public static Tuning tuning;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
//		oi = new OI();
		tuning = new Tuning();
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		OI.buttonIntake.whenPressed(new Intake());
		OI.buttonEject.whileHeld(new Eject());
		OI.buttonSpinup.whenPressed(new SpinupFlywheel());
		OI.buttonFire.whenPressed(new FireShooter());
		OI.buttonCancelShooter.whenPressed(new CancelShooter());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Flywheel Current", shooter.getCurrent());
		SmartDashboard.putNumber("Flywheel Setpoint", shooter.getSetpoint());
		SmartDashboard.putNumber("Flywheel Speed", shooter.getSpeed());
		SmartDashboard.putBoolean("Flywheel Up To Speed", shooter.upToSpeed(tuning.getFlywheelTargetSpeed()));
		
		OI.driver.setRumble(RumbleType.kRightRumble, 
				shooter.upToSpeed(Robot.tuning.getFlywheelTargetSpeed()) ? 0.5 : 0);
		OI.copilot.setRumble(RumbleType.kRightRumble, 
				shooter.upToSpeed(Robot.tuning.getFlywheelTargetSpeed()) ? 0.5 : 0);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
