package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//https://github.com/NerdHerd-FTC/CAMS-FTC/blob/develop_Jadon/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/MecanumControlDriverOriented.java
@TeleOp(name = "Arm Control Driver Oriented - SIM")
public class ArmControlDriverOriented extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare motors (F=front, B=back, R=right, L=left)
        DcMotor motorFL = hardwareMap.dcMotor.get("frontLeft");



        // Right motors should move in reverse
        motorFL.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_x; // Y stick is reversed
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing



            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) , 1);
            //Set motor powers
            double FLPower = ( y + x ) / denominator;



            //Run motors using powers
            motorFL.setPower(FLPower);
            telemetry.addData("Arm Encoder", motorFL.getCurrentPosition());
        }
    }
}