package org.firstinspires.ftc.teamcode.opmodes

import com.arcrobotics.ftclib.command.CommandOpMode
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.hardware.motors.CRServo
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.hardware.motors.MotorEx
import com.arcrobotics.ftclib.hardware.motors.MotorGroup
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.commands.TestDrive
import org.firstinspires.ftc.teamcode.subsystems.LeapfrogDriveBase
import java.util.function.DoubleSupplier


@TeleOp(name = "Leapfrog Drive Motor Test", group = "none")
public class LeapfrogDriveMotorTestOpmode() : CommandOpMode() {

    override fun initialize() {

        // let's get the drive motors
        val frontLeftDrive = MotorEx(hardwareMap, "frontLeftDrive", Motor.GoBILDA.BARE)
        val frontRRightDrive = MotorEx(hardwareMap, "frontRightDrive", Motor.GoBILDA.BARE)
        val backLeftDrive = MotorEx(hardwareMap, "backLeftDrive", Motor.GoBILDA.BARE)
        val backRightDrive = MotorEx(hardwareMap, "backRightDrive", Motor.GoBILDA.BARE)


        val frontLeftCRServo = CRServo(hardwareMap, "frontLeftServo")
        val frontRightCRServo = CRServo(hardwareMap, "frontRightServo")
        val backLeftCRServo = CRServo(hardwareMap, "backLeftServo")
        val backRightCRServo = CRServo(hardwareMap, "backRightServo")

        val frontLeftAnalogInput = hardwareMap.get(AnalogInput::class.java, "frontLeftServoAngle")
        val frontRightAnalogInput = hardwareMap.get(AnalogInput::class.java, "frontRightServoAngle")
        val backLeftAnalogInput = hardwareMap.get(AnalogInput::class.java,"backLeftServoAngle")
        val backRightAnalogInput = hardwareMap.get(AnalogInput::class.java,"backRightServoAngle")


        val leapfrogDrive = LeapfrogDriveBase(frontLeftDrive, frontRRightDrive, backLeftDrive, backRightDrive,
                                frontLeftCRServo, frontRightCRServo, backLeftCRServo, backRightCRServo,
                                frontLeftAnalogInput, frontRightAnalogInput, backLeftAnalogInput, backRightAnalogInput)
        val driverOp = GamepadEx(gamepad1);
        val command = TestDrive(leapfrogDrive, DoubleSupplier { driverOp.leftY });

        schedule(command);
    }


}