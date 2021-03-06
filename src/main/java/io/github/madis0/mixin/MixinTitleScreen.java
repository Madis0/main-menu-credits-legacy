package io.github.madis0.mixin;

import net.minecraft.client.gui.screen.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import io.github.madis0.config.MainMenuCreditsConfig;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends Screen {

    @Shadow @Final private boolean doBackgroundFade;
    @Shadow private long backgroundFadeStart;
    private Text menuText;
    private int menuTextWidth;
    private int textPosition;
    private int yOffset;

    protected MixinTitleScreen(Text title) {
        super(title);
    }
    @Inject(at = @At("TAIL"), method = "init")
    private void puzzle$init(CallbackInfo ci) {
        yOffset = 20;
        menuText = Text.of(MainMenuCreditsConfig.text);
        this.menuTextWidth = this.textRenderer.getWidth(menuText);
        assert client != null;
        int windowWidth = client.getWindow().getScaledWidth();
        textPosition = windowWidth - this.menuTextWidth - 2;
    }

    @Inject(at = @At("TAIL"), method = "render")
    private void puzzle$render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        float f = this.doBackgroundFade ? (float) (Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        textRenderer.drawWithShadow(matrices, menuText, textPosition,this.height - yOffset, 16777215 | l);
        if (mouseX > textPosition && mouseX < textPosition + this.menuTextWidth && mouseY > this.height - yOffset && mouseY < this.height - yOffset + 10) {
            fill(matrices, textPosition, this.height - yOffset + 9, textPosition + this.menuTextWidth, this.height - yOffset + 10, 16777215 | l);
        }
    }

    private void confirmLink(boolean open) {
        if (open) {
            Util.getOperatingSystem().open(MainMenuCreditsConfig.url);
        }
        Objects.requireNonNull(this.client).setScreen(this);
    }

    @Inject(at = @At("HEAD"), method = "mouseClicked",cancellable = true)
    private void puzzle$mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (mouseX > textPosition && mouseX < (double)(textPosition + this.menuTextWidth) && mouseY > (double)(this.height - yOffset) && mouseY < (double)this.height - yOffset + 10) {
            if (Objects.requireNonNull(this.client).options.chatLinksPrompt) {
                this.client.setScreen(new ConfirmChatLinkScreen(this::confirmLink, MainMenuCreditsConfig.url, true));
            } else {
                Util.getOperatingSystem().open(MainMenuCreditsConfig.url);
            }
            cir.setReturnValue(false);
        }
    }
}