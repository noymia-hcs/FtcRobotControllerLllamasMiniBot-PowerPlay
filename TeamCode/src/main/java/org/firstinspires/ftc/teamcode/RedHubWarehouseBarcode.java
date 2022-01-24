package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "REDHubWarehouseBarcode", group = "18051")
public class RedHubWarehouseBarcode extends LinearOpMode {

    LlamaBot robot = new LlamaBot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this);

        waitForStart();

        robot.closeClaw(500);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DRIVE, this);
        robot.driveForwardByTime(1, 800);

        int dropPosition = LlamaBot.ARM_POSITION_L3_DROP;

        if (robot.distance.getDistance(DistanceUnit.CM) < 35) {
            // L1
            robot.armMoveToPosition(LlamaBot.ARM_POSITION_L1_DRIVE, this);
            dropPosition = LlamaBot.ARM_POSITION_L1_DROP;
        } else {
            robot.strafeRightByTime(0.3, 2250);
            double distance = robot.distance.getDistance(DistanceUnit.CM);
            robot.strafeLeftByTime(0.3, 2200);

            if (distance < 35) {
                // L2
                robot.armMoveToPosition(LlamaBot.ARM_POSITION_L2_DRIVE, this);
                dropPosition = LlamaBot.ARM_POSITION_L2_DROP;
            } else {
                // L3
                robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DRIVE, this);
                dropPosition = LlamaBot.ARM_POSITION_L3_DROP;
            }
        }

        // Potential sleep to prevent robot collision with other team
        sleep(0);

        // Go back to wall
        robot.driveForwardByTime(-1, 800);
        robot.driveForwardByTime(-0.3, 200);

        // Strafe left and forward based on position (L3 require different values)
        if (dropPosition == LlamaBot.ARM_POSITION_L3_DROP) {
            robot.strafeLeftByTime(1, 850);
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1250);
        } else {
            robot.strafeLeftByTime(1, 800);
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1130);
        }

        // Drop cube
        robot.armMoveToPosition(dropPosition, this);
        robot.openClaw(300);

        // Go back to wall and drive to warehouse
        robot.driveForwardByTime(-1, 1400);
        robot.driveForwardByTime(-0.3, 600);
        robot.strafeRightByTime(robot.DRIVE_SPEED * 4, 3700);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);
    }
}
