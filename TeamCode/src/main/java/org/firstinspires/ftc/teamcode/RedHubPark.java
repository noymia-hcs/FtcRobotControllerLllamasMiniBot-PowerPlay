package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "REDHubPark", group = "18051")
public class RedHubPark extends LinearOpMode {

    LlamaBot robot = new LlamaBot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this);

        waitForStart();

        robot.closeClaw(1000);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DRIVE, this);
        robot.driveForwardByTime(1/*robot.DRIVE_SPEED * 4*/, 1370);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DROP, this);
        robot.openClaw(600);
        robot.driveForwardByTime(-1, 1400);
        robot.driveForwardByTime(-0.3, 600);
        robot.strafeRightByTime(robot.DRIVE_SPEED * 4, 3700);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);

    }
}
