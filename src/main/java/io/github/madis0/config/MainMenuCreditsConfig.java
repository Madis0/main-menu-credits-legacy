package io.github.madis0.config;

public class MainMenuCreditsConfig {
    @Config(config="settings", category = "windowTitle", key = "text", comment = "Text that is shown on the menu")
    public static String text = "§cMissing modpack config";

    @Config(config="settings", category = "windowTitle", key = "url", comment = "Link that is opened on click")
    public static String url = "https://example.com/help-wiki-here";
}
