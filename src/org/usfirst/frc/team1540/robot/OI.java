package org.usfirst.frc.team1540.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	/* COPILOT CONTROLS
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
	public static final Joystick littleKid = new Joystick(2);
	
	public static final JoystickButton buttonIntake = new JoystickButton(copilot, 3);
	public static final JoystickButton buttonSpinup = new JoystickButton(copilot, 1);
	public static final JoystickButton buttonFire = new JoystickButton(copilot, 6);
	public static final JoystickButton buttonCancelShooter = new JoystickButton(copilot, 2);
	public static final JoystickButton buttonEject = new JoystickButton(copilot, 4);
	
	public static final JoystickButton buttonKidFire = new JoystickButton(littleKid, 1);
	public static final JoystickButton buttonKidSpinup = new JoystickButton(littleKid, 2);
	
	public static final JoystickButton buttonDisableKid = new JoystickButton(copilot, 8);
	public static final JoystickButton buttonEnableKid = new JoystickButton(copilot, 5);
	
	private static final int rightAxisY = 5;
    private static final int leftAxisY = 1;
	private static final int leftAxisX = 0;
    
    private static final int rightTrigger = 3;
    private static final int leftTrigger = 2;
    
    private static final int singleX = 0;
    private static final int singleY = 1;
	
	public static double getDriveLeftAxis() {
		return driver.getRawAxis(leftAxisY);
	}
	
	public static double getDriveRightAxis() {
        return driver.getRawAxis(rightAxisY);
    }
    
    public static double getDriveAccAxis() {
    	return driver.getRawAxis(rightAxisY);
    }
    
    public static double getDriveTurnAxis() {
    	return driver.getRawAxis(leftAxisX);
    }
	
	public static double getDriveLeftTrigger() {
		return driver.getRawAxis(leftTrigger);
	}
	
	public static double getDriveRightTrigger() {
		return driver.getRawAxis(rightTrigger);
	}
	
	public static double getKidXAxis() {
		return littleKid.getRawAxis(singleX);
	}
	
	public static double getKidYAxis() {
		return littleKid.getRawAxis(singleY);
	}
	
	public static double getIntakeArmAxis() {
		return copilot.getRawAxis(leftAxisY);
	}
	
	public static double getPortcullisArmsAxis() {
		return copilot.getRawAxis(rightAxisY);
	}
	
}
