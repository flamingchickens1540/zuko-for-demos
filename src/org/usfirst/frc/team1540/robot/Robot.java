
package org.usfirst.frc.team1540.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;
import java.util.HashSet;
import org.team1540.base.power.PowerManager;
import org.usfirst.frc.team1540.robot.commands.ArcadeDrive;
import org.usfirst.frc.team1540.robot.commands.CancelShooter;
import org.usfirst.frc.team1540.robot.commands.Eject;
import org.usfirst.frc.team1540.robot.commands.FireShooter;
import org.usfirst.frc.team1540.robot.commands.Intake;
import org.usfirst.frc.team1540.robot.commands.MotionProfile;
import org.usfirst.frc.team1540.robot.commands.SpinupFlywheel;
import org.usfirst.frc.team1540.robot.commands.TankDrive;
import org.usfirst.frc.team1540.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1540.robot.subsystems.IntakeArm;
import org.usfirst.frc.team1540.robot.subsystems.IntakeRollers;
import org.usfirst.frc.team1540.robot.subsystems.PortcullisArms;
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
	public static final PortcullisArms portcullisArms = new PortcullisArms();
	public static Tuning tuning;
	
	public static final SendableChooser<Command> driveModeChooser = new SendableChooser<Command>();

	private static Command autoCommand = new Command() {
		@Override
		protected boolean isFinished() {
			return true;
		}
	};
	private PowerDistributionPanel pdp = new PowerDistributionPanel();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		tuning = new Tuning();
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("ze camera", 0);
		camera.setResolution(640, 480);
		MjpegServer mjpegServer0 = new MjpegServer("Front Server", 1181);
		mjpegServer0.setSource(camera);

		OI.buttonIntake.whenPressed(new Intake());
		OI.buttonEject.whileHeld(new Eject());
		OI.buttonSpinup.whenPressed(new SpinupFlywheel());
		OI.buttonFire.whenPressed(new FireShooter());
		OI.buttonCancelShooter.whenPressed(new CancelShooter());
		
		driveModeChooser.addDefault("Tank Drive", new TankDrive());
		driveModeChooser.addObject("Arcade Drive", new ArcadeDrive());
		SmartDashboard.putData("Drive Mode", driveModeChooser);

//		PowerManager.getInstance().setVoltageMargin(10);
//		PowerManager.getInstance().setVoltageDipLow(12);
//		PowerManager.getInstance().setCurrentSpikePeak(10);
//		PowerManager.getInstance().setCurrentTarget(10);
//		PowerManager.getInstance().setCurrentSpikeLength(0.2);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
//		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
//		autoCommand = new PositionTest();
//		Scheduler.getInstance().add(autoCommand);
		Trajectory[] trajectories = PathfinderPlayground.getModifiedTrajectory();
    HashSet<Properties> mps = new HashSet<>();
		Properties lProperties = new Properties(Robot.driveTrain::getDriveLeftTalonVelocity,
				Robot.driveTrain::setLeftVelocity, trajectories[0]);
		Properties rProperties = new Properties(Robot.driveTrain::getDriveRightTalonVelocity,
				Robot.driveTrain::setRightVelocity, trajectories[1]);
		lProperties.setEncoderTicksPerRev(1556);
		rProperties.setEncoderTicksPerRev(1556);
		lProperties.setWheelDiameter(.2);
		rProperties.setWheelDiameter(.2);
    mps.add(lProperties);
    mps.add(rProperties);
		Robot.driveTrain.prepareForMotionProfiling();
		autoCommand = new MotionProfile(mps);
		Scheduler.getInstance().add(autoCommand);
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData(Scheduler.getInstance());
		Robot.driveTrain.displayAutoInfo();
		SmartDashboard.putBoolean("Is Limiting", PowerManager.getInstance().isLimiting());
	}

	@Override
	public void teleopInit() {
		autoCommand.cancel();
		driveTrain.actuallyInitDefaultCommand();
	}

	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();

//		SmartDashboard.putNumber("Flywheel Current", shooter.getCurrent());
//		SmartDashboard.putNumber("Flywheel Setpoint", shooter.getSetpoint());
//		SmartDashboard.putNumber("Flywheel Speed", shooter.getSpeed());
//		SmartDashboard.putBoolean("Flywheel Up To Speed", shooter.upToSpeed(
//				tuning.getFlywheelTargetSpeed()));
//		SmartDashboard.putNumber("Intake Arm Current", intakeArm.getCurrent());
//		SmartDashboard.putBoolean("Is Limiting", PowerManager.getInstance().isLimiting());
//		SmartDashboard.putBoolean("Is Spiking/Dipping", PowerManager.getInstance().currentIsSpiking() ||
//				PowerManager.getInstance().voltageIsDipping());
//		SmartDashboard.putNumber("Battery Voltage", RobotController.getBatteryVoltage());
//		SmartDashboard.putNumber("Total Current", pdp.getTotalCurrent());

		OI.driver.setRumble(RumbleType.kRightRumble,
				shooter.upToSpeed(Robot.tuning.getFlywheelTargetSpeed()) ? 0.5 : 0);
		OI.copilot.setRumble(RumbleType.kRightRumble,
				shooter.upToSpeed(Robot.tuning.getFlywheelTargetSpeed()) ? 0.5 : 0);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
