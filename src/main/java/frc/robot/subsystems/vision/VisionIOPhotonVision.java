package frc.robot.subsystems.vision;

import org.photonvision.PhotonCamera;

import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;

public class VisionIOPhotonVision implements VisionIO {
    private final NetworkTableInstance instance; 
    private final PhotonCamera camera;

    public VisionIOPhotonVision(){
        instance = NetworkTableInstance.getDefault();
        if (Robot.isSimulation()) {
            instance.stopServer();
            // set the NT server if simulating this code.
            // "localhost" for photon on desktop, or "photonvision.local" / "[ip-address]"
            // for coprocessor
            instance.setServer("photonvision.local");
            instance.startClient3("myRobot");
            camera = new PhotonCamera(instance, "cam");
        } else {
            camera = new PhotonCamera("cam");
        }
    }
    
    
    @Override
    public void updateInputs(VisionIOInputs inputs) {
        var result = camera.getLatestResult();

        if(!result.hasTargets()){
            inputs.hasTargets = false;

            inputs.tagID = -1;
            inputs.tagArea = (int) Double.NaN;
            inputs.tagPitch = Double.NaN;
            inputs.tagYaw = Double.NaN;
            inputs.tagAmbiguity = Double.NaN;

            //inputs.bestCameraToTarget = null;
        } else { //has targets true
            inputs.hasTargets = true;

            var target = result.getBestTarget();

            inputs.tagID = target.getFiducialId();
            inputs.tagArea = target.getArea();
            inputs.tagPitch = target.getPitch();
            inputs.tagYaw = target.getYaw();
            inputs.tagAmbiguity = target.getPoseAmbiguity();

            //inputs.bestCameraToTarget = target.getBestCameraToTarget();
        }
    }

    @Override
    public void takeInputSnapshot() {
        camera.takeInputSnapshot();
    }

    @Override
    public void takeOutputSnapshot() {
        camera.takeOutputSnapshot();
    }
    
}