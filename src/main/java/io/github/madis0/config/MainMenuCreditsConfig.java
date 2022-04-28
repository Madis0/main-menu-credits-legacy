package io.github.madis0.config;

public class MainMenuCreditsConfig {
    @Config(config="settings", category = "windowTitle", key = "text", comment = "Text that is shown on the menu")
    public static String text = "§cMissing modpack config";

    @Config(config="settings", category = "windowTitle", key = "url", comment = "Link that is opened on click")
    public static String url = "https://github.com/Fabulously-Optimized/main-menu-credits/wiki/Missing-modpack-config";
}
