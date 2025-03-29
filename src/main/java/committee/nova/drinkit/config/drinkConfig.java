package committee.nova.drinkit.config;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

import static committee.nova.drinkit.DrinkIt.*;

public class drinkConfig {

    public static final ModConfigSpec COMMON_CONFIG;
    private static final ModConfigSpec.ConfigValue<List<? extends String>> cfgDrinkable;
    private static final ModConfigSpec.ConfigValue<List<? extends String>> cfgThick;
    private static final ModConfigSpec.ConfigValue<List<? extends String>> cfgSilent;

    static {
        final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("DrinkIt Configuration");
        cfgDrinkable = builder
                .comment("The items defined as drinks")
                .defineList("drinkable", List.of(
                        "minecraft:beetroot_soup", "example:another_drinkable"), () -> "", o -> (o instanceof String));

        cfgThick = builder
                .comment("The items defined as thick drinks")
                .defineList("thick", List.of(
                        "minecraft:suspicious_stew", "example:another_thick"), () -> "", o -> (o instanceof String));

        cfgSilent = builder
                .comment("The drinks without that eating sound")
                .comment("The items should be defined as drinks above")
                .defineList("noEatingSound", List.of(
                        "minecraft:beetroot_soup", "example:another_nosound"), () -> "", o -> (o instanceof String));

        COMMON_CONFIG = builder.build();
    }
    public static boolean isDrinkable(ItemStack i) {
        return i.is(TAG_DRINKABLE) || isItemInList(i.getItem(), cfgDrinkable.get()) || isThick(i);
    }

    public static boolean isThick(ItemStack i) {
        return i.is(
                TAG_THICK) || isItemInList(i.getItem(), cfgThick.get());
    }

    public static boolean isSilent(ItemStack i) {
        return i.is(
                TAG_SILENT) || isItemInList(i.getItem(), cfgSilent.get());
    }

    private static boolean isItemInList(Item i, List<? extends String> l) {
        final ResourceLocation r = BuiltInRegistries.ITEM.getKey(i);
        if (r == null) return false;
        return l.contains(r.toString());
    }



}
