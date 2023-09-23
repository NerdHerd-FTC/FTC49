package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Joint Encoder")
public class JointEncoder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Get servos
        DcMotor jointMotor = hardwareMap.dcMotor.get("frontLeft");

        jointMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        jointMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        jointMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        jointMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            telemetry.addData("Encoder location", jointMotor.getCurrentPosition());
            telemetry.update();

            sleep(50);
        }
    }

    //-621, 0
    // -600
}