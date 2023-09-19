package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Single Servo Testing")
public class ServoTesting extends LinearOpMode {
    public Servo servo1 = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Get servos
        servo1 = hardwareMap.get(Servo.class, "Servo1");

        waitForStart();

        if (isStopRequested()) return;

        double location = 0;

        while (opModeIsActive()) {
            if (gamepad1.y) {
                location = location + 0.1;
            } else if (gamepad1.a) {
                location = location - 0.1;
            } else if (gamepad1.x) {
                location = location + 0.06;
            } else if (gamepad1.b) {
                location = location - 0.06;
            }

            servo1.setPosition(location);
            telemetry.addData("Current Location", location);
            telemetry.update();
        }
    }
}

// Every time y is pressed, the servo location increases by 0.1
/// Every time a is pressed, the servo location decreases by 0.1

// float location = 0
// If y is clicked, then location = location + 0.1
// If a is clicked, then location = location - 0.1
// If x is clicked, then location = location + 0.06
// If b is clicked, then location = location - 0.06
// hi owen its me i
