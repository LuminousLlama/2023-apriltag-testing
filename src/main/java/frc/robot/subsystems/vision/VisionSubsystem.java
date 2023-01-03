// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import org.littletonrobotics.junction.Logger;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class VisionSubsystem extends SubsystemBase {
  private VisionIO io;
  private VisionIOInputsAutoLogged inputs = new VisionIOInputsAutoLogged(); 

  public VisionSubsystem(VisionIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
   
    Logger.getInstance().processInputs("Vision", inputs);
  }

  public int getTagID() {
    return (int) inputs.tagID;
  }

  public double getTagArea() {
    return inputs.tagArea;
  }

  public double getTagPitch() {
    return inputs.tagPitch;
  }
  public double getYaw(){
    return inputs.tagYaw;
  }

  public boolean hasTargets(){
    return inputs.hasTargets;
  }

  public void takeInputSnapshot(){
    io.takeInputSnapshot();
  }

  public void takeOutputSnapshot(){
    io.takeOutputSnapshot();
  }

  
}
