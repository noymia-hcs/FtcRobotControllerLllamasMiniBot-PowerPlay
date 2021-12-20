package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "BLUEDuckHubWarehousePark", group = "18051")
public class BlueDuckHubWarehousePark extends LinearOpMode {

    LlamaBot robot = new LlamaBot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this);

        waitForStart();
        ///19 points with storage unit - 26 points with warehouse

        robot.closeClaw(500);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DRIVE, this);
        robot.strafeRightByTime(robot.DRIVE_SPEED, 400);
        robot.spin(true, 3800);
        robot.strafeLeftByTime(1/*robot.DRIVE_SPEED * 4*/, 3000);
        robot.driveForwardByTime(-0.3, 300);
        robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1500);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DROP, this);
        robot.openClaw(300);
        robot.driveForwardByTime(-1, 1300);
        robot.driveForwardByTime(-0.3, 600);
        robot.strafeLeftByTime(robot.DRIVE_SPEED * 4, 3000);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);
    }
}