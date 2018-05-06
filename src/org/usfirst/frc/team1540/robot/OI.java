package org.usfirst.frc.team1540.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	/* ACTUAL COPILOT CONTROLS
	 * button A -> spinup
	 * button B -> cancel shooter
	 * button X -> intake
	 * button Y -> outtake
	 * right bumper -> fire
	 * left joystick thing -> intake arm up/down
	 * right joystick thing -> portcullis arms up/down
	 */
	
	public static final Joystick driver = new Joystick(0);
	public static final Joystick copilot = new Joystick(1);
	public static final Joystick kid = new Joystick(2);
	
	public static final JoystickButton buttonIntake = new JoystickButton(copilot, 3);
	public static final JoystickButton buttonSpinup = new JoystickButton(copilot, 1);
	public static final JoystickButton buttonFire = new JoystickButton(copilot, 6);
	public static final JoystickButton buttonCancelShooter = new JoystickButton(copilot, 2);
	public static final JoystickButton buttonEject = new JoystickButton(copilot, 4);

	public static final JoystickButton kidIntake = new JoystickButton(kid, 2);
	public static final JoystickButton kidShoot = new JoystickButton(kid, 3);
	
	private static final int rightAxisY = 5;
    private static final int leftAxisY = 1;
	private static final int leftAxisX = 0;
    
    private static final int rightTrigger = 3;
    private static final int leftTrigger = 2;
	
	public static double getDriveLeftAxis() {
		return RobotUtil.deadzone(driver.getRawAxis(leftAxisY), 0.1);
	}
	
	public static double getDriveRightAxis() {
        return RobotUtil.deadzone(driver.getRawAxis(rightAxisY), 0.1);
    }
    
    public static double getDriveAccAxis() {
    	return RobotUtil.deadzone(driver.getRawAxis(rightAxisY), 0.1);
    }
    
    public static double getDriveTurnAxis() {
    	return RobotUtil.deadzone(driver.getRawAxis(leftAxisX), 0.1);
    }
	
	public static double getDriveLeftTrigger() {
		return RobotUtil.deadzone(driver.getRawAxis(leftTrigger), 0.1);
	}
	
	public static double getDriveRightTrigger() {
		return RobotUtil.deadzone(driver.getRawAxis(rightTrigger), 0.1);
	}
	
	public static double getIntakeArmAxis() {
		return copilot.getRawAxis(leftAxisY);
	}
	
	public static double getPortcullisArmsAxis() {
		return copilot.getRawAxis(rightAxisY);
	}

	public static double getKidAxisX() {
		return RobotUtil.deadzone(kid.getRawAxis(0), 0.1);
	}

	public static double getKidAxisY() {
		return RobotUtil.deadzone(kid.getRawAxis(1), 0.1);
	}

}
