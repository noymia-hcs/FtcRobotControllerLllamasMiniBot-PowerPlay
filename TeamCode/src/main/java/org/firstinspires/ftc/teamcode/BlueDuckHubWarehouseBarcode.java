package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "BLUEDuckHubWarehouseBarcode", group = "18051")
public class BlueDuckHubWarehouseBarcode extends LinearOpMode {

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

        robot.driveForwardByTime(0.3, 500);
        robot.strafeRightByTime(1, 600);
        robot.spin(true, 4200);
        robot.strafeLeftByTime(1/*robot.DRIVE_SPEED * 4*/, 2800);
        robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1500);
        robot.armMoveToPosition(dropPosition, this);
        robot.openClaw(300);
        robot.driveForwardByTime(-1, 1300);
        robot.driveForwardByTime(-0.3, 600);
        robot.strafeLeftByTime(robot.DRIVE_SPEED * 4, 3000);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);
    }
}

