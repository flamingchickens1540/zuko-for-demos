package org.usfirst.frc.team1540.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import java.util.Map;
import org.team1540.base.wrappers.ChickenController;

public class MotionProfile extends Command {

  private int slotId = 0;
  private Map<ChickenController, Properties> motionProfiles;
  private Timer timer = new Timer();
  private double lastTime;
  private boolean isFinished = false;

  public MotionProfile(Map<ChickenController, Properties> motionProfiles) {
    this.motionProfiles = motionProfiles;
  }

  public int getSlotId() {
    return slotId;
  }

  public void setSlotId(int slotId) {
    this.slotId = slotId;
  }

  public Map<ChickenController, Properties> getMotionProfiles() {
    return motionProfiles;
  }

  public void setMotionProfiles(
      Map<ChickenController, Properties> motionProfiles) {
    this.motionProfiles = motionProfiles;
  }

  protected void initialize() {
    for (ChickenController currentController : motionProfiles.keySet()) {
      currentController.setControlMode(ControlMode.Velocity);
      currentController.config_kP(slotId, motionProfiles.get(currentController).getP());
      currentController.config_kI(slotId, motionProfiles.get(currentController).getI());
      currentController.config_kD(slotId, motionProfiles.get(currentController).getD());
      currentController.config_kF(slotId, motionProfiles.get(currentController).getF());
      currentController.config_IntegralZone(slotId, motionProfiles.get(currentController)
          .getiZone());
      currentController.configClosedloopRamp(motionProfiles.get(currentController)
          .getSecondsFromNeutralToFull());
    }
    timer.start();
    lastTime = timer.get();
    isFinished = false;
  }

  protected void execute() {

    for (ChickenController currentController : motionProfiles.keySet()) {
      // Each controller's setpoint is calculated at a slightly different time, but this doesn't
      // matter, since the motion profile is "continuous."
      currentController.set(getVelocitySetpoint(currentController, timer.get(), lastTime));
    }

    lastTime = timer.get();
  }

  private double getVelocitySetpoint(ChickenController currentController, double currentTime,
      double lastTime) {

    /*
      Whoa! This is weird. Although everything is ordered base on time, that's prone to getting off
      and just never correcting.
      However, positions can collide. Thus, we search forward in time from the last point we
      calculated until we get a position we like.

      Note that there's no guessing what the velocity setpoint should be based on how long it took
      the last loop to complete–that is, if your jerk is high, you might have some issues with the
      setpoint being a little wonky, as though it's lagging. Otherwise you shouldn't really notice.
    */

    Trajectory thisTrajectory = motionProfiles.get(currentController).trajectory;
    double dt = thisTrajectory.segments[0].dt;

    // Start from the current time and find the closest point.
    int startIndex = Math.toIntExact(Math.round(currentTime / dt));

    // Linear search is fine since we don't expect to be going far and the overhead of a different
    // data structure or search algorithm probably isn't worth it.
    for (int d = 0; ; d++) {

      Segment loSegment = thisTrajectory.segments[startIndex - d];
      Segment loSegmentn = thisTrajectory.segments[startIndex - d + 1];
      Segment hiSegment = thisTrajectory.segments[startIndex + d];
      Segment hiSegmentn = thisTrajectory.segments[startIndex + d - 1];

      // Grab the position, otherwise we might have issues where neither is true
      double position = currentController.getQuadraturePosition();

      // If the target position is between the last point's and this point's position
      // Take the one that's the least far ahead in time, then linearly interpolate between the two
      // to find the target velocity
      if (isBetween(position, loSegment.position, loSegmentn.position)
          && (startIndex - d) * dt > lastTime) {
        // Target the low segment; make sure it's actually ahead of the last time.
        return linearInterpolation(position, loSegment.position, loSegment.velocity,
            loSegmentn.position, loSegmentn.velocity);
      } else if (isBetween(position, hiSegmentn.position, hiSegment.position)) {
        // Target the high segment
        return linearInterpolation(position, hiSegmentn.position, hiSegmentn.velocity,
            hiSegment.position, hiSegment.velocity);
      } else if (startIndex + d > thisTrajectory.length() - 1
          || currentTime > thisTrajectory.length() * dt) {
        isFinished = true;
        // If we've overrun the end, set the velocity to be the end velocity.
        return thisTrajectory.segments[thisTrajectory.length() - 1].velocity;
      }
    }
  }

  /**
   * Calculate if a number is between two other numbers
   *
   * @param x The target
   * @param x1 Lower bound
   * @param x2 Upper bound
   * @return if x is between x1 and x2
   */
  private boolean isBetween(double x, double x1, double x2) {
    return (x >= x1 && x <= x2 || x >= x2 && x <= x1);
  }

  private double linearInterpolation(double x, double lowX, double lowY, double highX,
      double highY) {
    return (x - lowX) * (highY - lowY) / (highX - lowX) + lowY;
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

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

    public double getSecondsFromNeutralToFull() {
      return secondsFromNeutralToFull;
    }

    public void setSecondsFromNeutralToFull(double secondsFromNeutralToFull) {
      this.secondsFromNeutralToFull = secondsFromNeutralToFull;
    }

  }
}
