package org.usfirst.frc.team1540.robot;

import jaci.pathfinder.Trajectory;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class Properties {

  private double encoderTicksPerRev = 1023;

  private double wheelDiameter = 0.1;
  private double secondsFromNeutralToFull = 0;

  private DoubleSupplier getEncoderVelocityFunction;
  private DoubleConsumer setMotorVelocityFunction;

  private Trajectory trajectory;

  private double currentTime = 0;

  public Properties(DoubleSupplier getEncoderVelocityFunction,
      DoubleConsumer setMotorVelocityFunction, Trajectory trajectory) {
    this.getEncoderVelocityFunction = getEncoderVelocityFunction;
    this.setMotorVelocityFunction = setMotorVelocityFunction;
    this.trajectory = trajectory;
  }

  public Properties(double encoderTicksPerRev,
      double wheelDiameter, double secondsFromNeutralToFull,
      DoubleSupplier getEncoderVelocityFunction,
      DoubleConsumer setMotorVelocityFunction, Trajectory trajectory) {
    this.encoderTicksPerRev = encoderTicksPerRev;
    this.wheelDiameter = wheelDiameter;
    this.secondsFromNeutralToFull = secondsFromNeutralToFull;
    this.getEncoderVelocityFunction = getEncoderVelocityFunction;
    this.setMotorVelocityFunction = setMotorVelocityFunction;
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

  public DoubleSupplier getGetEncoderVelocityFunction() {
    return getEncoderVelocityFunction;
  }

  public void setGetEncoderVelocityFunction(DoubleSupplier getEncoderVelocityFunction) {
    this.getEncoderVelocityFunction = getEncoderVelocityFunction;
  }

  public DoubleConsumer getSetMotorVelocityFunction() {
    return setMotorVelocityFunction;
  }

  public void setSetMotorVelocityFunction(DoubleConsumer setMotorVelocityFunction) {
    this.setMotorVelocityFunction = setMotorVelocityFunction;
  }

}