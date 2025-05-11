package betteradvancements.util;

import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Criterion;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CriterionElement {

    public static int numUnobtained = 0;

    public static List<String> getCriterionElements(AdvancementProgress progress, Map<String, Criterion> criteria, CriteriaDetail detailLevel) {
        numUnobtained = 0;
        List<String> cellContents = new ArrayList<>();
        for (String criterion : criteria.keySet()) {
            if (progress.getCriterionProgress(criterion).isObtained()) {
                if (detailLevel.showObtained()) {
                    TextComponentString text = new TextComponentString(" + ");
                    text.getStyle().setColor(TextFormatting.GREEN);
                    TextComponentString text2 = new TextComponentString(criterion);
                    text2.getStyle().setColor(TextFormatting.WHITE);
                    text.appendSibling(text2);
                    cellContents.add(text.getFormattedText());
                }
            }
            else {
                if (detailLevel.showUnobtained()) {
                    TextComponentString text = new TextComponentString(" x ");
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    TextComponentString text2 = new TextComponentString(criterion);
                    text2.getStyle().setColor(TextFormatting.WHITE);
                    text.appendSibling(text2);
                    cellContents.add(text.getFormattedText());
                }
                numUnobtained++;
            }
        }
        return cellContents;
    }
}
