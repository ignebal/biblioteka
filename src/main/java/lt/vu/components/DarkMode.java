package lt.vu.components;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@RequestScoped
public class DarkMode implements Serializable {
    /*
    private boolean enabled = false;

    public void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getThemeClass() {
        return enabled ? "dark-mode" : "light-mode";
    } */

    public enum Theme{
        LIGHT("light-mode", "Light Mode"),
        DARK("dark-mode", "Dark Mode"),
        CONTRAST("contrast-mode", "Contrast Mode");

        public final String className;
        public final String label;

        Theme(String className, String label) {
            this.className = className;
            this.label = label;
        }
    }

    private Theme currentTheme = Theme.LIGHT;

    public void toggle() {
        currentTheme = switch(currentTheme) {
            case LIGHT -> Theme.DARK;
            case DARK -> Theme.CONTRAST;
            case CONTRAST -> Theme.LIGHT;
        };
    }

    public String getThemeClass() {
        return currentTheme.className;
    }

    public String getCurrentThemeLabel() {
        return currentTheme.label;
    }

    public String getNextThemeLabel() {
        Theme nextTheme = switch(currentTheme) {
            case LIGHT -> Theme.DARK;
            case DARK -> Theme.CONTRAST;
            case CONTRAST -> Theme.LIGHT;
        };
        return nextTheme.label;
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(Theme currentTheme) {
        this.currentTheme = currentTheme;
    }

}