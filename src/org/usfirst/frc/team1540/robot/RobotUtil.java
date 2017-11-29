package org.usfirst.frc.team1540.robot;

public class RobotUtil {
    
    public static double deadzone(double input, double range) {
        if (Math.abs(input) < range) {
            return 0.0;
        }
        return input;
    }
    
    public static double exponentDeadzone(double input, double deadzone, double exponent) {
        if (Math.abs(input) > deadzone) {
            if (input > 0) {
                return Math.pow((input - deadzone) / (1 - deadzone), exponent);
            }
            else {
                return -Math.pow((-input - deadzone) / (1 - deadzone), exponent);
            }
        }
        else {
            return 0;
        }
    }

    public static double limit(double input, double max, double min) {
        if (input > max) {
            return max;
        } else if (input < min) {
            return min;
        }
        return input;
    }
    
}
