package com.example.bigfatpenis;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.entity.BotEntity;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder.*;


//import com.noahbres.meepmeep.core.entity.*

//import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity

public class leftSideAuton {
    public static void main(String args[]){
        MeepMeep meepMeep = new MeepMeep(700);

        double a = 5.5;
        int b = 0;
        double c = 0;

        Pose2d startingPosition = new Pose2d(36, 59+2.3, Math.toRadians(-90));

        //Might need to rotate first before CONES
        Pose2d CONES = new Pose2d(57, (6 + a+c), Math.toRadians(180));
        Pose2d CONES2 = new Pose2d(63.3+3, (6 + a+c), Math.toRadians(180));

        //park1 is the same as CONES
        Pose2d park2 = new Pose2d(38, (6 + a+c), Math.toRadians(180));
        Pose2d park3 = new Pose2d(14, (6 + a+c), Math.toRadians(180));

        Pose2d colorPosition = new Pose2d(36, (41 - b), Math.toRadians(-90));

        Pose2d lineUpCones2and3and4 = new Pose2d(36, (6 + a+c), Math.toRadians(180));

        Pose2d lineUpCones2and3and4AfterTurn = new Pose2d(36, (6 + a+c), Math.toRadians(90));

        Pose2d afterPlacingAllCones = new Pose2d(36, (6 + a+c), Math.toRadians(180));

        Pose2d pushSignalConeAcross = new Pose2d(36, (-1 + a+c), Math.toRadians(-90));

        Pose2d lineUpCone1 = new Pose2d(36, (7 + a+c), Math.toRadians(-135));

        Pose2d placePosCone1 = new Pose2d(31, (2+a+c), Math.toRadians(-135));

        Pose2d placePosCones2and3and4 = new Pose2d(30, (16.5 - b), Math.toRadians(135));



        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 8)
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
                                .lineToLinearHeading(placePosCone1)
                                .lineToLinearHeading(afterPlacingAllCones)
                                .lineToLinearHeading(CONES)
                                .addDisplacementMarker(() -> {
                                    // Run your action in here!
                                    // Drop servo, start motor, whatever
                                })
                                .lineToLinearHeading(lineUpCones2and3and4)
                                .lineToLinearHeading(placePosCone1)
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