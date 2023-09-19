package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * TeleOp for Double Reverse Virtual 4Bar with Marco
 */
@TeleOp(name= "Jiwon_Servo", group="Robot")

public class Jiwon_Servo extends LinearOpMode {
            /* Declare OpMode members. */
    public Servo clawFinger = null;

    @Override
    public void runOpMode () {
        clawFinger = hardwareMap.get(Servo.class, "Jiwon_Servo");
        //Send Telemetry message to signify robot waiting;
        telemetry.addData(">", "Robot Ready. Press Play.");     //
        telemetry.update();

        //Wait for the game to start  (driver presses PLAY)
        waitForStart();

        //run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
                    if (gamepad1.a) {
                        clawFinger.setPosition(0.5);
                        telemetry.addLine("Zero position set.");
                    }
                    //Send telemetry message to signify robot running ;
            telemetry.update();

                    //Pace this loop so jaw action s reasonable speed
                    sleep(40);
            float left_stick_y = gamepad1.left_stick_y;
            float right_stick_y = gamepad2.right_stick_y;
        }
        }
    }












