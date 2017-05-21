package org.usfirst.frc.team1540.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static int driveLeftA = 1;
	public static int driveLeftB = 2;
	public static int driveLeftC = 3;
	
	public static int driveRightA = 4;
	public static int driveRightB = 5;
	public static int driveRightC = 6;
	
	public static int intakeArmRollers = 7;
	public static int intakeRollers = 8;
	public static int intakeArm = 9; // doesn't work
	
	public static int flywheelL = 10;
	public static int flywheelR = 11;
	
	public static int portcullisR = 12;
	public static int portcullisL = 13; // doesn't work
	
}
