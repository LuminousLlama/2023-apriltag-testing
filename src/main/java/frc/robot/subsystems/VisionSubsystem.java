// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class VisionSubsystem extends SubsystemBase {
  private final NetworkTableInstance instance;
  private final PhotonCamera camera;

  private PhotonPipelineResult latestResult;

  private int tagID = 0;
  private double tagArea = 0;
  private double tagPitch = 0;
  private double tagYaw = 0;

  private boolean hasTargets = false;

  private Transform3d bestCameraToTarget = new Transform3d();

  public VisionSubsystem() {
    instance = NetworkTableInstance.getDefault();

    if(Robot.isSimulation()) {
      instance.stopServer();
      // set the NT server if simulating this code.
      // "localhost" for photon on desktop, or "photonvision.local" / "[ip-address]" for coprocessor
      instance.setServer("photonvision.local");
      instance.startClient3("myRobot");
      camera = new PhotonCamera(instance, "cam");
    } else {
      camera = new PhotonCamera("cam");
    }
  }

  public int getTagID() {
    return tagID;
  }

  public double getTagArea() {
    return tagArea;
  }

  public double getTagPitch() {
    return tagPitch;
  }
  public double getYaw(){
    return tagYaw;
  }

  public boolean hasTargets(){
    return hasTargets;
  }

  @Override
  public void periodic() {
    latestResult = camera.getLatestResult();
    hasTargets = latestResult.hasTargets();
    SmartDashboard.putBoolean("has targets", hasTargets);

    if (hasTargets) {
      PhotonTrackedTarget target = latestResult.getBestTarget();
      
      tagID = target.getFiducialId();
      tagArea = target.getArea();
      tagPitch = target.getPitch();
      tagYaw = target.getYaw();
      bestCameraToTarget = target.getBestCameraToTarget();
      SmartDashboard.putNumber("tag ID", tagID);
      SmartDashboard.putNumber("tag Area", tagArea);
      SmartDashboard.putNumber("tag Pitch", tagPitch);
      SmartDashboard.putNumber("tag Yaw", tagYaw);
      
      SmartDashboard.putNumber("transform rotation angle", Math.toDegrees( bestCameraToTarget.getRotation().getAngle() ));
      SmartDashboard.putNumber("transform X distance (forward)", bestCameraToTarget.getX());
      SmartDashboard.putNumber("transform Y distance (left)", bestCameraToTarget.getY());
      SmartDashboard.putNumber("transform Z distance (up)", bestCameraToTarget.getZ());
    }
   
  }
}
