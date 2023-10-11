// Code Created By Derrick, Owen, Shash
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "All Lift - Claw & Joint")
public class AllLift extends LinearOpMode {
    ElapsedTime CSR = new ElapsedTime();
    ElapsedTime CSL = new ElapsedTime();
    ElapsedTime CW = new ElapsedTime();
    ElapsedTime matchTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        // Get motors
        DcMotor jointMotor = hardwareMap.dcMotor.get("joint");
        DcMotor armMotor = hardwareMap.dcMotor.get("arm");

        jointMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        jointMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        jointMotor.setDirection(DcMotor.Direction.FORWARD);
        jointMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setDirection(DcMotor.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        CRServo FrontRollerServoRight = hardwareMap.get(CRServo.class, "FRSR");
        CRServo FrontRollerServoLeft= hardwareMap.get(CRServo.class, "FRSL");
        Servo ClawServoRight = hardwareMap.get(Servo.class, "CSR");
        Servo ClawServoLeft = hardwareMap.get(Servo.class, "CSL");
        Servo DroneServo = hardwareMap.get(Servo.class, "DS");
        Servo WristServo = hardwareMap.get(Servo.class, "WS");

        // Reverse if opposite directions are seen
        FrontRollerServoRight.setDirection(CRServo.Direction.FORWARD);
        FrontRollerServoLeft.setDirection(CRServo.Direction.REVERSE);

        // Reverse if opposite directions are seen
        ClawServoRight.setDirection(Servo.Direction.FORWARD);
        ClawServoLeft.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        matchTime.reset();

        while (opModeIsActive()) {
            jointMotor.setPower(setJointPower(jointMotor, gamepad1));
            armMotor.setPower(setArmPower(armMotor, gamepad1));

            setRollerPowerRight(FrontRollerServoRight, gamepad2);
            setRollerPowerLeft(FrontRollerServoLeft, gamepad2);

            setClawServoLeft(ClawServoLeft, gamepad2, 0.5, 0.0);
            setClawServoRight(ClawServoRight, gamepad2, 0.5, 0.0);

            checkGamepadParameters(gamepad1, "Driver");
            telemetry.addLine("\n");
            //checkGamepadParameters(gamepad2, "Operator");

            motorTelemetry(jointMotor, "Joint");
            telemetry.addLine("\n");
            motorTelemetry(armMotor, "Arm");

            telemetry.update();
            sleep(50);
        }
    }

    // SERVO METHODS
    private void setRollerPowerRight(CRServo FrontRollerServoRight, Gamepad gamepad) {
        double power = 0;

        if(gamepad.right_trigger != 0 )  {
            power = 0.75;
        }

        FrontRollerServoRight.setPower(power);
    }
    private void setRollerPowerLeft(CRServo FrontRollerServoLeft, Gamepad gamepad) {
        double power = 0;

        if(gamepad.left_trigger != 0 )  {
            power = -0.75;
        }

        FrontRollerServoLeft.setPower(power);
    }
    private void setClawServoRight(Servo ClawServoRight, Gamepad gamepad, double closed_position, double open_position) {
        double position = ClawServoRight.getPosition();

        if(gamepad.right_bumper && CSR.seconds() > 0.5)  {
            if (position <= closed_position)
                position = open_position;
            else if (position >= open_position)
                position = closed_position;
            CSR.reset();
        }

        ClawServoRight.setPosition(position);
    }
    private void setClawServoLeft(Servo ClawServoLeft, Gamepad gamepad, double closed_position, double open_position) {
        double position = ClawServoLeft.getPosition();

        if(gamepad.left_bumper && CSL.seconds() > 0.5)  {
            if (position <= closed_position)
                position = open_position;
            else if (position >= open_position)
                position = closed_position;
            CSL.reset();
        }

        ClawServoLeft.setPosition(position);
    }
    private void setDroneServoPower(Servo DroneServo, Gamepad gamepad, double launch_position) {
        double position = DroneServo.getPosition();

        if(gamepad.a && matchTime.seconds() > 150)  {
            position = launch_position;
        }

        DroneServo.setPosition(position);
    }
    private void setWristServoPower(Servo WristServo, Gamepad gamepad){
        double position = WristServo.getPosition();

        if(gamepad.dpad_up){
            position += 0.1;
        }
        else if(gamepad.dpad_down){
            position -= 0.1;
        }

        WristServo.setPosition(position);
    }

    // ARM AND JOINT MOTOR METHODS
    private double setJointPower(DcMotor jointMotor, Gamepad gamepad) {
        double power;
        double mult = 1;
        double input = -gamepad.left_stick_y;

        power = input*mult;

        /*
        if (jointMotor.getCurrentPosition() <= 0 && input < 0) {
            power = 0;
        } else {
            power = input*mult;
        }
         */

        return power;
    }
    private double setArmPower(DcMotor jointMotor, Gamepad gamepad) {
        double power;
        double mult = 0.5;
        double input = -gamepad.right_stick_y;
        power = input*mult;

        /*
        if (jointMotor.getCurrentPosition() <= 0 && input < 0) {
            power = 0;
        } else {
            power = input*mult;
        }

         */

        return power;
    }

    // TELEMETRY METHODS
    private void checkGamepadParameters(Gamepad gamepad, String position) {
        telemetry.addLine("--- " + position + " ---");
        if (gamepad.left_stick_x != 0 || gamepad.left_stick_y != 0) {
            telemetry.addData("Left Stick X", gamepad.left_stick_x);
            telemetry.addData("Left Stick Y", gamepad.left_stick_y);
        }

        if (gamepad.right_stick_x != 0 || gamepad.right_stick_y != 0) {
            telemetry.addData("Right Stick X", gamepad.right_stick_x);
            telemetry.addData("Right Stick Y", gamepad.right_stick_y);

        }

        if (gamepad.left_trigger != 0) {
            telemetry.addData("Left Trigger", gamepad.left_trigger);
        }

        if (gamepad.right_trigger != 0) {
            telemetry.addData("Right Trigger", gamepad.right_trigger);
        }

        if (gamepad.dpad_up) {
            telemetry.addLine("DPad Up Pressed");
        }
        if (gamepad.dpad_down) {
            telemetry.addLine("DPad Down Pressed");
        }
        if (gamepad.dpad_left) {
            telemetry.addLine("DPad Left Pressed");
        }
        if (gamepad.dpad_right) {
            telemetry.addLine("DPad Right Pressed");
        }
    }

    private void motorTelemetry(DcMotor motor, String name) {
        telemetry.addLine("--- " + name + " ---");
        telemetry.addData(name + " Power", motor.getPower());
        telemetry.addData(name + " Position", motor.getCurrentPosition());
        telemetry.addData(name + " Target Position", motor.getTargetPosition());
    }
    //-621, 0
    // -600
}