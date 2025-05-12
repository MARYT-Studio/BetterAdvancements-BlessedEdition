package betteradvancements.advancements;

import betteradvancements.BetterAdvancements;
import betteradvancements.reference.Reference;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CriterionDesc {

    private static JsonObject dictionary;

    public static void loadJson() {
        try {
            ResourceLocation location = new ResourceLocation(Reference.ID, Reference.ID + ".json");

            IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(location);

            InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            JsonElement jsonElement = new JsonParser().parse(reader);

            if (jsonElement.isJsonObject()) {
                dictionary = jsonElement.getAsJsonObject();
            } else {
                BetterAdvancements.LOGGER.error("invalid criterion to description JSON.");
            }

        } catch (Exception e) {
            BetterAdvancements.LOGGER.error("invalid criterion to description JSON. {}", e.getMessage());
        }
    }

    @Nullable
    public static TextComponentTranslation criterion2Desc(Advancement advancement, String criterion) {
        if (dictionary.has(advancement.getId().toString())) {
            JsonObject criteria = dictionary.get(advancement.getId().toString()).getAsJsonObject();
            if (criteria.has(criterion)) return new TextComponentTranslation(criteria.get(criterion).getAsString());
        }
        return null;
    }
}
