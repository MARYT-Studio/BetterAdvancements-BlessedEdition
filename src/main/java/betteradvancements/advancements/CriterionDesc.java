package betteradvancements.advancements;

import betteradvancements.BetterAdvancements;
import betteradvancements.reference.Reference;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class CriterionDesc {

    private static List<String> loadedModIds;
    private static int loadedFailureCount = 0;

    @SuppressWarnings("unused")
    private static final HashMap<String, JsonObject> dictionaries = new HashMap<>();

    public static void getLoadedModIds() {
        loadedModIds = Loader.instance().getActiveModList().stream()
            .map(ModContainer::getModId)
            .collect(Collectors.toList());
    }


    public static void loadJson() {
        IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
        for (String modId : loadedModIds) {
            try {
                ResourceLocation location = new ResourceLocation(Reference.ID, "dictionaries/" + modId + ".json");
                IResource resource = manager.getResource(location);
                InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

                JsonElement element = new JsonParser().parse(reader);
                if (element.isJsonObject()) {
                    dictionaries.put(modId, element.getAsJsonObject());
                }

            } catch (Exception ignored) {
                loadedFailureCount ++;
            }
        }
        if (loadedFailureCount == loadedModIds.size()) BetterAdvancements.LOGGER.error("No valid dictionary is loaded successfully. Please check if there is something wrong.");
        else BetterAdvancements.LOGGER.info("{} dictionaries are loaded successfully.", loadedModIds.size()-loadedFailureCount);
    }

    @Nullable
    public static TextComponentTranslation criterion2Desc(Advancement advancement, String criterion) {
        String modId = advancement.getId().getNamespace();
        if (dictionaries.containsKey(modId)) {
            JsonObject dictionary = dictionaries.get(modId);
            if (dictionary.has(advancement.getId().toString())) {
                JsonObject criteria = dictionary.get(advancement.getId().toString()).getAsJsonObject();
                if (criteria.has(criterion)) {
                    String descValue = criteria.get(criterion).getAsString();
                    String[] desc = descValue.split(";");
                    return desc.length == 2 ? criterion2Desc(desc[0], desc[1]) :  new TextComponentTranslation(criteria.get(criterion).getAsString());

                }
            }
        } else BetterAdvancements.LOGGER.error("Namespace of {}: {} is not found in dictionaries. Current dictionaries keySet: {}", advancement.getId() ,advancement.getId().getNamespace(), dictionaries.keySet());
        return null;
    }

    public static TextComponentTranslation criterion2Desc(String template, String desc) {
        return new TextComponentTranslation(template, I18n.format(desc));
    }
}
