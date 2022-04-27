package io.github.madis0;

import io.github.madis0.config.Configuration;
import io.github.madis0.config.MainMenuCreditsConfig;
import net.fabricmc.api.ModInitializer;

public class MainMenuCredits implements ModInitializer {
	@Override
	public void onInitialize() {
		new Configuration(MainMenuCreditsConfig.class, "main-menu-credits");
	}
}
