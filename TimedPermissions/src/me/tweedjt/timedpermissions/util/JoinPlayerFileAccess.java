package me.tweedjt.timedpermissions.util;

	import java.io.File;
	import java.io.IOException;

	import org.bukkit.configuration.file.FileConfiguration;
	import org.bukkit.configuration.file.YamlConfiguration;


	import me.tweedjt.timedpermissions.TimedPermissions;

	// File access class
	public class JoinPlayerFileAccess {

	    // function to return the file from the filename
	    private static File getFile() { 
	        return new File(TimedPermissions.getInstance().getDataFolder() +  File.separator + "users.yml");
	    }
	    
	    // This actually loads the file from users.yml
	    public static void loadFromFile() {
	        File customYml = getFile(); // gets the file
	        
	        if (!customYml.exists()) {  // checks if the file exists, if not, create it
	            try {
	                customYml.createNewFile();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }   
	        }
	        if (customYml.exists()) // Check it exists again, if it doesn't now, we're fucked
	        {
	            FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml); // Get the FileConfiguration

	            try
	            {
	                TimedPermissions.getInstance().getLiveData().clearPlayers();  // Clears a list - for you it'd be a list of perms
	                if (customConfig.contains("users")) { // Checks if the users: section exists
	                    if (customConfig.isConfigurationSection(("users"))) {
	                        for (String playerUUID : customConfig.getConfigurationSection("users").getKeys(false)) { // loops through keys
	                            boolean error = false;
	                            String playerName = "";
	                            long lastSeen = 0; // We save date/time as a long value, just trust me on this
	                            if (customConfig.contains("users." + playerUUID + ".name")) { // checks the name section (you'd use perm or similar)
	                                if (customConfig.isString("users." + playerUUID + ".name")) {
	                                    playerName = customConfig.getString("users." + playerUUID + ".name");
	                                } else {
	                                    error = true;
	                                    Log.error(
	                                            "JoinedPlayerFileAccess.loadBlockFromFile.001",
	                                            "JoinedPlayerFileAccess",
	                                            "loadFromFile()",
	                                            "Unexpected error getting users. " + "users." + playerUUID + ".name" + " is not a String"
	                                    );
	                                }
	                            }
	                            if (customConfig.contains("users." + playerUUID + ".last_seen")) { // gets the last seen date/time, in your case, when created, then duplicate for when expires
	                                if (customConfig.isLong("users." + playerUUID + ".last_seen")) {
	                                    lastSeen = customConfig.getLong("users." + playerUUID + ".last_seen");
	                                } else {
	                                    error = true;
	                                    Log.error(
	                                            "JoinedPlayerFileAccess.loadBlockFromFile.002",
	                                            "JoinedPlayerFileAccess",
	                                            "loadFromFile()",
	                                            "Unexpected error getting users. " + "users." + playerUUID + ".last_seen is not a valid long timestamp"
	                                    );
	                                }
	                            }
	                            if (!error) {
	                                JoinedPlayer joinedPlayer = new JoinedPlayer(playerUUID, playerName, lastSeen);
	                                TimedPermissions.getInstance().getLiveData().addPlayer(joinedPlayer);
	                            }
	                        }
	                    }
	                }
	            } catch (Exception ex) {
	                Log.error(
	                        "JoinedPlayerFileAccess.loadBlockFromFile.003",
	                        "JoinedPlayerFileAccess",
	                        "loadFromFile()",
	                        "Unexpected error getting users. " + ex.getMessage()
	                );
	                return;
	            }

	        } else {
	            Log.error(
	                    "JoinedPlayerFileAccess.loadFromFile.004",
	                    "JoinedPlayerFileAccess",
	                    "loadFromFile(Integer)",
	                    "No such file: " + TimedPermissions.getInstance().getDataFolder() + File.separator + "users.yml"
	            );
	            return;
	        }
	        
	    }
	    
	    // This function adds a line to the config
	    public static void add(String playerUUID, String playerName, long lastSeen) {
	        Log.dev("BlockFileAccess", "add()", "Adding user: " + playerUUID + " + with name: " + playerName);
	        File customYml = getFile(); // gets the file
	        if (!customYml.exists()) {
	            try {
	                customYml.createNewFile();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }       
	        }
	        FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml); // gets the file config
	        customConfig.set("users." + playerUUID, null); // adds the users.playeruniqueid
	        customConfig.set("users." + playerUUID + ".name", playerName); // ad
	        customConfig.set("users." + playerUUID + ".last_seen", lastSeen);
	        try {
	            customConfig.save(customYml);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	}
