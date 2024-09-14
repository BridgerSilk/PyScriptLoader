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
<br>
> Called when a player joins the server.
<br>
<br>
> **Event-Values:**
<br>
> `player_name` Example: ``def on_player_join(player_name):``

### Effects
> ### Broadcast ``broadcastMessage``
> ``broadcastMessage()``
<br>
> Sends a message to all players on the server.
<br>
<br>
> **Objects:**
<br>
> ``any string`` Example: ``broadcastMessage("Hello World!")``
