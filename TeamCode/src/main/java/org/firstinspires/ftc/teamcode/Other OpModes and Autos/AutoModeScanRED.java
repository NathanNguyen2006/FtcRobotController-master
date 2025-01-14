package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Autonomous Mode Scaning Team Prop RED", group = "Concept")

public class AutoModeScanRED extends LinearOpMode {
    private RoboController roboController;

    @Override
    public void runOpMode() {
        roboController = new RoboController(this);

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            // !! ADD CODE FOR ACTUALLY SCANNING PROP ONCE ITS DONE !!

            // autonomous scoring for the red side (towards the backboard)
            sleep(1000);

            // move right to the middle of the adjacent panel
            roboController.moveOnXAxis(RoboController.inchesToCounts(27));

            // move forward to the middle of the adjacent panel
            roboController.moveOnYAxis(RoboController.inchesToCounts(27));

            // turn left by about 90 degrees
            roboController.Spin(RoboController.inchesToCounts(-18));

            // if team prop is on the LEFT
            roboController.moveOnXAxis(RoboController.inchesToCounts(2));

            // if team prop is in the MIDDLE
            // stay in that position

            // if team prop is on the RIGHT
            roboController.moveOnXAxis(RoboController.inchesToCounts(-2));

            // move back right against the middle of the backboard
            roboController.moveOnYAxis(RoboController.inchesToCounts(-19));

            // move the arm back until it reaches a position thats right against the backboard (2050)
            while(roboController.ArmR.getCurrentPosition() < 2050) {
                roboController.ArmL.setPower(0.45);
                roboController.ArmR.setPower(0.45);
            }

            // once the arm is against the backboard, stop moving it back
            roboController.ArmL.setPower(0);
            roboController.ArmR.setPower(0);

            // push pixels out
            roboController.ClawR.setDirection(DcMotorSimple.Direction.REVERSE);
            roboController.ClawR.setPower(0.75);
            roboController.ClawL.setDirection(DcMotorSimple.Direction.FORWARD);
            roboController.ClawL.setPower(0.75);

            // wait a second in case the pixels haven't been completely scored yet
            sleep(2000);

            // stop rotating claw
            roboController.ClawR.setPower(0);
            roboController.ClawL.setPower(0);

            // move the arm forward until it reaches a position thats about where the floor is (10)
            while(roboController.ArmR.getCurrentPosition() > 10) {
                roboController.ArmL.setPower(-0.45);
                roboController.ArmR.setPower(-0.45);
            }

            // once the arm is against the floor, stop moving it forward
            roboController.ArmL.setPower(0);
            roboController.ArmR.setPower(0);

            // wait a second to give the bot time to put down the arm
            sleep(1000);

            // move forward so that the bot isn't right against the backboard
            roboController.moveOnYAxis(RoboController.inchesToCounts(3));

            // move left to the middle of the adjacent panel
            roboController.moveOnXAxis(RoboController.inchesToCounts(-27));

            // move back to the space next to the backboard
            roboController.moveOnYAxis(RoboController.inchesToCounts(-13));
            stop();
        }
    }
}