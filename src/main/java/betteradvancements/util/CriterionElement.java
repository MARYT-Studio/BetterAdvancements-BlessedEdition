package betteradvancements.util;

import betteradvancements.BetterAdvancements;
import betteradvancements.advancements.CriterionDesc;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.NBTPredicate;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static betteradvancements.BetterAdvancements.LOGGER;

public class CriterionElement {

    public static int numUnobtained = 0;

    public static List<String> getCriterionElements(Advancement advancement, AdvancementProgress progress, Map<String, Criterion> criteria, CriteriaDetail detailLevel) {
        numUnobtained = 0;
        List<String> cellContents = new ArrayList<>();
        for (String criterion : criteria.keySet()) {
            if (progress.getCriterionProgress(criterion).isObtained()) {
                if (detailLevel.showObtained()) {
                    TextComponentString text = new TextComponentString(" + ");
                    text.getStyle().setColor(TextFormatting.GREEN);
                    ITextComponent description = getDescription(advancement, criterion);
                    description.getStyle().setColor(TextFormatting.WHITE);
                    text.appendSibling(description);
                    cellContents.add(text.getFormattedText());
                }
            }
            else {
                if (detailLevel.showUnobtained()) {
                    TextComponentString text = new TextComponentString(" x ");
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    ITextComponent description = getDescription(advancement, criterion);
                    description.getStyle().setColor(TextFormatting.WHITE);
                    text.appendSibling(description);
                    cellContents.add(text.getFormattedText());
                }
                numUnobtained++;
            }
        }
        return cellContents;
    }

    private static ITextComponent getDescription(Advancement advancement, String criterion) {
        TextComponentTranslation desc = CriterionDesc.criterion2Desc(advancement, criterion);
        return desc != null ? desc : new TextComponentString(criterion);
    }
}
