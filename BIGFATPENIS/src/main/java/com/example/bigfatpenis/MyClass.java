package com.example.bigfatpenis;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

/*Trajectory myTrajectory3 = drive.trajectoryBuilder(startingPosition)
                .lineToLinearHeading(new Pose2d(23.5, 53, Math.toRadians(-90)))
                //.strafeRight(12.5)
                .build();

        Pose2d pos2 = new Pose2d(23.5, 53, Math.toRadians(-90));
        Trajectory myTrajectory4 = drive.trajectoryBuilder(pos2)
                .forward(6)
                .build();

        Pose2d pos3 = new Pose2d(23.5, 46, Math.toRadians(-90));
        Trajectory myTrajectory5 = drive.trajectoryBuilder(pos3)
                .forward(-13)
                .build();

        Pose2d pos4 = new Pose2d(23.5, 59, Math.toRadians(-90));
        Trajectory myTrajectory6 = drive.trajectoryBuilder(pos4)
                .strafeLeft(12.5)
                .build();

        Pose2d pos5 = new Pose2d(36, 59, Math.toRadians(-90));
        Trajectory myTrajectory7 = drive.trajectoryBuilder(pos5)
                .lineToLinearHeading(new Pose2d (36, 7,Math.toRadians(0)))
                .build();

        Pose2d pos6 = new Pose2d(36, 7, Math.toRadians(0));
        Trajectory myTrajectory8 = drive.trajectoryBuilder(pos6)
                .forward(27)
                .build();

        Pose2d pos7 = new Pose2d(63, 7, Math.toRadians(0));
        Trajectory myTrajectory9 = drive.trajectoryBuilder(pos7)
                .lineToLinearHeading(new Pose2d (31, 5,Math.toRadians(135)))
                .build();

        Pose2d pos8 = new Pose2d(32, 5, Math.toRadians(135));
        Trajectory myTrajectory10 = drive.trajectoryBuilder(pos8)
                .forward(10)
                .build();*/

public class MyClass {
    public static void main(String args[]){
        MeepMeep meepMeep = new MeepMeep(800);

        double a = 5.5;
        int b = 0;
        double c = 0;

        Pose2d startingPosition = new Pose2d(-36, 59, Math.toRadians(-90));

        //Might need to rotate first before CONES
        Pose2d CONES = new Pose2d(-(62.8+2.5), (6 + a+c), Math.toRadians(180));
        Pose2d CONES2 = new Pose2d(-(63.3+3), (6 + a+c), Math.toRadians(180));

        //park1 is the same as CONES
        Pose2d park2 = new Pose2d(-38, (6 + a+c), Math.toRadians(90));
        Pose2d park3 = new Pose2d(-14, (6 + a+c), Math.toRadians(180));

        Pose2d colorPosition = new Pose2d(-36, (41 - b), Math.toRadians(180));

        Pose2d lineUpCones2and3and4 = new Pose2d(-36, (6 + a+c), Math.toRadians(180));

        Pose2d lineUpCones2and3and4AfterTurn = new Pose2d(-36, (6 + a+c), Math.toRadians(-90));

        Pose2d afterPlacingAllCones = new Pose2d(-36, (6 + a+c), Math.toRadians(95));

        Pose2d pushSignalConeAcross = new Pose2d(-36, (-1 + a+c), Math.toRadians(180));

        Pose2d lineUpCone1 = new Pose2d(-36, (7 + a+c), Math.toRadians(0));

        Pose2d placePosCone1 = new Pose2d(-(28-0.3), (16+0.3 - b), Math.toRadians(45));

        Pose2d placePosCones2and3and4 = new Pose2d(-30, (16.5 - b), Math.toRadians(45));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 30, Math.toRadians(180), Math.toRadians(180), 10.75)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startingPosition)
                                //.lineToLinearHeading(new Pose2d(23.5, (53+a), Math.toRadians(-90)))
                                //go up cone
                                .lineToLinearHeading(colorPosition)
                                .lineToLinearHeading(pushSignalConeAcross)
                                .lineToLinearHeading(lineUpCone1)
                                .lineToLinearHeading(placePosCone1)
                                .addDisplacementMarker(() -> {
                                    // Run your action in here!
                                    // Drop servo, start motor, whatever
                                })
                                .lineToLinearHeading(afterPlacingAllCones)
                                .lineToLinearHeading(CONES)
                                .addDisplacementMarker(() -> {
                                    // Run your action in here!
                                    // Drop servo, start motor, whatever
                                })
                                .lineToLinearHeading(lineUpCones2and3and4)
                                .lineToLinearHeading(placePosCones2and3and4)
                                .lineToLinearHeading(afterPlacingAllCones)
                                .lineToLinearHeading(CONES)
                                .addDisplacementMarker(() -> {
                                    // Run your action in here!
                                    // Drop servo, start motor, whatever
                                })
                                .lineToLinearHeading(lineUpCones2and3and4)
                                .lineToLinearHeading(placePosCones2and3and4)
                                .addDisplacementMarker(() -> {
                                    // Run your action in here!
                                    // Drop servo, start motor, whatever
                                })
                                .lineToLinearHeading(park2)
                                //.lineToLinearHeading(CONES)
                                .lineToLinearHeading(park3)





                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

}