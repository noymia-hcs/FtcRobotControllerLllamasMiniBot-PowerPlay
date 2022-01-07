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

        if (robot.distance.getDistance(DistanceUnit.CM) < 35) {
            // L3
            dropPosition = LlamaBot.ARM_POSITION_L3_DROP;
        } else {
            robot.strafeLeftByTime(0.3, 3000);
            double distance = robot.distance.getDistance(DistanceUnit.CM);
            robot.strafeRightByTime(0.3, 2500);

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
        robot.driveForwardByTime(-1, 800);
        robot.driveForwardByTime(-0.3, 200);
        robot.strafeRightByTime(1, 700);


        if (dropPosition == LlamaBot.ARM_POSITION_L3_DROP) {
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1200);
        } else {
            robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1120);
        }
        robot.armMoveToPosition(dropPosition, this);
        robot.openClaw(300);
        robot.driveForwardByTime(-1, 1300);
        robot.driveForwardByTime(-0.3, 600);
        robot.driveForwardByTime(1, 214);
        robot.strafeLeftByTime(1, 1300);
        robot.strafeLeftByTime(0.3, 300);
        robot.spin(false, 4200);
        robot.driveForwardByTime(1, 642);
        robot.strafeLeftByTime(1, 800);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);
    }
}


