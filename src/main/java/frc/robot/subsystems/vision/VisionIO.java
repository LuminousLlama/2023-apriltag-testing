package frc.robot.subsystems.vision;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

import edu.wpi.first.math.geometry.Transform3d;

public interface VisionIO {

    @AutoLog
    public static class VisionIOInputs{
        public long tagID = 0;
        public double tagArea = 0;
        public double tagPitch = 0;
        public double tagYaw = 0;
        public double tagAmbiguity = 0;

        public boolean hasTargets = false;

        //not supported
        //public Transform3d bestCameraToTarget = new Transform3d();
    }

    public default void updateInputs(VisionIOInputs inputs) {}

    public default void takeInputSnapshot() {}

    public default void takeOutputSnapshot() {}
}
