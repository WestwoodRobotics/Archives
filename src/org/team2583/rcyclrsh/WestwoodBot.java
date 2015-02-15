/*
 * Copyright (c) 2015 Westwood Robotics <code.westwoodrobotics@gmail.com>.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 */

package org.team2583.rcyclrsh;

import org.team2583.rcyclrsh.boxlift.BoxLift;
import org.team2583.rcyclrsh.drawer.Drawer;
import org.team2583.rcyclrsh.drawer.Ejector;
import org.team2583.rcyclrsh.drawer.Tailgate;
import org.team2583.rcyclrsh.drivetrain.Drivetrain;
import org.team2583.rcyclrsh.elevator.Elevator;
import org.team2583.rcyclrsh.elevator.Hands;

import io.github.robolib.RoboLibBot;
import io.github.robolib.util.mapper.RobotMap;

/**
 * 
 * @author Austin Reuland <amreuland@gmail.com>
 */
public class WestwoodBot extends RoboLibBot {
//    private LCDManager m_lcdManager;
    
    public WestwoodBot(){
        super("Stacker", "1.0.0");
        RobotMap.setMapFile("/home/lvuser/rmap.json");
        enableDebug(true);
//        m_lcdManager = new LCDManager();
//        m_lcdManager.startThread();
        
        new OI();
        

//        TableSender.setEnabled(false);
        
        Drivetrain.initialize();
        Drawer.initialize();
        Ejector.initialize();
        Tailgate.initialize();
        Elevator.initialize();
        BoxLift.initialize();
        Hands.initialize();
    }
}
