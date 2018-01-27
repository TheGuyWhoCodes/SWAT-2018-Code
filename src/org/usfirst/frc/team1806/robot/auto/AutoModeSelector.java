package org.usfirst.frc.team1806.robot.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



import org.json.simple.JSONArray;
import org.usfirst.frc.team1806.robot.auto.modes.DummyMode;
import org.usfirst.frc.team1806.robot.auto.modes.LeftSideElimScale;
import org.usfirst.frc.team1806.robot.auto.modes.ScaleAuto;
import org.usfirst.frc.team1806.robot.auto.modes.SwitchAuto;

import java.util.function.Supplier;

/**
 * Class that allows a user to select which autonomous mode to execute from the web dashboard.
 */
public class AutoModeSelector {

    public static final String AUTO_OPTIONS_DASHBOARD_KEY = "auto_options";
    public static final String SELECTED_AUTO_MODE_DASHBOARD_KEY = "selected_auto_mode";

    private static class AutoModeCreator {
        private final String mDashboardName;
        private final Supplier<AutoModeBase> mCreator;

        private AutoModeCreator(String dashboardName, Supplier<AutoModeBase> creator) {
            mDashboardName = dashboardName;
            mCreator = creator;
        }
    }

    private static final AutoModeCreator mDefaultMode = new AutoModeCreator(
            "DummyMode",
            () -> new DummyMode());
    private static final AutoModeCreator[] mAllModes = {
            new AutoModeCreator("Scale Auto", () -> new ScaleAuto()),
            new AutoModeCreator("Switch Auto", () -> new SwitchAuto()),
            new AutoModeCreator("Left Side Elim Auto", () -> new LeftSideElimScale()),
    };

    public static void initAutoModeSelector() {
        JSONArray modesArray = new JSONArray();
        for (AutoModeCreator mode : mAllModes) {
            modesArray.add(mode.mDashboardName);
        }
        SmartDashboard.putString(AUTO_OPTIONS_DASHBOARD_KEY, modesArray.toString());
        SmartDashboard.putString(SELECTED_AUTO_MODE_DASHBOARD_KEY, mDefaultMode.mDashboardName);
    }

    public static AutoModeBase getSelectedAutoMode() {
        String selectedModeName = SmartDashboard.getString(
                SELECTED_AUTO_MODE_DASHBOARD_KEY,
                "NO SELECTED MODE!!!!");
        for (AutoModeCreator mode : mAllModes) {
            if (mode.mDashboardName.equals(selectedModeName)) {
                return mode.mCreator.get();
            }
        }
        DriverStation.reportError("Failed to select auto mode: " + selectedModeName, false);
        return mDefaultMode.mCreator.get();
    }
}