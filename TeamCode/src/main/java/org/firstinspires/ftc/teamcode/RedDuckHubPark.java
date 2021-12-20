package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "REDDuckHubPark", group = "18051")
public class RedDuckHubPark extends LinearOpMode {

    LlamaBot robot = new LlamaBot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, this);

        waitForStart();
        ///19 points with storage unit - 26 points with warehouse

        robot.closeClaw(300);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DRIVE, this);
        robot.driveForwardByTime(-robot.DRIVE_SPEED, 300);
        robot.spin(false, 3800);
        robot.strafeLeftByTime(robot.DRIVE_SPEED * 4, 2200);
        robot.driveForwardByTime(robot.DRIVE_SPEED * 4, 1670);
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_L3_DROP, this);
        robot.openClaw(300);
        robot.driveForwardByTime(-robot.DRIVE_SPEED * 4, 2200);
        if (true) {
            robot.strafeRightByTime(robot.DRIVE_SPEED * 4, 700);
            robot.driveForwardByTime(-robot.DRIVE_SPEED, 400);
        } else {
            robot.strafeRightByTime(1 * robot.DRIVE_SPEED, 500);
            robot.driveForwardByTime(robot.DRIVE_SPEED * 2, 4500);
        }
        robot.armMoveToPosition(LlamaBot.ARM_POSITION_FLOOR, this);
   }
}