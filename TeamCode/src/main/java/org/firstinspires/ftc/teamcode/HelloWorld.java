package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name= "Hello World", group="Robot")
public class HelloWorld extends LinearOpMode {
@Override
    public void runOpMode () {
          telemetry.addLine("Hello Work=ld");
          telemetry.update() ;

    waitForStart();
          int count = 0;
          while (opModeIsActive()) {
               if(gamepad1.right_stick_button) {
              }
          }
    }
}