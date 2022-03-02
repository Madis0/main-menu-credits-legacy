package in.roflmuff.ftc.config;

public class FabricTitleChangerConfig {
    @Config(config="settings", category = "windowTitle", key = "text", comment = "Text that is shown on the menu")
    public static String text = "Bad modpack";

    @Config(config="settings", category = "windowTitle", key = "url", comment = "Link that is opened on click")
    public static String url = "https://example.com";
}
