package me.tweedjt.reputation.data;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RepData {

     /*    public void saveEntry() {


       File dataFile = FileUtils.getFileByUUID(this.key);
        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(dataFile);
        fileConfig.set("key", this.key.toString());
        fileConfig.set("type", this.pocketItem.getNamespacedKey().getKey());
        for (Map.Entry<Integer, ItemStack> itemEntry : this.items.entrySet()) {
            fileConfig.set("items." + itemEntry.getKey(), InventoryFunctions.itemToStringBlob(itemEntry.getValue()));
        }
        if (createdBy != null) {
            fileConfig.set("created_by", createdBy.toString());
        }
        fileConfig.set("last_accessed", new Timestamp(System.currentTimeMillis()).getTime());
        try {
            fileConfig.save(dataFile);
            if (dataFile.length() == 0) {
                //Logging.log("Blank file found", customYml.getName());
            }
        } catch (IOException e) {
            Pocket.getInstance().error().save(
                    "PocketEntry.save.001",
                    "PocketEntry",
                    "save()",
                    "Unexpected error trying to save",
                    "Pocket Key: " + this.getKey(),
                    Error.Severity.CRITICAL,
                    e.getStackTrace()
            );
        }
    }

    */


}
