package cpw.mods.fml.common.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StringTranslate;

public class LanguageRegistry
{
    private static final LanguageRegistry INSTANCE = new LanguageRegistry();

    private Map<String,Properties> modLanguageData=new HashMap<String,Properties>();

    public static LanguageRegistry instance()
    {
        return INSTANCE;
    }

    public void addStringLocalization(String key, String value)
    {
        addStringLocalization(key, "en_US", value);
    }
    public void addStringLocalization(String key, String lang, String value)
    {
        Properties langPack=modLanguageData.get(lang);
        if (langPack==null) {
            langPack=new Properties();
            modLanguageData.put(lang, langPack);
        }
        langPack.put(key,value);
    }

    public static void reloadLanguageTable()
    {
        // reload language table by forcing lang to null and reloading the properties file
        String lang = StringTranslate.getInstance().getCurrentLanguage();
        StringTranslate.getInstance().currentLanguage = null;
        StringTranslate.getInstance().setLanguage(lang);
    }


    public void addNameForObject(Object objectToName, String lang, String name)
    {
        String objectName;
        if (objectToName instanceof Item) {
            objectName=((Item)objectToName).getItemName();
        } else if (objectToName instanceof Block) {
            objectName=((Block)objectToName).getBlockName();
        } else if (objectToName instanceof ItemStack) {
            objectName=((ItemStack)objectToName).getItem().getItemNameIS((ItemStack)objectToName);
        } else {
            throw new IllegalArgumentException(String.format("Illegal object for naming %s",objectToName));
        }
        objectName+=".name";
        addStringLocalization(objectName, lang, name);
    }
    
    public static void addName(Object objectToName, String name)
    {
        instance().addNameForObject(objectToName, "en_US", name);
    }

    public void loadLanguageTable(Properties languagePack, String lang)
    {
        Properties usPack=modLanguageData.get("en_US");
        if (usPack!=null) {
            languagePack.putAll(usPack);
        }
        Properties langPack=modLanguageData.get(lang);
        if (langPack==null) {
            return;
        }
        languagePack.putAll(langPack);
    }
}
