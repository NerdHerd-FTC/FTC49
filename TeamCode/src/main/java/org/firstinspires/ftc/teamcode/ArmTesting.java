package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Arm Testing")
public class ArmTesting extends LinearOpMode {
    public Servo servo1 = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Get servos
        DcMotor bottomMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor topMotor = hardwareMap.dcMotor.get("backLeft");

        bottomMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        topMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            bottomMotor.setPower(gamepad1.left_stick_y);
            topMotor.setPower(gamepad1.right_stick_y);

            telemetry.addData("bottomMotor", bottomMotor.getPower());
            telemetry.addData("topMotor", topMotor.getPower());


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
