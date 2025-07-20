package gunging.ootilities.goof.spigot.ootilities.friendly;

import gunging.ootilities.goob.ootilities.friendly.palette.FriendlyColorContext;
import gunging.ootilities.goob.ootilities.friendly.palette.HexSupportContext;
import gunging.ootilities.goob.ootilities.friendly.palette.MessageLengthContext;
import gunging.ootilities.goob.ootilities.friendly.palette.ModuleContext;
import org.jetbrains.annotations.Nullable;

/**
 * A context for parsing the colors of Friendly Feedback
 * Palettes relevant to Minecraft Spigot platform.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotConsoleContext implements FriendlyColorContext, MessageLengthContext, ModuleContext, HexSupportContext {

    /**
     * If this is for console logging
     *
     * @since 1.0.0
     */
    boolean console;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public boolean isConsole() { return console; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotConsoleContext(boolean isConsole) { console = isConsole; }

    /**
     * The length of the message so far
     *
     * @since 1.0.0
     */
    int length;

    /**
     * The submodule currently running
     *
     * @since 1.0.0
     */
    @Nullable String module;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override  public int getCurrentLength() { return length; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setCurrentLength(int l) { length = l; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable String getModule() { return module; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setModule(@Nullable String mod) { module = mod; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void resetContext() { length = 0; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public boolean isHexSupported() { return !isConsole(); }
}
