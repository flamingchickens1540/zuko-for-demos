package org.usfirst.frc.team1540.robot;

import jaci.pathfinder.Trajectory;

public class Properties {

  private double p = 0.1;
  private double i = 0.0;
  private double d = 0.0;
  private double f = 0.5;
  private int iZone = 1000;
  private double secondsFromNeutralToFull = 0;
  private Trajectory trajectory;

  private double currentTime = 0;

  public Properties(Trajectory trajectory) {
    this.trajectory = trajectory;
  }

  public Properties(double p, double i, double d, Trajectory trajectory) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.trajectory = trajectory;
  }

  public double getP() {
    return p;
  }

  public void setP(double p) {
    this.p = p;
  }

  public double getI() {
    return i;
  }

  public void setI(double i) {
    this.i = i;
  }

  public double getD() {
    return d;
  }

  public void setD(double d) {
    this.d = d;
  }

  public double getF() {
    return f;
  }

  public void setF(double f) {
    this.f = f;
  }

  public int getiZone() {
    return iZone;
  }

  public void setiZone(int iZone) {
    this.iZone = iZone;
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

}