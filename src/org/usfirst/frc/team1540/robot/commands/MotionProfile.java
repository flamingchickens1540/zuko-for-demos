package org.usfirst.frc.team1540.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;
import java.util.Set;
import org.usfirst.frc.team1540.robot.Properties;

public class MotionProfile extends Command {

  private int slotId = 0;
  private Set<Properties> motionProfiles;
  private Timer timer = new Timer();
  private double lastTime;
  private boolean isFinished = false;

  public MotionProfile(Set<Properties> motionProfiles) {
    this.motionProfiles = motionProfiles;
  }

  public int getSlotId() {
    return slotId;
  }

  public void setSlotId(int slotId) {
    this.slotId = slotId;
  }

  public Set<Properties> getMotionProfiles() {
    return motionProfiles;
  }

  public void setMotionProfiles(
      Set<Properties> motionProfiles) {
    this.motionProfiles = motionProfiles;
  }

  protected void initialize() {
    timer.start();
    lastTime = timer.get();
    isFinished = false;
  }

  protected void execute() {
    for (Properties currentProperty : motionProfiles) {
      // Each controller's setpoint is calculated at a slightly different time, but this doesn't
      // matter, since the motion profile is "continuous."
      double postiton = getPostionSetpoint(currentProperty, timer.get(), lastTime,
          currentProperty.getEncoderTickRatio());
      SmartDashboard.putNumber("calculatedVelocity", postiton);
      currentProperty.getSetMotorPositionFunction().accept(postiton);
    }

    lastTime = timer.get();
  }

  private double getPostionSetpoint(Properties currentProperty, double currentTime,
      double lastTime, double encoderMultiplier) {

    /*
      Whoa! This is weird. Although everything is ordered base on time, that's prone to getting off
      and just never correcting.
      However, positions can collide. Thus, we search forward in time from the last point we
      calculated until we get a position we like.

      Note that there's no guessing what the velocity setpoint should be based on how long it took
      the last loop to complete–that is, if your jerk is high, you might have some issues with the
      setpoint being a little wonky, as though it's lagging. Otherwise you shouldn't really notice.
    */

    Trajectory thisTrajectory = currentProperty.getTrajectory();
    double dt = thisTrajectory.segments[0].dt;

    // Start from the current time and find the closest point.
    int startIndex = Math.toIntExact(Math.round(currentTime / dt));

    SmartDashboard.putNumber("startIndex", startIndex);

    // FIXME
    /*
    // Linear search is fine since we don't expect to be going far and the overhead of a different
    // data structure or search algorithm probably isn't worth it.
    for (int d = 0; ; d++) {

      SmartDashboard.putNumber("currentSearchIndex", d);
      // Set it either to the correct place or first item
      Segment loSegment = (startIndex - d >= 0 ? thisTrajectory.segments[startIndex - d]
          : thisTrajectory.segments[0]);
      // Set it either to the correct place or first item
      Segment loSegmentn = (startIndex - d + 1 >= 0 ? thisTrajectory.segments[startIndex - d + 1]
          : thisTrajectory.segments[0]);
      // Set it either to the correct place or last item
      Segment hiSegment = (startIndex + d < thisTrajectory.length() ?
          thisTrajectory.segments[startIndex + d] : thisTrajectory.segments[thisTrajectory.length()
          - 1]);
      // Set it either to the correct place, first place, or last item
      Segment hiSegmentn = (startIndex + d < thisTrajectory.length() ?
          (startIndex + d - 1 >= 0 ? thisTrajectory.segments[startIndex + d - 1] : hiSegment)
          : thisTrajectory.segments[thisTrajectory.length() - 1]);

      // Grab the position, otherwise we might have issues where neither is true
      double position = currentController.getQuadraturePosition() * encoderMultiplier;

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
    */

    int length = thisTrajectory.segments.length;
    int index = (startIndex < length) ? startIndex : length - 1;
    return thisTrajectory.segments[index].position / encoderMultiplier;
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
}
