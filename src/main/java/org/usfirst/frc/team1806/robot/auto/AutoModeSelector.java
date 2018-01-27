package org.usfirst.frc.team1806.robot.auto;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.reflections.Reflections;
import org.usfirst.frc.team1806.robot.auto.modes.LeftSideScaleAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class that allows a user to select which autonomous mode to execute from the web dashboard.
 */
public class AutoModeSelector {

    public static final String AUTO_OPTIONS_DASHBOARD_KEY = "auto_options";
    public static final String SELECTED_AUTO_MODE_DASHBOARD_KEY = "selected_auto_mode";
    public static final String AUTO_MODES_PACKAGE = "org.usfirst.frc.team1806.robot.auto.modes";
    
    /**
     * Uses reflection to get every auto mode in the defined auto modes package. The idea being to remove a step from the process of adding an autonomous mode.
     */
    public static void initAutoModeSelector() {
    	ObjectMapper modesMapper = new ObjectMapper();
    	ArrayNode modesArray = modesMapper.createArrayNode();
        
    	Reflections reflections = new Reflections(AUTO_MODES_PACKAGE);
        Set<Class<? extends AutoModeBase>> modes = reflections.getSubTypesOf(AutoModeBase.class);
        for(Class<?> mode:modes){
        	modesArray.add(mode.getClass().getName());
        }
        SmartDashboard.putString(AUTO_OPTIONS_DASHBOARD_KEY, modesArray.toString());
        //SmartDashboard.putString(SELECTED_AUTO_MODE_DASHBOARD_KEY, mDefaultMode.mDashboardName);
    }
    
    
    /**
     * Checks the returned automode against every mode in the defined auto modes package. If there is a problem instantiating it, or the mode selected doesn't exist, will return a default auto.
     */
    public static AutoModeBase getSelectedAutoMode() {
        String selectedModeName = SmartDashboard.getString(
                SELECTED_AUTO_MODE_DASHBOARD_KEY,
                "NO SELECTED MODE!!!!");
        
        Reflections reflections = new Reflections(AUTO_MODES_PACKAGE);
        Set<Class<? extends AutoModeBase>> modes = reflections.getSubTypesOf(AutoModeBase.class);
        
        for (Class<?> mode:modes) {
            if (selectedModeName.equals(mode.getClass().getName())) {
            	//TODO: Nicer messages when things go wrong.
                try {
					return (AutoModeBase) mode.getConstructor().newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
					 return fallBackToDefaultAuto(selectedModeName);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return fallBackToDefaultAuto(selectedModeName);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return fallBackToDefaultAuto(selectedModeName);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					return fallBackToDefaultAuto(selectedModeName);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					return fallBackToDefaultAuto(selectedModeName);
				} catch (SecurityException e) {
					e.printStackTrace();
					return fallBackToDefaultAuto(selectedModeName);
				}
            }
        }   
        return fallBackToDefaultAuto(selectedModeName);
    }
    
    public static AutoModeBase fallBackToDefaultAuto(String wantedAutoMode){
    	DriverStation.reportError("Failed to select auto mode: " + wantedAutoMode, false);
    	return new LeftSideScaleAuto(); //TODO make an actual reasonable default auto
    }
}