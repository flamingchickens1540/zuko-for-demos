package org.usfirst.frc.team1540.robot;

import jaci.pathfinder.Trajectory;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class Properties {

  private double encoderTicksPerRev = 1023;

  private double wheelDiameter = 0.1;
  private double secondsFromNeutralToFull = 0;

  private DoubleSupplier getEncoderPositionFunction;
  private DoubleConsumer setMotorPositionFunction;

  private Trajectory trajectory;

  private double currentTime = 0;

  public Properties(DoubleSupplier getEncoderPositionFunction,
      DoubleConsumer setMotorPositionFunction, Trajectory trajectory) {
    this.getEncoderPositionFunction = getEncoderPositionFunction;
    this.setMotorPositionFunction = setMotorPositionFunction;
    this.trajectory = trajectory;
  }

  public Properties(double encoderTicksPerRev,
      double wheelDiameter, double secondsFromNeutralToFull,
      DoubleSupplier getEncoderPositionFunction,
      DoubleConsumer setMotorPositionFunction, Trajectory trajectory) {
    this.encoderTicksPerRev = encoderTicksPerRev;
    this.wheelDiameter = wheelDiameter;
    this.secondsFromNeutralToFull = secondsFromNeutralToFull;
    this.getEncoderPositionFunction = getEncoderPositionFunction;
    this.setMotorPositionFunction = setMotorPositionFunction;
    this.trajectory = trajectory;
  }

  public double getEncoderTicksPerRev() {
    return encoderTicksPerRev;
  }

  public void setEncoderTicksPerRev(double encoderTicksPerRev) {
    this.encoderTicksPerRev = encoderTicksPerRev;
  }

  public double getWheelDiameter() {
    return wheelDiameter;
  }

  public void setWheelDiameter(double wheelDiameter) {
    this.wheelDiameter = wheelDiameter;
  }

  public double getEncoderTickRatio() {
    return (1 / encoderTicksPerRev) * (wheelDiameter * Math.PI);
  }

  public Trajectory getTrajectory() {
    return trajectory;
  }

  public double getSecondsFromNeutralToFull() {
    return secondsFromNeutralToFull;
  }

  public void setSecondsFromNeutralToFull(double secondsFromNeutralToFull) {
    this.secondsFromNeutralToFull = secondsFromNeutralToFull;
  }

  public DoubleSupplier getGetEncoderPositionFunction() {
    return getEncoderPositionFunction;
  }

  public void setGetEncoderPositionFunction(DoubleSupplier getEncoderPositionFunction) {
    this.getEncoderPositionFunction = getEncoderPositionFunction;
  }

  public DoubleConsumer getSetMotorPositionFunction() {
    return setMotorPositionFunction;
  }

  public void setSetMotorPositionFunction(DoubleConsumer setMotorPositionFunction) {
    this.setMotorPositionFunction = setMotorPositionFunction;
  }

}