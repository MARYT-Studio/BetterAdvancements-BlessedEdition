package betteradvancements.reference;

import betteradvancements.betteradvancements.Tags;

public final class Reference
{
    // User friendly version of our mods name.
    public static final String NAME = Tags.MOD_NAME;

    // Internal mod name used for reference purposes and resource gathering.
    public static final String ID = Tags.MOD_ID;

    // Main version information that will be displayed in mod listing and for other purposes.
    public static final String VERSION_FULL = Tags.VERSION;

    // proxy info
    public static final String SERVER_PROXY = ID + ".proxy.CommonProxy";
    public static final String CLIENT_PROXY = ID + ".proxy.ClientProxy";
    public static final String MOD_GUI_FACTORY = ID + ".config.ModGuiFactory";
}