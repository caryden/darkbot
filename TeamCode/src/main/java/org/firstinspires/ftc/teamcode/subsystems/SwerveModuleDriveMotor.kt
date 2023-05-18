package org.firstinspires.ftc.teamcode.subsystems

import com.arcrobotics.ftclib.command.SubsystemBase
import com.arcrobotics.ftclib.controller.PIDController
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.arcrobotics.ftclib.hardware.motors.MotorEx
import com.qualcomm.robotcore.hardware.DcMotor

class SwerveModuleDriveMotor(private val driveMotor: MotorEx, kp: Double, ki: Double, kd: Double) : SubsystemBase() {

    constructor(driveMotor: MotorEx): this(driveMotor, 0.003, 0.0,0.0)
    init {
        // register so tha the command scheduler can call periodic() where we update the drive motor
        register()

        // set up the drive motor
        // TODO: Tune the velocity PID coefficients, and move them to the SwerveDriveConfiguration.
        //       We may need a different one for each swerveModule because each has a different friction.

        driveMotor.motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        driveMotor.setRunMode(Motor.RunMode.VelocityControl)
        // kp = 0.003
        driveMotor.setVeloCoefficients(kp, ki, kd) // might want to move this to the SwerveDriveConfiguration
        driveMotor.setDistancePerPulse(SwerveDriveConfiguration.wheelCircumference * SwerveDriveConfiguration.wheelRevsPerMotorRev / driveMotor.cpr)
    }

    var velocity : Double
        get() = driveMotor.rate
        set(value) {
            driveMotor.set(value)
        }

    var inverted : Boolean
        get() = driveMotor.inverted
        set(value) { driveMotor.inverted = value}

    override fun periodic() {
        runDriveMotorPID()
    }
    private fun runDriveMotorPID() {
        // TODO: determine if we should use the built-in motor PID controller or the external PIDController class
        // right now, we are using the motor's built-in PID controller
        // we could also use the external PIDController class from the ftclib
        // if so, we would do an external PID Loop here.
        // The internal PID controller is not as flexible as the ftclib PIDController
        // and it runs at 20Hz (50ms).  With and external PID some teams can get 80Hz (12.5ms)
    }
}