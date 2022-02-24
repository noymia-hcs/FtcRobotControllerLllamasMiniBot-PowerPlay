package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "REDDuckHubStorageBarcode", group = "18051")
public class RedDuckHubStorageBarcode extends LinearOpMode {

    LlamaBot robot = new LlamaBot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this);

        waitForStart();

        robot.closeClaw(500);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DRIVE, this);
        robot.driveForwardByTime(1, 800);

        int dropPosition = LlamaBot.ARM_POSITION_L3_DROP;

        if (robot.distance.getDistance(DistanceUnit.CM) < LlamaBot.ELEMENT_THRESHHOLD) {
            // L3
            dropPosition = LlamaBot.ARM_POSITION_L3_DROP;
        } else {
            robot.strafeLeftByTime(0.3, 2250);
            double distance = robot.distance.getDistance(DistanceUnit.CM);
            robot.strafeRightByTime(0.3, 2200);

            if (distance < LlamaBot.ELEMENT_THRESHHOLD) {
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
            robot.strafeRightByTime(1, 1120);
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1300);
        } else {
            robot.strafeRightByTime(1, 1070);
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1220);
        }

        // Drop cube
        robot.armMoveToPosition(dropPosition, this);
        robot.openClaw(300);

        // Go back to wall
        robot.driveForwardByTime(-1, 1400);
//        robot.driveForwardByTime(-0.3, 600);

        // Move forward a bit and then strafe left for duck spin position
        robot.driveForwardByTime(1, 300);
        robot.strafeLeftByTime(1, 3450);
        robot.driveDiagonalByTime(false, false, 0.3, 800);

        // Spin duck
        robot.spin(false, 3000);

        // Go to storage unit
        robot.driveForwardByTime(1, 750);
        robot.strafeLeftByTime(1, 1000);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);
    }
}
