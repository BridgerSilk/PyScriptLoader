# Documentation

## Installation
- Download the latest release from the releases page
- Place the .jar file into your server's plugins folder
- Restart your server
- Place a python script (.py) into `/plugins/PyScriptLoader/`
- Use the command ``/pyscriptloader reload`` in the console or in game to reload the script
- To learn how to create functional scripts continue reading this documentation

## Permissions
- pyscriptloader.reload (permission to reload the scripts)

## Syntax
### Events
> ### Player Join Event ``on_player_join``
> ``def on_player_join():``
> <br>
> Called when a player joins the server.
> <br>
> <br>
> **Event-Values:**
> <br>
> `player_name` Example: ``def on_player_join(player_name):`` <br>
> Name of the player. (string)
> <br>
> `event_world` Example: ``def on_player_join(event_world):`` <br>
> Name of the World the player joined in. (string)
> <br>
> <p style="color:orange; border-left: 2px solid orange; padding-left: 5px;"><b>NOTE:</b> In the current version, you NEED to define all event-values in the right order in all events, otherwise it will throw a stacktrace in the console, but you don't need to USE all of them. A more dynamic system for event-values is being developed, be patient.</p>
> <p style="color:orange; border-left: 2px solid orange; padding-left: 5px;">In this case the event needs to be called like this: <br> def on_player_join(player_name, event_world):</p>

> ### Player Block Break Event ``on_block_break``
> ``def on_block_break():``
> <br>
> Called when a player joins the server.
> <br>
> <br>
> **Event-Values:**
> <br>
> `player_name` Example: ``def on_player_join(player_name):`` <br>
> Name of the player. (string)
> <br>
> `event_world` Example: ``def on_player_join(event_world):`` <br>
> Name of the World the block was broken in. (string)
> <br>
> `block_type` Example: ``def on_player_join(block_type):`` <br>
> Block type of the broken block. (string)
> <br>
> `block_loc` Example: ``def on_player_join(block_loc):`` <br>
> Location/Coordinates of the broken block. (string)
> <br>

### Effects
> ### Broadcast ``broadcastMessage``
> ``broadcastMessage()``
> <br>
> Sends a message to all players on the server.
> <br>
> <br>
> **Objects:**
> <br>
> ``any string`` Example: ``broadcastMessage("Hello World!")``

> ### Log to Console ``print``
> ``print()``
> <br>
> Sends a message to the server console.
> <br>
> <br>
> **Objects:**
> <br>
> ``any string`` Example: ``print("Hello World!")``

### Misc
> ### Variables
> Variables are used the exact same way as in a normal python script to save data. But you need to use .format() when using variables in strings.
> <br>
> <br>
> **Example:**
> <br>
> ``test = "Hello World!"``
> <br>
> ``broadcastMessage("{}").format(test)``