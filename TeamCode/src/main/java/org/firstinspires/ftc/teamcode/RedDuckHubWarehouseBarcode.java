package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "REDDuckHubWarehouseBarcode", group = "18051")
public class RedDuckHubWarehouseBarcode extends LinearOpMode {

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
            // L3
            dropPosition = LlamaBot.ARM_POSITION_L3_DROP;
        } else {
            robot.strafeLeftByTime(0.3, 2250);
            double distance = robot.distance.getDistance(DistanceUnit.CM);
            robot.strafeRightByTime(0.3, 2200);

            if (distance < 35) {
                // L2
                robot.armMoveToPosition(LlamaBot.ARM_POSITION_L2_DRIVE, this);
                dropPosition = LlamaBot.ARM_POSITION_L2_DROP;
            } else {
                // L1
                robot.armMoveToPosition(LlamaBot.ARM_POSITION_L1_DRIVE, this);
                dropPosition = LlamaBot.ARM_POSITION_L1_DROP;
            }
        }

        // Potential sleep to prevent robot collision with other team
        sleep(0);

        // Drive back to wall
        robot.driveForwardByTime(-1, 800);
        robot.driveForwardByTime(-0.3, 200);

        // Strafe right and forward based on position (L3 require different values)
        if (dropPosition == LlamaBot.ARM_POSITION_L3_DROP) {
            robot.strafeRightByTime(1, 1200);
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1220);
        } else {
            robot.strafeRightByTime(1, 1150);
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1200);
        }

        // Drop cube
        robot.armMoveToPosition(dropPosition, this);
        robot.openClaw(300);

        // Go back to wall
        robot.driveForwardByTime(-1, 1400);
        robot.driveForwardByTime(-0.3, 600);

        // Move forward a bit and then strafe left for duck spin position
        robot.driveForwardByTime(1, 214);
        robot.strafeLeftByTime(1, 3300);
        robot.strafeLeftByTime(0.3, 300);

        // Spin duck
        robot.spin(false, 4200);

        // Go to warehouse
        robot.driveDiagonalByTime(false, true, 1, 7500);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);
    }
}
