// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    private static final RobotType ROBOT = RobotType.ROBOT_SIMBOT; 
        
    //basically a check to make sure ROBOT isnt accidentally set to ROBOT_SIMBOT on a real robot
    public static RobotType getRobot() {
        if (RobotBase.isReal()) {
          if (ROBOT == RobotType.ROBOT_SIMBOT) { // Invalid robot selected
            System.out.println("*****************\n" + "INVALID ROBOT TYPE\n" + "**********************");
            return RobotType.ROBOT_2022;
          } else {
            return ROBOT;
          }
        } else {
          return ROBOT;
        }
    }

    public static Mode getMode() {
        switch (ROBOT) {
          case ROBOT_2022:
            return RobotBase.isReal() ? Mode.REAL : Mode.REPLAY;
    
          case ROBOT_SIMBOT:
            return Mode.SIM;
    
          default:
            return Mode.REAL;
        }
      }

    public enum RobotType {
        ROBOT_2022,
        ROBOT_SIMBOT
    }
    public enum Mode {
        REAL,
        SIM, 
        REPLAY
    }
}
